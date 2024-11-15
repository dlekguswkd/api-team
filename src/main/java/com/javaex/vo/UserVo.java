package com.javaex.vo;

public class UserVo {
	
	//필드
	private int userNum;
	private int regionNum;
	private int purchaseNum;
	private String userEmail;
	private String userPw;
	private String userName;
	private String socialLogin;
	private String usingProfilePic;
	private String userStatus;
	private int rates;
	
	
	
	//생성자
	public UserVo() {
		super();
	}



	public UserVo(int userNum, int regionNum, int purchaseNum, String userEmail, String userPw, String userName,
			String socialLogin, String usingProfilePic, String userStatus, int rates) {
		super();
		this.userNum = userNum;
		this.regionNum = regionNum;
		this.purchaseNum = purchaseNum;
		this.userEmail = userEmail;
		this.userPw = userPw;
		this.userName = userName;
		this.socialLogin = socialLogin;
		this.usingProfilePic = usingProfilePic;
		this.userStatus = userStatus;
		this.rates = rates;
	}



	public int getUserNum() {
		return userNum;
	}



	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}



	public int getRegionNum() {
		return regionNum;
	}



	public void setRegionNum(int regionNum) {
		this.regionNum = regionNum;
	}



	public int getPurchaseNum() {
		return purchaseNum;
	}



	public void setPurchaseNum(int purchaseNum) {
		this.purchaseNum = purchaseNum;
	}



	public String getUserEmail() {
		return userEmail;
	}



	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
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



	public String getSocialLogin() {
		return socialLogin;
	}



	public void setSocialLogin(String socialLogin) {
		this.socialLogin = socialLogin;
	}



	public String getUsingProfilePic() {
		return usingProfilePic;
	}



	public void setUsingProfilePic(String usingProfilePic) {
		this.usingProfilePic = usingProfilePic;
	}



	public String getUserStatus() {
		return userStatus;
	}



	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}



	public int getRates() {
		return rates;
	}



	public void setRates(int rates) {
		this.rates = rates;
	}



	@Override
	public String toString() {
		return "UserVo [userNum=" + userNum + ", regionNum=" + regionNum + ", purchaseNum=" + purchaseNum
				+ ", userEmail=" + userEmail + ", userPw=" + userPw + ", userName=" + userName + ", socialLogin="
				+ socialLogin + ", usingProfilePic=" + usingProfilePic + ", userStatus=" + userStatus + ", rates="
				+ rates + "]";
	}

	
}
