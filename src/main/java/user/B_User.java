package user;

public class B_User {

	String userId;
	String userPassword;
	String userName;
	String userTel;
	String userBirthday;
	
	public B_User(String userId, String userPassword, String userName, String userTel, String userBirthday) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userTel = userTel;
		this.userBirthday = userBirthday;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserTel() {
		return userTel;
	}

	public String getUserBirthday() {
		return userBirthday;
	}

}