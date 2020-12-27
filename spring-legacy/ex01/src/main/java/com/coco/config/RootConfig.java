package com.coco.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = {"com.coco.controller"})
//@MapperScan(basePackages = {"com.coco.mapper"})
public class RootConfig {
	
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
	

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource());
		return sqlSessionFactory.getObject();
	}
	
}
