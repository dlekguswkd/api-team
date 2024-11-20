// src/main/java/com/javaex/vo/UserVo.java

package com.javaex.vo;

public class HmkUserVo {
	//필드
    private int userNum;
    private String userEmail;
    private String nickname;
    private String profileImage;
    private String userStatus;
    private String socialLogin;
    private String region;
    private String currentPassword;
    private String newPassword;
    private String ownedProfileImages;
 // 소유한 프로필 이미지 리스트
    

	// 챌린지 통계 필드
    private int ongoingChallenges;
    private int upcomingChallenges;
    private int completedChallenges;
    private double participationScore;
    
	//생성자
    public HmkUserVo() {
		super();
	}

	public HmkUserVo(int userNum, String userEmail, String nickname, String profileImage, String userStatus,
			String socialLogin, String region, String currentPassword, String newPassword, int ongoingChallenges,
			int upcomingChallenges, int completedChallenges, double participationScore) {
		super();
		this.userNum = userNum;
		this.userEmail = userEmail;
		this.nickname = nickname;
		this.profileImage = profileImage;
		this.userStatus = userStatus;
		this.socialLogin = socialLogin;
		this.region = region;
		this.currentPassword = currentPassword;
		this.newPassword = newPassword;
		this.ongoingChallenges = ongoingChallenges;
		this.upcomingChallenges = upcomingChallenges;
		this.completedChallenges = completedChallenges;
		this.participationScore = participationScore;
	}
	
    //getter-setter

	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getSocialLogin() {
		return socialLogin;
	}

	public void setSocialLogin(String socialLogin) {
		this.socialLogin = socialLogin;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public int getOngoingChallenges() {
		return ongoingChallenges;
	}

	public void setOngoingChallenges(int ongoingChallenges) {
		this.ongoingChallenges = ongoingChallenges;
	}

	public int getUpcomingChallenges() {
		return upcomingChallenges;
	}

	public void setUpcomingChallenges(int upcomingChallenges) {
		this.upcomingChallenges = upcomingChallenges;
	}

	public int getCompletedChallenges() {
		return completedChallenges;
	}

	public void setCompletedChallenges(int completedChallenges) {
		this.completedChallenges = completedChallenges;
	}

	public double getParticipationScore() {
		return participationScore;
	}

	public void setParticipationScore(double participationScore) {
		this.participationScore = participationScore;
	}
	
	public String getOwnedProfileImages() {
		return ownedProfileImages;
	}

	public void setOwnedProfileImages(String ownedProfileImages) {
		this.ownedProfileImages = ownedProfileImages;
	}

	@Override
	public String toString() {
		return "UserVo [userNum=" + userNum + ", userEmail=" + userEmail + ", nickname=" + nickname + ", profileImage="
				+ profileImage + ", userStatus=" + userStatus + ", socialLogin=" + socialLogin + ", region=" + region
				+ ", currentPassword=" + currentPassword + ", newPassword=" + newPassword + ", ownedProfileImages="
				+ ownedProfileImages + ", ongoingChallenges=" + ongoingChallenges + ", upcomingChallenges="
				+ upcomingChallenges + ", completedChallenges=" + completedChallenges + ", participationScore="
				+ participationScore + "]";
	}

	

    
    
}