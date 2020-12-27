/**
 *  post.jsp에서 사용한 ajax 테스트 코드
 */

$(document).ready(() => {
	console.log('Javascript TEST')
	const bnoValue = '${post.bno}'
	console.log('bnoValue : ' + bnoValue)
	
	//댓글 작성 테스트
/* 	replyService.add(
			{reply	: 'ajax를 이용한 댓글 등록 테스트',
			 replyer: 'BestTester',
			 bno	: bnoValue
			}, (result) => {
				console.log('result : ' + result)
			}
	) */
	
	//댓글 목록 출력 테스트
	replyService.getReplyList({bno : bnoValue, page:1} ,(list) => {
		for(let i = 0, len = list.length||0; i < len; i++) {
			console.log(list[i])
		}
	})
	
	//댓글 삭제 테스트
/*  	replyService.remove(25, (count) => {
		console.log(count)
		
		const removeResult = count === 'success' ?
				'removed !' : 'error...'
		
		console.log(removeResult)
	}) */
	
	//댓글 수정 테스트

	
	/* 	replyService.update({
		rno   : 30,
		reply : '안녕~ 빡빡이 아저씨야~!'
	}, (result) => {
		console.log('수정 완료')
	}) */
	
})
