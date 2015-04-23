package test;

import dao.StoryDao;
import model.Story;

public class Test {
	public static void testCreateStory(){
		Story story = new Story(12,"Dsdsd");
		StoryDao sd = new StoryDao();
		boolean s = sd.createStory(story.getID(), story.getDescription(),story.getState().toString());
		assert(s);
	}
	
	public static void main(String ... args){
		testCreateStory();
	}
}
