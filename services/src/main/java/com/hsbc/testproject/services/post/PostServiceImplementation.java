package com.hsbc.testproject.services.post;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsbc.testproject.persistence.dao.post.Post;
import com.hsbc.testproject.persistence.dao.post.PostDAO;
import com.hsbc.testproject.persistence.dao.post.PostDAOException;
import com.hsbc.testproject.persistence.dao.user.User;
import com.hsbc.testproject.services.user.UserException;
import com.hsbc.testproject.services.user.UserServiceImplementation;

@Service
public class PostServiceImplementation implements PostService{
	
	private static final String ILLEGAL_ARGS_TAG = "Illegal arguments passed!";
	private static final String NO_SUCH_USER 	 = "No such user in memory!";
	
	private final UserServiceImplementation userService;
	private final PostDAO     postDAO;
	
	@Autowired
	public PostServiceImplementation(UserServiceImplementation userService, PostDAO postDAO) {
		this.userService = userService;
		this.postDAO     = postDAO;
	}
	
	@Override
	public List<Post> getUserPosts(User user) throws PostException {
		if(user == null)
			throw new PostException(ILLEGAL_ARGS_TAG);
		
		try {
			userService.checkUserExistance(user);
			return postDAO.getUserPosts(user);
		} catch (UserException e) {
			throw new PostException(NO_SUCH_USER, e);
		} catch (PostDAOException e) {
			throw new PostException("Could not get user posts!", e);
		} 
	}

	@Override
	public void saveUserPost(Post postToSave) throws PostException {
		if(postToSave == null || postToSave.getUserLogin() == null || postToSave.getUserLogin().isEmpty())
			throw new PostException(ILLEGAL_ARGS_TAG);
		
		if(postToSave.getPostMessage().length() > 140)
			throw new PostException("User can post messages with length <= 140 characters");
		
		try {
			User author = new User(postToSave.getUserLogin());
			userService.saveUser(author);
			postDAO.saveUserPost(postToSave, author);
		} catch (UserException e) {
			throw new PostException(NO_SUCH_USER, e);
		} catch (PostDAOException e) {
			throw new PostException("Could not save user post!", e);
		} 
	}
	
	@Override
	public List<Post> getPostsFromFollowedUsers(User user) throws PostException{
		if(user == null)
			throw new PostException(ILLEGAL_ARGS_TAG);
		
		try {
			userService.checkUserExistance(user);
			List<Post> followedPosts = new ArrayList<>();
			
			for(User followedUser : userService.getUsersFollowedByUser(user))
				followedPosts.addAll(getUserPosts(followedUser));
			
			return followedPosts;
			
		} catch (UserException e) {
			throw new PostException(NO_SUCH_USER, e);
		} 		
	}

}
