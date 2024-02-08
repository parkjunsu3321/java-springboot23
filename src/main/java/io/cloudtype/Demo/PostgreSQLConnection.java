package io.cloudtype.Demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgreSQLConnection 
{
	public String id, password;
	String url = "jdbc:postgresql://svc.sel4.cloudtype.app:32752/reaction_db";
        String user = "root";
        String sqlpassword = "3321";
	public PostgreSQLConnection()
	{
		
	}

	public PostgreSQLConnection(String id, String password)
	{
    		this.id = id;
		this.password = password;
	}

	public boolean CheckId(String check)
	{
		String selectQuery = "SELECT * FROM users WHERE user_id = ?";
		try
		{
			Class.forName("org.postgresql.Driver");
			//데이터 베이스 연결
			try (Connection connection = DriverManager.getConnection(url, user, sqlpassword)) 
			{
				//쿼리문 적용
				try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) 
				{
					//아이디와 패스워드 설정
					preparedStatement.setString(1, check);
					
					try (ResultSet resultSet = preparedStatement.executeQuery()) 
					{
						if (resultSet.next()) 
						{
	                        			return false;
	                    			} 
						else 
	                    			{
	                        			return true;
	                    			}
					}
				}
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{
			return false;
        	}
	}
	
	public boolean Join(String id, String name, String pass)
	{
        	try (Connection connection = DriverManager.getConnection(url, user, sqlpassword);)
        	{
            		String insertQuery = "INSERT INTO users(user_id, username, password, test, first, second, third, participation) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            		try(PreparedStatement ps = connection.prepareStatement(insertQuery);)
            		{
            			ps.setString(1, id);
                		ps.setString(2, name);
                		ps.setString(3, pass);
                		ps.setBoolean(4, false); // test
                		ps.setBoolean(5, false); // first
                		ps.setBoolean(6, false); // second
                		ps.setBoolean(7, false); // third
                		ps.setInt(8, 0); // participation
                		int rowsAffected = ps.executeUpdate();
            		}
			return true;
        	}
        	catch (SQLException e) 
        	{
        		return false;
        	}
	}

	public boolean Login()
	{
		String selectQuery = "SELECT * FROM users WHERE user_id = ? AND password = ?";
		try
		{
			Class.forName("org.postgresql.Driver");
			//데이터 베이스 연결
			try (Connection connection = DriverManager.getConnection(url, user, sqlpassword)) 
			{
				//쿼리문 적용
				try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) 
				{
					//아이디와 패스워드 설정
					preparedStatement.setString(1, id);
					preparedStatement.setString(2, password);
					
					try (ResultSet resultSet = preparedStatement.executeQuery()) 
					{
						if (resultSet.next()) 
						{
	                        			return true;
	                    			} 
						
						else 
	                    			{
	                        			return false;
	                    			}
					}
				}
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{
			return false;
        	}
	}
	
	public boolean Change_Password(String n_password)
	{
		String selectQuery = "UPDATE users SET password = ? WHERE user_id = ?;";
		try
		{
			Class.forName("org.postgresql.Driver");
			//데이터 베이스 연결
			try (Connection connection = DriverManager.getConnection(url, user, sqlpassword)) 
			{
				//쿼리문 적용
				try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) 
				{
					//아이디와 패스워드 설정
					preparedStatement.setString(1, n_password);
					preparedStatement.setString(2, id);
					int resultSet =  preparedStatement.executeUpdate();
					if (resultSet > 0) 
					{
						return true";
	                		} 
					else
					{
						return false;
					}
				}
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{
			return false;
        	}
	}
	
	public boolean DeleteId() 
	{
        String deleteQuery = "DELETE FROM users WHERE user_id = ?";
        try 
	{
            Class.forName("org.postgresql.Driver");
            try (Connection connection = DriverManager.getConnection(url, user, sqlpassword)) 
            {
                try (PreparedStatement ps = connection.prepareStatement(deleteQuery)) 
                {
                    ps.setString(1, id);
                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected > 0) 
                    {
                        return true;
                    } 
                    else
                    {
                        return false;
                    }
                }
            }
        } 
        catch (ClassNotFoundException | SQLException e) 
        {
            return false;
        }
    }

     public String[] getInfo() 
     {
        String[] str = new String[3];
        String selectQuery = "SELECT username, user_id, participation FROM users WHERE user_id = '" + id + "'";
        try 
        {
            Class.forName("org.postgresql.Driver");
            try (Connection connection = DriverManager.getConnection(url, user, sqlpassword)) 
            {
                try (PreparedStatement ps = connection.prepareStatement(selectQuery)) 
                {
                    ResultSet resultSet = ps.executeQuery();
                    if (resultSet.next()) 
                    {
                        str[0] = resultSet.getString("username");
                        str[1] = resultSet.getString("user_id");
                        str[2] = resultSet.getString("participation");
			return str;
                    } 
                    else 
                    {
                        return str;
                    }
                }
            }
        } 
        catch (ClassNotFoundException | SQLException e) 
        {
            str[0] = e.getMessage();
            return str;
        }
     }
}
