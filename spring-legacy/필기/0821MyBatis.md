![](https://images.velog.io/images/cocodori/post/13ef9a62-1f13-4c18-97ec-964857ae00d5/%EB%8B%A4%EC%9A%B4%EB%A1%9C%EB%93%9C.jpg)
# 💣 마이바티스는 무엇인가?
> 마이바티스는 개발자가 지정한 SQL, 저장프로시저 그리고 몇가지 고급 매핑을 지원하는 퍼시스턴스 프레임워크이다. 마이바티스는 JDBC로 처리하는 상당부분의 코드와 파라미터 설정및 결과 매핑을 대신해준다. 마이바티스는 데이터베이스 레코드에 원시타입과 Map 인터페이스 그리고 자바 POJO 를 설정해서 매핑하기 위해 XML과 애노테이션을 사용할 수 있다 
[출처](https://mybatis.org/mybatis-3/ko/index.html)

**퍼시스턴트?**

객체가 생성되면 메모리에 올라간다. 객체를 이용해서 다루는 데이터들은 객체가 사라지면 함께 사라진다. 이렇게 일회용으로 버려지는 데이터들을 유지하려면, 객체가 사라지기 전에 어딘가에 저장해두어야 한다. 그러면 나중에 객체가 다시 생성되었을 때 이전에 사용했던 데이터를 재사용할 수 있다. 다시 말해, 객체가 사라지기 전의 상태로 돌아갈 수 있다는 것이다. 이런 속성을 '영속성persistence'라고 한다. 

## JDBC | MyBatis 비교


- JDBC
  - 직접 Connection을 맺는다.(DriveManager.getConnection(......))
  - 직접 닫는다. (close())
  - PreparedStatement를 직접 생성하고 입력한다.
  - ResultSet으로 직접 결과를 받아야 하는 귀찮음.
  <br>
 - MyBatis
   - 자동으로 Connection만들고 알아서 닫는다.close()
   - #{속성}만 지정하면 알아서 PreparedStatment 만들고 처리한다.
   - 리턴 타입을 지정하면 ResultSet만들어서 하던 일 알아서 한다.
   
   ![](https://images.velog.io/images/cocodori/post/75f1f18c-bfec-4f86-afa7-ccb9be9f3799/Untitled%20Diagram%20(5).jpg)
   
## MyBatis연동해서 TimeMapper 만들기

pom.xml에 추가
```xml
<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
<dependency>
	<groupId>org.mybatis</groupId>
	<artifactId>mybatis</artifactId>
	<version>3.5.5</version>
</dependency>
		
<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
<dependency>
	<groupId>org.mybatis</groupId>
	<artifactId>mybatis-spring</artifactId>
	<version>2.0.5</version>
</dependency>
		
<!-- https://mvnrepository.com/artifact/org.springframework/spring-tx -->
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-tx</artifactId>
	<version>5.2.6.RELEASE</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-jdbc</artifactId>
	<version>5.2.6.RELEASE</version>
</dependency>
```

MyBatis에서 가장 핵심 객체는 SQLSessionFactory가 만드는 SQLSession이다. SQLSession은 Connection을 생성하거나 원하는 SQL을 운반하고, 결과를 받아온다.

![](https://images.velog.io/images/cocodori/post/74e520ab-e385-4951-82f8-5b0c91b0d0a8/29cf881bc5ceb14932b1039c7ab02edf.jpg)

** 설정 **
```java
@Configuration
@ComponentScan(basePackages = {"com.coco.controller"})
@MapperScan(basePackages = {"com.coco.mapper"})
public class RootConfig {
	
	@Bean
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		
		config.setDriverClassName("com.mysql.cj.jdbc.Driver");
		config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/springex?serverTimezone=Asia/Seoul");
		config.setUsername("springuser");
		config.setPassword("springuser");
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

		HikariDataSource ds = new HikariDataSource(config);
		
		return ds;
	}
	

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource());
		return sqlSessionFactory.getObject();
	}
}
```

여기까지 하면 연결 끝!
테스트는 알아서..

그 다음 Mapper를 만든다. Mapper는 인터페이스로 선언한다.

```java
public interface TimeMapper {
	@Select("SELECT now()")
	public String getTime();
}
```

이렇게 ```@Select```안에 쿼리를 작성하는 것만으로 결과를 얻어올 수 있다.
테스트도 간단하다.

```java
@Autowired
TimeMapper mapper;
	
@Test
public void getTime() {
	assertNotNull(mapper);
	log.info(mapper);
	log.info(mapper.getClass().getName());
	log.info(mapper.getTime());
}
```

클래스는 없고 인터페이스밖에 없는데도 테스트는 성공한다.
```
INFO : com.coco.mapper.TimeMapperTest - org.apache.ibatis.binding.MapperProxy@3243b914
INFO : com.coco.mapper.TimeMapperTest - com.sun.proxy.$Proxy29 <--익명 클래스
INFO : com.coco.mapper.TimeMapperTest - 2020-08-21 18:08:47
```
스프링 컨테이너가 내부적으로 적절한 클래스를 만들어서 주입한 것이다.


