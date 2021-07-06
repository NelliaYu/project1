package com.project.wf.resource;

public class Login {

	private String member;
	private String manager;
	private String boss;
	private String check;



	public String getCheck() {
		if(this.check.startsWith("X".toUpperCase())) {
			System.out.print("안녕하세요. 회원 ");
		}else if(this.check.startsWith("Y".toUpperCase())) {
			System.out.print("안녕하세요. 직원 ");
		}else if(this.check.startsWith("Z".toUpperCase())) {
			System.out.print("안녕하세요. 관리자 ");
		}
		
		return check.toUpperCase();
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public String getMember() {

		return member;
	}

	public void setMember(String member) {

		this.member = member;

	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {

		this.manager = manager;

	}

	public String getBoss() {
		return boss;
	}

	public void setBoss(String boss) {

		this.boss = boss;

	}

}
