package model;

import java.io.File;
import java.sql.Timestamp;
import java.util.stream.Stream;

public class Files {

	private String Userid;
	private String file_id;
	private String file_name;
	private String TotalAdd;
	private String TotalDelete;
	private String CanonicalFile;
	private Timestamp TimeStamp;
	private String Path;
	private String Status;
	private File Name;
	private String filepath;
	private String type;
	
	
	public Files()
	{
	
	}
	
	public Files(String file_id, String file_name, Timestamp TimeStamp, String filepath, String Userid) {
		this.file_id = file_id;
		this.file_name = file_name;
		this.TimeStamp = TimeStamp;
		this.filepath = filepath;
		this.Userid = Userid;
	}
	
	public Files(String User_id, String file_id, String file_name, Timestamp TimeStamp, String Status) {
		this.Userid = User_id;
		this.file_id = file_id;
		this.file_name = file_name;
		this.TimeStamp = TimeStamp;
		this.Status = Status;
	}
	
	public Files(String file_name, Timestamp TimeStamp, String Status) {
		this.file_name = file_name;
		this.TimeStamp = TimeStamp;
		this.Status = Status;
	}
	
	public void setPath(String Path) {
		this.Path = Path;
	}
	
	public String getPath() {
		return Path; 
	}
	
	public void setUserid(String Userid) {
		this.Userid = Userid;
	}
	
	public String getUserid() {
		return Userid;
	}
	
	public void setTimeStamp(Timestamp TimeStamp) {
		this.TimeStamp = TimeStamp;
	}
	
	public Timestamp getTimeStamp() {
		return TimeStamp;
	}
	
	public String getTotalAdd() {
		return TotalAdd;
	}
	
	public void setTotalAdd(String totalAdd) {
		TotalAdd = totalAdd;
	}
	
	public String getTotalDelete() {
		return TotalDelete;
	}
	
	public void setTotalDelete(String totalDelete) {
		TotalDelete = totalDelete;
	}
	
	public String getCanonicalFile() {
		return CanonicalFile;
	}
	public void setCanonicalFile(String canonicalFile) {
		CanonicalFile = canonicalFile;
	}

	public static Stream<java.nio.file.Path> walk(java.nio.file.Path path2) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public File getName() {
		return Name;
	}

	public void setName(File name) {
		Name = name;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	
}