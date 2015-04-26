package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Story;
import model.StoryState;
import model.Task;
import model.TaskState;

public class StoryDao implements IStoryDao {

	@Override
	public boolean createStory(long storyID, String description,String state) {
		Connection c = null; 
		DbHelper dbH = new DbHelper();
		try {
			c = dbH.getConnection();
			String sql = "INSERT INTO STORY(ID,DESCRIPTION,STATE) VALUES(?,?,?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setLong(1, storyID);
			ps.setString(2, description);
			ps.setString(3, state);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
		finally{
			dbH.closeConnection();
		}
	}

	@Override
	public List<Story> getUserStories() {
		Connection c = null;
		ArrayList<Story> storyList =  new ArrayList<Story>();
		DbHelper dbH = new DbHelper();
		try{
			Statement st = null;
			c = dbH.getConnection();
			String sql = "SELECT ID,DESCRIPTION,STATE FROM STORY";
			st = c.createStatement();
			//retrieving form database
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				Long ID = rs.getLong(1);
				String description = rs.getString(2);
				StoryState state = StoryState.valueOf(rs.getString(3));
				Story s = new Story(ID, description);
				s.setState(state);
				storyList.add(s);
			}
			st.close();
		}
		catch(SQLException e){
			//TODO exception
			e.printStackTrace();
			return null;
		}
		finally{
			dbH.closeConnection();
		}
		return storyList;
	}

	@Override
	public boolean deleteStory(long storyId) {
		Connection c = null;
		DbHelper dbH = new DbHelper();
		try {
			c = dbH.getConnection();
			String sql = "DELETE FROM STORY WHERE ID=?";
			String sql1 = "DELETE FROM TASK WHERE STORY_ID = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			PreparedStatement ps1 = c.prepareStatement(sql1);
			ps.setLong(1, storyId);
			ps1.setLong(1, storyId);
			int effectedRows = ps.executeUpdate();
			ps1.executeUpdate();
			if (effectedRows < 1){
				return false;
			}
			return true;
		} catch (SQLException e) {
			return false;
		}
		finally{
			dbH.closeConnection();
		}
	}

	@Override
	public Story getStoryByID(long storyId) {
		Connection c = null;
		DbHelper dbH = new DbHelper();
		try {
			c = dbH.getConnection();
			String sql = "SELECT ID, DESCRIPTION, STATE FROM STORY WHERE ID=?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setLong(1, storyId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				long id  = rs.getLong(1);
				String description = rs.getString(2);
				StoryState state = StoryState.valueOf(rs.getString(3));
				Story s = new Story(id, description);
				s.setState(state);
				return s;
			}
			else{
				return null;
			}
			
		} catch (SQLException e) {
			return null;
		}
		finally{
			dbH.closeConnection();
		}
	}

	@Override
	public boolean updateStoryDesc(long storyId, String newDescription) {
		Connection c = null; 
		DbHelper dbH = new DbHelper();
		try {
			c = dbH.getConnection();
			String sql = "UPDATE STORY SET DESCRIPTION=? WHERE ID=?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, newDescription);
			ps.setLong(2, storyId);
			int effectedRows = 	ps.executeUpdate();
			if (effectedRows < 1){
				return false;
			}
			return true;
		} catch (SQLException e) {
			return false;
		}
		finally{
			dbH.closeConnection();
		}
	}

	@Override
	public boolean updateStoryState(long storyId, String newState) {
		Connection c = null; 
		DbHelper dbH = new DbHelper();
		try {
			c = dbH.getConnection();
			String sql = "UPDATE STORY SET STATE=? WHERE ID=?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, newState);
			ps.setLong(2, storyId);
			int effectedRows = 	ps.executeUpdate();
			if (effectedRows < 1){
				return false;
			}
			return true;
		} catch (SQLException e) {
			return false;
		}
		finally{
			dbH.closeConnection();
		}
	}

	@Override
	public List<Task> getTasks(long storyId) {
		Connection c = null;
		ArrayList<Task> taskList = new ArrayList<Task>();
		DbHelper dbH = new DbHelper();
		try {
			c = dbH.getConnection();
			String sql = "SELECT ID,DESCRIPTION,STATE FROM TASK WHERE STORY_ID = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setLong(1, storyId);
			// retrieving form database
			ResultSet rs = ps.executeQuery();
			Story relatedStory = new StoryDao().getStoryByID(storyId);
			while (rs.next()) {
				Long ID = rs.getLong(1);
				String description = rs.getString(2);
				TaskState state = TaskState.valueOf(rs.getString(3));
				Task t = new Task(ID, description,relatedStory);
				t.setState(state);
				taskList.add(t);
			}
		} catch (SQLException e) {
			// TODO exception
			e.printStackTrace();
			return null;
		} finally {
			dbH.closeConnection();
		}
		return taskList;
	}

}
