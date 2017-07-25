package com.hsbc.testproject.persistence.dao.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hsbc.testproject.persistence.dao.user.User;
import com.hsbc.testproject.persistence.memory.MemoryService;

@Repository
public class PostDAOImplementation implements PostDAO{
	
private final MemoryService memoryService;
	
	@Autowired
	public PostDAOImplementation(MemoryService memoryService) {
		this.memoryService = memoryService;
	}
	
	@Override
	public List<Post> getUserPosts(User user) throws PostDAOException {
		if(user == null || user.getLogin().isEmpty())
			throw new PostDAOException("User parameter should not be null");
		
		return memoryService.getUsersPosts().get(user);
	}

	@Override
	public void saveUserPost(Post postToSave, User author) throws PostDAOException {
		if(postToSave == null || author == null)
			throw new PostDAOException("Parameters should not be null");
		
		memoryService.getUsersPosts().get(author).add(postToSave);
	}
	
	


}
