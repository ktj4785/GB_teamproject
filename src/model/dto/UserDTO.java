package model.dto;

public class UserDTO {
	
	private String userId;
	private String userPw;
	private String userName;
	private int userAge;
	private String socialNum;
	private String phone;
	private String userAddr;
	
	public UserDTO(String userId, String userPw, String userName, int userAge, String socialNum, String phone,
			String userAddr) {
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userAge = userAge;
		this.socialNum = socialNum;
		this.phone = phone;
		this.userAddr = userAddr;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public String getSocialNum() {
		return socialNum;
	}
	public void setSocialNum(String socialNum) {
		this.socialNum = socialNum;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUserAddr() {
		return userAddr;
	}
	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}
	
	
}




