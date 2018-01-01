package dao;

import org.junit.Test;
import static org.junit.Assert.*;

public class DbHelperTest {
	
	@Test
	public void testGetConnection(){
		DbHelper db = new DbHelper();
		assertTrue(db.getConnection() != null);
	}
}
