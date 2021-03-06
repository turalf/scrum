package model;


/**
 * This class represents  Tasks of a user story
 * @author turalf
 *
 */
public class Task {
	private long ID;
	private Story relatedStory;
	private String description;
	private TaskState state;
	
	/**
	 * 
	 * @param ID id to be set to the task
	 * @param description description of the task
	 * @param relatedStory  user story that contains this task
	 */
	public Task(long ID, String description, Story relatedStory){
		this.ID = ID;
		this.relatedStory = relatedStory;
		this.description = description;
		this.state = TaskState.TODO;
	}
	
	/**
	 * @return the iD
	 */
	public long getID() {
		return ID;
	}
	
	
	/**
	 * @return the relatedStory
	 */
	public Story getRelatedStory() {
		return relatedStory;
	}
	
	/**
	 * @param relatedStory the relatedStory to set
	 */
	public void setRelatedStory(Story relatedStory) {
		this.relatedStory = relatedStory;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the state
	 */
	public TaskState getState() {
		return state;
	}
	
	/**
	 * @param state the state to set
	 */
	public void setState(TaskState state) {
		this.state = state;
	}
	
	
	/**
	 * Checks if the task can change its state. The task other than DONE can change its
	 * state to new state if new state's sequence number is less than or equal to its current state's 
	 * sequence number plus one
	 * @param t task to be changed
	 * @param newState potential new state of the task
	 * @return true if task state is changeable, false otherwise.
	 */
	public  boolean canChangeState(TaskState newState){
		if(this.getState().equals(TaskState.DONE)) return false;
		else if (newState.getSequenceNumber() <= this.getState().getSequenceNumber() + 1){
			return true;
		}
		return false;
	}

	
	/* Overrides equals method. Two Tasks are equal if their id and story id are equal
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() != this.getClass()){
			return false;
		}
		Task t = (Task) obj;
		return (t.ID == this.ID && t.relatedStory.getID() == this.relatedStory.getID());
	}
}
