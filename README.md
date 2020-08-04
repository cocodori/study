# servlet
> 📚 [자바 웹을 다루는 기술](http://www.yes24.com/Product/Goods/68371015?OzSrank=1) 을 정리한 내용입니다.

> 📚 [자바 웹을 다루는 기술](http://www.yes24.com/Product/Goods/68371015?OzSrank=1)을 정리한 내용입니다.

# Servlet이란?

이전에 정적인 웹을 너머서 동적 웹 프로그래밍을 위한 자바 클래스다.

> 클라이언트 요청에 따라 서버에서 동적으로 서비스를 제공하는 클래스


 서블릿은 일반적인 자바와 달리 톰캣과 같은 JSP/Servlet 컨테이너에서 실행된다.
 
 ![](https://images.velog.io/images/cocodori/post/cbb7f393-a680-478d-b62b-5c783dbac1d2/Untitled%20Diagram.jpg)
 서블릿의 동작 과정이다.
 
 클라이언트가 요청하면 web server는 WAS에 위임한다. WAS는 각 요청에 해당하는 서블릿을 실행한 다음 결과를 반환하여 클라이언트에게 전송한다.
 
 특징
 - 서버 쪽에서 실행되면서 기능 수행
 - 정적 웹 문제를 보완하여 동적 기능 제공
 - 쓰레드 방식으로 실행
 - 컨테이너에서 실행
 
 # Servlet API 계층 구조
 ![](https://images.velog.io/images/cocodori/post/7d94dbf2-a81d-45e7-8dc6-6951666672a9/Untitled%20Diagram%20(1).jpg)
 
두 인터페이스 Servlet, ServletConfig를 추상클래스 GenericServlet가 구현한다. 그리고 GenericServlet을 상속하는 HttpServlet이 있다.

- Servlet, ServletConfig
Servlet관련 추상 메서드가 선언 되어 있다.

- GenericServlet 클래스
위의 두 인터페이스를 구현하여 일반적인 서블릿 기능을 구현한 클래스.
이 클래스를 구현한다면 프로토콜에 따라 service() 오버라이딩 필요
- HttpServlet 클래스
HTTP프로토콜을 사용하는 웹브라우저에서 서블릿 기능 수행
웹 브라우저 기반 서비스를 제공하는 서블릿 만들 때 상속 받아 사용
요청 시 service()가 호출되면서 방식에 따라 doGet() 혹은 doPost()가 차례로 호출 됨

**HttpServlet의 메서드**
```java
//삭제 요청 수행
protected doDelete(HttpServletRequest req, HttpServletResponse resp);

//GET 요청 수행
protected doGet(HttpServletRequest req, HttpServletResponse resp);

//HEAD 요청 수행
protected doHead(HttpServletRequest req, HttpServletResponse resp);

//POST 요청 수행
protected doPost(HttpServletRequest req, HttpServletResponse resp);

//표준 HTTP request를 public service()에서 전달 받아 doXXX()호출
protected service(HttpServletRequest req, HttpServletResponse resp);

//클라이언트의 request를 protected service()에게 전달
public service (HttpServletRequest req, HttpServletResponse resp)
```

과정은 public service() -> protected service() -> doXXX()

# 서블릿 생명주기Servlet Lifecycle

서블릿도 마찬가지로 초기화를 거쳐 메모리가 인스턴스에 올라가고, 작업을 마치면 소멸한다.
이 단계를 거칠 때마다 서블릿 클래스의 메서드가 호출되어 초기화, DB연동, 마무리 작업을 수행한다. 각 과정에서 호출되어 기능을 수행하는 메서드들이 서블릿 생명주기 메서드다.

말하자면 실행 단계마다 호출되는 (콜백)메서드를 말한다.

|생명주기 단계|호출 메서드|특징|
|----------|-----------|--------|
|초기화|init()|- 서블릿 요청 시 맨 처음 한 번만 호출<br>- 서블릿 생성 시 초기화 작업 수행|
|작업 수행 |doGet()<br>doPost()| - 서블릿 요청 시 매번 호출<br>- 실제로 클라이언트가 요청하는 작업 수행|
|종료|destroy()|- 서블릿이 기능을 수행하고 메모리에서 소멸될 때 호출<br>- 서블릿의 마무리 작업 수행|

상황에 따라 destroy()는 생략할 수 있지만, doGet()이나 doPost()는 핵심기능을 처리하므로 반드시 구현해야 한다.

테스트
 
```java
package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/first")
public class FirstServlet extends HttpServlet {
	@Override
	public void init() throws ServletException {
		System.out.println("init.......");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet().............");
	}
	
}

/*
doGet() 4회 호출

init.......
doGet().............
doGet().............
doGet().............
doGet().............

여러 번 호출해도 init()은 한 번만 실행되는 것을 확인할 수 있다.
*/

```

----

> 📚 [자바 웹을 다루는 기술](http://www.yes24.com/Product/Goods/68371015?OzSrank=1)을 정리한 내용입니다.

# 서블릿의 기본 기능

톰캣과 같은 Web Application Server(이하 WAS)가 처음 나왔을 때 웹 브라우저 요청을 쓰레드 방식으로 처리하는 기술이 서블릿이었다. 서블릿은 자바로 웹 프로그래밍을 하는 데 있어서 가장 기초가 되는 내용이다.

- 서블릿 기본 기능 수행 과정
![](https://images.velog.io/images/cocodori/post/0725451b-ea4d-4067-95cd-145b0f0eb411/Untitled%20Diagram.png)

1. 클라이언트로 요청을 받는다.
2. 데이터 베이스와 연동 하여 비즈니스 로직을 처리한다.
3. 처리된 결과를 다시 클라이언트에게 돌려준다.


## HttpServletRequest & HttpServletReponse

- HttpServletRequest
요청을 돕기 위한 클래스

- HttpServletResponse
응답을 돕기 위한 클래스


### 요청과 응답

login.html
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<form name="login" method="post" action="login">
아이디 : <input type="text" name="id"><br>
비밀번호:<input type="password" name="password">
<button>확인</button>
</form>
</body>
</html>
```

LoginServlet.java
```java
package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	@Override
	public void init() throws ServletException {
		//한 번만 실행
		System.out.println("init()........");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet().........");
		
		//doGet을 호출하면 login.html을 띄운다.
		response.sendRedirect("/login.html");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//전송 받은 데이터를 UTF-8로 인코딩
		request.setCharacterEncoding("utf-8");
		
		//getParameter()는 input tag의 name을 입력하면 value값을 받아올 수 있다.
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		
		System.out.println("아이디 : " + id);
		System.out.println("비밀번호 : " + pw);
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy().......");
	}

}
/*
결과 
init()........
doGet().........
아이디 : cocoLee
비밀번호 : 1234
*/
```

### 여러 값 전송할 때 요청 처리

login.html
```html
<!-- 변경된 부분-->
<form name="login" method="post" action="login">
아이디 : <input type="text" name="id"><br>
비밀번호:<input type="password" name="password">
<input type="checkbox" name="subject" value="java" checked> Java
<input type="checkbox" name="subject" value="C언어"> C
<input type="checkbox" name="subject" value="JSP"> jsp
<input type="checkbox" name="subject" value="Android"> Android
<button>확인</button>
```

LoginServlet.java 변경된 부분

```java
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//전송 받은 데이터를 UTF-8로 인코딩
	request.setCharacterEncoding("utf-8");
		
	//하나의 name으로 여러 값을 전송하는 경우 getParameterValues()를 이용해 배열로 반환한다.
	String[] subject = request.getParameterValues("subject");
		
	//하나씩 전송된 값은 getParameter()를 이용한다
	String id = request.getParameter("id");
	String pw = request.getParameter("password");
	
	System.out.println("아이디 : " + id);
	System.out.println("비밀번호 : " + pw);
	System.out.println("subject : " + Arrays.toString(subject));
}

/*
결과
init()........
아이디 : cocoLee
비밀번호 : 1234
subject : [java, JSP]
*/
```

# 서블릿 응답처리 방법

1. doGet(), doPost() 안에서 처리
2. javax.servlet.http.HttpServletResponse객체 이용
3. setContentType()을 이용해 MIME-TYPE 지정
4. 클라이언트와 서블릿 통신은 자바 I/O의 스트림을 이용

## MIME-TYPE

![](https://images.velog.io/images/cocodori/post/7ad63d42-11bc-4c92-85d5-8c92abf1390f/ex.jpg)

웹은 기본적으로 I/O다. 웹 브라우저가 네트워크를 통해 서블릿 데이터를 보내는 경우, 서블릿은 네트워크로부터 데이터를 입력 받는다. 반대로 서블릿이 웹 브라우저로 데이터를 전송하는 경우, 서블릿은 네트워크로 데이터를 출력한다.
 서버(서블릿)에서 브라우저로 데이터를 전송할 때, 어떤 종류의 데이터를 전송하는지 브라우저에게 알려줘야 한다. 그게 이미지인지, 텍스트인지 오디오인지 브라우저가 알고 있어야 더 빠르게 처리할 수 있다. 서블릿은 톰캣 컨테이너에서 제공하는 데이터 종류 중 하나를 지정해서 브라우저로 전송한다. 이처럼 미리 설정해놓은 데이터 종류를 MIME-TYPE이라고 한다.
 - text/html
 HTML로 전송할 때
 
 - text/plain
 일반 텍스트로 전송할 때
 
 - application/xml
 XML 데이터로 전송할 때
 
 
 ## Response과정
 
 1. setContentType()으로 MIME-TYPE 지정
 2. 데이터를 출력할 PrintWriter객체 생성
 3. 출력 데이터 HTML형식으로 만들기
 4. PrintWriter의 print()나 println()을 이용해 데이터 출력

login.html body
```html
<body>
<form name="login" method="get" action="login2">
아이디 : <input type="text" name="id"><br>
비밀번호:<input type="password" name="password">
<button>확인</button>
</form>
</body>
```
 
 LoginServlet2.java
 
 ```java
 package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login2")
public class LoginServlet2 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩 설정
		request.setCharacterEncoding("utf-8");
		//MIME-TYPE설정 응답할 데이터 종류가 html임을 미리 알림.
		response.setContentType("text/html;charset=utf-8");
		//HttpServletResponse객체의 getWriter()를 이용해
                 //출력 스트림 PrintWriter 객체를 받아온다.
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		
		String data = "<html>";
		data += "<body>";
		data += "아이디 : " +id;
		data += "<br>";
		data += "비밀번호 : " + pw;
		data += "</body>";
		data += "</html>";
		
		out.print(data);
	}

}
 ```

 ![](https://images.velog.io/images/cocodori/post/8e81a6d4-1109-496e-8d53-476e6a5baf7b/image.png)
 
 위와 같은 결과를 브라우저에서 확인할 수 있다.
 
 
 > 📚 [자바 웹을 다루는 기술](http://www.yes24.com/Product/Goods/68371015?OzSrank=1)을 정리한 내용입니다.

# GET 방식

- 서블릿에 데이터를 전송할 때 데이터가 URL 뒤에 따라 붙는다.

- 여러 개의 데이터를 전송할 때는 '&'로 구분한다.

- 보안 취약

- 전송할 수 있는 데이터는 최대 255자

- 기본 전송 방식이며 사용이 쉽다.

- 웹 브라우저에 직접 입력해서 전송할 수도 있다.

- 서블릿에서는 doGet()으로 전송 받은 데이터를 처리한다.

# POST 방식
- 서블릿에 데이터를 전송할 때는 TCP/IP 프로토콜 데이터의 HEAD영역에 숨겨진 채 전송된다.

- 보안에 유리하다.

- 전송 데이터 용량이 무제한.

- 전송 시 서블릿에서 다시 가져오는 작업을 해야 하므로 속도가 GET보다 느리다.

- 서블릿에서는 doPost()를 이용해 처리한다.

