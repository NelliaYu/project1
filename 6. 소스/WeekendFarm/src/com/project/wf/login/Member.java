package com.project.wf.login;

/**
 * 회원 정보를 담는 객체
 * @author 이미현
 *
 */
public class Member {
	
	private String memberNum;
	private String memberName;
	private String memberBirth;
	private String memberPhone;
	private String memberAddress;
	private String memberAccount;
	private String memberFarmStart;
	private String memberFarmEnd;
	private String memberFarmNum;
	private String memberArea;
	private String memberPrice;
	
	//생성자
	public Member() {
		
	}

	public Member(String memberNum, String memberName, String memberBirth, String memberPhone, String memberAddress,
			String memberAccount, String memberFarmStart, String memberFarmEnd, String memberFarmNum, String memberArea,
			String memberPrice) {
		super();
		this.memberNum = memberNum;
		this.memberName = memberName;
		this.memberBirth = memberBirth;
		this.memberPhone = memberPhone;
		this.memberAddress = memberAddress;
		this.memberAccount = memberAccount;
		this.memberFarmStart = memberFarmStart;
		this.memberFarmEnd = memberFarmEnd;
		this.memberFarmNum = memberFarmNum;
		this.memberArea = memberArea;
		this.memberPrice = memberPrice;
	}

	
	//Getter, Setter
	public String getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberBirth() {
		return memberBirth;
	}

	public void setMemberBirth(String memberBirth) {
		this.memberBirth = memberBirth;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public String getMemberAccount() {
		return memberAccount;
	}

	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}

	public String getMemberFarmStart() {
		return memberFarmStart;
	}

	public void setMemberFarmStart(String memberFarmStart) {
		this.memberFarmStart = memberFarmStart;
	}

	public String getMemberFarmEnd() {
		return memberFarmEnd;
	}

	public void setMemberFarmEnd(String memberFarmEnd) {
		this.memberFarmEnd = memberFarmEnd;
	}

	public String getMemberFarmNum() {
		return memberFarmNum;
	}

	public void setMemberFarmNum(String memberFarmNum) {
		this.memberFarmNum = memberFarmNum;
	}

	public String getMemberArea() {
		return memberArea;
	}

	public void setMemberArea(String memberArea) {
		this.memberArea = memberArea;
	}

	public String getMemberPrice() {
		return memberPrice;
	}

	public void setMemberPrice(String memberPrice) {
		this.memberPrice = memberPrice;
	}

	@Override
	public String toString() {
		return "Member [memberNum=" + memberNum + ", memberName=" + memberName + ", memberBirth=" + memberBirth
				+ ", memberphone=" + memberPhone + ", memberAddress=" + memberAddress + ", memberAccount="
				+ memberAccount + ", memberFarmStart=" + memberFarmStart + ", memberFarmEnd=" + memberFarmEnd
				+ ", memberFarmNum=" + memberFarmNum + ", memberArea=" + memberArea + ", memberPrice=" + memberPrice
				+ "]";
	}

	
	

}
