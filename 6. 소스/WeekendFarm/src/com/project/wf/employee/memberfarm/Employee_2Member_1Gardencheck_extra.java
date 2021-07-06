package com.project.wf.employee.memberfarm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 직원, 관리자가 회원의 텃밭 현황을 조회하는 클래스
 */
public class Employee_2Member_1Gardencheck_extra {
	//직원] 2. 회원 농장 조회 - 2.1 회원 텃밭 현황
	
	private static Scanner scan;
	private static String DATA;
	private static String DATA1;
	private static String membernum; //회원번호
	private static int plantcount; //회원재배수량
	
	
	static {
		membernum = ""; //두 파일간 공유할 회원번호들
		scan = new Scanner(System.in);
		DATA = "dat\\1. MemberList.dat"; //회원정보목록
		DATA1 = "dat\\3. PlanListDummy.dat"; //농기두대여목록
		plantcount = 0;
	}
	
	/**
	 * 직원, 관리자가 전체회원의 텃밭 현황을 확인한 후, 원하는 회원의 텃밭 현황만 조회할 수 있게 하는 메소드
	 */
	public void memberplantcheck() {
	
		boolean loop = true;
		while(loop) {
			
			list();
			System.out.println();
			
			// 뒤로가기 버튼
			System.out.println("1.회원 선택\t\t\t\t0.뒤로가기");
			System.out.print("번호 입력: ");
			String back = scan.nextLine();
			
			
			boolean loop2 = true;
			//조회할 회원 번호
			while(loop2) {
			
				if(back.equals("1")) {
					System.out.print("조회할 회원 번호: ");
					String member = scan.nextLine();
					membercheck(member.toUpperCase());
					System.out.println();
					
					System.out.println("1.회원 선택\t\t\t\t0.뒤로가기");
					System.out.print("번호 입력: ");
					back = scan.nextLine();
					
				} else if (back.equals("0")) {
					System.out.println("[종료]");
					loop2 = false;
				}
			}//while
			
			if(back.equals("0")) {
				loop = false;
			}
			
		}//while

	
	}
	
	/**
	 * 직원, 관리자가 전체회원(작물을 재배하지 않는 회원 제외)의 텃밭 현황을 확인할 수 있는 메소드
	 */
	private void list() {
		// 회원 텃밭 현황 목록
		
		try {

			BufferedReader reader = new BufferedReader(new FileReader(DATA));
			
			String line = "", line1 = "";
			
			System.out.println("[회원 텃밭 현황 목록]");
			System.out.println("[회원 번호]  [이름]\t\t[전화번호]\t\t[텃밭 면적]\t[텃밭 대여 가격]\t\t[텃밭 대여 기간]\t\t[재배중인 농작물]\t[재배 농작물 종류 수량]");
			
				while ((line = reader.readLine()) != null) {//회원목록

					String[] temp = line.split("★");
					membernum = temp[0];
					
						BufferedReader reader1 = new BufferedReader(new FileReader(DATA1));
						while ((line1 = reader1.readLine()) != null) { //PlanListDummy
							
							String[] temp1 = line1.split("★"); 
							
							if (membernum.equals(temp1[4])) {
								
									System.out.printf("  %s\t   %s\t      %s\t  %4s㎡\t\t%,9d원\t      %s\t\t%8s\t\t%,1d\n"
										, temp[0]
										, temp[1]
										, temp[3]
										, temp[9]
										, Integer.parseInt(temp[10])
										, temp[6] + "~" + temp[7]
										, temp1[2] //재배중 농작물 	 		
										, Integer.parseInt(temp1[5]) //재배 수량
										); 		
								
							}
							
						} 
						reader1.close();
					
					}
			reader.close();
			

		} catch (Exception e) {
			System.out.println(e);
		}
		
		
	}
	
	/**
	 * 직원, 관리자가 원하는 회원의 번호를 입력하여 해당 회원의 텃밭 현황만을 확인할 수 있는 메소드
	 * @param member 조회할 회원 번호
	 */
	private void membercheck(String member) {
		// 입력한 회원번호만 조회
		
		try {

			BufferedReader reader = new BufferedReader(new FileReader(DATA));
			
			String line = "", line1 = "";
			
			System.out.println("[회원 텃밭 현황 목록]");
			System.out.println("[회원 번호]  [이름]\t\t[전화번호]\t\t[텃밭 면적]\t[텃밭 대여 가격]\t\t[텃밭 대여 기간]\t\t[재배중인 농작물]\t[재배 농작물 종류 수량]");
			
				while ((line = reader.readLine()) != null) {//회원목록

					String[] temp = line.split("★");
					membernum = temp[0];
					
					if(member.equals(membernum)) {
					
						BufferedReader reader1 = new BufferedReader(new FileReader(DATA1));
						while ((line1 = reader1.readLine()) != null) { //PlanListDummy
							
							String[] temp1 = line1.split("★"); 
							
							if (membernum.equals(temp1[4])) {
								
									System.out.printf("  %s\t   %s\t      %s\t  %4s㎡\t\t%,9d원\t      %s\t\t%8s\t\t%,1d\n"
										, temp[0]
										, temp[1]
										, temp[3]
										, temp[9]
										, Integer.parseInt(temp[10])
										, temp[6] + "~" + temp[7]
										, temp1[2] //재배중 농작물 	 		
										, Integer.parseInt(temp1[5]) //재배 수량
										); 		
								
							}
							
						} 
						reader1.close();
				}
					
			}
			reader.close();
			

		} catch (Exception e) {
			System.out.println(e);
		}
		
		
	}
	
	

}//class
