package com.project.wf.member.myfarm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import com.project.wf.login.mainclass;

/**
 * 회원이 대여한 농기구 목록을 조회하는 클래스
 */
public class Member_1Myfarm_4Toolrentcheck {
	//회원] 1. 내 농장 조회 - 4. 농기구 대여 현황
	
	private static String mynum; //메인에서 가져오는 회원번호/직원번호
	private static Scanner scan;
	private static String DATA;
	
	static {

		mynum = mainclass.inputwho; // 입력받은 회원번호
		scan = new Scanner(System.in);
		DATA = "dat\\5. FarmToolRentalList.dat";
	}
	
	/**
	 * 회원이 대여한 농기구 목록을 확인 후, 전 페이지로 이동할 수 있는 메소드
	 */
	public void Myfarmtoolbuylist() {
		
		boolean loop = true;
		while(loop) {
			
			list(mynum);
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
		
		
	}//class

	/**
	 * 회원이 대여한 농기구와 관련한 정보를 확인할 수 있는 메소드
	 * @param mynum 로그인시 입력된 회원번호
	 */
	private void list(String mynum) {
		//회원 - 1.4 농기구 대여 현황
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(DATA)); //5. FarmToolRentalList.dat
			String line = "";
			
			System.out.println("[농기구 대여 목록]");
			System.out.println("[농기구 번호]\t[농기구 이름]\t[대여 개수]\t[대여 시작일]\t[대여 종료일]\t[총 결제 금액]");
			
			while ((line=reader.readLine()) != null) {
				String temp[] = line.split("★");
				
				if (mynum.toUpperCase().equals(temp[0])) { //로그인 회원번호=data내 회원번호
							
					System.out.printf("%7s\t\t%2s\t\t%4d개\t\t%9s\t%9s\t%,8d원\n"
									,temp[1]
									,temp[2]
									,Integer.parseInt(temp[3])
									,temp[4]
									,temp[5]
									,Integer.parseInt(temp[3]) * Integer.parseInt(temp[6])
									);
					}
			}
			reader.close();
			
		} catch (Exception e) {
			System.out.println(e);
		
		}
		
	}

}
