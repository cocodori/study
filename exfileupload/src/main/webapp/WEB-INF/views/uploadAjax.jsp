<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax를 이용한 파일 업로드</title>
</head>
<body>

<h1>Upload With Ajax</h1>

<div class="uploadDiv">
	<input type="file" name="uploadFile" multiple>
</div>
	<button id="uploadBtn">UPLOAD</button>
	
<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>

<script type="text/javascript">
$(document).ready(() => {
	$('#uploadBtn').on('click', (e) => {
		const formData = new FormData();
		const inputFile = $('input[name="uploadFile"]');
		const files = inputFile[0].files;
		
		console.log(files);
		
		//add filedate to formdata
		for (let i = 0; i < files.length; i++) {
			formData.append('uploadFile', files[i]);
		}
		
		$.ajax({
			url 		: '/uploadAjaxAction',
			processData : false,
			contentType : false,
			data		: formData,
			type		:'post',
			success		:(result) => {
				console.log('Uploaded');
			}
		}) //ajax
	})
}) // doc
</script>

</body>
</html>