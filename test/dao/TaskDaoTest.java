package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.*;
import model.Story;
import model.Task;

import org.junit.Test;

import business.StoryManager;

public class TaskDaoTest {
	
	@Test
	public void setUp() throws SQLException{
		DbHelper db = new DbHelper();
		String sql = "DELETE FROM TASK; DELETE FROM STORY;";
		Connection c = db.getConnection();
		PreparedStatement ps = c.prepareStatement(sql);
		ps.executeUpdate();
	}
	
	@Test
	public void testCreateTask(){
		long storyId = 100;
		long taskId = 1;
		String desc = "test";
		String state = model.TaskState.TODO.getStoreValue();
		StoryManager.persistStory(new Story(storyId, "Test story"));
		TaskDao td = new TaskDao();
		assertTrue(td.createTask(storyId, taskId, desc, state));
	}
	
	@Test
	public void testUpdateTask(){
		long storyId = 101;
		long taskId = 1;
		String desc = "test";
		String state = model.TaskState.TODO.getStoreValue();
		StoryManager.persistStory(new Story(storyId, "Test story"));
		TaskDao td = new TaskDao();
		td.createTask(storyId, taskId, desc, state);
		String newDesc = "test1";
		td.updateTask(storyId, taskId, newDesc);
		Task t = td.getTaskById(storyId, taskId);
		assertTrue(t.getDescription().equals(newDesc));
		
	}
	
}
