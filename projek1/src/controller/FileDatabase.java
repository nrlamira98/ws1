package controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import database.DBConnection;
import model.Files;
 

public class FileDatabase {

    // Recursive Java program to print all files 
    // in a folder(and sub-folders)

	public static void UpdateDB(File[] arr,int index,int level) throws ClassNotFoundException, SQLException, ParseException  
    { 
		File file = new File("D:\\bengkel1");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       	System.out.println(sdf.format(file.lastModified()));
        // terminate condition 
        if(index == arr.length) 
        	return; 
                
        // tabs for internal levels 
        for (int i = 0; i < level; i++) 
            System.out.print("\t"); 
        
        System.out.println(arr[index].isFile());
        
        
        // for files 
        if(arr[index].isFile()) 
        {
        	StorageController sc = new StorageController();
           	file = new File(arr[index].getName());
       		Timestamp ts = Timestamp.valueOf(sdf.format(arr[index].lastModified()));
       		File path = new File(arr[index].getAbsolutePath());
       		Files files = new Files();
       		files.setStatus("non-duplicate");
       		files.setType("file");
       		sc.AddFiles(file, ts, path, files);
       		
        }         
                
        // for sub-directories 
        else if(arr[index].isDirectory()) 
        { 
        	System.out.println("[" + arr[index].getName() + "]"); 
                 
            // recursion for sub-directories 
            UpdateDB(arr[index].listFiles(), 0, level + 1); 
            StorageController sc = new StorageController();
           	file = new File(arr[index].getName());
       		Timestamp ts = Timestamp.valueOf(sdf.format(arr[index].lastModified()));
       		File path = new File(arr[index].getAbsolutePath());
       		Files files = new Files();
       		files.setStatus("non-duplicate");
       		files.setType("folder");
       		sc.AddFiles(file, ts,path, files);
      		
        } 
    
           // recursion for main directory 
           UpdateDB(arr,++index, level); 
    } 
           
    // Driver Method 
	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException 
	{ 
		// Provide full path for directory(change accordingly)   
        String maindirpath = "D:\\bengkel1";
                       
        // File object 
        File maindir = new File(maindirpath); 
             
        File arr[] = maindir.listFiles(); 
                   
       	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       	
       	StorageController sc = new StorageController();
       	File file = new File(maindirpath);
   		Timestamp ts = Timestamp.valueOf(sdf.format(maindir.lastModified()));
   		File path = new File(maindir.getAbsolutePath());
   		Files files = new Files();
   		files.setStatus("non-duplicate");
   		files.setType("folder");
   		sc.AddFiles(file, ts, path, files);
       	
       
        // Calling recursive method 
        UpdateDB(arr,0,0);  
        //}  
    }
}