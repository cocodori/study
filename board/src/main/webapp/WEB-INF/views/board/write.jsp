<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>
<link href="/resources/css/fileUpload.css" rel="stylesheet">
<div class="container">
	<div class="row justify-content-center">
		<div class="col-lg-5">
			<div class="card shadow-lg border-0 rounded-lg mt-5">
				<div class="card-header">
					<h3 class="text-center font-weight-light my-4">📝<i>Write</i><h3>
				</div>
				<div class="card-body">
					<form id="writeForm" >
						<div class="form-group">
							<label class="small mb-1">제목</label> <input
								class="form-control py-4" type="text"
								placeholder="제목을 입력하세요" name="title">
						</div>
						<div class="form-group">
							<label "small mb-1">내용</label>
							<textarea class="form-control py-4" rows="20" cols="54" name="content"></textarea>
						</div>
						<div class="form-group uploadDiv">
							<label class="small mb-1">파일 업로드</label> 
							<input type='file' class="form-control py-1" name="uploadFile" multiple>
							<div class="uploadResult">
								<ul>
								
								</ul>
							</div>
						</div>
						<div class="form-group">
							<label class="small mb-1" for="inputEmailAddress">작성자</label> 
							<input type='text' class="form-control py-4" name="writer"
								value='<sec:authentication property="principal.username"/>' readonly>
						</div>
						<div>
							<input type="button" id="register" class="btn btn-dark" value="등록">
							<input type="button" id="cancel" class="btn btn-dark" value="취소">
						</div>
						
						<input type="hidden" name="page" value="${pageInfo.page }">
						<input type="hidden" name="amount" value="${pageInfo.amount}">
						<input type='hidden' name='${_csrf.parameterName }' value='${_csrf.token }'>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	const regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	const maxSize = 5242880; // 5MB
	//security
	const csrfHeaderName = '${_csrf.headerName}';
	const csrfTokenValue = '${_csrf.token}';
	
	function showUploadedResult(uploadResultArr) {
		if(!uploadResultArr || uploadResultArr.length == 0) {
			return;
		}
		
		const uploadUL = $('.uploadResult ul');
		let str = '';
		$(uploadResultArr).each((i, obj) => {
			if(obj.image) {
				const fileCallPath = encodeURIComponent('/'+obj.uploadPath + '/s_'+obj.uuid+'_'+obj.fileName);
				str += '<li data-uploadpath="'+obj.uploadPath+'" data-uuid="'+obj.uuid+'" data-filename="'+obj.fileName+'" data-filetype="'+obj.image+'">';
				str += '</div><span>'+obj.fileName+'</span>';
				str += '<button type="button" class="btn btn-circle" data-file=\"'+fileCallPath+'\" data-type="image">';
				str += '<i class="fa fa-times"></i></button><br>';
				str += '<img src="/display?fileName='+fileCallPath+'">';
				str += '</div>';
				str += '</li>';
			} else {
				const fileCallPath = encodeURIComponent('/'+obj.uploadPath+'/'+obj.uuid+'_'+obj.fileName);
				const fileLink = fileCallPath.replace(new RegExp(/\\/g),'/');
				
				str += '<li data-uploadpath="'+obj.uploadPath+'" data-uuid="'+obj.uuid+'" data-filename="'+obj.fileName+'" data-filetype="'+obj.image+'">';
				str += '<div><span>' +obj.fileName +'</span>';
				str += '<button type="button" class="btn btn-circle" data-file=\"'+fileCallPath+'\" data-type="file">';
				str += '<i class="fa fa-times"></i></button><br>'
				str += '<img src="/resources/img/fileIcon.png">'
				str += '</div>'
				str += '</li>'
			}
		})
		
		uploadUL.append(str);
		
	}
	
	//확장자 및 파일 크기 체크
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
	
	$('input[type="file"]').change((e)=>{
		const formData = new FormData();
		const inputFile = $('input[name="uploadFile"]');
		const files = inputFile[0].files;
		
		for(let i=0; i<files.length; i++) {
			//업로드 된 파일이 지정한 용량보다 크거나, 지원하지 않는 확장자라면 작업 X
			if(!checkExtension(files[i].name, files[i].size)) {
				return false;
			}
			
			formData.append('uploadFile',files[i]);
		}
		
		$.ajax({
			url			: '/uploadAjaxPost',
			processData	: false,
			contentType : false,
			beforeSend	: (xhr) => {
				//security
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
			},
			data		: formData,
			type		: 'POST',
			dataType	: 'json',
			success		: (result) => {
				console.log(result);
				showUploadedResult(result);
			}
		})//ajax
	})//change()
	
	$('.uploadResult').on('click', 'button', function() {
		console.log('deleteFile...');
		
		const targetFile = $(this).data('file');	//파일이름
		const type = $(this).data('type');			//파일 타입(image or file)
		const targetLi = $(this).closest('li');	
		
		$.ajax({
			url			: '/deleteFile',
			data		: {fileName : targetFile, type:type},
			dataType	: 'text',
			beforeSend	: (xhr) => {
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
			},
			type		: 'POST', 
			success		: (result) => {
				console.log(result);
				targetLi.remove();
			}
		})
	})

</script>
<script>
$(document).ready(() => {
		const form = $("#writeForm")

		$("#cancel").on("click", (e) => {
			e.preventDefault()
			form.empty()
			form.attr("action", "/board/list")
				.submit()
				
			return
		})
		
		$("#register").on("click", (e) => {
			e.preventDefault();
			
			let str = '';
			
			$('.uploadResult ul li').each((i, obj) => {
				const jobj = $(obj);
				
				console.log(jobj);
				
				str += '<input type="hidden" name="attachList['+i+'].fileName" value="'+jobj.data('filename')+'">';
				str += '<input type="hidden" name="attachList['+i+'].uuid" value="'+jobj.data('uuid')+'">';
				str += '<input type="hidden" name="attachList['+i+'].uploadPath" value="'+jobj.data('uploadpath')+'">';
				str += '<input type="hidden" name="attachList['+i+'].fileType" value="'+jobj.data('filetype')+'">';
				
			}) //each
			
			form.append(str);
			form.attr('action','/board/register')
				.attr('method', 'post')
				.submit();
		})
		
})//docuemnt
</script>

<%@ include file="../includes/footer.jsp"%>