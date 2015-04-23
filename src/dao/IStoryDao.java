package dao;

import java.util.List;

import model.Story;

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
	 * @return True if story is created, False otherwise
	 */
	public boolean createStory(long storyID, String description);
	
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
}
