package dao;

import java.util.List;

import model.Story;
import model.Task;

/**
 * Data Access Object Interface that contains all CRUD methods for Story 
 * @author turalf
 *
 */
public interface IStoryDao {
	
	/**
	 * Creates a user story with a given id
	 * @param storyID id of the user story to be created
	 * @param description description of the user story
	 * @param state of the user story
	 * @return True if story is created, False otherwise
	 */
	public boolean createStory(long storyID, String description, String state);
	
	/**
	 * Retrieves all user stories
	 * @return list of user stories
	 */
	public List<Story> getUserStories();
	
	/**
	 * Deletes a user story with a given id
	 * @param storyId Id of the user story to be deleted
	 * @return True if operation is successful, False otherwise
	 */
	public boolean deleteStory(long storyId);
	
	/**
	 * Gets the story of the specified ID
	 * @param storyId story id 
	 * @return Story object with a specified ID, null if there is no such story
	 */
	public Story getStoryByID(long storyId);

	/**
	 * Updates the description of the story
	 * @param storyId Id of the story to be updated
	 * @param newDescription new description
	 */
	public boolean updateStoryDesc(long storyId,String newDescription);
	
	/**
	 * Updates the state of the story
	 * @param storyId id of the story to be updated
	 * @param newState new state of the story
	 * @return true if successful, false otherwise
	 */
	public boolean updateStoryState(long storyId, String newState);
	
	/**
	 * Retrieve list of tasks in a given user story
	 * @param storyId ID of the story whose tasks are to be retrieved
	 * @return list of the tasks in the specified story
	 */
	public List<Task> getTasks(long storyId);
	
}
