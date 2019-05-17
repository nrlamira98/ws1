package controller;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import database.DBConnection;
import model.Files;
import model.User;

public class StorageController {
	
	User user = new User();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	DefaultTableModel dm;
	
	public int UpdateFile(File newfile, String filename, String userid, String id) throws ClassNotFoundException, SQLException
	{
		String sql = "Update content set name=?, path=?, timestamp=?, user_id=? where id="+id;
		
		Connection con=DBConnection.doConnection();
		
		PreparedStatement preparedStatement= (PreparedStatement) con.prepareStatement(sql);
		preparedStatement.setString(1, newfile.getName());
		preparedStatement.setString(2, filename);
		Date date = new Date();
		Timestamp ts = Timestamp.valueOf(sdf.format(date));
		preparedStatement.setTimestamp(3, ts);
		preparedStatement.setInt(4, user.getId());
				
		con.close();
		return 0;
		
	}
	
public int Activity(File newfile, String filename, String id, Files file) throws ClassNotFoundException, SQLException
{
	String query = "insert into activity( file_name,date_time,user_id,activity_desc,file_id) values (?,?,?,?,?)";
	Connection con=DBConnection.doConnection();
	PreparedStatement preparedStatement= (PreparedStatement) con.prepareStatement(query);
	preparedStatement.setString(1, newfile.getName());
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	Timestamp ts = Timestamp.valueOf(sdf.format(date));
	preparedStatement.setTimestamp(2, ts);
	preparedStatement.setInt(3, user.getId());
	preparedStatement.setString(4, file.getStatus());
	preparedStatement.setString(5, id);
	return preparedStatement.executeUpdate();
}

public ArrayList<Files> LoadView() throws ClassNotFoundException, SQLException {
	ArrayList<Files> users = new ArrayList<Files>();
	
	String query="Select * from content";
	
	Connection con=DBConnection.doConnection();
	
	PreparedStatement preparedStatement=con.prepareStatement(query);
	
	ResultSet rs =preparedStatement.executeQuery();
	
	Files files;
	
	while(rs.next()) {
		files = new Files(
				rs.getString("id"),
				rs.getString("name"),
				rs.getTimestamp("timestamp"),
				rs.getString("path"),
				rs.getString("user_id"));
		
		users.add(files);
	}
	
	System.out.println(users.size());
	return users;
}

	public ArrayList<Files> DuplicateView() throws ClassNotFoundException, SQLException {
		ArrayList<Files> users = new ArrayList<Files>();
		
		String query="Select name,timestamp,status from content where status = 'Duplicate'";
		
		Connection con=DBConnection.doConnection();
		
		PreparedStatement preparedStatement=con.prepareStatement(query);
		
		ResultSet rs =preparedStatement.executeQuery();
		
		Files files;
		
		while(rs.next()) {
			files = new Files(
			rs.getString("name"),
			rs.getTimestamp("timestamp"),
			rs.getString("status"));
			
			users.add(files);
		}
		
		System.out.println(users.size());
		return users;
	}
	
	
	public ArrayList<Files> ActivityView() throws ClassNotFoundException, SQLException {
		ArrayList<Files> activity = new ArrayList<Files>();
		
		String query="Select * from activity";
		
		Connection con=DBConnection.doConnection();
		
		PreparedStatement preparedStatement=con.prepareStatement(query);
		
		ResultSet rs =preparedStatement.executeQuery();
		
		Files files;
		
		while(rs.next()) {
			files = new Files(
			rs.getString("user_id"),
			rs.getString("file_id"),
			rs.getString("file_name"),
			rs.getTimestamp("date_time"),
			rs.getString("activity_desc"));
			
			activity.add(files);
		}
		
		return activity;
	}
	
	
	public int DeleteFile(String id) throws ClassNotFoundException, SQLException
	{
		String sql = "Delete from content where id="+id;
		Connection con=DBConnection.doConnection();
		PreparedStatement preparedStatement= (PreparedStatement) con.prepareStatement(sql);
		preparedStatement.executeUpdate();
		int success=preparedStatement.executeUpdate();
		con.close();
		return 0;
	}
	
