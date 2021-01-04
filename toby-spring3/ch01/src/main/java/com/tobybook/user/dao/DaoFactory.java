package com.tobybook.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {

	@Bean
	public UserDao6 userDao() {
		return new UserDao6(connectionMaker());
	}
	
	public AccountDao accountDao() {
		return new AccountDao(connectionMaker() );
	}
	
	public MessageDao messageDao() {
		return new MessageDao(connectionMaker() );
	}

	@Bean
	public ConnectionMaker connectionMaker() {
		return new DConnectionMaker();
	}
}
