<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>
<link href="/resources/css/fileUpload.css" rel="stylesheet">
<!-- 첨부파일 원본 이미지 -->
<div class="bigPictureWrapper">
	<div class="bigPicture"></div>
</div>

<div class="container">
	<div class="row justify-content-center">
		<div class="col-lg-5">
			<div class="card shadow-lg border-0 rounded-lg mt-5">
				<div class="card-header">
					<h3 class="text-center font-weight-light my-4">
						📝<i>Write</i>
						<h3>
				</div>
				<div class="card-body">
					<form id="actionForm">
						<div class="form-group">
							<label class="small mb-1">게시물 번호</label> <input
								class="form-control py-4" type="text" name="bno"
								value='${post.bno }' disabled>
						</div>
						<div class="form-group">
							<label class="small mb-1">제목</label> <input
								class="form-control py-4 mod" type="text" name="title"
								value='${post.title }' disabled>
						</div>
						<div class="form-group">
							<label "smallmb-1">내용</label>
							<textarea class="form-control py-4 mod" rows="20" cols="54"
								name="content" disabled>${post.content }</textarea>
						</div>
						
						<div class="form-group">
							<label class="small mb-1">첨부파일</label>
							
						<div class="form-group uploadDiv" style="display:none">
							<input type='file' name="uploadFile" multiple>
						</div>
							
							<div class="uploadResult">
								<ul>
								</ul>
							</div>
						</div>
						
						<div class="form-group">
							<label class="small mb-1">작성자</label> <input
								type='text' class="form-control py-4" name="writer"
								value="${post.writer }" disabled>
						</div>
						<div class="form-group">
							<p align="right">
								<label class="small mb-1">작성일</label>
								<fmt:formatDate value="${post.regdate }"
									pattern="yyyy-MM-dd HH:mm:ss" />
							</p>
							<c:choose>
								<c:when test="${post.moddate != post.regdate}">
									<p align="right">
										<label class="small mb-1">수정일</label>
										<fmt:formatDate value="${post.moddate }"
											pattern="yyyy-MM-dd HH:mm:ss" />
									</p>
								</c:when>
							</c:choose>
						</div>
						<div class="btns">
							<button class="btn bg-dark" id="modBtn" style='color: white'>수정</button>
							<button class="btn bg-dark" id="list" style='color: white'>목록</button>
							<button class="btn bg-dark" id="remove" style='color: white'>삭제</button>
						</div>
						<div class="modBtns" style="display: none">
							<button class="btn bg-dark" id="modify" style='color: white'>완료</button>
							<button class="btn bg-dark" id="cancel" style='color: white'>취소</button>
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
								&nbsp&nbsp
								<h4>
									<strong>💬 댓글 </strong>

								</h4>
								<div class="input-group">
									<input class="form-control py-4" type="text" name="reply"
										placeholder="댓글을 입력하세요">
									<div class="input-group-append">
										<input type='submit' class="btn btn-dark" id="replyBtn"
											value="등록">
									</div>
								</div>
							</div>
							<br>
							<div class="panel-body">
								<ul class="chat">
									<!-- 댓글이 생성되는 영역 -->
								</ul>
							</div>
							<div class="panel-footer">
								<!-- 페이지 번호가 생성되는 영역 -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Reply Modify Modal -->
<div class="modal fade" id="replyModifyModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">댓글 수정</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
            <label class="col-form-label"> 👩‍🚀  작성자 </label>
            <input type="text" class="form-control" name="replyer" disabled>
          </div>
          <div class="form-group">
            <label class="col-form-label"> 💬  댓글</label>
            <textarea class="form-control" name="reply"></textarea>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-dark" id="modalModBtn">수정 완료</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript" src="/resources/dist/js/reply.js"></script>

