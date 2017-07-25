package com.hsbc.testproject.persistence.memory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.hsbc.testproject.persistence.dao.post.Post;
import com.hsbc.testproject.persistence.dao.user.User;

@Repository
@Scope("singleton")
public class MemoryService implements InitializingBean{
	
	private Map<String, User>     usersMap;
	private Map<User, List<Post>> usersPosts;
	private Map<User, List<User>> usersFollows;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.usersMap	  = new HashMap<>();
		this.usersPosts   = new HashMap<>();
		this.usersFollows = new HashMap<>();
	}

	public Map<String, User> getUsersMap() {
		return usersMap;
	}

	public void setUsersMap(Map<String, User> usersMap) {
		this.usersMap = usersMap;
	}

	public Map<User, List<Post>> getUsersPosts() {
		return usersPosts;
	}

	public void setUsersPosts(Map<User, List<Post>> usersPosts) {
		this.usersPosts = usersPosts;
	}

	public Map<User, List<User>> getUsersFollows() {
		return usersFollows;
	}

	public void setUsersFollows(Map<User, List<User>> usersFollows) {
		this.usersFollows = usersFollows;
	}
	
	
}
