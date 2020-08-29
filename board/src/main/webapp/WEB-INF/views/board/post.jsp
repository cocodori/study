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
<!-- 댓글 -->
<div class="row">
	<div class="col-lg-12">
	 	<div class="panel panel-default">
	 		<div class="pannel-heading">
	 			&nbsp&nbsp <h4><strong>💬  댓글 </strong>
	 			
	 			</h4>
				<div class="input-group">
                    <input class="form-control py-4" type="text" name="reply" placeholder="댓글을 입력하세요">
                    <div class="input-group-append">
                        <input type='submit' class="btn btn-dark" id="replyBtn" value="등록">
                    </div>
                </div>
			</div>
			
			<br>
			
			
			
			<div class="panel-body">
				<ul class="chat">
<!-- 			<dd class="left clearfix" data-rno='28'>
					<div>
						<div class="header">
							<strong class="primary-font">user00</strong>
							<small class="pull-right text-muted">0000-00-00</small>
						</div>
						<p>First Reply</p>
					</div>
					</dd> -->
				</ul>
			</div>
		</div>
	</div>
</div>

		</div>
		</div>
	</div>
</div>


<script type="text/javascript" src="/resources/dist/js/reply.js"></script>

<script>
$(document).ready(()=>{
	const bnoValue = '${post.bno}'
	const replyDL = $(".chat")
	
	showReplyList(1)
	
	//댓글 삭제 || 수정
	$('.chat').on('click', 'a', function(e) {
		e.preventDefault()
		const rno = $(this).data('rno')
		const btn = $(this).data('oper')

		console.log(rno)
		console.log(btn)
		//삭제 버튼을 클릭했다면.
		if(btn === 'replyRemove') {
			if(confirm('정말 삭제하겠습니까?')) { //정말 삭제할 것인지 확인
				//삭제 함수 호출
				replyService.remove(rno,(result)=>{
					console.log(result)
					
					//삭제 처리 후, 댓글 목록 갱신
					showReplyList(1)
					return
				})
			}
		}
		
		if(btn === 'replyModify') {
			
			console.log('hello mod')
			console.log(rno)
/* 			$("#mod").css("display","none")
			$('#reply').css('display','none')
			$('#replyRemove').css('display','none')
			$('#replyInput').css('display','block') */
			$('.beforeMod').css('display','none')
			$('.afterMod').css('display','block')
		
			let modReply = $('#reply'+rno)
			console.log(modReply.textContent)
			
			
		}
		

		
	}) //댓글 삭제
	
	//댓글 등록
	$('#replyBtn').on('click', () => {
		//댓글 내용을 받아온다.
		const replyVal = $('input[name="reply"]').val()
		
		//만약, 내용이 없다면 아무 일도 하지 않는다.
		if(replyVal.length === 0) { return }

		//reply객체를 만든다.
		const reply = {
				reply 	: replyVal,
				replyer : 'tester',
				bno		: bnoValue
		} //reply

		//댓글 추가하는 함수를 호출해서 reply객체를 인자로 넘긴다.
		replyService.add(reply, (result) => {
			console.log(result)
			
			//input태그를 초기화 한다.
			$('input[name="reply"]').val('')
			
			//댓글이 추가 되었으므로 새롭게 목록을 갱신한다.
			showReplyList(1)
		})
		
	})
	
	//댓글 목록을 출력하는 함수
	function showReplyList(page) {
		replyService.getReplyList({
			bno	: bnoValue,
			page: page||1
			}, (list) => {
				let str = ""
				
				if(list == null || list.length == 0 ) {
					replyDL.html('')
					return
				}
				
				for (let i = 0, len = list.length || 0; i < len; i++) {
/* 					str += '<dd class="left clearfix" data-rno="'+list[i].rno+'">'
					str += '<div><div class="header"><strong class="primary-font"> 👩‍🚀‍ '+list[i].replyer+'</strong>'
					str += '<small class="pull-right text-muted">'+list[i].replyDate+'</small>'
					str += '&nbsp<small><a href="#" data-oper="replyModify" data-rno="'+list[i].rno+'" id="mod">수정</a></small>'
					str += '&nbsp<small><a href="#" data-oper="replyRemove" data-rno="'+list[i].rno+'" id="replyRemove">삭제</a></small></div>'
					str += '<p id="reply'+i+'">'+list[i].reply+'</p></div></dd>'
					str += '<div class="replyModClass" style="display:none">'
					str += '<input type="text" class="form-control py-4" id="replyInput'+i+'" value="'+list[i].reply+'">'
					str += '<button class="btn btn-dark">완료</button></div>'
					str += '<hr>' */
					
					str += '<dd class="left clearfix" data-rno="'+list[i].rno+'">'
					str += '<div class="beforeMod"><div class="header"><strong class="primary-font"> 👩‍🚀‍ '+list[i].replyer+'</strong>'
					str += '<small class="pull-right text-muted">'+list[i].replyDate+'</small>'
					str += '&nbsp<small><a href="#" data-oper="replyModify" data-rno="'+list[i].rno+'" id="mod">수정</a></small>'
					str += '&nbsp<small><a href="#" data-oper="replyRemove" data-rno="'+list[i].rno+'" id="replyRemove">삭제</a></small></div>'
					str += '<p id="reply'+list[i].rno+'">'+list[i].reply+'</p></div>'
					
					str += '<div class="afterMod" style="display:none"><div class="header"><strong class="primary-font"> 👩‍🚀‍ '+list[i].replyer+'</strong>'
					str += '<small class="pull-right text-muted">'+list[i].replyDate+'</small>'
					str += '&nbsp<small><a href="#" data-oper="replyModify" data-rno="'+list[i].rno+'" id="mod">수정</a></small>'
					str += '&nbsp<small><a href="#" data-oper="replyRemove" data-rno="'+list[i].rno+'" id="replyRemove">삭제</a></small></div>'
					str += '<p id="reply'+list[i].rno+'">'+list[i].reply+'</p></div>'
					
					str += '</dd>'
					str += '<hr>'
				} //for
				
				replyDL.html(str)
			})
			

	} //showReplyList
}) // docu
</script>

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