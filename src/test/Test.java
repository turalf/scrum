package test;

import java.util.List;

import dao.StoryDao;
import dao.TaskDao;
import model.Story;
import model.TaskState;

public class Test {
	public static void testCreateStory(){
		Story story = new Story(12,"Dsdsd");
		Story story1 = new Story(13,"Dsdsd");
		Story story2 = new Story(14,"Dsdsd");
		StoryDao sd = new StoryDao();
		boolean s = sd.createStory(story.getID(), story.getDescription(),story.getState().toString());
		boolean s1 = sd.createStory(story1.getID(), story1.getDescription(),story1.getState().toString());
		boolean s2 = sd.createStory(story2.getID(), story2.getDescription(),story2.getState().toString());
	}
	
	public static void testGetStories(){
		StoryDao sd = new StoryDao();
		List a = sd.getUserStories();
	}
	
	public static void testDeletStory(){
		StoryDao sd = new StoryDao();
		sd.deleteStory(12);
	}
	
	public static void testCreateTask(){
		TaskDao td = new TaskDao();
		td.createTask(12, 2,"test", TaskState.IN_PROCESS.toString());
	}
	
	public static void testGetTasks(){
		TaskDao td = new TaskDao();
		List a = td.getTasks(12);
		System.out.println();
	}
	
	public static void testDeleteTask(){
		TaskDao t = new TaskDao();
		t.deleteTask(12, 1);
	}
	
	public static void testUpdateTask(){
		TaskDao td = new TaskDao();
		td.updateTask(12, 2, "wqwqwqwq");
	}
	
	public static void main(String ... args){
		testUpdateTask();
	}
	
	
}
