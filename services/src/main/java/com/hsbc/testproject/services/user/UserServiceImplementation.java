package com.hsbc.testproject.services.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsbc.testproject.persistence.dao.user.User;
import com.hsbc.testproject.persistence.dao.user.UserDAO;
import com.hsbc.testproject.persistence.dao.user.UserDAOException;

@Service
public class UserServiceImplementation implements UserService{
	
	private final UserDAO userDAO;
	private static final String ILLEGAL_ARGS_TAG = "Illegal arguments passed!";
	
	@Autowired
	public UserServiceImplementation(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@Override
	public User getUserByLogin(String login) throws UserException {
		checkUserExistance(new User(login));
		try {
			return userDAO.getUserByLogin(login);
		} catch (UserDAOException e) {
			throw new UserException(ILLEGAL_ARGS_TAG, e);
		}		
	}
	
	@Override
	public void saveUser(User user) throws UserException {
		try {
			userDAO.saveUser(user);
		} catch (UserDAOException e) {
			throw new UserException(ILLEGAL_ARGS_TAG, e);
		}
	}

	@Override
	public void addUserToFollowedBy(User user, User userToBeFollowed) throws UserException {
		checkUserExistance(user);
		checkUserExistance(userToBeFollowed);
		try {
			userDAO.addUserToFollowedBy(user, userToBeFollowed);
		} catch (UserDAOException e) {
			throw new UserException(ILLEGAL_ARGS_TAG, e);
		}	
	}

	public List<User> getUsersFollowedByUser(User user) throws UserException {
		checkUserExistance(user);
		try {
			return userDAO.getUsersFollowedByUser(user);
		} catch (UserDAOException e) {
			throw new UserException(ILLEGAL_ARGS_TAG, e);
		}
	}
	
	@Override
	public void checkUserExistance(User user) throws UserException {
		try {
			if(userDAO.getUserByLogin(user.getLogin()) == null)
				throw new UserException("No such user defined!");
		} catch (UserDAOException e) {
			throw new UserException(ILLEGAL_ARGS_TAG, e);
		}
	}
}
