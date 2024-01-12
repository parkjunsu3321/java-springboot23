package io.cloudtype.Demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgreSQLConnection 
{
	String id, password;
	String url = "jdbc:postgresql://svc.sel4.cloudtype.app:32752/reaction_db";
        String user = "root";
        String sqlpassword = "3321";
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
        	}
        	catch (SQLException e) 
        	{
        		
        	}
	}

	public String Login()
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
	                        			return "로그인 성공";
	                    			} 
						
						else 
	                    			{
	                        			return "아이디 또는 패스워드가 잘못되었습니다!";
	                    			}
					}
				}
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{
            		return "로그인 중에 문제가 생겼습니다. 관리자에게 문의 바랍니다.";
        	}
	}
}
