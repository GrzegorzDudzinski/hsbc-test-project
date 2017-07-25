package com.hsbc.testproject.persistence.dao.user;

import java.util.List;

public interface UserDAO {
	public abstract User getUserByLogin(String login) throws UserDAOException;
	public abstract void saveUser(User user) throws UserDAOException;
	public abstract void addUserToFollowedBy(User user, User userToBeFollowed) throws UserDAOException;
	public abstract List<User> getUsersFollowedByUser(User user) throws UserDAOException; 
}
