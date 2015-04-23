package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.Story;

public class StoryDao implements IStoryDao {

	@Override
	public boolean createStory(long storyID, String description,String state) {
		try {
			Connection c = DbHelper.getConnection();
			String sql = "INSERT INTO STORY(ID,DESCRIPTION,STATE) VALUES(?,?,?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setLong(1, storyID);
			ps.setString(2, description);
			ps.setString(3, state);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			//TODO: properly hanling exceptions
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Story> getUserStories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteStory(long storyId) {
		// TODO Auto-generated method stub
		return false;
	}

}
