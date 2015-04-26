package business;

import java.util.ArrayList;

import model.Story;
import model.Task;
import model.TaskState;
import dao.StoryDao;
import dao.TaskDao;

/**
 * Manager class for tasks 
 * @author turalf
 *
 */
public class TaskManager {
	//TaskDao object to persist tasks
	private static TaskDao td = new TaskDao();
	//StoryDao Object to get the related storyId
	private static StoryDao sd = new StoryDao();
	
	
	/**
	 * Persisting a task
	 * @param t task to be persisted
	 * @return true if successful, false otherwise
	 */
	public static  boolean persistTask(Task t){
		return td.createTask(t.getRelatedStory().getID(), t.getID(), t.getDescription(), t.getState().getStoreValue());
	}
	
	/**
	 * Updates the state of the task
	 * @param storyId story id of the task
	 * @param taskId id of the task
	 * @param newState new state of the task
	 * @return true if successful, false otherwise
	 */
	public static boolean updateTask(long storyId, long taskId, TaskState newState){
			return td.moveTask(storyId, taskId, newState.getStoreValue());
	}
	
	/**
	 * Updates the description of the task
	 * @param storyId story id of the task
	 * @param taskId id of the task
	 * @param description new description of the task
	 * @return true if successful, false otherwise
	 */
	public static boolean  updateTask(long storyId, long taskId, String  description){
		return td.updateTask(storyId, taskId, description);
	}
	
	/**
	 * This method is used in attaching a task to a story
	 * @return the  story from persistence
	 */
	public static Story getTaskStory(long storyId){
		return sd.getStoryByID(storyId);
	}
	
	
	
	/**
	 * Deletes task from persistence 
	 * @param storyId story id of the task
	 * @param taskId id of the task
	 * @return true if successful, false otherwise
	 */
	public static boolean deleteTask(long storyId, long taskId){
		return td.deleteTask(storyId, taskId);
	}
	
	/**
	 * 
	 * @param storyId story id of the task
	 * @param taskId id of the task
	 * @return relevant task
	 */
	public static Task getTaskById(long storyId, long taskId){
		return td.getTaskById(storyId, taskId);
	}
	
	
}
