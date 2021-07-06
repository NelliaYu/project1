package com.project.wf.farm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.project.wf.login.Member;
import com.project.wf.reservation.ReservationCheck;

/**
 * 회원 - 텃밭 분양에 관련된 클래스
 * @author 이미현
 *
 */
public class FarmSale {
	//회원 - 3. 텃밭 분양
	
	private static Scanner scan;
	private final static String RESERVATION_LIST;
	private final static String MEMBER;
	private final static String LIMIT;
	private final static String FARMSALE;
	
	private static ArrayList<ReservationCheck> reservationList; //방문예약 리스트에 담기
	private static ArrayList<Member> memberList; //방문예약 리스트에 담기
	private static double[] farmSale = new double[4]; //텃밭분양 정보 담기
	
	
	static {
		
		scan = new Scanner(System.in);
		RESERVATION_LIST = "dat\\2. ReservationCheck.dat"; //방문예약 더미
		MEMBER = "dat\\1. MemberList.dat"; //회원리스트 더미 
		LIMIT = "dat\\2. ReservationLimit.dat"; //최대방문객수 데이터
		FARMSALE = "dat\\3. FarmSale.dat"; //회원 텃밭 분양 정보
		
		reservationList = new ArrayList<ReservationCheck>();
		memberList = new ArrayList<Member>();
		
	}
	
	/**
	 * 텃밭을 추가로 분양하기 위한 메소드
	 * @param memberNum 회원번호 계속적으로 사용하기 위한 매개변수
	 * @throws IOException farmSale
	 */
	public static void farmSale(String memberNum) throws IOException { //memberNum : 회원번호
		
		farmSaleLoad();
		memberLoad();
		
		//평당 가격은 관리자가 수정할 수 잇는 부분//총평수/1인분양가능최대평수/평당가격정보(30일)/30일이후 하루 대여료 //이
		System.out.println("[텃밭 분양]");
		System.out.println("[총 평수]\t\t\t[분양 가능 평수]\t\t[1인 분양 가능 최대 평수]\t[평당 가격 정보(30일)]\t[30일 이후]");
		System.out.printf("%,.0f㎡(%,.0f평)\t%,.1f㎡(%,.1f평)\t%,.1f㎡(%,.1f평)\t\t1평당 %,.0f원\t\t1일당 %,.0f원 추가\n"
						, farmSale[0], farmSale[0]*0.3025
						, memberArea(), memberArea()*0.3025
						, farmSale[1], farmSale[1]*0.3025
						, farmSale[2]
						, farmSale[3]);
		
		getMenu(memberNum);
		
		save();
		
	}

	/**
	 * memberList에 담겨있던 데이터를 파일에 저장하는 메소드
	 */
	public static void save() {
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(MEMBER));
			
			for (Member member : memberList) {
				
				//회원번호/이름/생년월일/전화번호/주소/은행계좌번호/
				//텃밭대여시작일/텃밭대여종료일/텃밭번호/텃밭면적/텃밭가격
				writer.write(String.format("%s★%s★%s★%s★%s★%s★%s★%s★%s★%s★%s\n"
								, member.getMemberNum()
								, member.getMemberName()
								, member.getMemberBirth()
								, member.getMemberPhone()
								, member.getMemberAddress()
								, member.getMemberAccount()
								, member.getMemberFarmStart()
								, member.getMemberFarmEnd()
								, member.getMemberFarmNum()
								, member.getMemberArea()
								, member.getMemberPrice()));
			}
			
			writer.close(); //************ 
			System.out.println("==========================");
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	/**
	 * 텃밭 분양에 대한 메뉴를 가져오는 메 소드
	 * @param memberNum 회원번호 계속적으로 사용하기 위한 매개변수
	 */
	public static void getMenu(String memberNum) {
		//분양받을지 뒤로갈지 고르기
		
		boolean loop = true;
		
		while (loop) {
			System.out.println("==========================");
			System.out.println("1. 분양 받기");
			System.out.println("0. 뒤로가기");
			System.out.println("==========================");
			
			System.out.print("번호 입력:");
			int sel = scan.nextInt();
			
			if (sel == 1) {
				//신규회원의 텃밭분양? > 애초에 가입할때부터 분양받음
				//기존회원의 텃밭분양 > 기존 평수랑 기간에 +
				
				System.out.print("분양 받을 평수(10평 이상): ");//제곱미터 X 평수 O
				Double area = scan.nextDouble();
				
				System.out.print("분양 받을 기간(30일 이상): ");
				int date = scan.nextInt();
						
				
				if (area >= 10 && date >= 30) {
					area *= 3.305785; //평수 -> 제곱미터 전환
					try {
						
						for (Member member : memberList) {
							if (member.getMemberNum().equals(memberNum)) {
								member.setMemberArea(getArea(area, member.getMemberArea()));
								member.setMemberFarmEnd(getFarmEnd(date, member.getMemberFarmEnd()));
								member.setMemberPrice(getPrice(date, member.getMemberPrice()));
							}
						}
						
					} catch (Exception e) {
						System.out.println( e);
					}
					
					System.out.println("추가 분양이 완료되었습니다.");
				} else {
					System.out.println("올바르지 않은 값입니다.");
				}
				
				
			} else {
				loop = false;
			}
		}
		
		
		pause();
		
	}

