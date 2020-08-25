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
									<td><a class='move' href='/board/post?no=${post.bno}'>${post.title }</a></td>
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
					<li class="page-item disabled"><a class="page-link" href="#"
						tabindex="-1">Previous</a></li>
					<li class="page-item"><a class="page-link" href="#">1</a></li>
					<li class="page-item active"><a class="page-link" href="#">2
							<span class="sr-only">(current)</span>
					</a></li>
					<li class="page-item"><a class="page-link" href="#">3</a></li>
					<li class="page-item"><a class="page-link" href="#">Next</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>

<%@include file="../includes/footer.jsp"%>

<script>

$(document).ready( () => {
	$(".write").on("click", () => {
		location.replace("/board/write")
	})
	
	
	
}) //docu
	


</script>