<script>
$(document).ready(() => {
	const showFileList = (()=>{	//화면에 첨부파일 출력
		const bno = '${post.bno}';
		
		$.getJSON('/board/getAttachList',{bno:bno}, (arr) => {
			console.log(arr)
			
			let str = '';
			
			$(arr).each((i, attach) => {
				//이미지 파일인경우
				if(attach.fileType) {
					const fileCallPath = encodeURIComponent("/"+attach.uploadPath+'/s_'+attach.uuid+'_'+attach.fileName);
					
					str += '<li data-uploadpath="'+attach.uploadPath+'" data-uuid="'+attach.uuid+'" data-filename="'+attach.fileName+'" data-filetype="'+attach.fileType+'">';
					str += '<div>'
					str += '<div class="fileModify" style="display:none">'
					str += '<span>'+attach.fileName+'</span><br>';
					str += '<button type="button" data-file=\"'+fileCallPath+'\" class="btn btn-circle" data-type="image">';
					str += '<i class="fa fa-times"></i></button><br>';
					str += '</div>'

					
					str += '<img src="/display?fileName='+fileCallPath+'">';
					str += '</div>';
					str += '</li>';
				} else {
					const fileCallPath = encodeURIComponent("/"+attach.uploadPath+'/'+attach.uuid+'_'+attach.fileName);
					str += '<li data-uploadpath="'+attach.uploadPath+'" data-uuid="'+attach.uuid+'" data-filename="'+attach.fileName+'" data-filetype="'+attach.fileType+'">';
					str += '<div><span>'+attach.fileName+'</span>';
					str += '<div class="fileModify" style="display:none">'
					str += '<button type="button" data-file=\"'+fileCallPath+'\" class="btn btn-circle" data-type="file">';
					str += '<i class="fa fa-times"></i></button><br>';

					str += '</div>'
					str += '<img src="/resources/img/fileIcon.png">';
					str += '</div>';
					str += '</li>';
				}
			});
			$('.uploadResult ul').html(str);
		})	//getJSON
	})() //end - function
	
	//이미지 확대 && 파일 다운로드
	$('.uploadResult').on('click', 'li', function() {
		const liObj = $(this);
		const path = encodeURIComponent(liObj.data('uploadpath')+'/'+liObj.data('uuid')+'_'+liObj.data('filename'));
		
		if(liObj.data('filetype')) {
			showImage('/'+path.replace(new RegExp(/\\/g),'/'));
		} else {
			//download
			self.location = '/download?fileName='+path;
		}
	})
	
	//이미지 축소
	$('.bigPictureWrapper').on('click', ()=> {
		$('.bigPictureWrapper').hide();
	})
	
	$('.uploadResult').on('click','button', function(e) {
		e.preventDefault();
		console.log('deleteFile');
		if(confirm('정말 삭제하겠습니까?')) {
			const targetLi = $(this).closest('li');
			targetLi.remove();
		}
	})
	
	
	function showImage(fileCallPath) {	//섬네일 이미지를 원본 이미지로 확대하는 함수
		console.log(fileCallPath);
		
		$('.bigPictureWrapper').css('display','flex').show();
		$('.bigPicture').html('<img src="/display?fileName='+fileCallPath+'">');
	}
	
	const regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	const maxSize = 5242880; // 5MB
	
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
			data		: formData,
			type		: 'POST',
			dataType	: 'json',
			success		: (result) => {
				console.log(result);
				showUploadedResult(result);
			}
		})//ajax
	})//change()
	
	
})
</script>

