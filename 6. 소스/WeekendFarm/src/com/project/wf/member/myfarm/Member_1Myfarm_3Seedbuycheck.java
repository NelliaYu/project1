package com.project.wf.member.myfarm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import com.project.wf.login.mainclass;

/**
 * 회원이 구매한 농작물(씨앗) 목록을 조회하는 클래스
 */
public class Member_1Myfarm_3Seedbuycheck {
	//회원] 1. 내 농장 조회 - 3. 농작물 구매 현황
	
	private static String mynum; //메인에서 가져오는 회원번호/직원번호
	private static Scanner scan;
	private static String DATA;
	private static String DATA1;
	
	static {

		mynum = mainclass.inputwho; // 입력받은 회원번호
		scan = new Scanner(System.in);
		DATA = "dat\\4. MemberSeed.dat";
		DATA1 = "dat\\4. Seed.dat";
	}
	
	
	/**
	 * 회원이 구매한 농작물(씨앗) 목록을 확인 후, 전 페이지로 이동할 수 있는 메소드
	 */
	public void Myfarmplantbuylist() {
		
		boolean loop = true;
		while(loop) {
			
			list(mynum);
			
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
	 * 회원이 구매한 농작물(씨앗)과 관련한 정보를 확인할 수 있는 메소드
	 * @param mynum 로그인시 입력된 회원번호
	 */
	private void list(String mynum) {
		//회원 - 1.3 농작물 구매 내역
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(DATA)); //4. MemberSeed.dat
			
			String line = "", line1 = "";
			String result = "";
			String plantname; //농작물 이름(파일간 연결고리)
			
			System.out.println("[농작물 구매 목록]");
			System.out.println("[농작물 번호]\t[농작물 종류]\t[농작물 이름]\t[씨앗 수량]\t[씨앗 가격]\t[총 결제 금액]");
			
			while ((line=reader.readLine()) != null) {
				
				String temp[] = line.split("★");
				
				
				if (mynum.toUpperCase().equals(temp[0])) { //로그인 회원번호=data내 회원번호
				plantname = temp[2];
				
					
				BufferedReader reader1 = new BufferedReader(new FileReader(DATA1)); //4. Seed.dat
				
				while ((line1=reader1.readLine()) != null) {
					
					String temp1[] = line1.split("★");
					
					if(plantname.equals(temp1[1])) { //같은 농작물 이름찾기
					
						result = String.format("  %6s\t%6s\t%5s\t\t%5d개\t\t%,6d원\t\t%,5d원\n"
								,temp1[3]
								,temp1[0]
								,temp1[1]
								,Integer.parseInt(temp[3])
								,Integer.parseInt(temp[4])
								,Integer.parseInt(temp[3]) * Integer.parseInt(temp[4])
								);
						
						
							}
						}
						reader1.close();
						System.out.println(result);
					}
				
			}
			reader.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}

		
	}//list
	
	

}
