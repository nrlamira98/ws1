package model;

public class User {
	
	private int id;
	private String name;
	private String password;
	private int level;
	private String address;
	private String email;
	private String notel;
	private String username;
	
	
	public User() {
		
	}
	
	public User(int id, String name, String password, String address, String email, String notel, String username) {
		this.id =id;
		this.name = name;
		this.password = password;
		this.address = address;
		this.email = email;
		this.notel = notel;
		this.username = username;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNotel() {
		return notel;
	}
	public void setNotel(String notel) {
		this.notel = notel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
