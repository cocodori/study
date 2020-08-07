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

<br>
<br>

**아무튼**

GET방식이든 POST방식이든. 어떤 식으로 요청request를 해오든
응답response라는 것은 웹 애플리케이션 화면을 구현하는 기능이다.

브라우저가 서블릿에게 어떤 정보를 요청한다면
서블릿은 요청에 알맞는 데이터, 그러니까 요청 받은 HTML문서를 반환한다.
# 비즈니스 로직

![](https://images.velog.io/images/cocodori/post/9e418cb5-797e-46c4-9b31-9ee1b947eecd/servlet.png)

> 클라이언트로부터 받은 요청을 처리하는 과정을 비즈니스 로직이라 한다.

이를테면 쇼핑몰에서 클라이언트가 1번 상품 정보를 요청한다. 그럼 서버는 데이터베이스에 1번 상품 정보를 조회한다. 조회 결과를 클라이언트에게 반환하기까지 수행하는 작업이 **비즈니스 로직**이다.

## 서블릿 - DB 연동 실습

MySQL

```sql
-- table 생성
CREATE TABLE `servletex`.`t_member` (
  `id` VARCHAR(30) NOT NULL,
  `pwd` VARCHAR(50) NOT NULL,
  `name` VARCHAR(50) NULL,
  `email` VARCHAR(50) NULL,
  `regdate` TIMESTAMP NOT NULL DEFAULT now(),
  PRIMARY KEY (`id`));

-- 데이터 추가
insert into t_member(id, pwd, name, email)
values('hong', '1234', '홍길동', 'hongil@google.com');
insert into t_member(id, pwd, name, email)
values('lee', '1234', '이순신', 'turtleship@google.com');
insert into t_member(id, pwd, name, email)
values('jung', '1234', '정약용', 'dasan@google.com');

-- 조회
select * from t_member;

결과 :
hong	1234	홍길동	hongil@google.com	2020-08-06 09:16:34
jung	1234	정약용	dasan@google.com	2020-08-06 09:16:40
lee	1234	이순신	turtleship@google.com	2020-08-06 09:16:37
```

## JDBC 설정
> **Java DataBace connectivity** <br>자바에서 데이터베이스에 접속할 수 있도록 하는 자바 API<br>데이터베이스에서 자료를 쿼리하거나, 업데이트 하는 방법(java.sql패키지)을 제공한다.(위키백과)<br>

![](https://images.velog.io/images/cocodori/post/8bc7ebf5-460b-4425-87d5-c2641b4a1f68/data-access-layer.png)

개발자들은 표준을 좋아한다. 오라클 SQL, Mysql, MariaDB 어떤 DB를 쓰든 똑같은 방식으로 서버에 연결하고 싶었을 것이다. 그래서 JDBC라는 표준 인터페이스를 만들었다. JDBC는 어떤 데이터베이스를 쓰든, 같은 방식으로 서버와 연결할 수 있다. 그때 필요한 것이 JDBC드라이버다. 그런 건 이미 벤더에서 다 만들어놨다. 사용자는 그저 다운받아서 쓰기만 하면 된다.  

JDBC로 구현하는 자바 연동 과정은 이렇다.

JDBC프로그램 - JDBC인터페이스 -JDBC드라이버 - DB

MySQL을 이미 설치했다고 가정하고, [mysql-connector-java.jar](https://dev.mysql.com/downloads/connector/j/#downloads) 파일이 추가로 필요하다.

MySQL을 설치할 때 같이 설치 되었을 수도 있으니 MySQL폴더를 확인해보자.

![](https://images.velog.io/images/cocodori/post/d3e47854-2c04-4a76-9c3b-6f82d4a563ad/1.png)

![](https://images.velog.io/images/cocodori/post/605de312-2e6b-41b2-87e3-e8dfcb279f9f/2.png)

![](https://images.velog.io/images/cocodori/post/9ad2744f-1b91-4087-9dc0-68ddb80dcaa3/3.png)

![](https://images.velog.io/images/cocodori/post/8394e88f-5bda-4212-a0e9-37f7598b3c45/4.png)

![](https://images.velog.io/images/cocodori/post/13fabf9f-87ae-4ef7-880a-b2e058ab74e9/5.png)

![](https://images.velog.io/images/cocodori/post/38c3ebed-5a7b-4722-befc-19ffb4aaea61/6.png)

**예제**
1. DB - Servlet 연결
2. select를 날려서 결과 값을 받아온다.
3. 결과를 화면에 출력한다.

항상 VO부터 만든다.
_**MemberVO.java**_
```java
package pro07.sec01.ex01;

import java.util.Date;

public class MemberVO {
	/* 반드시 Column이름과 같아야 한다.*/
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date regdate;

	//Getter & Setter 생략
	....
}
```

_**MemberDAO.java**_
```java
package pro07.sec01.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberDAO {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";	//DRIVER NAME
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/servletex?serverTimezone=Asia/Seoul";	//JDBC URL/스키마
	private static final String USER = "servlet"; //DB ID
	private static final String PWD = "1234";	  //DB PW
	
	private Connection con;
	private PreparedStatement pstmt;
	
	List<MemberVO> listMembers() {
		List<MemberVO> list = new ArrayList<>();

		try {
			connectDB();
			String sql = "select * from t_member";
			System.out.println("Query : " + sql);
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				/*
				 * select문을 날려서 받아올 칼럼들.
				 * getString(String columnLabel)으로 받아온다.
				 * */
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date regdate = rs.getDate("regdate");
				
				/*
				 * 받아온 데이터를
				 * MemberVO객체에 담는다.
				 * */
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setRegdate(regdate);
				
				list.add(vo);
			}
			//연결했던 반대순서로 닫는다.
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return list;
	} //listMembers()
	
	private void connectDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("DRIVER LOADING.....");
			con = DriverManager.getConnection(URL, USER, PWD);
			System.out.println("Connection 생성");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
```

_**MemberServlet.java**_
```java
package pro07.sec01.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberDAO {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";	//DRIVER NAME
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/servletex?serverTimezone=Asia/Seoul";	//JDBC URL/스키마?serverTimezone
	private static final String USER = "servlet"; //DB ID
	private static final String PWD = "1234";	  //DB PW
	private Connection con;
	private PreparedStatement pstmt;
	
	List<MemberVO> listMembers() {
		List<MemberVO> list = new ArrayList<>();

		try {
			connectDB();
			String sql = "select * from t_member";
			System.out.println("Query : " + sql);
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				/*
				 * select문을 날려서 받아올 칼럼들.
				 * getString(String columnLabel)으로 받아온다.
				 * */
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date regdate = rs.getDate("regdate");
				
				/*
				 * 받아온 데이터를
				 * MemberVO객체에 담는다.
				 * */
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setRegdate(regdate);
				
				list.add(vo);
			}
			//연결했던 반대순서로 닫는다.
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	} //listMembers()
	
	private void connectDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("DRIVER LOADING.....");
			con = DriverManager.getConnection(URL, USER, PWD);
			System.out.println("Connection 생성");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
```

DAO클래스 구현 순서

### 1.드라이버 클래스 로딩
JDBC드라이버 파일을 사용할 수 있도록 메모리에 로딩한다.
.jar파일을 빌드패스에 지정해주지 않았다면 ClassNotFoundException 발생

```java
Class.forName("com.mysql.cj.jdbc.Driver");
```

### 2.DBMS 서버 접속
```java
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/servletex?serverTimezone=Asia/Seoul";	//JDBC URL/스키마?serverTimezone
	private static final String USER = "servlet"; //DB ID
	private static final String PWD = "1234";	  //DB PW
	private Connection con;
...
...
	con = DriverManager.getConnection(URL, USER, PWD);

```

주의할 점은, Mysql의 경우 스키마 이름과 serverTimezone을 꼭 써줘야 한다.
DriverManager클래스의 getConnection()를 이용해서 URL, DB계정을 입력하여 연결한다.
- URL : 접속할 서버의 URL. 프로토콜, 서버주소, 서버포트, DB이름(스키마) + ?serverTimezone

이렇게까지 설정해주면 JAVA - DB의 연결이 끝난다.
이제 statement를 이용해서 SQL문을 전송하고 결과를 받아올 수 있다.

### 3.Statement
Statement와 PrepareStatement가 있다.
두 인터페이스 모두 SQL문을 전송하고, 받아오는 역할을 한다.

Statement는 정적쿼리만을 전송할 수 있다. 또한 실행할 때 매번 서버에서 분석해야 한다.
반면 PrepareStatement는 동적쿼리가 가능하고, PrepareStatement는 한 번만 분석하고 캐시에 저장해두기 때문에 성능상 더 좋다. 보통 PrepareStatement를 사용하는 것을 권장하는 듯하다.
[참고](https://all-record.tistory.com/79)

```java
	private PreparedStatement pstmt;
    ...
 	String sql = "select * from t_member";
    //SQL문을 전송
    pstmt = con.prepareStatement(sql);
```

### 4.ResultSet
```java

	
	ResultSet rs = pstmt.executeQuery();

	while(rs.next()) {
				/*
				 * select문을 날려서 받아올 칼럼들.
				 * getString(String columnLabel)으로 받아온다.
				 * */
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date regdate = rs.getDate("regdate");
		}
        //모두 닫아줘야 한다.
        rs.close();
		pstmt.close();
		con.close();

```

executeQuery()은 결과값을 가지고 있는 객체다.

> 참고 : [JDBC 프로그래밍](https://opentutorials.org/module/3569/21222)

# 커넥션 풀

> 웹 애플리케이션이 실행됨과 동시에 연동할 데이터베이스와 미리 연결 해둔다. 필요할 때마다 미리 연결해놓은 상태를 이용해 빠르게 데이터베이스 관련 작업을 할 수 있다. 요약하자면 미리 데이터베이스와 연결 시킨 상태를 유지하는 기술을 **커넥션 풀**이라 부른다.

 JDBC의 문제점은 오래 걸린다는 것이다.
 Connection Pool객체를 이용하면 미리 연결된 상태를 유지하므로 빠르게 작업할 수 있다.
 
 # 커넥션 풀 동작과정
 1. 톰캣 컨테이너 실행
 2. Connection pool 객체 생성
 3. 커넥션 객체 <-> DBMS 연결
 4. 데이터베이스와 연동 작업 시, ConnectionPool이 제공하는 메서드를 호출
 
 톰캣은 자체적으로 ConnectionPool을 제공한다.
 
 
# JNDI
 >Java Naming and Directory Interface<br>필요한 자원을 key-value로 저장해서 필요할 때 key를 이용해 value를 얻는 방법.
 
 즉 미리 접근할 자원에 키를 지정한 다음, 앱이 실행 중일 때 해당 키를 이용해 자원에 접근해서 작업하는 것이다.

 실제로 ConnectionPool객체를 구현할 때 javaSE가 제공하는 javax.sql.DataSource클래스를 이용한다. 그리고 톰캣이 만들어놓은 ConnectionPool객체에 접근할 때는 JNDI를 이용한다.
 
 JNDI 사용 예
 - 웹 브라우저 name / value 쌍으로 전송한 후 서블릿에서 getParameter()로 값을 가져올 때
 - HashMap에 key/value로 저장한 후 키를 이용해 값을 가져올 때
 
 
# 톰캣 DataSource 설정 및 사용
## 1.dbpc.jar파일 설치
- [jar파일](http://www.java2s.com/Code/Jar/t/Downloadtomcatdbcp7030jar.htm) 다운 받고 압축 푼다.

- 프로젝트 빌드패스에 jar파일을 추가한다
참고 : [이전 글](https://velog.io/@cocodori/JDBC)

![](https://images.velog.io/images/cocodori/post/78346478-7c33-4902-b2b3-c5c9d1be33c7/%EC%A3%BC%EC%84%9D%202020-08-06%20155519.png)


[이전 글](https://velog.io/@cocodori/JDBC) 에서 약간 코드를 수정했다.

```java
package pro07.sec01.ex01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	/* JDBC 설정
	 * private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; //DRIVER
	 * NAME private static final String URL =
	 * "jdbc:mysql://127.0.0.1:3306/servletex?serverTimezone=Asia/Seoul"; //JDBC
	 * URL/스키마 private static final String USER = "servlet"; //DB ID private static
	 * final String PWD = "1234"; //DB PW
	 */	
	
	private Connection con;
	private PreparedStatement pstmt;
	//DataSource설정
	private DataSource ds;
	
	public MemberDAO() {
		try {
			//JNDI에 접근하기 위해 기본 경로를 지정
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			//context.xml에 설정한 name값을 이용해 톰캣이 미리 연결한 DataSource를 받아온다.
			ds = (DataSource) envContext.lookup("jdbc/mysql");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	} //생성자
	
	List<MemberVO> listMembers() {
		List<MemberVO> list = new ArrayList<>();

		try {
//			connectDB(); //JDBC설정
			
			//DataSource를 이용해 데이터베이스에 연결한다.
			con = ds.getConnection();
			String sql = "select * from t_member";
			System.out.println("Query : " + sql);
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				/*
				 * select문을 날려서 받아올 칼럼들.
				 * getString(String columnLabel)으로 받아온다.
				 * */
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date regdate = rs.getDate("regdate");
				
				/*
				 * 받아온 데이터를
				 * MemberVO객체에 담는다.
				 * */
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setRegdate(regdate);
				
				list.add(vo);
			}
			//연결했던 반대순서로 닫는다.
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	} //listMembers()
	
	/* JDBC설정
	 * private void connectDB() { try { Class.forName("com.mysql.cj.jdbc.Driver");
	 * System.out.println("DRIVER LOADING....."); con =
	 * DriverManager.getConnection(URL, USER, PWD);
	 * System.out.println("Connection 생성");
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } }
	 */
}
```

결과는 같다. 다만 이번엔 커넥션풀을 이용해 DB와 연동했다는 점이 다르다.

----
# Forward

> 하나의 서블릿에서 다른 서블릿 또는 JSP와 연동하는 방법을 **포워드**라고 한다.

- 요청에 대한 추가 작업을 다른 서블릿에서 수행하게 한다.
- 요청에 포함된 정보를 다른 서블릿, JSP와 공유할 수 있다.
- 요청에 정보를 포함시켜 다른 서블릿에 전달할 수 있다.
- **Model2 개발 시 서블릿에서 JSP로 데이터를 전달하는 데 사용한다.**


# 네 가지 포워드
### 1.redirect
- HttpServletResponse객체의 sendRedirect() 이용
- 웹 브라우저에 재요청하는 방식

_예시_
```java
sendRedirect("/board/register");
```

### 2. Refresh
- HttpServletResponse의 addHeader() 이용
- 웹브라우저에 재요청

_예시_
```java
response.addHeader("Refresh",경과시간(초);url=요청할 서블릿/JSP");
```

### 3.location
- 자바스크립트 location객체의 href 속성 이용
- 자바스크립트에서 재요청하는 방식
_예시_
```javascript
location.href='/board/list';
```

### 4.dispatch
- 일반적으로 포워딩 기능을 지칭
- 서블릿이 직접 요청하는 방식
- RequestDispatcher클래스의 forward() 이용

_예시_
```java
RequestDispatcher dis = request.getRequestDispatcher("포워드 할 서블릿/JSP");
dis.forward(request,response);
```

위의 방법과 달리 서블릿에서 서블릿을 요청한다. 다른 방법은 URL을 통해 재요청 했다는 것을 확인할 수 있는 반면 dispatch방식은 서버가 포워드 했는지 하지 않았는지 클라이언트 쪽에서는 알 수 없다.

<br>
redirect, refresh, location은 서블릿이 브라우저를 거쳐 다른 서블릿, JSP에게 요청하는 방식이다. 반면 dispatcher는 서블릿에서 클라이언트를 거치지 않고 바로 다른 서블릿에게 요청하는 방법이다. 네 가지 모두 자주 사용하는 방식이다.

# 바인딩
> 웹 프로그램 실행 시 자원(데이터)을 서블릿 관련 객체에 저장하는 방법

GET방식으로 많은 데이터를 전달하기에 한계가 있다. 많은 양의 데이터를 전달할 때 **바인딩**binding을 사용한다.

그냥 key-value다.

```java
/*데이터를 각 객체에 바인딩한다.*/
setAttribute(String name, Object obj)

/*각 객체에 바인딩된 데이터를 name으로 가져온다.*/
getAttribute(String name)

/*각 객체에 바인딩된 데이터를 name으로 제거한다.*/
removeAttribute(String name)

```


_**예제**_

보내는 쪽
```java
@WebServlet("/bind")
public class BindingTestServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		request.setAttribute("address", "소행성 b-612");
		
		RequestDispatcher dis = request.getRequestDispatcher("/getBind");
		dis.forward(request, response);
	}
}
```

받는 쪽

```java
@WebServlet("/getBind")
public class GetBindingTestServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String address = (String)request.getAttribute("address");
		System.out.println("adress : " + address);
		out.print("<html><body><h1>나의 주소 : "+address+"</h1></body></html>");
	}
}
```

redirect가 아닌 RequestDispatcher를 이용한 이유는 redirect는 클라이언트를 거치기 때문에 중간에서 데이터가 손실된다. 반면 dispatcher는 다이렉트로 지정한 서블릿으로 가기 때문에 데이터를 유지한다.

이미지 출처 : https://www.oreilly.com/library/view/head-first-servlets/9780596516680/ch05s10.html

# ✔ javax.servlet.ServletContext

## 특징

- 서블릿 - 컨테이너 연동을 위해 사용한다.

- 컨텍스트(웹 애플리케이션)마다 하나의 ServletContext가 생성된다.

- 서블릿끼리 자원을 공유하는 데 사용한다.

- 컨테이너 실행 시 생성되고 종료 시 소멸한다.

## 제공하는 기능

- 서블릿에서 파일 접근

- 자원 바인딩

- 로그 파일

- 컨텍스트에서 제공하는 설정 정보 제공


## ServletContext의 메서드
``` java
/*주어진 name을 이용해 바인딩된 value를 가져온다.*/
getAttribute(String name)

/*바인딩된 속성들의 name을 반환한다.*/
getAttributeNames()

/*지정한 uripath에 해당하는 객체를 반환*/
getContext(String uripath)

/*name에 해당하는 매개변수의 초기화 값을 반환*/
getInitParameter(String name)

/*컨텍스트 초기화 관련 매개변수의 이름을 String객체가 저장된 Enumeration타입으로 반환*/
getInitParameterNames()

/*서블릿 컨테이너가 지원하는 주요 서블릿 API버전을 반환*/
getMajorVersion()

/*지정한 path에 해당하는 절대 경로를 반환*/
getRealPath(String path)

/*지정한 path에 해당하는 Resource를 반환*/
getResource(String path)

/*현재 서블릿이 실행되고 있는 컨테이너의 이름과 버전을 반환*/
getServerInfo()

/*해당 애플리케이션의 배치 관리자가 지정한 ServletContext에 대한 웹 애플리케이션 이름 반환*/
getServletContextName()

/*로그 파일에 로그 기록*/
log(String msg)

/*해당 name으로 ServletContext에 바인딩된 객체를 제거*/
removeAttribute(String name)

/*해당 name으로 객체를 ServletContext에 바인딩*/
setAttribute(String name, Object object)

/*주어진 name으로 value를 컨텍스트 초기화 매개변수로 설정*/
setInitParameter(String name, String value)
```

## 바인딩 사용


```java

List member = new ArrayList();
member.add("김공");
member.add(20);

//객체 생성
ServletContext context = getServletContext();

//바인딩
context.setAttribute("member", member);

//바인딩한 객체 꺼내기
List result = (ArrayList)context.getAttribute("member");
```

## 파라미터 설정

web.xml
```xml
<web-app...>
  ...
  ...
  <context-param>
  	<param-name>menu_member</param-name>
  	<param-value>회원등록 회원조회 회원수정</param-value>
  </context-param>
  <context-param>
  	<param-name>menu_order</param-name>
  	<param-value>주문조회 주문등록 주문수정 주문취소</param-value>
  </context-param>
    <context-param>
  	<param-name>menu_goods</param-name>
  	<param-value>상품조회 상품등록 상품수정 상품삭제</param-value>
  </context-param>
</web-app>
```

```java
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      request.setCharacterEncoding("utf-8");
	      response.setContentType("text/html;charset=utf-8");
	      
	      PrintWriter out = response.getWriter();
	      ServletContext context = getServletContext();
	      
          //xml에 context-name 설정해둔 이름으로 값을 가져온다.
	      String menu_member = context.getInitParameter("menu_member");
	      String menu_order = context.getInitParameter("menu_order");
	      String menu_goods = context.getInitParameter("menu_goods");
		
	      out.print("<html><body>");
	      out.print("<table border=1 cellspacing=0><tr>메뉴 이름</tr>");
	      out.print("<tr><td>" + menu_member + "</td></tr>");
	      out.print("<tr><td>" + menu_order + "</td></tr>");
	      out.print("<tr><td>" + menu_goods + "</td></tr>");
	      out.print("</tr></table></body></html>");	
	}
```

결과는 이렇다.

![](https://images.velog.io/images/cocodori/post/9eb7e18e-9150-4375-92c8-92ae299847c0/image.png)

# ✔ javax.servlet.ServletConfig

![](https://images.velog.io/images/cocodori/post/ad707a0d-0444-4df6-8db3-6c5f39e3c99a/servletContext.jpg)

같은 그림
> ServletConfig는 각 Servlet객체에 대해 생성된다.<br>ServletConfig인터페이스를 GenericSerlvet클래스가 실제로 구현한다.

## ServletConfig의 기능
- ServletContext 객체를 얻는다.
- 서블릿 초기화 작업
 @WebServlet 애너테이션과 web.xml설정하는 방법
 
### 애너테이션을 이용한 설정


|요소|설명|
|----|----|
|urlPatterns|웹 브라우저에서 서블릿 요청 시 사용하는 매핑 이름|
|name|서블릿 이름|
|loadOnStartUp|컨테이너 실행 시 서블릿이 로드되는 순서 지정|
|initParams|@WebInitParam애너테이션을 이용해 매개변수를 추가하는 기능|
|description|서블릿에 대한 설명|

```java
@WebServlet(name = "initParamServlet", urlPatterns = {"/sinit", "/sinit2"}
			, initParams = {
					@WebInitParam(name = "email", value="admin@web.com")
					,@WebInitParam(name="tel", value = "000-0000-0000")
			})
public class InitParamServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String email = getInitParameter("email");
		String tel = getInitParameter("tel");
		
		System.out.println("tel :" + tel);
		System.out.println("email : " + email);
	}
}

/*
결과
tel :000-0000-0000
email : admin@web.com
*/
```

### Web.xml을 이용한 설정
```xml
<web app>
   <init-param>
      <param-name>email</param-name>
      <param-value>admin@web.com</param-value>
   </init-param>
</web app>
```
잘 이용하지 않지만 이런 식으로 설정할 수 있다.

### load-on-startup
> 서블릿을 처음 구동하면 init()를 실행하여 메모리에 로드한다. 따라서 최초 요청 시 시간이 좀 더 길어질 수밖에 없다. 이것을 보완하는 기능이 **load-on-startup**이다.

#### 특징

- 톰캣 컨테이너가 실행되면서 미리 서블릿 실행
- 지정한 숫자가 0보다 크면 톰캣 컨테이너가 실행되면서 서블릿 초기화
- 지정한 숫자는 우선순위를 의미, 작은 숫자부터 먼저 초기화

#### 애너테이션을 이용한 구현
```java
@WebServlet(urlPatterns = "/load", loadOnStartup = 1)
public class LoadAppConfig extends HttpServlet {
	private ServletContext context;
	
	public void init(ServletConfig config) throws ServletException {
		System.out.println("초기화 시작");
		
		context = config.getServletContext();
		
		//.xml에 설정해둔 메뉴 정보 읽기
		String menu_member = context.getInitParameter("menu_member");
		String menu_order = context.getInitParameter("menu_order");
		String menu_goods = context.getInitParameter("menu_goods");
		
		//메뉴 정보 바인딩
		context.setAttribute("menu_member", menu_member);
		context.setAttribute("menu_order", menu_order);
		context.setAttribute("menu_goods", menu_goods);
		
		System.out.println("초기화 완료");
	}
}
```

```java
@WebServlet(urlPatterns = "/load", loadOnStartup = 1)
```
WebSerlvet 어노테이션에 loadOnStartup속성을 주면 톰캣이 실행되는 동시에 초기화 된다.

#### xml을 이용한 구현
```xml
<web-app .....>
  ...
  <servlet>
  	<servlet-name>loadConfig</servlet-name>
    	<servlet-class>패키지 + 클래스</servlet-class>
    	<load-on-startup>1</load-on-startup>
  </servlet>
  ...
</web-app>
```

servlet-name은 @WebServlet의 name속성과 같다.

# 세션 트래킹

![](https://images.velog.io/images/cocodori/post/52402f8a-7444-4d1a-af50-d81b9fc8d2d0/Untitled%20Diagram%20(2).jpg)

HTTP프로토콜 방식으로 통신하는 웹 페이지는 서로 어떤 정보도 공유하지 않는다.

> 쿠팡 메인 페이지에서 로그인하고 주문 페이지에서 또 다시 로그인하지 않아도 되는 이유는 **세션 트래킹Session Tracking**이라는 웹 페이지 연결기능을 구현했기 때문이다.

HTTP프로토콜은 서버-클라이언트 통신 시 **스테이트리스stateless방식**으로 통신한다. 브라우저에서 새 웹 페이지를 열면 이전 웹 페이지에 대한 어떤 정보도 새 웹페이지는 알 수 없다.
**stateless**란, 각각 웹 페이지 정보, 상태를 다른 웹페이지와 공유하지 않는 방식을 말한다.
따라서 웹 페이지를 서로 연결하기 위해 세션 트래킹을 이용해야 한다.

웹 페이지를 연동하는 방법은 몇 가지가 있다.
- hidden 태그
- URL Rewriting
GET방식으로 URL뒤에 정보를 붙이는 방식
- 쿠키
클라이언트 PC의 Cookie파일에 정보를 저장한 후 웹 페이지들이 공유한다.
- 세션
서버 메모리에 정보를 저장한 후 웹 페이지들이 공유한다.

## 쿠키를 이용한 연동
>**쿠키Cookie**는 웹 페이지들끼리 공유하는 정보를 클라이언트 PC에 저장해두고, 필요할 때 사용할 수 있도록 매개하는 역할을 한다.

#### 쿠키의 특징
- 정보가 클라이언트 PC에 저장
- 저장 정보 용량 제한
- 보안 취약
- 클라이언트 브라우저에서 사용 유무 설정 가능
- 도메인당 쿠키가 만들어진다.

#### 쿠키의 종류
|속성|Persistence쿠키|Session쿠키|
|----|------------|-----------|
|생성 위치|파일로 생성|브라우저 메모리에 생성|
|종료 시기|쿠키를 삭제하거나 쿠키 설정 값이 종료된 경우|브라우저를 종료한 경우|
|최초 접속 시 전송 여부|최초 접속 시 서버로 전송|최초 접속 시 서버로 전송되지 않음|
|용도|로그인 유무 또는 팝업창 제한|사이트 접속 시 Session인증 정보 유지할 때|

Persistence쿠키는 파일로 정보를 저장한다. 파일로 생성된 쿠키는 사용자가 만료 기한을 정할 수 있다. 반면 Session쿠키는 브라우저가 사용하는 메모리에 생성된다. 따라서 브라우저를 종료하면 Session쿠키도 함께 소멸한다.

## 쿠키 생성 과정
1. 브라우저로 사이트에 접속한다.
2. 서버는 정보를 저장한 쿠키를 생성한다.
3. 생성한 쿠키를 브라우저로 전송한다.
4. 브라우저는 서버로부터 받은 쿠키를 파일에 저장한다.
5. 재접속 시, 서버는 브라우저에게 쿠키를 요청하고, 브라우저는 서버로 쿠키를 보낸다.
6. 서버는 쿠키를 이용해서 작업한다.

## javax.servlet.http.Cookie

서블릿에서 이용할 수 있는 쿠키 API다. 
- HttpServbletResponse의 addCookie()를 이용해서 클라이언트 브라우저로 쿠키를 전송한다.
- HttpServletRequest의 getCookie()를 이용해서 쿠키를 서버로 가져온다.

### Cookie클래스의 메서드


|Method|설명|
|------|----|
|getComment()|쿠키에 대한 설명을 가져온다|
|getDomain()|쿠키의 유효한 도메인 정보를 가져온다.|
|getMaxAge()|쿠키 유효 기간을 가져온다.|
|getName()|쿠키 이름을 가져온다.|
|getPath()|쿠키의 디렉터리 정보를 가져온다.|
|getValue()|쿠키의 설정 값을 가져온다.|
|setComment(String comment)|쿠키에 대한 설명을 설정|
|setDomain(String domain)|쿠키의 유효한 도메인을 설정|
|setMaxAge(int expiry)|쿠키 유효기간 설정|
|setValue(String value)|쿠키 값 설정|

setMaxAge()를 사용하지 않거나 인자가 음수일 경우 Session쿠키 그 외는 Persistence쿠키다.


## Persistence Cookie Test
SetCookieValue.java
```java
@WebServlet("/scook")
public class SetCookieValue extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Date date = new Date();
		
		//쿠키 객체를 생성한 후, cookTest라는 이름으로 한글 정보를 인코딩해서 쿠키에 저장한다.
		Cookie cookie = new Cookie("cookieTest", URLEncoder.encode("JSP PROGRAMMING", "utf-8"));
		
		cookie.setMaxAge(24*60*60); //유효기간을 1일로 한다.

		//응답에 쿠키를 포함한다.
		response.addCookie(cookie);
		
		out.println("현재 시간 : " + date);
		out.print("현재시간을 쿠키로 저장한다.");
	}
}
```
GetCookieValue.java
```java
@WebServlet("/gcook")
public class GetCookieValue extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//request의 getCookie()로 요청한 쿠키 정보를 배열로 받는다.
		Cookie[] allValues = request.getCookies();
		
		//
		for(int i=0; i<allValues.length; i++) {
			if(allValues[i].getName().equals("cookieTest")) {
				out.println("<h2>Cookie Value : "+ URLDecoder.decode(allValues[i].getValue(),"utf-8")+"</h2> ");
			}
		}
	}
}
```

<br>**브라우저에서 /scook 호출**

![](https://images.velog.io/images/cocodori/post/a5ab8469-8763-4f41-8d82-6d435660b605/setCook1.png)

F12 개발자 도구를 열어서 Application탭에서 쿠키가 생성되었다는 것을 확인한다.

**/gcook 호출**

![](https://images.velog.io/images/cocodori/post/c1601b40-2c90-447b-b2bd-6a5e451eeef4/getCook.png)

저장된 cookie를 HttpServletRequest의 getCookie()로 불러와서 읽었다.
쿠키 이름, 값, 유효기간까지 잘 유지 되어 전달 받은 것을 확인한다.

만약 쿠키를 지운다면?
![](https://images.velog.io/images/cocodori/post/2737bb0c-68f2-4ffd-ad22-b1185cbb7c50/3.png)
![](https://images.velog.io/images/cocodori/post/cc590040-47fe-4c1e-b2d2-e3a8e67a2814/4.png)

읽어들일 Cookie가 없으므로 NullPointException이 발생한다
500번대 에러는 서버에서 예외가 발생했을 때 나는 에러다.

위 예제는 setMaxSize()를 설정했고, 음수가 아니므로 Persistence쿠키다.

## Session Cookie Test

SetCookieValue.java에서 설정해준 setMaxSize()의 인자를 음수로 바꾸거나, 지우면 Session Cookie가 된다.

![](https://images.velog.io/images/cocodori/post/f1993d0a-2ad7-4454-81c8-88af7accce9e/image.png)

setMaxSize()를 지우고 다시 확인한 결과다. 'Session'이라고 날짜가 바뀌었다.

## 자바스크립트로 팝업창 제한하기

popupTest.html
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JS로 쿠키 제어하기</title>
</head>
<body>
	<form>
		<input type='button' value='쿠키 삭제' onclick="deleteCookie()">
	</form>
<script>

window.onload = () => {
	//notShowPop의 쿠키 값을 getCookieValue()를 호출하여 얻는다.
	notShowPop = getCookieValue();
	
	console.log(getCookieValue());
	console.log(typeof notShowPop);
	console.log(notShowPop !== 'true')
	
	
	//notShowPop이 true가 아니면 팝업창을 나타낸다.
	if(notShowPop !== 'true') {
		window.open("popUp.html","pop","width=400,height=500,history=no,"
				+"resizable=no,status=no,scrollbars=yes,menubar=no");
	}
} 

function getCookieValue() {
	var result = 'false';
	if(document.cookie!== '') {
		//document의 cookie속성으로 쿠키 정보를 문자열로 가져온 후 ';'를 구분자로하여 각각 쿠키를 얻는다.
		cookie = document.cookie.split(';');
		
		for(var i=0; i<cookie.length;i++) {
			element = cookie[i].split('=');
			value=element[0];
			//정규식을 이용해 쿠키 이름 문자열의 공백을 제거
			value=value.replace(/^\s*/,'');
			
			if(value==='notShowPop') {
				result = element[1];
			}
		} //for
	} // if
	return result;
} //getCookieValue()

//쿠키 삭제 클릭 시 호출. notShowPop 쿠키 값을 false로 설정.
function deleteCookie() {
	document.cookie = 'notShowPop='+'false'+';path=/; expires=-1';
}
</script>	
	
</body>
</html>
```

popUp.html
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>알림 팝업창</h1>
<br><br>
<form>
	<input type='checkbox' onClick='setPopUpStart(this)'>오늘 더 이상 팝업창 띄우지 않기
</form>

<script>
	function setPopUpStart(obj) {
		if(obj.checked === true) {
			var expireDate = new Date();
			//쿠키의 유효 기간을 한 달로 설정한다.
			expireDate.setMonth(expireDate.getMonth() +1);
			//체크하면 notShowPop쿠키 값을 true로 설정하여 재접속 시 팝업창을 나타내지 않는다.
			document.cookie='notShowPop='+'true'+';path=/; expires='+expireDate.toGMTString();
			
			window.close();
		} 
	}
</script>
</body>
</html>
```

![](https://images.velog.io/images/cocodori/post/6bf8db42-7b42-43ea-b1c1-8793f8c35c2d/1.png)

/popupTest를 호출하면 이렇게 팝업창이 나온다.
1.현재는 Value가 false다.
2.체크박스를 체크하고, 새로 고침한다.

![](https://images.velog.io/images/cocodori/post/cc7c39c5-a54c-4ea2-9f35-54bb56c3456b/2.png)

Value가 true로 바뀌면서 팝업창이 뜨지 않는 것을 확인한다.

# Session
>** 웹 페이지들 사이에서 공유하는 정보를 서버에 저장해두고, 웹 페이지들을 매개한다**는 점에서 세션도 쿠키와 다르지 않다. 다른 점이라면 쿠키는 클라이언트 PC에 저장되고, **세션은 서버 메모리에 저장된다**는 점이다. 쿠키에 비해 **보안이 좋기 때문에 로그인처럼 보안을 요구하는 데이터를 다룰 때 세션을 이용**한다. 세션은 브라우저 당 하나가 생성된다.

## 세션의 특징
- 데이터를 서버 메모리에 저장한다.
- 블아줘의 세션 연동은 쿠키를 이용한다.
- 쿠키보다 보안에 유리하다.
- 서버에 부하를 줄 수 있다.
- 브라우저 당 하나의 세션SessionID이 생성된다.
- 세션은 유효시간을 가진다.(기본 30분)
- 로그인 유지, 장바구니 등에 주로 사용한다.

## 세션 생성 과정

1. 브라우저로 사이트에 접속한다.
2. 서버는 접속한 브라우저에 대한 세션 객체를 생성한다.
3. 서버는 생성한 세션 ID를 클라이언트 브라우저로 보낸다.(response)
4. 브라우저는 서버에서 받은 세션ID를 브라우저가 사용하는 메모리의 세션 쿠키에 저장한다.
쿠키 이름은 JSESSIONID
5. 브라우저가 재접속하면 브라우저는 세션쿠키에 저장한 세션id를 서버에 전달한다.
6. 서버는 전송받은 세션ID를 이용해 해당 세션에 접근하여 작업한다.

세션의 중요한 특징은 브라우저 당 하나씩 생성된다는 것이다. 브라우저가 서버에 JSESSIONID를 전송하면 서버는 그 값을 이용해서 브라우저를 구분한다.

## 세션API
>HttpSessoin

서블릿은 HttpSession클래스를 이용해서 세션을 다룬다. HttpSession객체는 HttpServletRequest의 getSession()를 호출해서 생성한다.
- getSession()
기존 세션 객체가 존재하면 반환, 없으면 생성
- getSession(true):기존 세션 객체가 존재하면 반환, 없으면 생성
- getSession(false):기존 세션 객체가 존재하면 반환, 없으면 null

### HttpSession클래스의 메서드


|메서드|설명|
|-----|----|
|Object getAttribute(String name)|지정한 이름을 가진 속성 값을 반환|
|Enumeration getAttributeNames()|세션 속성 이름들을 Enumeration객체 타입으로 반환|
|long getCreationTime()|1970년 1월 1일 0시 0초를 기준으로 현재 세션이 생성된 시간까지 경과한 시간을 계산하여 1/1000값으로 반환|
|String getId()|세션에 할당된 고유 식별자를 반환|
|int getMaxInactiveInterval()|현재 생성된 세션을 유지하기 위해 설정한 세션 유지시간을 int타입으로 변환|
|void invalidate()|현재 생성된 세션을 소멸|
|boolean isNew()|최초로 생성한 세션인지 기존에 생성된 세션인지 판별|
|void removeAttribute(String name)|세션 속성 이름이 name인 속성을 제거|
|void setAttribute(String name, Object value)|세션 속성 이름이 name인 속성에 속성 값으로 value를 할당한다.|
|void setMaxInactiveInterval(int interval)|세션을 유지하기 위한 세션 유지 시간을 초 단위로 설정|

<br>

## HttpSession으로 세션 다루기
```java
@WebServlet("/st")
public class SessionTest extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//HttpSession객체 생성
		HttpSession session = request.getSession();
	
		out.println("세션 아이디 :" + session.getId()+"<br>");
		out.println("세션 생성 시간 : " + new Date(session.getCreationTime())+"<br>");
		out.println("최근 세션 접근 시각  : " + new Date(session.getLastAccessedTime())+ "<br>");
		//세션의 유효기간을 5초로 한다.
		session.setMaxInactiveInterval(5);
		out.println("세션 유효 시간 : " + session.getMaxInactiveInterval() +"<br>");
		
		if (session.isNew()) {
			out.print("새 세션이 만들어졌습니다.");
		}
	}
}
```
유효기간 5초짜리 세션이다.

![](https://images.velog.io/images/cocodori/post/748af147-9bb7-41bc-96c6-27a7b1c5fa35/s1.png)

5초 뒤에 새로고침하면 새로운 세션이 생성된다.

DED7A38FD66953BF141A0EDA6231924C

2E9DB673F46CF57873B7377ED4F42C63

## 세션 로그인
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<form name="login" method="post" action="/st2">
아이디 : <input type="text" name="id"><br>
비밀번호:<input type="password" name="password">
<button>확인</button>
</form>
</body>
</html>
```

```java
@WebServlet(name = "SessionTest", urlPatterns = { "/st2" })
public class SessionTest extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//세션 객체 생성
		HttpSession session = request.getSession();
		//id, pw값을 받아온다.
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		
		System.out.println("id : " + id);
		System.out.println("pw : " + pw);
		
		if (session.isNew()){ //새 세션이라면,
			if(id != null){ //로그인 상태라면,
				session.setAttribute("id", id);	//세션에 id라는 이름으로 id를 바인딩한다.
				out.println("<a href='st2'>로그인 상태 확인</a>");	//다시 st2로 들어온다.
			}else {
				out.print("<a href='login.html'>다시 로그인 하세요!!</a>"); //로그아웃 상태라면 다시 로그인 창으로 돌려보낸다.
				session.invalidate();
			}
			
		} else { // 새 세션이 아닐 때 들어온다.
			id = (String) session.getAttribute("id"); //세션에 id라고 바인딩된 값을 받아온다.
			if (id != null && id.length() != 0) {	//해당 id가 있을 경우,
				out.print("안녕하세요 " + id + "님!!!");
			} else { //없다면 session을 지우고 login창으로 돌려보낸다.
				out.print("<a href='login2.html'>다시 로그인 하세요!!</a>");
				session.invalidate();
			}
		}
	}
}
```
### 1. 로그인

![](https://images.velog.io/images/cocodori/post/0c65a2bd-4721-48bc-9a50-4233d90e9aed/l1.png)


### 2.JSESSIONID 생성

![](https://images.velog.io/images/cocodori/post/54f4a16c-17fb-4130-b555-26208929d349/l2.png)

로그인을 하면 JSESSIONID가 발급된다.
내부적으로는 
```java
if (session.isNew()){ //새 세션이라면,
	if(id != null){ //로그인 상태라면,
	session.setAttribute("id", id);	//세션에 id라는 이름으로 id를 바인딩한다.
	out.println("<a href='st2'>로그인 상태 확인</a>");	//다시 st2로 들어온다.
}else {
	out.print("<a href='login.html'>다시 로그인 하세요!!</a>"); //로그아웃 상태라면 다시 로그인 창으로 돌려보낸다.
	session.invalidate();
}
```

이 단계다. 새로 생성된 세션이 맞고, 로그인 상태이므로, 세션에 id를 바인딩한다.
그리고 '로그인 상태 확인'을 누르면,

### 3. JSESSIONID에 바인딩된 값 받아오기

![](https://images.velog.io/images/cocodori/post/38acfcc9-25b1-4803-b2d5-833485bea36e/l3.png)

내부적으로는
```java
else { // 새 세션이 아닐 때 들어온다.
	id = (String) session.getAttribute("id"); //세션에 id라고 바인딩된 값을 받아온다.
	if (id != null && id.length() != 0) {	//해당 id가 있을 경우,
	out.print("안녕하세요 " + id + "님!!!");
	}
```

이 부분이다. session에 id라는 이름으로 바인딩된 객체를 꺼내서 화면에 출력한다. 



