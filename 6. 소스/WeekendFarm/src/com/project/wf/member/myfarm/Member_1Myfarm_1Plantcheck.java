package com.project.wf.member.myfarm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import com.project.wf.login.mainclass;

/**
 * 회원이 재배중인 농작물 현황을 조회하는 클래스
 */
public class Member_1Myfarm_1Plantcheck {
	// 1.내 농장 조회 - 1.재배 농작물 확인
	// 부여받은 고유의 회원번호가 있음

	private static Scanner scan;
	private static String DATA;
	private static String DATA1;
	private static String mynum; //메인에서 가져오는 회원번호/직원번호

	static {

		mynum = mainclass.inputwho; // 입력받은 회원번호
		scan = new Scanner(System.in);
		DATA = "dat\\3. PlanListDummy.dat";
		DATA1 = "dat\\3. PlantList.dat";
	}
	
/**
 * 회원이 재배중인 농작물 목록을 확인 후, 전 페이지로 이동할 수 있는 메소드
 */
	public void MyfarmPlantcheck() {
		
		boolean loop = true;
		while(loop) {
			
			list(mynum);
			
			// 뒤로가기 버튼
			System.out.println("0.뒤로가기");
			System.out.print("번호 입력: ");
			String back = scan.nextLine();
			
			if(back.equals("0")) {
				loop = false;
			}
			
		}

		
	}//메인메서드

	/**
	 * 회원이 재배중인 농작물과 관련한 정보를 확인할 수 있는 메소드
	 * @param mynum 로그인시 입력된 회원번호
	 */
	private void list(String mynum) {
		
		try {

			BufferedReader reader = new BufferedReader(new FileReader(DATA));
			
			String line = "", line1 = "";
			String result = "";
			String plantnum; //두 파일간 공유할 제품 번호

			System.out.println("[재배 농작물 목록]");
			System.out.println("[농작물 번호]\t[농작물 이름]\t[농작물 종류]\t[재배 수량]\t[재배 시작일]\t[재배 종료일]\t[가격]");

				while ((line = reader.readLine()) != null) {// PlanListDummy
					
					String[] temp = line.split("★");
					
					plantnum = temp[1]; // 농작물번호

					int plantamout = Integer.parseInt(temp[5]); // 재배수량

					if (mynum.equals(temp[4])) { //로그인 회원번호=data내 회원번호

						BufferedReader reader1 = new BufferedReader(new FileReader(DATA1));

						while ((line1 = reader1.readLine()) != null) {// PlanList

							String[] temp1 = line1.split("★");
							int price1kg = Integer.parseInt(temp1[3]); // 1kg당 가격

							if (plantnum.equals(temp1[0])) { // 같은 농작물번호 찾기**

								result = String.format("%6s\t\t%6s\t\t%7s\t%5skg\t\t%s\t%s\t%,d원\n"
										, temp[1]
										, temp[2]
										, temp[3]
										, temp[5]
										, temp[6]
										, temp[7]
										, plantamout * price1kg);
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
		
	}

}
