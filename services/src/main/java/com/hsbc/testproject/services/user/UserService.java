package com.hsbc.testproject.services.user;

import java.util.List;

import com.hsbc.testproject.persistence.dao.user.User;

public interface UserService {
	public abstract User getUserByLogin(String login) throws UserException;

	public abstract List<User> getUsersFollowedByUser(User user) throws UserException;

	public abstract void checkUserExistance(User user) throws UserException;
	
	public abstract void saveUser(User user) throws UserException;

	public abstract void addUserToFollowedBy(User user, User userToBeFollowed) throws UserException;
}
