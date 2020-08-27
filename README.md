# ✏ Spring Study

> [코드로 배우는 스프링 웹 프로젝트](http://www.yes24.com/Product/Goods/64340061)를 참고하여 공부한 내용을 정리한 저장소입니다.

필기한 내용은 ['필기'](https://github.com/cocodori/SpringEx/tree/master/%ED%95%84%EA%B8%B0) 폴더에서 확인할 수 있습니다.
[블로그](https://velog.io/@cocodori/series/Spring)에서도 확인할 수 있습니다.

# 스프링이라는 프레임 워크

![](https://images.velog.io/images/cocodori/post/a58ab921-eed5-48ac-b223-e4e8d8c57a41/Wood-framed_house.jpg)

<center>image <a href='https://en.wikipedia.org/wiki/Framing_(construction)'>🌍Wikipedia</a></center>

> 프레임워크의 사전적 의미는 뼈대, 근간이다.

## 이까짓 뼈대가 왜 필요하나?

 a,b,c, ...z가 함께 일하는데 모두가 다른 방식으로 일한다면 어떨까? 뭐 그럭저럭 흘러는 가겠지만... 지금은 상상할 수 없는 어떤 무시무시한 일들이 벌어질지도 모르겠다. a와 w가 밤새 개발한 다음, 이제 우리 코드를 합쳐 보자! 했을 때, a의 방식과 w의 방식이 전혀 달라서 대략난감한 경우 말이다. 흠. 이렇게 하자고 했잖아! 난 이게 더 좋단 말이야!
 
 이런이런.
 
  프레임워크는 독재차처럼 **흐름을 제어**한다. 개발자는 프레임워크가 제어하는 흐름을 따라 개발할 수밖에 없다. 위에서 예로든 a부터 z로 이루어진 알파벳 팀 모두가 Spring을 쓴다고 해보자. 그럼 a와 w는 자신만의 방식 대로 개발할 수 없다. 오로지 Spring이 제어하는 흐름 위에서 자신의 코드를 추가할 것이다. 그렇다면 위와 같은 일은 일어나지 않겠지. 

 그러니까 이제부터는 직접 구조를 짤 필요가 없이, 스프링이 만들어놓은 구조 위에 살을 붙이고 색을 칠하기만 하면 된다.
 
## 특징

- POJO기반 구성
  - 스프링은 내부적으로 객체 간의 관계를 구성할 수 있는 특징을 가지고 있다. 이때 별도의 API를 사용하지 않고 일반적인 자바 코드만으로 객체를 구성할 수 있다. 이런 구성을 Plain Old Java Object(POJO)구성이라고 한다. 이렇게 했을 때, 어떤 기술에 종속적이지 않으므로 만들기 편하고, 고치기도 쉽고, 테스트하기도 좋다.
- 의존성 주입Dependency Injection(DI)
  - 의존성은 하나의 객체가 다른 객체 없이 자신의 역할을 할 수 없다는 것을 말한다.
  주입은, 밀어넣는 것이다. 이 두개를 합치면 의존성 주입이다. 어떤 객체가 필요로 하는 객체를 찾아서 밀어넣는 것이다.
 
  - 왜?
  편하기 때문이다. 주입 받는 입장에서는 어떤 객체인지 신경 쓸 필요가 없다. 어떤 객체에 의존하든, 역할은 변하지 않는다.
  누가 주입해주는가? **Application Context**다. Application Context는 일종의 컨테이너다. 늘 대기하면서 빈을 관리한다. 그리고 누군가 빈을 요청하면, 해당 빈을 찾아서 주입한다.
  
    - Application Context가 관리하는 객체를 빈Bean이라고 부른다.
    
- AOP(Aspect-Oriented-Programming)
  - 개발할 때 핵심 비즈니스 로직에만 집중할 수 있게 돕는 기능이다. 시스템이 공통으로 가지고 있는 반복적인 코드가 있다. 핵심 로직은 아니지만, 반드시 처리해야만 하는 귀찮은 것들. 이것을 스프링에서는 **횡단 관심사(cross-concern)**라고 한다. AOP는 횡단 관심사를 모듈로 분리하는 프로그래밍 패러다임이다.
 
  - 장점
    - 개발자는 비즈니스 로직에만 집중할 수 있다.
    - 프로젝트마다 다른 관심사를 적용할 때, 코드 수정을 최소화 할 수 있다.
    - 원하는 관심사의 유지보수가 수월한 코드를 구성할 수 있다.
- MVC구조

- WAS에 종속적이지 않음

- 트랜잭션
  - 트랜젹선 관리를 어노테이션이나 XML설정만으로 끝낼 수 있다.


# Version 1

### /board/list

![](https://images.velog.io/images/cocodori/post/c6fadc7e-90d8-4677-8168-6c82236421d0/image.png)

### /board/write

![](https://images.velog.io/images/cocodori/post/856b53e5-5eaa-497d-9aa1-425f74cf896e/image.png)

### /board/post

![](https://images.velog.io/images/cocodori/post/1e71ef0c-fb91-4c55-a808-5873f695f877/ezgif.com-video-to-gif.gif)

### post페이지에서 수정

![](https://images.velog.io/images/cocodori/post/3f9257b2-fc09-4a36-8ff5-e7ad223f1058/ezgif.com-video-to-gif%20(1).gif)

따로 수정 페이지를 만들지 않고 자바스크립트를 이용하여 조회 페이지에서 바로 수정할 수 있도록 했다.

### /board/remove

![](https://images.velog.io/images/cocodori/post/fa51338b-f0ba-4e32-a74a-ca2ccdd53373/ezgif.com-video-to-gif%20(2).gif)

모달 창을 쓸까, 말까 고민했지만 쓰지 않기로 했다. 네이버나, 페이스북 같은 웹에서 게시물을 삭제했을 때, 따로 삭제했다는 메세지 같은 것을 받은 기억이 없다. 따라서 굳이 필요한가... 있어도 되고 없어도 되는 기능이고, 없다고 사용자 입장에서 불편한 기능이 아니라고 판단해서 사용하지 않았다. 

> 코드가 궁금하다면 [참고](https://github.com/cocodori/SpringEx/tree/master/board/src/main/webapp/WEB-INF/views)

## 페이징

![](https://images.velog.io/images/cocodori/post/04944875-b313-47bb-8aa7-011f48027b14/ezgif.com-video-to-gif%20(3).gif)


게시글을 조회했다가, 다시 목록으로 돌아갈 때 원래의 페이지를 유지할 수 있도록 했다.
수정, 삭제 시에도 마찬가지로 원래 페이지를 유지한다.

## 검색

![](https://images.velog.io/images/cocodori/post/94d9b664-41e1-4c25-bcb1-4890db8de070/ezgif.com-video-to-gif%20(4).gif)

검색 조건을 유지하고 페이지 이동 가능. 게시물 조회, 수정 한 다음에도 정상 작동