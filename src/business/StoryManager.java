package business;

import java.util.ArrayList;

import model.Story;
import model.StoryState;
import model.Task;
import model.TaskState;
import dao.StoryDao;

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
	 * @return true if successful, false otherwise
	 */
	public static boolean updateStory(long storyId, String newDescription){
		return sd.updateStoryDesc(storyId, newDescription);
	}
	
	/**
	 * Updating a story given its ID
	 * @param storyId id of the story
	 * @param newState new state of the story
	 * @return true if successful, false otherwise
	 */
	public static boolean updateStory(long storyId, StoryState newState){
		return sd.updateStoryState(storyId, newState.toString());
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
		
	/**
	 * 
	 * @param storyId story id for the tasks to be retrieved
	 * @return the list of tasks in the story
	 */
	public static ArrayList<Task> getTasks(long storyId){
		return (ArrayList<Task>) sd.getTasks(storyId);
	}
	
	/**
	 * Checks if the user story is complete
	 * @param storyId id of the user story
	 * @return true if complete, false otherwise
	 */
	public static boolean isComplete(long storyId){
		boolean isComplete = true;
		for (Task t: getTasks(storyId)){
			if (!t.getState().equals(TaskState.DONE)){
				isComplete = false;
				break;
			}
		}
		return isComplete;
	}
}
