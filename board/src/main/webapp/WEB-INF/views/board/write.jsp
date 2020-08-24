<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>
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
						<div class="form-group">
							<label class="small mb-1" for="inputEmailAddress">작성자</label> 
							<input type='text' class="form-control py-4" name="writer">
						</div>
						<div>
							<button class="btn bg-dark" id="submit" style='color:white'>등록</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
$(document).ready(() => {
	
		const form = $("#writeForm")
		
		console.log(form)
		
		const btn = $("#submit")
		
		console.log(btn)
	
		$("#submit").on("click", (e) => {
			//e.preventDefault()
			console.log('hi hello?')
			
			form.attr("action","/board/register")
				.attr('method', 'POST')
				.submit()
			
		})
		
})//docuemnt
</script>

<%@ include file="../includes/footer.jsp"%>