package com.hsbc.testproject.services.post;

import java.util.List;

import com.hsbc.testproject.persistence.dao.post.Post;
import com.hsbc.testproject.persistence.dao.user.User;

public interface PostService {

	public abstract List<Post> getUserPosts(User user) throws PostException;	
	public abstract List<Post> getPostsFromFollowedUsers(User user) throws PostException;
	public abstract void saveUserPost(Post postToSave) throws PostException;

}
