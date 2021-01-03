package com.tobybook.user.dao;

public class MessageDao {
	private ConnectionMaker connectionMaker;
	
	public MessageDao(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}
}
