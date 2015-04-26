package dao;

import java.util.List;

import model.Task;

/**
 * Data Access Object Interface for tasks
 * @author turalf
 *
 */
public interface ITaskDao {
	
	/**
	 * Creates a task in a user story
	 * @param storyId story id which task will be created in
	 * @param taskId id of the new task
	 * @param description description of the new task
	 * @param state state of the task
	 * @return True if creation is successful, False otherwise
	 */
	public boolean createTask(long storyId, long taskId, String description, String state);
	
	
	/**
	 * Deletes a task from a user story
	 * @param storyId id of the story
	 * @param taskId id of the task
	 * @return True if successful, False otherwise
	 */
	public boolean deleteTask(long storyId, long taskId);
	
	/**
	 * Updates the description of the task
	 * @param taskId id of the task to be updated
	 * @param newDescription new description for the task
	 * @return True in case successful, false otherwise
	 */
	public boolean updateTask(long storyId, long taskId, String newDescription);
	
	/**
	 * Updates the description of the task
	 * @param taskId id of the task to be updated
	 * @param newDescription new description for the task
	 * @return True in case successful, false otherwise
	 */
	public boolean moveTask(long storyId, long taskId, String state);
	
	/**
	 * Get the specified task 
	 * @param storyId story id of the task
	 * @param taskId task id
	 * @return the Task object
	 */
	public Task getTaskById(long storyId, long taskId);

	
}