	/**
	 * 텃밭을 추가로 분양받은 뒤 기존 가격에 더해질 가격을 합친 최종 가격을 구하는 메소드
	 * @param addDate 추가된 분양 기간을 계속적으로 사용하기 위한 매개변수
	 * @param memberPrice 회원의 기존 텃밭 대여 가격을 계속적으로 사용하기 위한 매개변수
	 * @return 최종 텃밭 대여 가격
	 */
	public static String getPrice(int addDate, String memberPrice) {
		//가격이 중간에 변동되었을 수도 있으니 한번에 구하는게 아니라 기존가격 + 현재가격
		
		int mprice = Integer.parseInt(memberPrice);
		
		int totalPrice = 0;
		
		totalPrice = mprice + addDate*1500;
		
		return String.format("%s", totalPrice);
	}
	
	/**
	 * 텃밭 대여 기간 추가한 뒤 총 대여기간 구하는 메소드
	 * @param addDate 추가된 대여기간을 계속적으로 사용하기 위한 매개변수
	 * @param memberFarmEnd 회원의 기존 텃밭 대여 종료일을 계속적으로 사용하기 위한 매개변수
	 * @return 총 텃밭 대여 기간
	 */
	public static String getFarmEnd(int addDate, String memberFarmEnd) {
		
		Calendar c = Calendar.getInstance();
		
		int year = Integer.parseInt(memberFarmEnd.substring(0,4)); 
		int month = Integer.parseInt(memberFarmEnd.substring(4,6)); 
		int date = Integer.parseInt(memberFarmEnd.substring(6)); 
		
		c.set(year, month-1, date);
		c.add(0, addDate);
		
		String total = String.format("%d%02d%02d", c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DATE));
		
		return total;
	}

	/**
	 * 텃밭을 추가로 분양받은 뒤 총 면적을 구하는 메소드
	 * @param area 사용자가 입력한 추가될 면적을 계속적으로 사용하기 위한 매개변수
	 * @param memberArea 기존 면적을 계속적으로 사용하기 위한 매개변수
	 * @return 총 텃밭 면적
	 */
	public static String getArea(Double area, String memberArea) { //매개변수 : 추가될 면적, 기존 면적 

		double d = Double.parseDouble(memberArea);
		
		return String.format("%,.1f", area + d);
	}

	/**
	 * 현재 분양 가능한 평수 구하는 메소드
	 * @return memberArea()
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
	 * 텃밭 분양 정보 파일 읽어오는 메소드
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
	 * 회원목록 ArrayList에 담는 메소드
	 * @throws IOException memberLoad()
	 */
	public static void memberLoad() throws IOException {
		
		//BufferedReader
		// - memberList.dat -> ArrayList<Member>
		
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(MEMBER));
			
			String line = "";
			
			while ((line = reader.readLine()) != null) {
				
				//Member 객체 1개 생성
				Member member = new Member();
				
				//회원번호/예약번호/텃밭번호/예약방문일/상태 
				
				String[] temp = line.split("★");
				
				//System.out.println(Arrays.toString(temp));
				
				member.setMemberNum(temp[0]);
				member.setMemberName(temp[1]);
				member.setMemberBirth(temp[2]);
				member.setMemberPhone(temp[3]);
				member.setMemberAddress(temp[4]);
				member.setMemberAccount(temp[5]);
				member.setMemberFarmStart(temp[6]);
				member.setMemberFarmEnd(temp[7]);
				member.setMemberFarmNum(temp[8]);
				member.setMemberArea(temp[9]);
				member.setMemberPrice(temp[10]);
				
				//방문예약 1건 -> ReservationCheck 객체 1개에 옮겨 담기
				
				memberList.add(member);
				
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			
			System.out.println("load: " + e);
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
