# 자바 ORM 표준 JPA 프로그래밍

## 1. 데이터 중심 설계의 문제점

테이블 외래키를 객체에 그대로 가져온 부분, 이를 테 OrderItem클래스의 orderId나, itemId 같은 부분은 문제가 될 수 있다.
왜냐하면 관계형 데이터베이스는 연관된 객체를 찾을 때 외래키를 이용해서 조인하면 되지만, 객체에는 조인이라는 기능이 없다. 객체는
연관된 객체를 찾을 때 **참조**를 사용한다. 

 지금처럼 객체를 참조하지 않고, 외래키를 그대로 가지고 있으면 order.getMember()처럼 객체 그래프 탐색을 할 수 없다.
 이렇게 외래키만 가지고 있으면 연관된 엔티티를 찾을 때 외래 키로 데이터베이스에 다시 조회해야 한다.
 
 ```java
Order order = em.find(Order.class, orderId);

//외래 키로 다시 조회
Member member = em.find(Member.class, order.getMemberId();
``` 

참조를 이용하면 더 간편하게 조회할 수 있다.

```java
Order oder = em.find(Order.class , orderId);
//참조를 사용하므로 다시 DB에 조회할 필요가 없다.
Member member = order.getMember(); 
```
객체는 참조를 사용해서 연관된 객체를 찾고, 데이터베이스는 외래 키를 사용해서 연관된 테이블을 찾는다.
JPA는 객체의 참조와 테이블의 외래킬를 매핑해서 객체에서는 참조를, 테이블에서는 외래 키를 사용할 수 있도록 지원한다.