package couk.Adamki11s.SQL;

import java.io.File;
import java.io.IOException;
import java.sql.*;

import couk.Adamki11s.Managers.SyncLog;

public class SyncSQL extends SQLOperations {

	private String host, database, username, password;
	private SCHEMA schema;
	private Connection connection;
	private File databaseFile;

	public SyncSQL(String host, String database, String username, String password) {
		this.host = host;
		this.database = database;
		this.username = username;
		this.password = password;
		this.schema = SCHEMA.MySQL;
	}

	public SyncSQL(File databaseFile) {
		this.databaseFile = databaseFile;
		this.schema = SCHEMA.SQLite;
	}
	
	public void refreshConnection(){
		if(connection == null){
			initialise();
		}
	}
	
	public void closeConnection(){
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean initialise() {
		if (schema == SCHEMA.MySQL) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database, username, password);
				return true;
			} catch (ClassNotFoundException ex) {
				SyncLog.logSevere("Could not find MySQL driver class!");
				ex.printStackTrace();
			} catch (SQLException ex) {
				SyncLog.logSevere("SQL Exception!");
				ex.printStackTrace();
			}
		} else {
			if(!databaseFile.exists()){
				try {
					databaseFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				Class.forName("org.sqlite.JDBC");
				connection = DriverManager.getConnection("jdbc:sqlite:" + this.databaseFile.getAbsolutePath());
				return true;
			} catch (SQLException ex) {
				SyncLog.logSevere("SQL Exception!");
				ex.printStackTrace();
			} catch (ClassNotFoundException ex) {
				SyncLog.logSevere("Could not find SQLite driver class!");
				ex.printStackTrace();
			}
		}
		return false;
	}
	
	public void standardQuery(String query){
		this.refreshConnection();
		super.standardQuery(query, this.connection);
	}
	
	public ResultSet sqlQuery(String query){
		this.refreshConnection();
		return super.sqlQuery(query, this.connection);
	}
	
	public boolean doesTableExist(String table){
		this.refreshConnection();
		return super.checkTable(table, this.connection);
	}

}
