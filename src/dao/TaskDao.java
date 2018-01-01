package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import business.StoryManager;
import model.Story;
import model.StoryState;
import model.Task;
import model.TaskState;

public class TaskDao implements ITaskDao {

	@Override
	public boolean createTask(long storyId, long taskId, String description, String state) {
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
			e.printStackTrace();
			return false;
		} finally {
			dbH.closeConnection();
		}
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
			int effectedRows = ps.executeUpdate();
			if (effectedRows < 1) {
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
			if (effectedRows < 1) {
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
			if (effectedRows < 1) {
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
	public Task getTaskById(long storyId, long taskId) {
		Connection c = null;
		DbHelper dbH = new DbHelper();
		try {
			c = dbH.getConnection();
			String sql = "SELECT ID,STORY_ID,DESCRIPTION,STATE FROM TASK WHERE ID=? AND STORY_ID=?";
			String sqlStory = "SELECT ID,DESCRIPTION,STATE FROM STORY WHERE ID=?";
			PreparedStatement ps = c.prepareStatement(sql);
			PreparedStatement ps2 = c.prepareStatement(sqlStory);
			ps2.setLong(1, storyId);
			ps.setLong(1, taskId);
			ps.setLong(2, storyId);
			ResultSet rs = ps.executeQuery();
			ResultSet rs2 = ps2.executeQuery();
			Story relatedStory = null;
			if (rs2.next()) {
				relatedStory = new Story(rs2.getLong(1), rs2.getString(2));
				relatedStory.setState(StoryState.valueOf(rs2.getString(3)));
			}
			if (rs.next()) {
				String description = rs.getString(3);
				TaskState state = TaskState.valueP(rs.getString(4));
				Task t = new Task(taskId, description, relatedStory);
				t.setState(state);
				return t;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			dbH.closeConnection();
		}
	}

}
