package model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This enum represents possible states for the task
 * @author turalf
 *
 */
public enum TaskState {
	TODO("todo","TO DO",0),
	IN_PROCESS("ip","IN PROCESS",1),
	TO_VERIFY("tv","TO VERIFY",2),
	DONE("d","DONE",3);
	
	//user input string value
	private String stateStr;
	// string value to be persisted 
	private String storeValue;
	//sequence number of the state. This is used when task 
	//is moved into the different column
	private int sequenceNumber;
	
	private TaskState(String stateStr, String storeValue, int seqeunceNumber){
		this.stateStr = stateStr;
		this.storeValue = storeValue;
		this.sequenceNumber = seqeunceNumber;
	}
	
	/**
	 * 
	 * @return the sequence number of the task state
	 */
	public int getSequenceNumber(){
		return this.sequenceNumber;
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
	
	/**
	 * Similar method of valueOf() for user input string 
	 * @param  String value of the field stateStr
	 * @return relevant TaskState enum
	 */
	public static TaskState valueU(String value){
		for(TaskState t : values())
            if(t.getStateStr().equalsIgnoreCase(value)) return t;
        throw new IllegalArgumentException();
	}
	
	/**
	 * Similar method of valueOf() for persisted string 
	 * @param  String value of the field storeStr
	 * @return relevant TaskState enum
	 */
	public static TaskState valueP(String value){
		for(TaskState t : values())
            if(t.getStoreValue().equalsIgnoreCase(value)) return t;
        throw new IllegalArgumentException();
	}
}
