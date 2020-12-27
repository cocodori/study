<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File Upload Test</title>
</head>
<body>

<form action="uploadFormAction" method="post" enctype="multipart/form-data">
	<input type="file" name="uploadFile" multiple>
	<button>UPLOAD</button>
</form>

</body>
</html>