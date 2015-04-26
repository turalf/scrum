package business;

import java.util.ArrayList;

import dao.StoryDao;
import model.Story;
import model.StoryState;

/**
 * Manager class for stories
 * @author turalf
 *
 */
public class StoryManager {
	
	
	private static StoryDao sd = new StoryDao();
	
	/**
	 * Persisting a story
	 * @param s Story to be persisted
	 */
	public static boolean persistStory(Story s){
		return sd.createStory(s.getID(), s.getDescription(),s.getState().toString());
	}
	
	/**
	 * Updating a story given its ID
	 * @param storyId id of the story
	 * @param newState new state of the story
	 */
	public static void updateStory(long storyId, StoryState newState){
		sd.updateStoryDesc(storyId, newState.toString());
	}

	/**
	 * 
	 * @return list of stories from persistence
	 */
	public static ArrayList<Story> getStories(){
		return (ArrayList<Story>) sd.getUserStories();
	}

	/**
	 * Deletes the story from the persistence
	 * @param storyId id of the story to be deleted
	 * @return true if successful,false otherwise
	 */
	public static boolean deleteStory(long storyId){
		return sd.deleteStory(storyId);
	}
	
}
