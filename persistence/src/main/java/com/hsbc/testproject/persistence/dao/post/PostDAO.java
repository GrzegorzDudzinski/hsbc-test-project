package com.hsbc.testproject.persistence.dao.post;

import java.util.List;

import com.hsbc.testproject.persistence.dao.user.User;

public interface PostDAO {
	public abstract List<Post> getUserPosts(User user) throws PostDAOException;
	public abstract void saveUserPost(Post postToSave, User author) throws PostDAOException;
}
