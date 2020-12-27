<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax를 이용한 파일 업로드</title>
<style type="text/css">
.uploadResult {
	width:100%;
	background-color:gray;
}

.uploadResult ul {
	display:flex;
	flex-flow:row;
	justify-content:center;
	align-items:center;
}

.uploadResult ul li {
	list-style:none;
	padding:10px;
}

.uploadResult ul li img{
	width:100%;
}

.uploadResult ul li span {
	color:white;
}

.bigPictureWrapper {
	position:absolute;
	display:none;
	justify-content:center;
	align-items:center;
	top:0%;
	width:100%;
	height:100%;
	background-color:gray;
	z-index:100;
	background:rgba(255,255,255,0,5);
}

.bigPicture {
	position:relative;
	display:flex;
	justify-content:center;
	align-items:center;
}

.bigPicture img {
	width:600px;
}
</style>
</head>
<body>
<h1>Upload With Ajax</h1>

<div class="uploadDiv">
	<input type="file" name="uploadFile" multiple>
</div>
	<button id="uploadBtn">UPLOAD</button>

<div class="uploadResult">
	<ul></ul>
</div>

<div class="bigPictureWrapper">
	<div class="bigPicture">
	
	</div>
</div>
	
<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>

<script type="text/javascript">
function showImage(fileCallPath) {
	console.log(fileCallPath);
	
	$('.bigPictureWrapper').css('display','flex').show();
	
	$('.bigPicture')
	.html('<img src="/display?fileName='+encodeURI(fileCallPath)+'">')
	//.animate({width:'100%', height:'100%'}, 1000);
	
	//다시 클릭 시 이미지 축소
	$('.bigPictureWrapper').on('click',(e) => {
		$('.bigPictureWrapper').hide()
	})
	

}
</script>
<script type="text/javascript">
$(document).ready(() => {
	//확장자 exe, sh, zip, alz 제한
	const regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	const maxSize = 10485760; //10MB
	const uploadDiv = $('.uploadDiv').clone();
	const uploadResult = $('.uploadResult ul');

	//삭제
	$('.uploadResult').on('click','span', function(e){
		const targetFile = $(this).data('file');
		const type = $(this).data('type');
		console.log(targetFile)
		console.log(type)
		
		$.ajax({
			url		: '/deleteFile',
			data	: {fileName:targetFile, type:type},
			dataType: 'text',
			type	: 'POST',
			success : (result) => {
				console.log(result);
			}
		}); //Delete Ajax
	})
	
	/*
		화면에 업로드한 파일 정보를 출력하는 함수	
	*/
	function showUploadedFile(uploadResultArr) {
		let str = '';
		
		$(uploadResultArr).each((i, obj) => {
	
			// 이미지가 아니면 파일 아이콘을 출력한다.
			if(!obj.image) {
				const fileCallPath = encodeURIComponent("/"+obj.uploadPath+"/"+obj.uuid+"_"+obj.fileName);
				
				const fileLink = fileCallPath.replace(new RegExp(/\\/g), "/");
				
 				str +='<li><div><a href="/download?fileName='+fileCallPath+'">'
 				+'<img src="/resources/img/fileIcon.png">'+obj.fileName+'</a>'; 
 				
 				//삭제를 위한 'x' 추가
 				str += '<span data-file=\"'+fileCallPath+'\" data-type="file"> X </span></div></li>';
		
					
			} else {
				const fileCallPath = encodeURIComponent("/"+obj.uploadPath+'/s_'+obj.uuid+'_'+obj.fileName);
				let originPath = obj.uploadPath + "\\" + obj.uuid + "_"+obj.fileName;
				originPath = "/"+originPath.replace(new RegExp(/\\/g),"/")
				//이미지 파일이면 섬네일을 출력한다.
				str += '<li><a href=\'javascript:showImage(\"'+originPath+'\")\'>'
					+'<img src="/display?fileName='+fileCallPath+'">'+obj.fileName+'</a>';
					
 				//삭제를 위한 'x' 추가
 				str += '<span data-file=\"'+fileCallPath+'\" data-type="image"> X </span></div></li>';
			}
		})
		uploadResult.append(str);
	} //showUploadedFile()
	
	function checkExtension(fileName, fileSize) {
		if(fileSize >= maxSize) {
			alert('파일이 너무 큽니다.');
			return false;
		}
		
		if(regex.test(fileName)) {
			alert('업로드 할 수 없는 파일입니다.');
			return false;
		}
		
		return true;
	} //checkExtention()
	

	
	$('#uploadBtn').on('click', (e) => {
		let formData = new FormData();
		const inputFile = $('input[name="uploadFile"]');
		const files = inputFile[0].files;

		console.log(files);
		
		//add filedate to formdata
		for (let i = 0; i < files.length; i++) {
	
			//파일 확장자 & 용량 체크
			if(!checkExtension(files[i].name, files[i].size)) {
				console.log('check Extension')
				return false;
			}
			
			
			formData.append('uploadFile', files[i]);
		}
		
		$.ajax({
			url 		: "/uploadAjaxPost",
			processData : false,
			contentType : false,
			data		: formData,
			type		:'POST',
			dataType	: 'json',	//받는 타입이 json이다.
			success		:function (result) {
				console.log(result);
				
				//화면에 업로드한 파일을 띄운다.
				showUploadedFile(result);
				
				//업로드 창을 초기화
				$(".uploadDiv").html(uploadDiv.html());
			}
		}) //ajax
	})
	
	
	
}) // doc
</script>
</body>
</html>