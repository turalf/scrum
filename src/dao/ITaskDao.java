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
	 * @return True if creation is successful, False otherwise
	 */
	public boolean createTask(long storyId, long taskId, String description);
	
	/**
	 * Retrieve list of tasks in a given user story
	 * @param storyId ID of the story whose tasks are to be retrieved
	 * @return list of the tasks in the specified story
	 */
	public List<Task> getTasks(long storyId);
	
	/**
	 * Deletes a task from a user story
	 * @param taskId id of the task
	 * @return True if successful, False otherwise
	 */
	public boolean deleteTask(long taskId);
	
	/**
	 * Updates the description of the task
	 * @param taskId id of the task to be updated
	 * @param newDescription new description for the task
	 * @return updated Task object
	 * @see Task
	 */
	public Task updateTask(long taskId, String newDescription);
	
}
