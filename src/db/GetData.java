package db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetData 
{
	public ResultSet getDataFromEventTable(String username) throws SQLException
	{
		DBConnection connection = DBConnection.getInstance();
		String query = "SELECT * FROM Event WHERE username = '" + username + "'";
		return connection.query(query);
	}


}


