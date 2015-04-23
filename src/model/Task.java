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
	 * When Task object is created ID must be provided
	 * @param ID id to be set to the task
	 */
	public Task(long ID){
		this.ID = ID;
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
	
	
	
	
}
