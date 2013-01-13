package couk.Adamki11s.SQL;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLOperations {

	protected synchronized int standardQuery(String query, Connection connection) throws SQLException{
		Statement statement = connection.createStatement();
		int rowsUpdated = statement.executeUpdate(query);
		statement.close();
		return rowsUpdated;
	}

	protected synchronized ResultSet sqlQuery(String query, Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(query);
		return result;
	}

	protected synchronized boolean checkTable(String table, Connection connection) throws SQLException {
		DatabaseMetaData dbm;
		dbm = connection.getMetaData();
		ResultSet tables = dbm.getTables(null, null, table, null);
		return tables.next();
	}

}
