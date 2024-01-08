package vo;


public class MemberVO {

	private String userID;
	
	private String password; // UNIQUE
	
	private String userName;
	
	private String phoneNum;
	
	public MemberVO() {}

	public MemberVO(String userID, String password) {
		this.userID = userID;
		this.password = password;
	}

	public MemberVO(String userID, String password, String userName, String phoneNum) {
		this.userID = userID;
		this.password = password;
		this.userName = userName;
		this.phoneNum = phoneNum;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	@Override
	public String toString() {
		return userID + "," + password + "," + userName + ","+ phoneNum;
	}
	
}
