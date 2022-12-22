package pageclass;



//pojo - plain old java object
public class User {

	String email;
	String password;
	String id;
	String token;
	
	

	public User() {

	}

	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	
	//getters and setters methods:
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String pwd) {
		this.password = password;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	
	
	
	
	
	
	

}