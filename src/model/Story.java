package model;


/**
 * The class that represents user Story in a backlog
 * @author turalf
 *
 */
public class Story {
	
	private long ID;
	private String description;
	private StoryState state;
	
	/**
	 * When a story object is created an ID for the story must be provided
	 * @param ID id to be set to the story
	 */
	public Story(long ID){
		this.ID = ID;
	}
	
	/**
	 * @return the iD of the Story object
	 */
	public long getID() {
		return ID;
	}
	
	/**
	 * @param iD the iD to set to the Story object
	 */
	public void setID(long iD) {
		ID = iD;
	}
	
	/**
	 * @return the description of the Story object
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description the description to set to the Story object
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the state
	 */
	public StoryState getState() {
		return state;
	}
	
	/**
	 * @param state the state to set the Story object
	 */
	public void setState(StoryState state) {
		this.state = state;
	}

	
	
}
