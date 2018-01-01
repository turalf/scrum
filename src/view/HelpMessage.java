package view;
/**
 * This enum contains all help messages
 * @author turalf
 *
 */
public enum HelpMessage {
	CREATE_STORY("create story <id> <description> - Create a new user story with the given ID and description"
			+ ". description must be in double quotes"),

	LIST_STORY("list stories - List all user stories that have been created, including Id’s"),

	DELETE_STORY("delete story <id> - Delete the user story with the given ID"),

	COMPLETE_STORY("complete story <id> - Mark the user story with the given ID as completed"),

	CREATE_TASK("create task <storyId> <id> <description> -  Create a new task with the given task ID and description, and"
			+ " associate it with the given storyId. Description must be in quotes"),

	LIST_TASK("list tasks <storyId> - List all the tasks associated with the given storyId"),

	DELETE_TASK("delete task <storyId> <id> - Deletes the task with the given ID associated with the given"),

	MOVE_TASK("move task <storyId> <id> <new column> - Move the task to the new column (todo-To Do,ip-In Process,tv-To verify"
			+ ",d-Done)"),

	UPDATE("update task <storyId> <id> <new description> - Update/Modify a task’s description. new description must be in double quotes");

	private final String message;
	
	/**
	 * Constructor to attach a string message to the enum element
	 * @param message String message of the enum element
	 */
	private HelpMessage(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return this.message;
	}
	
}
