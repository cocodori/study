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
	
<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>

<script type="text/javascript">
$(document).ready(() => {
	//확장자 exe, sh, zip, alz 제한
	const regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	const maxSize = 10485760; //10MB
	const uploadDiv = $('.uploadDiv').clone();
	const uploadResult = $('.uploadResult ul');

	/*
		화면에 업로드한 파일 정보를 출력하는 함수	
	*/
	function showUploadedFile(uploadResultArr) {
		let str = '';
		$(uploadResultArr).each((i, obj) => {
			// 이미지가 아니면 파일 아이콘을 출력한다.
			if(!obj.image) {
				str += '<li><img src="/resources/img/fileIcon.png">'+obj.fileName+'</li>';
			} else {
				//이미지 파일이면 섬네일을 출력한다.
				const fileCallPath = encodeURIComponent("/"+obj.uploadPath+'/s_'+obj.uuid+'_'+obj.fileName);
				console.log(fileCallPath)
				str += '<img src="/display?fileName='+fileCallPath+'">'+obj.fileName+'</li>';
			}
		})
		uploadResult.append(str);
	}
	
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
	}
	
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