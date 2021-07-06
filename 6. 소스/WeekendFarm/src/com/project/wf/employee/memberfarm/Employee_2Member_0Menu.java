package com.project.wf.employee.memberfarm;

import java.util.Scanner;

/**
 * 직원, 관리자가 전체회원의 농장 관련 정보들을 조회하기 위해 필요한 메뉴를 불러오는 클래스 
 */
public class Employee_2Member_0Menu {
	//직원] 메뉴 -> 2.회원 농장 조회 메뉴화면
	
	private static Scanner scan;
	
	static {
		scan = new Scanner(System.in);
	}
	
	/**
	 * 직원, 관리자가 회원의 농장관련 정보들을 조회하기 위해 필요한 상세메뉴를 불러오는 메서드
	 */
	public static void employeemenu() {
		
		System.out.println("[2. 회원 농장 조회]");
		
		boolean loop = true;
		while(loop) {
			
			String seq = menu();
			if(seq.equals("1")) {
				//회원 텃밭 현황				
				Employee_2Member_1Gardencheck employee = new Employee_2Member_1Gardencheck();
				employee.Memberplantcheck();
				
			} else if(seq.equals("2")) {
				//회원 정보 검색
				Employee_2Member_2MemberSearch employee = new Employee_2Member_2MemberSearch();
				employee.MemberSearch();
				
			} else if(seq.equals("3")) {
				//회원 농자재 구매내역
				Employee_2Member_3Plantbuycheck employee = new Employee_2Member_3Plantbuycheck();
				employee.Memberplantbuylist();
				
			} else if(seq.equals("4")) {
				//회원 농기구 대여내역
				Employee_2Member_4Toolrentcheck employee = new Employee_2Member_4Toolrentcheck();
				employee.Membertoolbuylist();
				
			} else if(seq.equals("5")) {
				//회원 농장계약서
				Employee_2Member_5Farmcontract employee = new Employee_2Member_5Farmcontract();
				employee.Farmcontract();
			
			} else if(seq.equals("0")) {
				//뒤로가기
				//직원 메뉴 목록으로 back
				loop = false;
				
			} 
			
			
		}//while
		
		
	}//main
	
	/**
	 * 직원, 관리자가 [2. 회원 농장 조회]를 들어온 상태에서 보여지는 메뉴
	 * @return 원하는 메뉴로 이동하기 위해 직원, 관리자가 입력할 번호값
	 */
	private static String menu() {
		
		System.out.println("====================");
		System.out.println("1. 회원 텃밭 현황");
		System.out.println("2. 회원 정보 검색");
		System.out.println("3. 회원 농자재 구매내역");
		System.out.println("4. 회원 농기구 대여내역");
		System.out.println("5. 회원 농장계약서");
		System.out.println("0. 뒤로가기");
		System.out.println("====================");
		System.out.print("번호 입력: ");
		
		String sel = scan.nextLine();
		
		return sel;
	}



}
