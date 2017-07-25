package com.hsbc.testproject.rest.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hsbc.testproject.persistence.dao.post.Post;
import com.hsbc.testproject.persistence.dao.user.User;
import com.hsbc.testproject.services.post.PostException;
import com.hsbc.testproject.services.post.PostService;
import com.hsbc.testproject.services.user.UserService;

@RestController
@RequestMapping("/rest")
public class RestAPIController {
	
	private static final  Logger LOG = LoggerFactory.getLogger(RestAPIController.class);
	
	private final PostService postService;
	private final UserService userService;
	
	@Autowired
	public RestAPIController(
			PostService postService,
			UserService userService) {
		this.postService = postService;
		this.userService = userService;
	}
	
	@RequestMapping(value="/post", method = RequestMethod.POST)
    public ResponseEntity<?> createPost(@RequestBody Post postToSave) throws PostException{
    	LOG.info("Creating post: " + postToSave);
    	
    	postService.saveUserPost(postToSave);
    	
    	return new ResponseEntity<>(HttpStatus.CREATED);
    }
	
	@RequestMapping(value="/posts/{login}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserPosts(@PathVariable String login) throws Exception{
    	LOG.info("Retrieving posts list. Login: " + login);
    	
    	User user = userService.getUserByLogin(login);
    	List<Post> usersPosts = postService.getUserPosts(user);
    	
    	return usersPosts == null || usersPosts.isEmpty() 
    			? new ResponseEntity<>(HttpStatus.NO_CONTENT)
    			: new ResponseEntity<>(usersPosts, HttpStatus.OK);
    }
	
	@RequestMapping(value="/trackedUsers/{login}", method = RequestMethod.PUT)
    public ResponseEntity<List<User>> addTrackedUser(@PathVariable String login, @RequestBody User userToTrack) throws Exception {
    	LOG.info("Adding user to tracked users list: " + userToTrack);
    	
    	User destinationUser = userService.getUserByLogin(login);
    	userService.addUserToFollowedBy(destinationUser, userToTrack);
    	
    	return new ResponseEntity<List<User>>(userService.getUsersFollowedByUser(destinationUser), HttpStatus.OK);
    }
	
	@RequestMapping(value="/trackedPosts/{login}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserTrackedPosts(@PathVariable String login) throws Exception{
    	LOG.info("Retrieving tracked posts list. Login: " + login);
    	
    	User user = userService.getUserByLogin(login);
    	List<Post> trackedUsersPosts = postService.getPostsFromFollowedUsers(user);
    	
    	return trackedUsersPosts == null || trackedUsersPosts.isEmpty() 
    			? new ResponseEntity<>(HttpStatus.NO_CONTENT)
    			: new ResponseEntity<>(trackedUsersPosts, HttpStatus.OK);
    }
}