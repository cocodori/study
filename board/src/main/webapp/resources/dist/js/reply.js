/**
 *  댓글 처리 모듈화
 */

console.log('Reply Module...')
const replyService = (() => {
	
	function add(reply, callback) {
		console.log('function add(reply, callback)')
	
		$.ajax({
			type		: 'POST',
			url			: '/replies/new',
			data		: JSON.stringify(reply),				//JS의 객체를 JSON문자열로 바꾼다.(반대는 JSON.Parse)
			contentType : 'application/json;charset=UTF-8',	//지금 보내는 데이터가 json이다.
			success		: (result, status, xhr) => {
				callback ? callback(result) : ''
			}, //success
			error		: (xhr, status, er) => {
				er ? error(er) : ''
			} // error
		}) //ajax
	} //add()
	
	function getReplyList(param, callback, error) {
		const bno = param.bno
		const page = param.page || 1
		
		$.getJSON('/replies/pages/'+bno+'/'+page+'.json',
				(data) => {
					if(callback) callback(data.replyCount, data.replyList)
		}).fail((xhr, status, err) => {
			err ? error(err) : ''
		})
	}	//getReplyList()
	
	function remove(rno, replyer, callback, error) {
		$.ajax({
			type	: 'DELETE',
			url		: '/replies/'+rno,
			data	: JSON.stringify({rno:rno, replyer:replyer}),
			contentType : 'application/json; charset=UTF-8',
			success : (deleteResult, status, xhr) => {
				callback ? callback(deleteResult) : ''
			}, //success
			error	: (xhr, status, er) => {
				error ? error(er) : ''
			}
		})
	} //remove()
	
	function update(reply, callback, error) {
		console.log('rno :' + reply.rno)
		
		$.ajax({
			type		: 'PUT', 
			url			: '/replies/'+reply.rno,
			data		: JSON.stringify(reply),
			contentType : 'application/json;charset=UTF-8',
			success		: (result, status, xhr) => {
				callback ? callback(result) : ''
			},
			error		: (xhr, status, er) => {
				error ? error(er) : ''
			}
		})
	} // update()
	
	function get(rno, callback, error) {
		$.get('/replies/'+rno+'.json', (result)=> {
			callback ? callback(result) : ''
		}).fail((xhr, status, err) => {
			error ? error() : ''
		})
	} //getReply()
		
	return {
		 add			: add
		,getReplyList	: getReplyList
		,remove			: remove
		,update			: update
		,get			: get
		}
})() 