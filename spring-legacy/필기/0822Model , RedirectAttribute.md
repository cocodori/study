![](https://images.velog.io/images/cocodori/post/574c218e-bcea-49ae-a683-146ffaac6dda/Noun_48949_-_Delivery.svg.png)
# Model

Servlet은 request.setAttribute()로 데이터를 전달한다면,
스프링은 Model객체로 데이터를 전달한다.
>Controller
```java
@GetMapping("/testModel")
public void model(SampleDTO sampleDTO,Model model) {
	SampleDTO dto = new SampleDTO();
	dto.setName("cocokim");
	dto.setAge(123);
        //dto객체를 Model객체에 바인딩
        model.addAttribute("dto", dto);
}
```

> view
```html
<h1>Model Test</h1>
<h3>Model로 전달 받은 데이터 : ${dto }</h3>
<h4>name : ${dto.name }</h4>
<h4>age  : ${dto.age }</h4>
```

![](https://images.velog.io/images/cocodori/post/c936e7fc-58a8-4cad-8701-832bd3d75961/image.png)

## @ModelAttribute

@ModelAttribute는 전달 받은 파라미터를 강제로 Model에 담아서 화면으로 전달한다.
굳이 모델객체를 생성하지 않아도 그냥 전달한다.

한 가지 예제를 보자.

>Controller
```java
	@GetMapping("/testModel2")
	public void model2(SampleDTO dto, int page) {
		log.info(dto);
		log.info(page);
	}
```
인자로 SampleDTO객체와 page를 받게 되어 있다.

>view
```html
<h3>SampleDTO</h3>
<h4>name : ${sampleDTO.name}</h4>
<h4>age  : ${sampleDTO.age}</h4>
<h4>page : ${page}</h4>
<h4>test : ${test}</h4>
```

```testModel2?name=리박&age=1234&page=2020&test=아무%20말이나%20해봐```를 호출했을 때 결과는 어떨까?

```
INFO : com.coco.controller.SampleController - SampleDTO(name=리박, age=1234)
INFO : com.coco.controller.SampleController - 2020
INFO : com.coco.controller.SampleController - 아무 말이나 해봐
```

우선 서버에는 문제 없이 전달 됐다.
화면은?

![](https://images.velog.io/images/cocodori/post/7d82725d-34dc-40ae-90bf-398f6b4acf88/image.png)

DTO는 뷰까지 전달 됐지만, int와 String은 전달되지 않았다.

@ModelAttribute는 이럴 때 사용한다.

>Controller 메서드 선언부수정
```java
	@GetMapping("/testModel2")
	public void model2(SampleDTO dto, @ModelAttribute("page")int page, @ModelAttribute("test")String test)
```

다시 ```testModel2?name=리박&age=1234&page=2020&test=아무%20말이나%20해봐``` 호출

결과

![](https://images.velog.io/images/cocodori/post/604622ad-0edf-4049-b59c-c6e49555463b/image.png)

정상적으로 화면까지 전달된 것을 확인할 수 있다.


# RedirectAttribute

RedirectAttribute객체를 이용해서 메세지나 객체, 혹은 파라미터를 전달할 수 있다.
HttpServletResponse의 sendRedirect()를 대체한다고 볼 수 있다.

>컨트롤러
```java
@GetMapping("/rttr")
public void rttrTest() {
}
//
@GetMapping("/testRttr")
public String rttrTest(RedirectAttributes rttr) {
	//일회용, 화면으로 전달
	rttr.addFlashAttribute("msg", "다시 시도하세요.");
	//바인딩한 이름으로 파라미터 전달
	rttr.addAttribute("id","cocolog");
       	//'/sample/rttr'로 포워딩
	return "redirect:/sample/rttr";
}
```

testRttr.jsp는 껍데기므로 그냥 빈 파일이다.
>view
```html
<h1>RedirectAttribute</h1>
<h3>${msg }</h3>
```

이제 브라우저에서
```http://localhost:8080/sample/testRttr```을 호출하면

![](https://images.velog.io/images/cocodori/post/8dc64c0c-f2cf-4b53-864a-f8912fb05acc/image.png)

addFlashAttribute()로 전달한 데이터는 새로고침하면 사라진다.

![](https://images.velog.io/images/cocodori/post/cb432d97-ab5f-4b5a-a85a-baeb277128ac/image.png)

addAttribute()로 전달한 데이터는 여전히 파라미터로 남아있다.

