package couk.Adamki11s.SQL;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import couk.Adamki11s.Managers.SyncLog;

public class SQLOperations {

	protected void standardQuery(String query, Connection connection) {
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			statement.close();
		} catch (SQLException sqlx) {
			sqlx.printStackTrace();
		}
	}

	protected ResultSet sqlQuery(String query, Connection connection) {
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			result.close();
			statement.close();
			return result;
		} catch (SQLException sqlx) {
			sqlx.printStackTrace();
		}
		return null;
	}

	protected boolean checkTable(String table, Connection connection) {
		DatabaseMetaData dbm;
		try {
			dbm = connection.getMetaData();
			ResultSet tables = dbm.getTables(null, null, table, null);
			tables.close();
			return tables.next();
		} catch (SQLException e) {
			SyncLog.logSevere("Failed to check existance of table : " + table);
			e.printStackTrace();
			return false;
		}
	}

}
