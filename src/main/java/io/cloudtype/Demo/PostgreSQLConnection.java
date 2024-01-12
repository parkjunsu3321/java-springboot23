package io.cloudtype.Demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostgreSQLConnection {
	String id, password;
	String url = "jdbc:postgresql://svc.sel4.cloudtype.app:32752/reaction_db";
        String user = "root";
        String password = "3321";
	public PostgreSQLConnection()
	{
    		Join("3213", "이상한", "aaa3321");
	}

	public PostgreSQLConnection(String id, String password)
	{
    		this.id = id;
		this.password = password;
	}
	
	
	public void Join(String id, String name, String pass)
	{
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
        	}
        	catch (SQLException e) 
        	{
            	
		} 
        	finally 
        	{
            		try 
            		{
                		if (preparedStatement != null) 
                		{
                    			preparedStatement.close();
                		}
                		if (connection != null && !connection.isClosed()) 
                		{
                    			connection.close();
                    		}
            		} 
            		catch (SQLException e) 
            		{
                		e.printStackTrace();
            		}
        	}
	}

	public String Login()
	{
		String selectQuery = "SELECT * FROM users WHERE id = ? AND password = ?";
		try
		{
			Class.forName("org.postgresql.Driver");
			//데이터 베이스 연결
			try (Connection connection = DriverManager.getConnection(url, user, password)) 
			{
				//쿼리문 적용
				try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) 
				{
					//아이디와 패스워드 설정
					preparedStatement.setString(1, id);
					preparedStatement.setString(2, password);
					
					try (ResultSet resultSet = preparedStatement.executeQuery()) 
					{
						return "로그인 성공";
					}
				}
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{
            		return e.printStackTrace();
        	}
	}
}
