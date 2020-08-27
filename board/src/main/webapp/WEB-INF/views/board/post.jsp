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
					<form id="actionForm" >
						<div class="form-group">
							<label class="small mb-1">게시물 번호</label> <input
								class="form-control py-4" type="text"
								name="bno" value='${post.bno }' disabled>
						</div>
						<div class="form-group">
							<label class="small mb-1">제목</label> <input
								class="form-control py-4 mod" type="text"
								name="title" value='${post.title }' disabled>
						</div>
						<div class="form-group">
							<label "small mb-1">내용</label>
							<textarea class="form-control py-4 mod" rows="20" cols="54" name="content" disabled>${post.content }</textarea>
						</div>
						<div class="form-group">
							<label class="small mb-1" for="inputEmailAddress">작성자</label> 
							<input type='text' class="form-control py-4" name="writer" 
							value="${post.writer }" disabled>
						</div>
						<div class="form-group">
							<p align="right">
							<label class="small mb-1">작성일</label>
							<fmt:formatDate value="${post.regdate }" pattern="yyyy-MM-dd HH:mm:ss"/>
							</p>
							<c:choose>
								<c:when test="${post.moddate != post.regdate}">
									<p align="right">
									<label class="small mb-1">수정일</label>
									<fmt:formatDate value="${post.moddate }" pattern="yyyy-MM-dd HH:mm:ss"/>
									</p>	
								</c:when>
							</c:choose>
						</div>
						<div class="btns">
							<button class="btn bg-dark" id="modBtn" style='color:white'>수정</button>
							<button class="btn bg-dark" id="list" style='color:white'>목록</button>
							<button class="btn bg-dark" id="remove" style='color:white'>삭제</button>
						</div>
						<div class="modBtns" style="display:none">
							<button class="btn bg-dark" id="modify" style='color:white'>완료</button>
							<button class="btn bg-dark" id="cancel" style='color:white'>취소</button>
						</div>
						
						<!-- disabled속성을 가진 태그는 전달되지 않아서 hidden태그로 따로 보낸다. -->
						<input type="hidden" id="bno" name="bno" value="${post.bno }">
						<input type="hidden" name='page' value='${pageInfo.page }'>
						<input type='hidden' name='amount' value='${pageInfo.amount}'>
						<input type='hidden' name='type' value='${pageInfo.type}'>
						<input type='hidden' name='keyword' value='${pageInfo.keyword}'>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
$(document).ready(() => {
		const form = $("#actionForm")
		/* '수정' 버튼 클릭 전*/
		
		//목록
		$('#list').on('click', (e) => {
			e.preventDefault()
			
			form.attr('action','/board/list')
				.submit()
		})
		
		//삭제
		$('#remove').on('click',(e) => {
			e.preventDefault()
			if(confirm('정말 삭제하겠습니까?')) {
				form.attr('action', '/board/remove')
					.attr('method', 'POST')
					.submit()
			}
			return
		})
	
		/* '수정' 버튼 클릭 후  이벤트 처리*/
		//'수정' 버튼 클릭 시, 기존 버튼 없애고, 새 버튼 생성
		$('#modBtn').on("click", (e) => {
			e.preventDefault()
			$('.btns').css('display','none')
			$('.modBtns').css('display','block')
			$('.mod').attr('disabled',false)		
		})
		
		//'취소' 버튼 클릭 시, 원상태로 복구
		$('#cancel').on('click', (e) => {
			e.preventDefault()
			$('.btns').css('display','block')
			$('.modBtns').css('display','none')
			$('.mod').attr('disabled',true)
		})
		
		//'완료' 버튼 클릭 시 수정 처리
		$('#modify').on("click", (e) => {
			e.preventDefault()
			form.attr('action', '/board/modify')
				.attr('method','POST')
				.submit()
		})
		
		
})//docuemnt
</script>

<%@ include file="../includes/footer.jsp"%>