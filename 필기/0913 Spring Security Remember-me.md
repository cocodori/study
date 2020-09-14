# 자동 로그인 (Remember-me)

자동 로그인은 쿠키를 이용해서 구현한다.  
스프링 시큐리티는 security-context.xml에 `security:remember-me`태그를 이용해서 구현할 수 있다.  
이 태그에는 몇 가지 속성이 있다.  
\- key  
\- 쿠키에 사용되는 값을 암호화하기 위한 key값  
\- data-source-ref  
\- DataSource를 지정하고, 테이블을 이용해서 기존 로그인 정보를 기록한다.  
\- remember-me-cookie  
\- 브라우저에 보관하는 쿠키의 이름을 지정한다. 기본값은 'remember-me'  
\-remember-me-parameter  
\- 웹 화면에서 로그인할 때, 자동 로그인은 보통 체크박스를 이용한다. 이때 체크박스 태그는 name 속성을 의미한다.  
\-token-validity-second  
\- 쿠키의 유효시간을 지정한다.

## 데이터베이스를 이용하는 자동 로그인

로그인한 정보를 DB에 저장해뒀다가, 재접속 시 세션에 정보가 없으면 DB를 조회해서 사용하는 방법이다. 서버 메모리에만 저장하는 방식보다 안정적이게 운영할 수 있다는 장점이 있다.

'remember-me-기능도 JDBC를 이용하는 로그인 방식처럼, JDBC를 이용할 수도 있고, 직접 구현할 수도 있다. 단순히 로그인 유지를 위한 정보 보관이 용도이므로 직접 구현하기보다 지정된 방식을 이용한다.

### 테이블 생성

```
CREATE TABLE persistent_logins (
    username varchar(64) not null,
    series varchar(64) primary key,
    token varchar(64) not null,
    last_used timestamp not null
);
```

### security-context.xml 설정

data-source-ref를 지정하면 된다.

```
  ...생략

      <security:http>
        <!-- 접근 제한 -->
        <security:intercept-url pattern="/sample/all" access="permitAll"/>
        <security:intercept-url pattern="/sample/member" access="hasRole('ROLE_MANAGER')"/>
        <security:intercept-url pattern="/sample/admin" access="hasRole('ROLE_ADMIN')"/>
         <security:access-denied-handler ref="customAccessDenied"/>

        <!-- 직접 만드는 로그인 페이지 -->
        <security:form-login login-page="/login"
            authentication-success-handler-ref="customLoginSuccess"/>

        <security:logout logout-url="/logout" invalidate-session="true"/>


        <!-- 로그인 정보를 1주일 동안 보관한다-->
        <security:remember-me data-source-ref="dataSource" token-validity-seconds="604800"/>

    </security:http>

  ...생략

```

## 화면에서 테스트

login.jsp