<script>
$(document).ready(()=>{
	const bnoValue = '${post.bno}'
	const replyDL = $(".chat")
	const modal = $('#replyModifyModal')
	let pageNum = 1
	const replyPageFooter = $('.panel-footer')
	
	showReplyList(pageNum)
	
	//댓글 삭제 || 수정
	$('.chat').on('click', 'a', function(e) {
		e.preventDefault()
		const rno = $(this).data('rno')
		const btn = $(this).data('oper')

		console.log(rno);
		
		//삭제 버튼을 클릭했다면.
		if(btn === 'replyRemove') {
			if(confirm('정말 삭제하겠습니까?')) { //정말 삭제할 것인지 확인
				//삭제 함수 호출
				replyService.remove(rno,(result)=>{
					console.log(result)
					
					//삭제 처리 후, 댓글 목록 갱신
					showReplyList(pageNum)
					return
				})
			}
		}
		
		if(btn === 'replyModify') {
			let replyText
				
			//모달창을 띄우고, 모달창에 수정할 댓글 내용을 출력한다
			$('#replyModifyModal').modal('show')
			
			replyService.get(rno,(result) => {
				modal.find('input[name="replyer"]').val(result.replyer)
				replyText = modal.find('textarea[name="reply"]').val(result.reply)
		
				//수정 처리
				$("#modalModBtn").on('click', () => {
					//수정할 데이터를 객체 형식으로 저장
					const reply = {
						rno:rno,
						reply:replyText.val()
					}
					
					console.log('----------rno----------')
					
					replyService.update(reply, (result)=>{
						//수정을 마치면 모달을 닫고 목록을 갱신한다.
						modal.modal('hide')
						showReplyList(pageNum)
					}) //update
				})
			})
		} //if(modify)
	}) 
	
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
			//input태그를 초기화 한다.
			$('input[name="reply"]').val('')
			
			//댓글이 추가 되었으므로 새롭게 목록을 갱신한다.
			showReplyList(1)
		})
		
	})
	
	replyPageFooter.on('click', 'li a', function(e) {
		e.preventDefault()
		pageNum = $(this).attr('href')
		
		showReplyList(pageNum)
	})
	
	
	//댓글 목록을 출력하는 함수
	function showReplyList(page) {
		replyService.getReplyList({
			bno	: bnoValue,
			page: page||1
			}, (replyCount, list) => {

				let str = ""
				
				if(list == null || list.length == 0 ) {
					replyDL.html('')
					return
				}
				
				for (let i = 0, len = list.length || 0; i < len; i++) {
 					str += '<dd class="left clearfix">'
					str += '<div><div class="header"><strong class="primary-font"> 👩‍🚀‍ '+list[i].replyer+'</strong>'
					str += '<small class="pull-right text-muted">'+list[i].replyDate+'</small>'
					str += '&nbsp<small><a href="#" data-oper="replyModify" data-rno="'+list[i].rno+'">수정</a></small>'
					str += '&nbsp<small><a href="#" data-oper="replyRemove" data-rno="'+list[i].rno+'">삭제</a></small></div>'
					str += '<p>'+list[i].reply+'</p></div></dd>'
					str += '<hr>'
				} //for
				
				replyDL.html(str)
				
				showReplyPage(replyCount)
			})
	} //showReplyList
	
	function showReplyPage(replyCount) {
		let endNum = Math.ceil(pageNum / 10.0) * 10
		let startNum = endNum - 9
		
		let prev = startNum != 1
		let next = false
		
		endNum * 10 >= replyCount ?
				endNum = Math.ceil(replyCount/10.0) : ''
		
		if(endNum * 10 < replyCount) next = true
		
		let str = "<ul class='pagination pull-right'>"
		
		prev ? str += "<li class='page-item'><a class='page-link'"
				+"href='"+(startNum - 1)+"'> Previous </a></li>" : ''
		
		for(let i = startNum; i <= endNum; i++) {
			let active = pageNum == i ? 'active' : ''
			str += "<li class='page-item "+active+"'><a class='page-link' href='"+i+"'>"+i+"</a></li>"
		}
		
		if(next) {
			str += "<li class='page-item'><a class='page-link' href='"+(endNum + 1)+"'>NEXT</a></li>"
		}
		
		str += "</ul></div>"
		
		replyPageFooter.html(str)
	}
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
			//$(".uploadResult").unbind();

			
			//파일 업로드 관련
			$('.uploadDiv').css('display', 'block')
			$('.fileModify').css('display', 'block')
		})
		
		//'취소' 버튼 클릭 시, 원상태로 복구
		$('#cancel').on('click', (e) => {
			e.preventDefault()
			$('.btns').css('display','block')
			$('.modBtns').css('display','none')
			$('.mod').attr('disabled',true)
a			//파일 업로드 관련
			$('.uploadDiv').css('display', 'none')
			$('.fileModify').css('display', 'none')
		})
		
		//'완료' 버튼 클릭 시 수정 처리
		$('#modify').on("click", (e) => {
			let str = '';
			
			$('.uploadResult ul li').each((i, obj)=>{
				const jobj = $(obj);
				console.log(jobj);
				
				str += '<input type="hidden" name="attachList['+i+'].fileName" value="'+jobj.data('filename')+'">';
				str += '<input type="hidden" name="attachList['+i+'].uuid" value="'+jobj.data('uuid')+'">';
				str += '<input type="hidden" name="attachList['+i+'].uploadPath" value="'+jobj.data('uploadpath')+'">';
				str += '<input type="hidden" name="attachList['+i+'].fileType" value="'+jobj.data('filetype')+'">';
		
			}) 
				form.append(str)
					.attr('action', '/board/modify')
					.attr('method','POST')	
					.submit();
		})
		
		
})//docuemnt
</script>

<%@ include file="../includes/footer.jsp"%>