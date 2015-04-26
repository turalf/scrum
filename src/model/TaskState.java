package model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This enum represents possible states for the task
 * @author turalf
 *
 */
public enum TaskState {
	TODO("todo","TO DO"),
	IN_PROCESS("ip","IN PROCESS"),
	TO_VERIFY("tv","TO VERIFY"),
	DONE("d","DONE");
	
	//user input string value
	private String stateStr;
	// string value to be persisted 
	private String storeValue;
	
	private TaskState(String stateStr, String storeValue){
		this.stateStr = stateStr;
		this.storeValue = storeValue;
	}
	
	/**
	 * 
	 * @return string value for the state
	 */
	public String getStateStr(){
		return this.stateStr;
	}
	
	/**
	 * 
	 * @return string value for the state which is persisted
	 */
	public String getStoreValue(){
		return this.storeValue;
	}
	
	/**
	 * 
	 * @return The list of string values of the states
	 */
	public static ArrayList<String> getStrValues(){
		ArrayList<String> strValues = new ArrayList<String>();
		for(TaskState t : TaskState.values()){
			strValues.add(t.getStateStr());
		}
		return strValues;
	}
	
	
	public static TaskState value(String value){
		for(TaskState t : values())
            if(t.getStateStr().equalsIgnoreCase(value)) return t;
        throw new IllegalArgumentException();
	}
	
}
