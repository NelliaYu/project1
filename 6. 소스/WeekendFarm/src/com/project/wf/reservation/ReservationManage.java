package com.project.wf.reservation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.project.wf.login.Member;

/**
 * 직원, 관리자 - 방문 예약 관리하는 클래스
 * @author 이미현
 *
 */
public class ReservationManage {
//직원, 관리자 - 3.1 방문 예약 관리
	
	private static Scanner scan;
	private final static String RESERVATION_LIST;
	private final static String MEMBER;
	private final static String LIMIT;
	
	private static ArrayList<ReservationCheck> reservationList; //방문예약 리스트에 담기
	private static ArrayList<Member> memberList; //방문예약 리스트에 담기
	
	
	static {
		
		scan = new Scanner(System.in);
		RESERVATION_LIST = "dat\\2. ReservationCheck.dat"; //방문예약 더미
		MEMBER = "dat\\1. MemberList.dat"; //회원리스트 더미 
		LIMIT = "dat\\2. ReservationLimit.dat"; //최대방문객수 데이터
		
		reservationList = new ArrayList<ReservationCheck>();
		memberList = new ArrayList<Member>();
		
	}
	
	/**
	 * 방문예약 관리 메뉴를 가져오는 메소드
	 * @param managerNum 직원/관리자번호 계속적으로 사용하기 위한 매개변수
	 * @throws IOException BufferedReader 오류
	 */
	public static void getMenu(String managerNum) throws IOException {
		
		
		memberLoad();
		reservationLoad();
		
		boolean loop = true;
		
		while(loop){
			System.out.println("====================");
			System.out.println("1. 방문 예약 확인하기");
			System.out.println("2. 방문 객수 설정하기");
			System.out.println("0. 뒤로가기");
			System.out.println("====================");
			
			System.out.print("번호 선택 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")){
				reservation();
			}else if(sel.equals("2")){
				limit();
			}else{
				loop = false;
			}
		}//while
	}
	
	/**
	 * 방문 예약 목록 확인하는 메소드
	 */
	public static void reservation() {
		//직원, 관리자 - 3.1 방문 예약 확인하기
		
		System.out.println("[예약 신청 완료 목록]");
		System.out.println("[회원 번호]\t[회원 이름]\t[회원 전화번호]\t\t[예약 방문일]");
		
//		int cnt = 0;
		
		for (int i = 0; i < reservationList.size(); i++) { 
			if (reservationList.get(i).getStatus().equals("예약")) {
				System.out.printf("%s\t\t%s\t\t%s\t\t%s\n"
						, reservationList.get(i).getMemberNum()
						, getName(reservationList.get(i).getMemberNum()) //회원 이름
						, getPhone(reservationList.get(i).getMemberNum()) //회원 전화번호
						, reservationList.get(i).getDate());
				
//				cnt++;
//				if (cnt == 10) { //예약번호 앞에서부터 10개만 잘라서 보여주기 
//					break;
//				}
			}
			
		}//for
		
		System.out.println("====================");
		System.out.println("1. 회원선택");
		System.out.println("0. 뒤로가기");
		System.out.println("====================");
		
		System.out.print("번호 입력 : ");
		String sel = scan.nextLine();
		
		if(sel.equals("1")){
			
			memberPick();
			
		} else {
			
		}
		pause();
		
	}


	/**
	 * 선택한 회원의 방문예약 정보를 출력하는 메소드
	 */
	public static void memberPick() {
		
		System.out.print("조회할 회원 번호: ");
		String memberNum = scan.nextLine().toUpperCase();
		
		System.out.println("[회원 번호]\t[회원 이름]\t[회원 전화번호]\t\t[예약 방문일]");
		
		for (ReservationCheck reCheck : reservationList) {
			if ((reCheck.getMemberNum()).equals(memberNum)) {
				System.out.printf("%s\t\t%s\t\t%s\t\t%s\n"
						, reCheck.getMemberNum()
						, getName(reCheck.getMemberNum()) //회원 이름
						, getPhone(reCheck.getMemberNum()) //회원 전화번호
						, reCheck.getDate());
			}
		}
		
	}

	/**
	 * 방문 예약한 회원의 이름 가져오는 메소드
	 * @param memberNum 직원/관리자번호 계속적으로 사용하기 위한 매개변수
	 * @return 방문 예약한 회원의 이름
	 */
	public static String getName(String memberNum) {
		
		String name = "";
		
		for (Member member : memberList) {
			if ((member.getMemberNum()).equals(memberNum)) {
				name = member.getMemberName();
				
			}
		}
		
		return name;
	}
	
	/**
	 * 방문 예약한 회원의 전화번호 가져오는 메소드
	 * @param memberNum 직원/관리자번호 계속적으로 사용하기 위한 매개변수
	 * @return 방문 예약한 회원의 전화번호
	 */
	public static String getPhone(String memberNum) {
		
		String phone = "";
		
		for (Member member : memberList) {
			if ((member.getMemberNum()).equals(memberNum)) {
				phone = member.getMemberPhone();
				
			}
		}
		
		return phone;
	}

	/**
	 * 하루 최대 방문 객수 설정하는 메소드
	 */
	public static void limit() {
		//직원, 관리자 - 3.2 방문 객수 설정하기
		
		System.out.print("최대 방문객 수 입력: ");
		String max = scan.nextLine();
		System.out.println("====================");
		
		System.out.println("====================");
		System.out.println("1. 저장하기");
		System.out.println("0. 뒤로가기");
		System.out.println("====================");
		
		System.out.print("번호 입력 : ");
		String sel = scan.nextLine();
		
		if(sel.equals("1")){
			save(max);
			
		} else {
			
		}
		pause();
		
	}


	/**
	 * 최대 방문객 수를 파일에 저장하는 메소드
	 * @param max 입력받은 최대 방문객 수
	 */
	public static void save(String max) {
		//입력받은 최대 방문 객수 저장하기
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(LIMIT));
			
			writer.write(max);
			
			writer.close();
		
			System.out.println("저장이 완료되었습니다.");
			
//			pause();
		
		} catch (Exception e) {
			System.out.println("최대방문객수" + e);
		}
		
		
	}
	
	
	/**
	 * 텃밭 방문 예약 목록 읽어서 ArrayList에 담기 위한 메소드
	 * @throws IOException reservationLoad()
	 */
	public static void reservationLoad() throws IOException {
		
		//BufferedReader
		// - memo.dat -> ArrayList<Memo>
		
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(RESERVATION_LIST));
			
			String line = "";
			
			while ((line = reader.readLine()) != null) { //중요한 while문. 핵심.
				
				//ReservationCheck 객체 1개 생성
				ReservationCheck reCheck = new ReservationCheck();
				
				//회원번호/예약번호/텃밭번호/예약방문일/상태 
				
				String[] temp = line.split("★");
				
				//System.out.println(Arrays.toString(temp));
				
				reCheck.setMemberNum(temp[0]);
				reCheck.setReservationNum(temp[1]);
				reCheck.setFarmNum(temp[2]);
				reCheck.setDate(temp[3]);
				reCheck.setStatus(temp[4]);
				
				
				
				//방문예약 1건 -> ReservationCheck 객체 1개에 옮겨 담기
				
				reservationList.add(reCheck);
				
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			
			System.out.println("load: " + e);
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
			
			while ((line = reader.readLine()) != null) { //중요한 while문. 핵심.
				
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
				member.setMemberFarmNum(temp[7]);
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
		scan.nextLine(); //Block //사용자가 엔터 칠때까지 블럭걸림
	}


}
