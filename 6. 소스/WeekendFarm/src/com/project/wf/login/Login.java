package com.project.wf.login;

public class Login {

	private String check;


	/**
	 * 회원/직원/관리자 판별 메소드
	 * @return 입력한 번호
	 */
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
	
	/**
	 * 입력된 ID Setter
	 * @param check 입력된 ID
	 */
	public void setCheck(String check) {
		this.check = check;
	}


}
