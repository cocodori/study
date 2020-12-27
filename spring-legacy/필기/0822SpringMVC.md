# 스프링 MVC

![](https://images.velog.io/images/cocodori/post/d30923c3-907a-43ba-8790-ec5930b413f2/Untitled%20Diagram%20(6).jpg)

<center><a href='https://docs.spring.io/spring/docs/4.2.0.RELEASE/spring-framework-reference/html/overview.html'>이미지 출처</a></center>


스프링의 목적은 웹이 아니다.
위에 보이는 것처럼 스프링 웹은 스프링의 기능 중 하나일 뿐이다.

스프링 MVC 프로젝트를 생성하면, 자바 영역(POJO)과 웹 영역을 연동해서 프로젝트를 구동하게 된다.
웹 영역이 MVC설정을 포함하고 있다.

## 스프링 MVC의 사상

스프링 MVC 이전까지는 Servlet/JSP API를 이용했다. HttpServletRequest/Response 같은 클래스를 이용해서 브라우저와 서버 간 데이터를 주고 받았다. 이런 부분을 내부적으로 처리하고, 개발자는 자바 코드로 개발에만 몰두할 수 있도록 하자는 생각에서 탄생한 것이 스프링 MVC다. 

## 스프링 MVC 구조


![](https://images.velog.io/images/cocodori/post/6eb41843-b440-4739-98be-ddcba7356e27/spring-mvc-flow.png)
<center><a href='http://javawebtutor.com/articles/spring/spring-mvc-tutorial.php'>이미지 출처</a></center>

모든 요청은 DispatcherServlet(web.xml)으로 들어온다.
HandlerMapping은 쉽게 말해서 @RequestMapping이 적용된 것을 보고 어떤 컨트롤러로 가야 하는지 찾는 것이다.
Controller는 실제 요청에 대한 처리를 진행한다.
View로 보내기 전, View Resolver는 어떤 view를 통해서 처리할 것인지 해석한다. servlet-context.xml에 정의되어 있다.

- 모든 요청은 DispatcherServlet을 거친다. 이것을 Front-Controller패턴이라고 한다. Front-Controller패턴은 흐름을 제어한다. 이럴 경우, 요청을 처리할 때 정해진 방식대로 동작하기 때문에 엄격한 구조를 만들 수 있다.


## MVC 구성

웹은 기본적으로 3티어 방식으로 구성된다. 스프링MVC도 마찬가지다.

![](https://images.velog.io/images/cocodori/post/b9c1d872-f964-4ebb-9538-54e5db139723/15545765386427_Screen%20Shot%202019-04-06%20at%205.29.27%20pm.png)

### Presentation Layer
스프링 MVC가 담당하는 영역이다. 실질적으로 사용자가 보는 화면을 구성한다. CS(client-server)로 구성하지만, 앱으로 제작할 수도 있다.

### Business Layer
스프링코어의 영역이다. 요청을 처리하는 로직을 담당한다. 보통 Service라는 이름으로 만든다.

### Data Layer(Persistence Layer)
데이터 계층 혹은 영속 계층이라고 한다. 데이터를 어떻게 보관하고 사용할 것인지를 설계한다.

<hr>

- Controller
  - 스프링 MVC에서 동작하는 클래스를 설계할 때 사용한다.
  
- Service
  - 비즈니스 영역을 담당한다. 인터페이스를 만들고 그것을 구현하는 형태로 만든다.
  
  
- DAO, Repository
  - DAO(Data Access Object)나 Repository라는 이름으로 구성한다. DAO를 구성하는 대신 MyBatis의 Mapper인터페이스로 대체할 수 있다.

- VO, DTO
  - VO와 DTO는 데이터를 담는 객체를 의미한다. VO는 주로 ReadOnly의 목적이 강하다. 데이터도 불변하게 설계하는 것이 정석. 반면 DTO는 데이터를 수집하는 용도가 강하다. 화면에서 데이터를 수집해야 할 때 DTO를 이용한다.