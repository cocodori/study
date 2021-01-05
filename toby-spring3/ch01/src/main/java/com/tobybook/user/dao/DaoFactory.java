package com.tobybook.user.dao;

import com.tobybook.user.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {

	@Bean
	public UserDao6 userDao() {
		UserDao6 userDao6 = new UserDao6();
		userDao6.setConnectionMaker(connectionMaker());
		return userDao6;
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
