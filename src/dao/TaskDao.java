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

public class TaskDao implements ITaskDao {

	@Override
	public boolean createTask(long storyId, long taskId, String description,
			String state) {
		Connection c = null;
		DbHelper dbH = new DbHelper();
		try {
			c = dbH.getConnection();
			String sql = "INSERT INTO TASK(ID,STORY_ID,DESCRIPTION,STATE) VALUES(?,?,?,?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setLong(1, taskId);
			ps.setLong(2, storyId);
			ps.setString(3, description);
			ps.setString(4, state);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		} finally {
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

	@Override
	public boolean deleteTask(long storyId, long taskId) {
		Connection c = null;
		DbHelper dbH = new DbHelper();
		try {
			c = dbH.getConnection();
			String sql = "DELETE FROM TASK WHERE ID=? AND STORY_ID=?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setLong(1, taskId);
			ps.setLong(2, storyId);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO: properly hanling exceptions
			e.printStackTrace();
			return false;
		} finally {
			dbH.closeConnection();
		}
	}

	@Override
	public boolean updateTask(long storyId, long taskId, String newDescription) {
		Connection c = null;
		DbHelper dbH = new DbHelper();
		try {
			c = dbH.getConnection();
			String sql = "UPDATE TASK SET DESCRIPTION=? WHERE ID=? AND STORY_ID=?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, newDescription);
			ps.setLong(2, taskId);
			ps.setLong(3, storyId);
			int effectedRows = ps.executeUpdate();
			if(effectedRows < 1 ){
				return false;
			}
			return true;
		} catch (SQLException e) {
			return false;
		} finally {
			dbH.closeConnection();
		}
	}

	@Override
	public boolean moveTask(long storyId, long taskId, String state) {
	Connection c = null;
		DbHelper dbH = new DbHelper();
		try {
			c = dbH.getConnection();
			String sql = "UPDATE TASK SET STATE=? WHERE ID=? AND STORY_ID=?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, state);
			ps.setLong(2, taskId);
			ps.setLong(3, storyId);
			int effectedRows = ps.executeUpdate();
			if (effectedRows <  1){
				return false;
			}
			return true;
		} catch (SQLException e) {
			return false;
		} finally {
			dbH.closeConnection();
		}
	}

}
