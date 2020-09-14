# UriComponentBuilder

게시물을 POST방식으로 처리하고 나서도  페이지나 검색 조건을 유지하기 위해서는 페이지 정보와 검색 조건, 검색 키워드 정보를 RedirectAttribute객체로 직접 지정해야 했다.

```java
	@PostMapping("/modify")
	public String modify(BoardVO boardVO, PageInfo pageInfo,RedirectAttributes redirect) {
		log.info("/board/modify");
		log.info("BoardVO : " + boardVO);
		
		int result = boardService.modify(boardVO);
		
		log.info("MODIFY RESULT : " + result);
		
		redirect.addAttribute("no",boardVO.getBno());
		redirect.addAttribute("page", pageInfo.getPage());
		redirect.addAttribute("amount", pageInfo.getAmount());
		redirect.addAttribute("type", pageInfo.getType());
		redirect.addAttribute("keyword", pageInfo.getKeyword());
		
		return "redirect:/board/post";
	}

	@PostMapping("/remove")
	public String remove(Long bno, PageInfo pageInfo, RedirectAttributes redirect) {
		log.info("/board/remove");
		
		int result = boardService.remove(bno);
		log.info("result : " + result);

		redirect.addAttribute("page", pageInfo.getPage());
		redirect.addAttribute("amount", pageInfo.getAmount());
		redirect.addAttribute("type", pageInfo.getType());
		redirect.addAttribute("keyword", pageInfo.getKeyword());
		
		return "redirect:/board/list";
	}
```
이것은 수정/삭제 후에도 검색 조건과 페이지 정보를 유지하기 위해서 직접 redirect한 코드다. 보듯이 remove메서드와 modify메서드 여러 부분이 중복이다. 하나하나 지정하기도 귀찮은 일이고, 중복은 영 찝찝한 일이다. **UriComponentBuilder**객체를 이용하면 코드를 한 줄로 줄이고, 중복도 제거할 수 있다.

페이지 정보와 검색 정보를 관리하는 PageInfo 객체에 UriComponentBuilder를 이용해서 내가 필요한 Uri를 반환하는 메서드를 만들었다.

```java
	public String getUrlList () {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("page", this.getPage())
				.queryParam("amount", this.getAmount())
				.queryParam("type", this.getType())
				.queryParam("keyword", this.getKeyword());
		return builder.toUriString();
	}
```

Key - Value 형식으로 지정하면 된다. 이 메서드는
```?page=8&amount=10&type=tcw&keyword=``` 이런 uri 문자열을 반환한다.


아래는 UriComponentBuilder을 적용한 다음 remove와 modify메서드 코드다.

```java
	@PostMapping("/modify")
	public String modify(BoardVO boardVO, PageInfo pageInfo,RedirectAttributes redirect) {
		log.info("/board/modify");
		log.info("BoardVO : " + boardVO);
		
		int result = boardService.modify(boardVO);
		
		log.info("MODIFY RESULT : " + result);
		
		redirect.addAttribute("no",boardVO.getBno());
		
		return "redirect:/board/post" + pageInfo.getUrlList();
	}

	@PostMapping("/remove")
	public String remove(Long bno, PageInfo pageInfo) {
		log.info("/board/remove");
		
		int result = boardService.remove(bno);
		log.info("result : " + result);
		
		return "redirect:/board/list" + pageInfo.getUrlList();
	}
```

중복되는 redirect를 모두 제거했다. remove메서드에서는 더 이상 RedirectAttribute객체를 사용하지 않아도 된다.