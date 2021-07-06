package com.project.wf.member.myfarm;

import java.io.IOException;
import java.util.Scanner;

import com.project.wf.login.Login;
import com.project.wf.login.mainclass;

/**
 * 회원이 자신의 농장 계약서를 확인할 수 있는 클래스
 */
public class Member_1Myfarm_5Farmcontract {
	//회원] 1. 내 농장 조회 - 5.농장 계약서 조회
	
	private static Scanner scan;
	private static String mynum; //메인에서 가져오는 회원번호/직원번호
	private static String member;
	
	
	static {
		mynum = mainclass.inputwho; // 입력받은 회원번호
		scan = new Scanner(System.in);
	}
	
	/**
	 * 회원이 자신의 농장 계약서를 확인 후, 전 페이지로 이동할 수 있는 메소드
	 */
	public void Myfarmcontract() {
		
		boolean loop = true;
		while(loop) {
			
			contractcheck(mynum);
			System.out.println("\"실행된 파일을 확인하세요.\"");
			System.out.println();
			
			// 뒤로가기 버튼
			System.out.println("0.뒤로가기");
			System.out.print("번호 입력: ");
			String back = scan.nextLine();
			
			if(back.equals("0")) {
				System.out.println("[종료]");
				loop = false;
			}
		}//while

	
	}
	
	/**
	 * 회원이 자신의 농장계약서를 파일로 실행시키는 메소드
	 * @param mynum 조회할 회원 번호
	 */
	private void contractcheck(String mynum) {
		
		try {
			Process p1 = new ProcessBuilder("notepad.exe"
					, "dat\\회원 농장 계약서\\"+mynum+".txt").start();
			
		} catch (IOException e) {
				System.out.println(e);
		}
		
	}//메소드
	

}
