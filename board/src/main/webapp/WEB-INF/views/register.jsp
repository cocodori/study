<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>회원가입</title>
        <link href="/resources/dist/css/styles.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js" crossorigin="anonymous"></script>
    </head>
    <body class="bg-dark">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-7">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">회원가입</h3></div>
                                    <div class="card-body">
                                        <form id="registerForm" action="/register" method="post">
                                            <div class="form-row">
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label class="small mb-1" for="inputFirstName">아이디</label>
                                                        <input class="form-control py-4" name="userid" type="text"/>
                                                    </div>
                                                </div>
                                            </div>
											<div class="form-row">
                                                 <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label class="small mb-1">이름</label>
                                                        <input class="form-control py-4" name="username" type="text"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-row">
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label class="small mb-1" for="inputPassword">비밀번호</label>
                                                        <input class="form-control py-4" name='userpw' type="password" />
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label class="small mb-1" for="inputConfirmPassword">비밀번호 확인</label>
                                                        <input class="form-control py-4" id="inputConfirmPassword" type="password"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group mt-4 mb-0">
                                            	<button class="btn btn-dark btn-block registerBtn">가입하기</button>
                                            </div>
                                            
                                            <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"> 
                                        </form>
                                    </div>
                                    <div class="card-footer text-center">
                                        <div class="small"><a href="/login">로그인</a></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
            <div id="layoutAuthentication_footer">
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; Your Website 2020</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
    </body>
</html>
<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
<script type="text/javascript">
$(document).ready(()=> {
	$('.registerBtn').on('click', (e)=>{
		e.preventDefault();
		
		const password = $('input[name="userpw"]').val();
		const confirmPassword = $('#inputConfirmPassword').val();
		const userid = $('input[name="userid"]').val();
		const username = $('input[name="username"]').val();
		
		if(userid === null || userid.length < 4) {
			alert('아이디는 네 글자 이상이어야 합니다.');
			return;
		}
		
		if(username === null || username.length < 2){
			alert('이름은 두 글자 이상이어야 합니다.');
			return;
		}
		
		if(password.length < 4) {
			alert('비밀번호는 네 글자 이상이어야 합니다.');
			return;
		}
		
		if(password !== confirmPassword) {
			alert('비밀번호가 일치하지 않습니다.');
			return;
		}
		
		$('#registerForm').submit();
	})
})
</script>
