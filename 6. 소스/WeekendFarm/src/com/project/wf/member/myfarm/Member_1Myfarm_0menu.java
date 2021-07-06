package com.project.wf.member.myfarm;

import java.io.IOException;
import java.util.Scanner;

/**
 * 회원이 내 농장과 관련한 정보들을 조회하기 위해 필요한 메뉴를 불러오는 클래스 
 */
public class Member_1Myfarm_0menu {
	//1.내 농장 조회 메뉴 목록
	
	private static Scanner scan;
	
	static {
		scan = new Scanner(System.in);
	}
	
	/**
	 * 회원이 자신의 농장관련 정보들을 조회하기 위해 필요한 상세메뉴를 불러오는 메서드
	 */
	public static void membermenu() {
		
		System.out.println("[1. 내 농장 조회]");
		
		boolean loop = true;
		while(loop) {
			
			String seq = menu();
			if(seq.equals("1")) {
				//재배 농작물 확인
				//1Plantcheck 클래스 임포트
				Member_1Myfarm_1Plantcheck myfarm = new Member_1Myfarm_1Plantcheck();
				myfarm.MyfarmPlantcheck();
				
			} else if(seq.equals("2")) {
				//텃밭 현황
				Member_1Myfarm_2Gardencheck myfarm = new Member_1Myfarm_2Gardencheck();
				myfarm.Myfarmfarmcheck();
				
			} else if(seq.equals("3")) {
				//농작물 구매 내역
				Member_1Myfarm_3Seedbuycheck myfarm = new Member_1Myfarm_3Seedbuycheck();
				myfarm.Myfarmplantbuylist();
				
			} else if(seq.equals("4")) {
				//농기구 대여 현황
				Member_1Myfarm_4Toolrentcheck myfarm = new Member_1Myfarm_4Toolrentcheck();
				myfarm.Myfarmtoolbuylist();
				
				
			} else if(seq.equals("5")) {
				//농장 계약서 조회
				Member_1Myfarm_5Farmcontract myfarm = new Member_1Myfarm_5Farmcontract();
				myfarm.Myfarmcontract();
			
				
			} else if(seq.equals("0")) {
				//뒤로가기
				//회원 메뉴 목록으로 back
				loop = false;
				
			} 
			
			//System.out.println("[종료]");
			
		}//while
		
		
	}//main
	
	/**
	 * 회원이 [1. 내 농장 조회]를 들어온 상태에서 보여지는 메뉴
	 * @return 원하는 메뉴로 이동하기 위해 회원이 입력할 번호값
	 */
	private static String menu() {
		
		System.out.println("====================");
		System.out.println("1. 재배 농작물 확인");
		System.out.println("2. 텃밭 현황");
		System.out.println("3. 농작물 구매내역");
		System.out.println("4. 농기구 대여 현황");
		System.out.println("5. 농장 계약서 조회");
		System.out.println("0. 뒤로가기");
		System.out.println("====================");
		System.out.print("번호 입력: ");
		
		String sel = scan.nextLine();
		
		return sel;
	}
	
	/**
	 * 다음으로 화면이 구현되기 전, 일시정지를 해주는 메소드
	 */
	private static void pause() {
		System.out.println("엔터를 누르시면 다음으로 이동됩니다.");
		scan.nextLine();
	}

}
