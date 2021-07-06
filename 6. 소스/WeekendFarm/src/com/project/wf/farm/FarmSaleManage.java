package com.project.wf.farm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.project.wf.login.Member;
import com.project.wf.reservation.ReservationCheck;

/**
 * 직원, 관리자 - 텃밭 분양 관리 클래스
 * @author 이미현
 *
 */
public class FarmSaleManage {
	//직원, 관리자 - 4. 텃밭 분양 관리

	private static Scanner scan;
	private final static String RESERVATION_LIST;
	private final static String MEMBER;
	private final static String FARMSALE;
	
	private static ArrayList<ReservationCheck> reservationList; //방문예약 리스트에 담기
	private static ArrayList<Member> memberList; //방문예약 리스트에 담기
	private static double[] farmSale = new double[4]; //텃밭분양 정보 담기
	
	
	static {
		
		scan = new Scanner(System.in);
		RESERVATION_LIST = "dat\\2. ReservationCheck.dat"; //방문예약 더미
		MEMBER = "dat\\1. MemberList.dat"; //회원리스트 더미 
		FARMSALE = "dat\\3. FarmSale.dat"; //회원 텃밭 분양 정보
		
		reservationList = new ArrayList<ReservationCheck>();
		memberList = new ArrayList<Member>();
		
	}
	
	/**
	 * 현재 회원들의 텃밭 분양 상태를 가져오는 메소드 
	 * @param managerNum 직원/관리자번호 계속적으로 사용하기 위한 매개변수
	 */
	public static void getStatus(String managerNum) {
		
		farmSaleLoad();
		
		System.out.println("[텃밭 분양 관리 현황]");
		
		System.out.println("[텃밭 분양]");
		System.out.println("[총 평수]\t\t\t[분양 가능 평수]\t\t[1인 분양 가능 최대 평수]\t[평당 가격 정보(30일)]");
		System.out.printf("%,.0f㎡(%,.0f평)\t%,.1f㎡(%,.1f평)\t%,.1f㎡(%,.1f평)\t\t%,.0f원\n"
						, farmSale[0], farmSale[0]*0.3025
						, memberArea(), memberArea()*0.3025
						, farmSale[1], farmSale[1]*0.3025
						, farmSale[2]);
		
		
		getMenu(managerNum);
		
		save();
		
		
		
	}
	
	/**
	 * 관리자가 수정한 평당 가격 데이터 파일에 저장하는 메소드
	 */
	public static void save() {
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(FARMSALE));
			
			writer.write(String.format("%s★%s★%s★%s"
								, farmSale[0]
								, farmSale[1]
								, farmSale[2]
								, farmSale[3]));
			
			writer.close();
			System.out.println("==========================");
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	/**
	 * 현재 로그인한 사람의 유형(직원/관리자)에 따라 메뉴를 가져오는 메소드
	 * @param managerNum 직원/관리자번호 계속적으로 사용하기 위한 매개변수
	 */
	public static void getMenu(String managerNum) {
		if (managerNum.startsWith("Y".toUpperCase())) { //직원
			
			System.out.println("============================");
			System.out.println("0. 뒤로가기");
			System.out.println("============================");
			
			System.out.print("번호 입력: ");
			scan.nextLine();
			
		} else if (managerNum.startsWith("Z".toUpperCase())) { //관리자
			
			
			boolean loop = true;
			
			while (loop) {
				
				System.out.println("============================");
				System.out.println("1. 평당 가격 수정하기");
				System.out.println("0. 뒤로가기");
				System.out.println("============================");
				
				System.out.print("번호 입력: ");
				int sel = scan.nextInt();
				
				if (sel == 1) {
					
					System.out.println("============================");
					System.out.print("수정할 평당 가격: ");
					int newPrice = scan.nextInt();
					System.out.println("============================");
					
					farmSale[2] = newPrice;
					
					System.out.println("가격 수정이 완료되었습니다.");
					System.out.println("============================");
					
					pause();
					
				} else {
					loop = false;
					
					pause();
				}
				
			}//while
			
		}
		
	}

	/**
	 * 현재 분양 가능한 평수 구하는 메소드
	 * @return 현재 분양 가능한 평수
	 */
	public static double memberArea() {
		//전체 면적에서 현재 회원들에게 분양중인 면적을 뺀 평수 구하기 
		
		double sum = 0;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(MEMBER));
			
			String line = "";
			
			while((line = reader.readLine()) != null) {
				
				String[] temp = line.split("★");
				
				sum += Double.parseDouble(temp[9]);
				
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return sum;
	}
	
	
	/**
	 * 텃밭 분양 정보를 읽어오는 메소드
	 */
	public static void farmSaleLoad() {
		//FarmSale.dat -> String[] farmSale
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(FARMSALE));
			
			String line = "";
			while((line = reader.readLine()) != null) {
				String[] temp = line.split("★");
				
				farmSale[0] = Double.parseDouble(temp[0]); //총 평수
				farmSale[1] = Double.parseDouble(temp[1]); //1인 분양 가능 최대 평수
				farmSale[2] = Double.parseDouble(temp[2]); //평당 가격 정보(30일)
				farmSale[3]	= Double.parseDouble(temp[3]); //30일 이후 하루 대여료
				
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	/**
	 * 특정 행동이 끝났을 때 잠시 멈추기 위한 메소드
	 */
	public static void pause() { 
		System.out.println("엔터를 누르시면 다음으로 진행합니다.");
		scan.nextLine(); //Block 
	}
	
}
