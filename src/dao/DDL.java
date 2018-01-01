package dao;

/**
 * This enum contains Data Definition Language statements in order to create sql model	
 * @author turalf
 *
 */
public enum DDL {
	CREATE_STORY("create table STORY("
				+ "ID int  not null primary key,"
				+ "DESCRIPTION varchar(200),"
				+ "STATE varchar(10)"
				+ ")"),

	CREATE_TASK("create table TASK("
				+ "ID int  not null,"
				+ "STORY_ID int not null,"
				+ "DESCRIPTION varchar(200),"
				+ "STATE varchar(10),"
				+ "foreign key (STORY_ID) references STORY(ID),"
				+ "primary key (ID,STORY_ID)"
				+ ")");
	
	private String sql;
	
	private DDL(String sql){
		this.sql = sql;
	}
	
	public String getSql(){
		return this.sql;
	}
}
