package view;

import model.TaskState;

public enum ErrorMessage {
	OK("OK"),
	INVALID_ARGUMENTS("Provide valid arguments"),
	INVALID_STATE("Please, make sure that the entered state is valid:\n"+TaskState.TODO.getStateStr() +
			" for " + TaskState.TODO.getStoreValue() + "\n" +
			TaskState.IN_PROCESS.getStateStr() + " for " + TaskState.IN_PROCESS.getStoreValue() + "\n"+
			TaskState.TO_VERIFY.getStateStr() + " for " + TaskState.TO_VERIFY.getStoreValue() + "\n"+
			TaskState.DONE.getStateStr() + " for " + TaskState.DONE.getStoreValue() + "\n"),
	PK_CONSTRAINT_STORY("The story with this id is already there"),
	PK_CONSTRAINT_TASK("This story already has a task with that task id"),
	NO_STORY("No such story available"),
	INCOMPLETE_STORY("Some tasks inside a user story are not done"),
	INVALID_MOVE("The task cannot be moved to specified state"),
	NO_TASK("No such task available");
	
	private final String errorMsg;
	private ErrorMessage(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public String getMessage(){
		return this.errorMsg;
	}
		

}
