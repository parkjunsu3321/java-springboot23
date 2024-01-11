package io.cloudtype.Demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostgreSQLConnection {
	public PostgreSQLConnection(String id, String name, String pass)
	{
    	Join("3213", "이상한", "aaa3321");
	}
	public void Join(String id, String name, String pass)
	{
		String url = "jdbc:postgresql://svc.sel4.cloudtype.app:32752/reaction_db";
        String user = "root";
        String password = "3321";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try 
        {
            connection = DriverManager.getConnection(url, user, password);
            String insertQuery = "INSERT INTO users(user_id, username, password, test, first, second, third, participation) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, pass);
            preparedStatement.setBoolean(4, false); // test
            preparedStatement.setBoolean(5, false); // first
            preparedStatement.setBoolean(6, false); // second
            preparedStatement.setBoolean(7, false); // third
            preparedStatement.setInt(8, 0); // participation
            int rowsAffected = preparedStatement.executeUpdate();

            System.out.println(rowsAffected + " row(s) affected by the insert operation.");
            System.out.println("Connected to the PostgreSQL server successfully.");
        }
        catch (SQLException e) 
        {
            System.err.println("Error connecting to the PostgreSQL server: " + e.getMessage());
        } 
        finally 
        {
            try 
            {
                // Closing PreparedStatement
                if (preparedStatement != null) 
                {
                    preparedStatement.close();
                }

                // Closing connection
                if (connection != null && !connection.isClosed()) 
                {
                    connection.close();
                    System.out.println("Connection closed.");
                }
            } 
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
        }

	}
}
