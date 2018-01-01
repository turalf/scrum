package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Story;

import org.junit.Test;

import static org.junit.Assert.*;

public class StoryDaoTest {
	
	@Test
	public void setUp() throws SQLException{
		DbHelper db = new DbHelper();
		String sql = "DELETE FROM TASK; DELETE FROM STORY";
		Connection c = db.getConnection();
		PreparedStatement ps = c.prepareStatement(sql);
		ps.executeUpdate();
	}
	
	@Test
	public void testCreateStory(){
		long storyId = 100;
		String description = "test";
		String state = model.StoryState.PENDING.toString();
		StoryDao sd = new StoryDao();
		assertTrue(sd.createStory(storyId, description, state));
	}
	
	@Test
	public void testDeleteStory(){
		long storyId = 101;
		String description = "test";
		String state = model.StoryState.PENDING.toString();
		StoryDao sd = new StoryDao();
		sd.createStory(storyId, description, state);
		assertTrue(sd.deleteStory(storyId));
	}
	
	@Test
	public void testGetStoryById(){
		long storyId = 102;
		String description = "test";
		String state = model.StoryState.PENDING.toString();
		StoryDao sd = new StoryDao();
		sd.createStory(storyId, description, state);
		assertEquals(sd.getStoryByID(storyId),new Story(storyId, description));
	}
	
	@Test
	public void getUserStories(){
		StoryDao sd = new StoryDao();
		assertTrue(sd.getUserStories() != null);
	}
	
	@Test
	public void getTasks(){
		long storyId = 102;
		StoryDao sd = new StoryDao();
		assertTrue(sd.getTasks(storyId) != null);
	}
	
	@Test
	public void testUpdateStoryDesc(){
		long storyId = 103;
		String description = "test";
		String state = model.StoryState.PENDING.toString();
		StoryDao sd = new StoryDao();
		sd.createStory(storyId, description, state);
		String newDesc = "test1";
		sd.updateStoryDesc(storyId, newDesc);
		assertTrue(sd.getStoryByID(storyId).getDescription().equals(newDesc));
	}
	
	@Test
	public void testUpdateStoryState(){
		long storyId = 103;
		String description = "test";
		String state = model.StoryState.PENDING.toString();
		StoryDao sd = new StoryDao();
		sd.createStory(storyId, description, state);
		String newState = model.StoryState.COMPLETED.toString();
		sd.updateStoryState(storyId, newState);
		assertTrue(sd.getStoryByID(storyId).getState().toString().equals(newState));
	}
	
}
