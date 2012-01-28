package couk.Adamki11s.SQL;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import couk.Adamki11s.Managers.SyncLog;

public class SQLOperations {

	protected synchronized void standardQuery(String query, Connection connection) {
		try {
			System.out.println(query);
			System.out.println(connection);
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			statement.close();
		} catch (SQLException sqlx) {
			sqlx.printStackTrace();
		}
	}

	protected synchronized ResultSet sqlQuery(String query, Connection connection) {
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			return result;
		} catch (SQLException sqlx) {
			sqlx.printStackTrace();
		}
		return null;
	}

	protected synchronized boolean checkTable(String table, Connection connection) {
		DatabaseMetaData dbm;
		try {
			dbm = connection.getMetaData();
			ResultSet tables = dbm.getTables(null, null, table, null);
			return tables.next();
		} catch (SQLException e) {
			SyncLog.logSevere("Failed to check existance of table : " + table);
			e.printStackTrace();
			return false;
		}
	}

}
