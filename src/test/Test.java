package test;

import java.util.List;

import dao.StoryDao;
import model.Story;

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
	
	public static void main(String ... args){
		testDeletStory();
	}
}