	public int TotalDelete(Files file )throws ClassNotFoundException, SQLException
	{
		String query="update fileview set no_deletefile=? where user_id=?";
		Connection con=DBConnection.doConnection();
		PreparedStatement preparedStatement=con.prepareStatement(query);
		//preparedStatement.setInt(1, ++);
		preparedStatement.setInt(2, user.getId());
		int sukses =preparedStatement.executeUpdate();
		con.close();
		return sukses;
	}
	
	public int AddFile(File file) throws ClassNotFoundException, SQLException
	{
		String sql = "insert into content(name, timestamp, path, type, user_id, status) values(?,?,?,?,?,?) ";
   		Connection con = DBConnection.doConnection();
   		PreparedStatement preparedStatement= con.prepareStatement(sql);
  		preparedStatement.setString(1, file.getName());
   		Timestamp ts = Timestamp.valueOf(sdf.format(file.lastModified()));
   		preparedStatement.setTimestamp(2, ts);
   		preparedStatement.setString(3, file.getAbsolutePath());
   		preparedStatement.setString(4, "file");
   		preparedStatement.setInt(5, user.getId());
   		preparedStatement.setString(6, "non-duplicate");
   		int success = preparedStatement.executeUpdate();
		return success;
	}
	
	public int AddFiles(File file, Timestamp ts, File path, Files files) throws ClassNotFoundException, SQLException
	{
		String sql = "insert into content(name, timestamp, path, type, user_id, status) values(?,?,?,?,?,?) ";
   		Connection con = DBConnection.doConnection();
   		PreparedStatement preparedStatement= con.prepareStatement(sql);
  		preparedStatement.setString(1, file.getName());
  		//ts = Timestamp.valueOf(sdf.format(file.lastModified()));
   		preparedStatement.setTimestamp(2, ts);
   		preparedStatement.setString(3, path.getAbsolutePath());
   		preparedStatement.setString(4, files.getType());
   		preparedStatement.setInt(5, user.getId());
   		preparedStatement.setString(6, files.getStatus());
   		int success = preparedStatement.executeUpdate();
		return success;
	}
	
	public int TotalAdd(String totals) throws ClassNotFoundException, SQLException
	{
		String query="update fileview set no_newfile=?";
		Connection con=DBConnection.doConnection();
		PreparedStatement preparedStatement=con.prepareStatement(query);
		preparedStatement.setString(1, totals);
		int success =preparedStatement.executeUpdate();
		con.close();
		return success;
	}
	
	public int SearchFile(Files files) throws SQLException, ClassNotFoundException {
		
		int found =-1;
		String sql = "Select path from content where path = ?";
		Connection con=DBConnection.doConnection();
		PreparedStatement preparedStatement=con.prepareStatement(sql);
		preparedStatement.setString(1, files.getFilepath());
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()) 
			found = 1;
		con.close();
		return found;
		
	}
	
	public int getData(String line1, String line2) throws ClassNotFoundException, SQLException
	{
			String sql = "UPDATE content SET path = CAST(REPLACE(CAST(path as NVarchar(MAX)),'?','?') AS NText) WHERE Content LIKE '%?%'";
					//"UPDATE content SET path = REPLACE(path, ?, ?) where path like '%?%'";
			
			Connection con=DBConnection.doConnection();
			PreparedStatement preparedStatement=con.prepareStatement(sql);
			preparedStatement.setString(1, line1);
			preparedStatement.setString(2, line2);
			preparedStatement.setString(3, line1);
			int success = preparedStatement.executeUpdate();
			System.out.println(success);
			return success;
			
	}
	
	public static void ViewFile(User user) throws ClassNotFoundException, SQLException
	{
		String query = "Select * from content ";
		Connection con=DBConnection.doConnection();
		PreparedStatement preparedStatement=con.prepareStatement(query);
		ResultSet rs = preparedStatement.executeQuery(query);
	
	}
	

	
}
	
	
	