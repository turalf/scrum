package view;
/**
 * Possible commands
 * @author turalf
 *
 */
public enum Command {
	
	CREATE("create"),
	LIST("list"),
	DELETE("delete"),
	COMPLETE("complete"),
	MOVE("move"),
	UPDATE("update"),
	STORY("story"),
	STORIES("stories"),
	TASK("task"),
	TASKS("tasks");
	
	private final String command;
	private Command(String command){
		this.command = command;
	}


	public String getCommand(){
		return this.command;
	}
}
