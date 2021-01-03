package com.tobybook.user.dao;

public class DaoFactory {
	public UserDao6 userDao() {
		return new UserDao6(connectionMaker());
	}
	
	public AccountDao accountDao() {
		return new AccountDao(connectionMaker() );
	}
	
	public MessageDao messageDao() {
		return new MessageDao(connectionMaker() );
	}
	
	public ConnectionMaker connectionMaker() {
		return new DConnectionMaker();
	}
}
