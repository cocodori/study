<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp"%>
<br><h1 class="asdfasdt-4">📝 <i>Any story is fine!</i></h1><br>
<div align="right">
	<button id="write" class="btn bg-dark write" style="color:white">✒글쓰기</button>
</div>
<br>
<div class="card asdfasdb-4">
	<div class="card-body">
		<div class="table-responsive">
			<table class="table table-bordered" id="" width="100%"
				cellspacing="0">
				<thead>
					<tr>
						<th>NO</th>
						<th>TITLE</th>
						<th>WRITER</th>
						<th>DATE</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${list.size() == 0}">
							<td colspan="4">
								<p align="center">
									<br> 아직 등록된 게시물이 없습니다 😅<br> 첫 번째 글을 써보세요!<br> <strong><a
										class="write" href='/board/write'>✏글쓰기</a></strong>
								</p>

							</td>
						</c:when>
						<c:otherwise>
							<c:forEach var="post" items="${list }" begin="0"
								end="${list.size()}">
								<tr>
									<td>${post.bno }</td>
									<td><a class='move' href='${post.bno}'>${post.title }</a></td>
									<td>${post.writer }</td>
									<td><fmt:formatDate value="${post.regdate }"
											pattern="yyyy-MM-dd" /></td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			
 			<div class="pagination" align="center">
				<ul class="pagination">
				
					<c:if test="${pageDTO.prev }">
						<li class="page-item"><a class="page-link" href="${pageDTO.startPage - 1 }"
							tabindex="-1">Previous</a></li>
					</c:if>
					
					<c:forEach var="pageNumber" begin="${pageDTO.startPage }" end="${pageDTO.endPage }">
						<li class="page-item ${pageNumber == pageDTO.pageInfo.page ? "active":""}">
							 <a href="${pageNumber }" class="page-link">
								${pageNumber}
							 </a>
						 </li>
					</c:forEach>
					
					<c:if test="${pageDTO.next}">
						<li class="page-item"><a class="page-link" href="${pageDTO.endPage + 1 }">Next</a></li>
					</c:if>
				</ul>
			</div>
			
		</div>
	</div>
</div>

<form id="form" action="/board/list">
	<input type="hidden" name='page' value='${pageDTO.pageInfo.page }'>
	<input type='hidden' name='amount' value='${pageDTO.pageInfo.amount}'>
</form>

<script type="text/javascript">
$(document).ready(() => {
	const form = $("#form")
	
	$(".write").on("click", () => {
		location.replace("/board/write")
	})
	
	//게시물 조회 페이지로 이동 시에, page정보 함께 가져 갈 수 있도록 핸들링
	$(".move").on("click", function(e) {
		e.preventDefault()
		form.attr("action", "/board/post")
			.append("<input type='hidden' name='no' value='"+
												$(this).attr("href")+"'>")
		form.submit()
	})
	
	$(".page-item a").on("click", function(e) {
		e.preventDefault();
		
		form.find("input[name='page']").val($(this).attr('href'))
		form.submit()
	})

}) //docu
</script>

<%@include file="../includes/footer.jsp"%>