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
		double score;
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
	 
	 public void getScore(String tag)
	 {
		String selectQuery = "SELECT "+ tag +" FROM users WHERE user_id = ?";
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
					
					try (ResultSet resultSet = preparedStatement.executeQuery()) 
					{
						if (resultSet.next()) 
						{
							score =  resultSet.getDouble(tag);
						}
						else 
						{
							System.out.println("SQL Error : there are no result score data");
						}
					}
				}
			}
		}
		catch (ClassNotFoundException | SQLException e) 
		{
			System.out.println("Exception Error : " + e.getMessage());
        }
	 }
     
     public void DataInputFun(String tag, double tag_score) 
     {
    	 getScore(tag);
    	 score += tag_score;
    	 String selectQuery = "UPDATE users SET " + tag + " = ? WHERE user_id = ?";
    	 try 
    	 {
    		 Class.forName("org.postgresql.Driver");
    	     // 데이터베이스 연결
    	     try (Connection connection = DriverManager.getConnection(url, user, sqlpassword)) 
    	     {
    	    	 System.out.println("데이터베이스 연결 성공");
    	         // 쿼리문 적용
    	         try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) 
    	         {
    	             // 아이디와 점수 설정
    	             preparedStatement.setDouble(1, score);
    	             preparedStatement.setString(2, id);
    	             int rowsAffected = preparedStatement.executeUpdate();
    	             if (rowsAffected > 0) 
    	             {
    	            	 System.out.println("데이터 업데이트 성공");
    	             }
    	             else 
    	             {
    	            	 System.out.println("해당 사용자를 찾을 수 없습니다.");
    	             }
    	           }
    	     }
    	     catch (SQLException e)
    	     {
    	    	 System.out.println("SQL 예외 발생: " + e.getMessage());
    	     }
    	 }
    	 catch (ClassNotFoundException e)
    	 {
    		 System.out.println("드라이버를 찾을 수 없습니다: " + e.getMessage());
    	 }
     }
}
