package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import database.DBConnection;
import model.Files;

public class FindDuplicate {
	
	public static ArrayList<Files> Test() throws SQLException, ClassNotFoundException, ParseException, IOException {
		
		ArrayList<Files> isfile = new ArrayList<>();
		String types = "file";
		String sql = "select filepath, file_name, file_id, timestamp from content where type=?";
		Connection con=DBConnection.doConnection();
		PreparedStatement preparedStatement=con.prepareStatement(sql);
		preparedStatement.setString(1, types);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next())
		{
			Files files = new Files();
			files.setFilepath(resultSet.getString("filepath"));
			files.setFile_name(resultSet.getString("file_name"));
			files.setFile_id(resultSet.getString("file_id"));
			files.setTimeStamp(resultSet.getTimestamp("timestamp"));
			isfile.add(files);
			System.out.println(isfile);
		}
		
		NewCompares(isfile);
		return isfile;
		
	}
  
	public static void NewCompares(ArrayList<Files> isfile) throws ClassNotFoundException, SQLException, ParseException, IOException 
	{
		
		for(int i = 0; i<isfile.size(); i++) 
		{		
			
		for(int j=i+1; j<isfile.size(); j++) 
		{		
			BufferedReader reader1 = new BufferedReader(new FileReader(isfile.get(i).getFilepath().replace("\\" , "\\\\")));
			BufferedReader reader2 = new BufferedReader(new FileReader(isfile.get(j).getFilepath().replace("\\" , "\\\\")));
			
			String line1 = reader1.readLine();
			String line2 = reader2.readLine();
			boolean areEqual = true;
			int lineNum = 1;

			while (line1 != null || line2 != null)
			{
				if(line1 == null || line2 == null)
				{
					areEqual = false;
					break;
				}
				else if(! line1.equalsIgnoreCase(line2))
				{
					areEqual = false;
					break;
				}
	 
				line1 = reader1.readLine();
				line2 = reader2.readLine();
	 
				lineNum++;
			}
	
			if(areEqual)
			{

				System.out.println("Two files have same content.");
				System.out.println("File1 and File2 has same content "+line2+" at line "+lineNum);
				System.out.println("File 1 : "+isfile.get(i)+" & File 2 : "+isfile.get(j)+"\n");
				int m = j;
				int loop=1;
				
				for(int k = i; loop<3; k=j) {
				loop++;
				String id = isfile.get(k).getFile_id();
				String sql = "Update content set status=? where file_id ="+id ;
				Connection con=DBConnection.doConnection();
				PreparedStatement preparedStatement=con.prepareStatement(sql);
				preparedStatement.setString(1, "Duplicate");
				preparedStatement.executeUpdate(); 
				m=i;
				
				
				}
			}
		
			else
			{
				System.out.println("Two files have different content. They differ at line "+lineNum);
				System.out.println("File 1 : "+isfile.get(j-1)+" & File 2 : "+isfile.get(j)+"\n");
		}
		}
		}
	}
  
	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException, IOException  
	{  	
		String maindirpath = "D:\\bengkel1";
	      
	    // File object 
	    File maindir = new File(maindirpath); 
	    
	 	Test();
	 	
	 }
		
}