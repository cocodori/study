# log4jdbc-log4j2 
해당 라이브러리를 이용하면 콘솔에서 SQL문과 결과를 가지런하게 확인할 수 있다.

![](https://images.velog.io/images/cocodori/post/59344eba-3273-44dc-a412-b51524c4b88c/image.png)

우선 메이븐 설정을 추가한다. [링크](https://mvnrepository.com/artifact/org.bgee.log4jdbc-log4j2/log4jdbc-log4j2-jdbc4.1)

```
<!-- https://mvnrepository.com/artifact/org.bgee.log4jdbc-log4j2/log4jdbc-log4j2-jdbc4.1 -->
<dependency>
    <groupId>org.bgee.log4jdbc-log4j2</groupId>
    <artifactId>log4jdbc-log4j2-jdbc4.1</artifactId>
    <version>1.16</version>
</dependency>
```

RootConfig.java
```java
	
	@Bean
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		
//		config.setDriverClassName("com.mysql.cj.jdbc.Driver");
//		config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/springex?serverTimezone=Asia/Seoul");
		config.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		config.setJdbcUrl("jdbc:log4jdbc:mysql://127.0.0.1:3306/springex?serverTimezone=Asia/Seoul");
		config.setUsername("springuser");
		config.setPassword("springuser");
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

		HikariDataSource ds = new HikariDataSource(config);
		
		return ds;
	}
```


![](https://images.velog.io/images/cocodori/post/be0bece6-2272-4f97-bd2d-4cd2e756b5d9/%EC%A3%BC%EC%84%9D%202020-08-21%20183335.jpg)

그 다음 해당 경로에 log4jdbc.log4j2.properties파일을 만든다.

```log4jdbc.spylogdelegator.name=net.sf.log4jdbc.log.slf4j.Slf4jSpyLogDelegator```
log4jdbc.log4j2.properties에 이 한 줄을 추가한다.

그리고 테스트를 돌리면

![](https://images.velog.io/images/cocodori/post/59344eba-3273-44dc-a412-b51524c4b88c/image.png)

적용한 결과를 볼 수 있다.