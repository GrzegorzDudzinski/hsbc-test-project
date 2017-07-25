package com.hsbc.testproject.persistence.dao.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hsbc.testproject.persistence.memory.MemoryService;

@Repository
public class UserDAOImplementation implements UserDAO {
	
	private final MemoryService memoryService;
	
	@Autowired
	public UserDAOImplementation(MemoryService memoryService) {
		this.memoryService = memoryService;
	}
	
	@Override
	public User getUserByLogin(String login) throws UserDAOException {
		if(login == null || login.isEmpty())
			throw new UserDAOException("Login should not be null nor empty!");
		
		return memoryService.getUsersMap().get(login);
	}

	@Override
	public void saveUser(User user) throws UserDAOException {
		if(user == null || user.getLogin() == null)
			throw new UserDAOException("Could not save null entity!");
		
		if(!memoryService.getUsersMap().containsKey(user.getLogin())) {
			memoryService.getUsersMap().put(user.getLogin(), user);
			memoryService.getUsersFollows().put(user, new ArrayList<>());
			memoryService.getUsersPosts().put(user, new ArrayList<>());
		}
	}

	@Override
	public void addUserToFollowedBy(User user, User userToBeFollowed) throws UserDAOException  {
		if(user == null || userToBeFollowed == null)
			throw new UserDAOException("Cannot operate on null entities!");
		
		if(user.getLogin() == null || userToBeFollowed.getLogin() == null)
			throw new UserDAOException("User login should not be null!");
		
		if(!memoryService.getUsersFollows().get(user).contains(userToBeFollowed))
			memoryService.getUsersFollows().get(user).add(userToBeFollowed);
	}

	@Override
	public List<User> getUsersFollowedByUser(User user) throws UserDAOException {
		if(user == null || user.getLogin() == null)
			throw new UserDAOException("Cannot operate on null entities!");
		
		return memoryService.getUsersFollows().get(user);
	}
}
