package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DBConnection;
import model.Files;
import model.User;


public class UserController {
		public int addUser(User user) throws ClassNotFoundException, SQLException
		{
			String sql = "insert into User(name,password,level,address,email,notel,username) values(?,?,?,?,?,?,?)";
			Connection con=DBConnection.doConnection();
			PreparedStatement preparedStatement=con.prepareStatement(sql);
			preparedStatement.setString(1,user.getName());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setInt(3, user.getLevel());
			preparedStatement.setString(4, user.getAddress());
			preparedStatement.setString(5, user.getEmail());
			preparedStatement.setString(6, user.getNotel());
			preparedStatement.setString(7, user.getUsername());
			int success = preparedStatement.executeUpdate(); 
			con.close();
			return success;
		}
		
		public int doLogin(User user) throws ClassNotFoundException, SQLException
		{
			int level =-1;
			String sql ="select level from bengkel2018.user where username =? and password =?";
			Connection con=DBConnection.doConnection();
			PreparedStatement preparedStatement=con.prepareStatement(sql);
			preparedStatement.setString(1,user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			System.out.println("preparedStatement= "+preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
				level = resultSet.getInt(1);
			System.out.println("level= "+level);
			con.close();
			return level;
		}
		
		public ArrayList<User> StaffView() throws ClassNotFoundException, SQLException {
			ArrayList<User> activity = new ArrayList<User>();
			int i = 1;
			String query="Select * from user where level = "+i;
			
			Connection con=DBConnection.doConnection();
			
			PreparedStatement preparedStatement=con.prepareStatement(query);
			
			ResultSet rs =preparedStatement.executeQuery();
			
			User user;
			
			while(rs.next()) {
				user = new User(
				rs.getInt("id"),
				rs.getString("name"),
				rs.getString("password"),
				rs.getString("address"),
				rs.getString("email"),
				rs.getString("notel"),
				rs.getString("username"));
				
				activity.add(user);
			}
			
			return activity;
		}
		
		
		public int findUser(User user) throws ClassNotFoundException, SQLException
		{
			int found = -1;
			String sql = "select email from user where email =?";
			Connection con=DBConnection.doConnection();
			PreparedStatement preparedStatement=con.prepareStatement(sql);
			preparedStatement.setString(1,user.getEmail());
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
				found = 1;
			con.close();
			return found;
		}
		
		public int searchId(User user) throws ClassNotFoundException, SQLException
		{
			int found = -1;
			String sql = "select id from user where username =?";
			Connection con=DBConnection.doConnection();
			PreparedStatement preparedStatement=con.prepareStatement(sql);
			preparedStatement.setString(1,user.getUsername());
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
				found = 1;
			con.close();
			return found;
		}
		
		public ArrayList<User> getUsers() throws ClassNotFoundException, SQLException
		{
			ArrayList<User> users = new ArrayList<User>();
			String sql = "select name, level from User";
			Connection con=DBConnection.doConnection();
			PreparedStatement preparedStatement=con.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				User user = new User();
				user.setName(resultSet.getString(1));
				user.setLevel(resultSet.getInt(2));
				
				users.add(user);
				
			}
			con.close();
			return users;
			
		}
		
		public static void main(String[] args) {
			User abu = new User();
			abu.setName("Abu Bakar");
			abu.setPassword("123");
			abu.setLevel(1);
			
			UserController loginController = new UserController();
			try {
				System.out.println("level "+loginController.doLogin(abu));
				
				if(loginController.findUser(abu)==1)
					System.out.println("user is already exist!");
				else
					System.out.println(loginController.addUser(abu));
				
				for(User user : loginController.getUsers())
					System.out.println("Name : "+user.getName() + " Level : "+user.getLevel());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
}
