package com.hsbc.testproject.persistence.dao.post;

import java.time.LocalDateTime;

import com.hsbc.testproject.persistence.dao.user.User;

public class Post {

	private String userLogin;
	private String postMessage;
	private LocalDateTime postTimestamp = LocalDateTime.now();
	
	public Post() {}
	
	public Post(String postMessage, User author) {
		this(postMessage, author.getLogin());
	}
	
	public Post(String postMessage, String login) {
		this.postMessage 	= postMessage;
		this.userLogin   	= login;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getPostMessage() {
		return postMessage;
	}

	public void setPostMessage(String postMessage) {
		this.postMessage = postMessage;
	}
	

	public LocalDateTime getPostTimestamp() {
		return postTimestamp;
	}

	public void setPostTimestamp(LocalDateTime postTimestamp) {
		this.postTimestamp = postTimestamp;
	}

	@Override
	public String toString() {
		return "Post [userLogin=" + userLogin + ", postMessage=" + postMessage + ", postTimestamp=" + postTimestamp
				+ "]";
	}

}
