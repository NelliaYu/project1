package com.project.wf.reservation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.project.wf.login.Login;
import com.project.wf.login.Member;

/**
 * 회원 - 방문 예약에 관련된 클래스
 * @author 이미현
 *
 */
public class Reservation {
	// 회원2. 방문 예약
	
	private static Scanner scan;
	private final static String RESERVATION_LIST;
	private final static String MEMBER;

	private final static String LIMIT;
	
	private static ArrayList<ReservationCheck> reservationList; //방문예약목록 리스트에 담기
	private static ArrayList<Member> memberList; //회원목록 리스트에 담기
	
	
	static {
		
		scan = new Scanner(System.in);
		RESERVATION_LIST = "dat\\2. ReservationCheck.dat"; //방문예약 더미
		MEMBER = "dat\\1. MemberList.dat"; //회원리스트 더미 
		LIMIT = "dat\\2. ReservationLimit.dat"; //최대방문객수 데이터
		
		reservationList = new ArrayList<ReservationCheck>();
		memberList = new ArrayList<Member>();
		
	}
	
	/**
	 * 방문 예약 관련 메뉴를 가져오는 메소드
	 * @param memberNum 회원번호 계속적으로 사용하기 위한 매개변수
	 * @throws IOException getMenu
	 */
	public static void getMenu(String memberNum) throws IOException {
		

		reservationLoad(); //방문예약 목록 ArrayList에 담기
		memberLoad(); //회원목록 ArrayList에 담기

		
		boolean loop = true;
		
		while(loop){
			
			System.out.println("=============================");
			System.out.println("1. 방문 예약하기");
			System.out.println("2. 예약 확인하기");
			System.out.println("0. 뒤로가기");
			System.out.println("=============================");
			
			System.out.print("번호 입력 : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")){
				reservation(memberNum);
				
			}else if(sel.equals("2")){
				check(memberNum);
			}else{
				loop = false;
			}
		}//while
		
		pause();
		
		save(); //최종적으로 파일에 저장
	}

	/**
	 * 방문예약 확인하는 메소드
	 * @param memberNum 회원번호 계속적으로 사용하기 위한 매개변수
	 * @throws IOException check
	 */
	public static void check(String memberNum) throws IOException {
		//2. 예약 확인하기
		
		System.out.println("[예약 확인하기]");
		//현재 로그인한 회원번호로 RESERVATION_LIST 들어가서 본인 예약건만 뽑아서 출력
		
		System.out.println("[예약번호]\t[이름]\t\t[전화번호]\t\t[텃밭번호]\t[예약방문일]\t[상태]");
		
		String name = "";
		String phone = "";
		
		//현재 로그인한 회원의 정보(이름,전화번호) 가져오기
		for (Member member : memberList) {
			if (member.getMemberNum().equals(memberNum)) {
				name = member.getMemberName();
				phone = member.getMemberPhone();
				
			}
		}
		
		//입력받은 회원번호와 파일의 회원번호가 같다면 출력
		for (ReservationCheck reCheck : reservationList) {
			if (reCheck.getMemberNum().equals(memberNum)) { 
				System.out.printf("%s\t\t%s\t\t%s\t\t%s\t\t%s\t%s\n" 
						, reCheck.getReservationNum()
						, name
						, phone
						, reCheck.getFarmNum()
						, reCheck.getDate()
						, reCheck.getStatus());
			}
		}
		
		
		//예약 취소하기 뒤로가기
		System.out.println("=============================");
		System.out.println("1. 예약 취소하기 0. 뒤로가기");
		System.out.println("=============================");
		
		System.out.print("번호 입력 : ");
		int sel = scan.nextInt();
		scan.nextLine();
		
		if (sel == 1) {
			delete(memberNum);
		} else {
			pause();
		}
		
		
	}

	/**
	 * 방문예약 취소하는 메소드
	 * @param memberNum 회원번호 계속적으로 사용하기 위한 매개변수
	 */
	public static void delete(String memberNum) {
		//예약확인 파일에서 찾아서 삭제! //가 아니라 예약상태를 취소로 바꿔줌
		
		System.out.print("취소할 예약번호: ");
		String deleteNum = scan.nextLine().toUpperCase();
		
		boolean result = false;
		
		for (int i = 0; i < reservationList.size(); i++) {
			
			if (reservationList.get(i).getMemberNum().equals(memberNum)) {
				
				if (reservationList.get(i).getReservationNum().equals(deleteNum)) {
					reservationList.get(i).setStatus("취소");
					
					System.out.println("=============================");
					System.out.println("취소가 완료되었습니다.");
					System.out.println("=============================");
					
					result = true;
				} 
			}
			
		}//for

		if (!result) {
			System.out.println("=============================");
			System.out.println("취소할 예약번호가 올바르지 않습니다.");
			System.out.println("=============================");
		}
		
		pause();
		
		
	}

	/**
	 * 방문 예약하기 위한 메소드
	 * @param memberNum 회원번호 계속적으로 사용하기 위한 매개변수
	 */
	public static void reservation(String memberNum) {
		//회원 - 2.1 방문 예약하기
		
		System.out.println("[방문 예약하기]");
		
		selectDay(memberNum);
		
		
	}
	
	/**
	 * 예약 진행하기 위한 현재 달력 출력하는 메소드
	 * @param memberNum 회원번호 계속적으로 사용하기 위한 매개변수
	 */
	public static void selectDay(String memberNum) {

		Calendar c = Calendar.getInstance();
		int moveMonth = 0;
		boolean flag = true;
		boolean startdayflag = true;
		while (startdayflag) {
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int startday = 1;

			while (flag) {
				month += moveMonth;

				if (moveMonth == 1) {
					if (month == 12) {
						year += 1;
						month = 0;
						c.set(year, month, startday);
					} else {
						c.set(year, month, startday);
					}
				} else if (moveMonth == -1) {
					if (month == -1) {
						year -= 1;
						month = 11;
						c.set(year, month, startday);
					} else {
						c.set(year, month, startday);
					}
				} else {
					month += moveMonth;
					c.set(year, month, startday);
				} // 달력 월수 제대로 뽑아주기위해 분기태우는 if end

				int week = c.getActualMaximum((Calendar.WEEK_OF_MONTH)); //해당 월 몇주까지 있는지
				int startDate = c.get(Calendar.DAY_OF_WEEK); //요일
				int lastDay = c.getActualMaximum(Calendar.DATE); //해당 월 며칠까지 있는지

				System.out.println("==========================");
				System.out.printf("\t\t\t%d년 %d월     \n", year, month + 1);
				System.out.println("\t\t==========================");
				System.out.print("[일]\t[월]\t[화]\t[수]\t[목]\t[금]\t[토]\n");
				for (int i = 0; i < startDate - 1; i++) {
					System.out.print("\t");
				} // 달력 시작일 전에 공백 찍어주는 반복문

				for (int i = 1; i <= lastDay; i++) {
					System.out.print(i + "\t"); // 달력 1234 쭈루륵 찍어줌
					
					if(i%7==(7-startDate+1)%7){//토요일마다 개행?
						System.out.println();
					}
				}

				System.out.println();
				System.out.println("==========================");
				System.out.println("1. 이전달 2. 다음달");
				System.out.println("3. 원하는 날짜 입력하기 0. 뒤로가기");
				System.out.println("==========================");
				System.out.print("번호 입력 : ");
				int sel = scan.nextInt();
				scan.nextLine();
				if (sel == 1) {
					moveMonth = -1;
				} else if (sel == 2) {
					moveMonth = +1;
				} else if (sel == 3) {
					System.out.print("예약할 날짜: ");
					int visitDay = scan.nextInt();
					scan.nextLine();
					Calendar checkDay = Calendar.getInstance();
					if (c.get(Calendar.YEAR) == checkDay.get(Calendar.YEAR)) {
						if (lastDay < visitDay) {
							System.out.printf("%d일보다 큰 수는 입력하실 수 없습니다.\n", lastDay);
							moveMonth = 0;
						} else if (c.get(Calendar.MONTH) <= checkDay.get(Calendar.MONTH)
								&& visitDay < checkDay.get(Calendar.DATE)) {
							System.out.println("==========================");
							System.out.println("지난 일은 예약하실 수 없습니다.");
							System.out.println("==========================");
							moveMonth = 0;
							pause();
						} else if (c.get(Calendar.MONTH) < checkDay.get(Calendar.MONTH)) {
							System.out.println("==========================");
							System.out.println("지난 달은 예약하실 수 없습니다.");
							System.out.println("==========================");
							moveMonth = 0;
							pause();
						} else {
							if(lastDay<visitDay){
								System.out.printf("%d일보다 큰 수는 입력하실 수 없습니다.\n",lastDay);
								moveMonth = 0;
							}else if(lastDay>=visitDay){ //모든 검사 통과하고 예약가능할때
								
								//최대방문객수 체크해서 가능한지 검사..
								String date = String.format("%d%02d%02d", year,month+1,visitDay);
								try {
									limitCheck(date);
								} catch (IOException e) {
									System.out.println(e);
								}
								
								//최종적으로 예약할건지 뒤로갈건지
								reservationEnd(date, memberNum);
								
								flag = false;
								startdayflag = false; //루프 종료
							}

						} // 예약 날짜 지난날 체크
					}
				} else if (sel == 4) {
					flag = false;
					startdayflag = false;
					moveMonth = 0;
//					return 1;
				} else {
					flag = false;
					startdayflag = false; //루프 종료
					
					pause();
				} // 출발일 선택 메뉴 분기 if end

			}// flag while
		} // startdayflag while

	}
	

	/**
	 * 회원이 선택한 날짜에 최종적으로 예약하는 메소드
	 * @param date 회원이 선택한 날짜 계속적으로 사용하기 위한 매개변수
	 * @param memberNum 회원번호 계속적으로 사용하기 위한 매개변수
	 */
	public static void reservationEnd(String date, String memberNum) {
		//최종적으로 예약할건지 뒤로갈건지
		
		System.out.println("=============================");
		System.out.println("1. 방문 예약하기");
		System.out.println("0. 뒤로가기");
		System.out.print("번호 입력 : ");
		int sel = scan.nextInt();
		
		if (sel == 1) {
			
			String farmNum = getFarmNum(memberNum);
			
			//회원번호/예약번호/텃밭번호/예약방문일/상태 
			//입력받은 내용 바로 file에 넣는게 아니라 ArrayList에 먼저! 그담에 넣어줌
			ReservationCheck reCheck = new ReservationCheck();
			
			reCheck.setMemberNum(memberNum);
			reCheck.setReservationNum(getReservationNum()); //마지막 예약번호 + 1
			reCheck.setFarmNum(farmNum);
			reCheck.setDate(date); //예약방문일
			reCheck.setStatus("예약");
			
			reservationList.add(reCheck);

			System.out.println("예약이 완료되었습니다.");
			System.out.println("=============================");
			
			pause();
			scan.nextLine(); 
			
		}
		
		
	}

	/**
	 * 회원의 텃밭 번호 가져오는 메소드
	 * @param memberNum 회원번호 계속적으로 사용하기 위한 매개변수
	 * @return 회원의 텃밭 번호를 반환
	 */
	public static String getFarmNum(String memberNum) {
		
		for (Member m : memberList) {
			if (m.getMemberNum().equals(memberNum)) {
				return m.getMemberFarmNum();
			}
		}
		return null;
		
	}

	/**
	 * 모든 행동이 끝난 뒤, ArrayList에 담겨있던 데이터를 파일에 저장하는 메소드
	 */
	public static void save() {
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(RESERVATION_LIST));
			
			for (ReservationCheck rc : reservationList) {
				
				writer.write(String.format("%s★%s★%s★%s★%s\n"
											, rc.getMemberNum()
											, rc.getReservationNum()
											, rc.getFarmNum()
											, rc.getDate() 
											, rc.getStatus()));
				
			}
			
			writer.close(); //************ 
			System.out.println("=============================");
			
//			pause();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	/**
	 * 추가될 예약의 예약번호 구하는 메소드
	 * @return 추가될 예약번호
	 */
	public static String getReservationNum() {
		
		int max = 0;
		
		for (ReservationCheck rc : reservationList) {
			int num = Integer.parseInt(rc.getReservationNum().substring(1));
			
			if (num > max) {
				max = num;
			}
		}
		
		return "D" + (max + 1);
		
	}

	/**
	 * 특정 날짜에 방문 예약 가능한지 체크하는 메소드
	 * @param date 회원이 선택한 날짜 계속적으로 사용하기 위한 매개변수
	 * @throws IOException reader.readLine()
	 * @throws NumberFormatException Integer.parseInt
	 */
	public static void limitCheck(String date) throws NumberFormatException, IOException { //매개변수 = 사용자가 선택한 예약날짜
		//최대방문객수 체크해서 예약 가능한지 검사
		
		
		try {
			//최대방문객수 파일 읽어서 max변수에 담기
			BufferedReader readerLimit = new BufferedReader(new FileReader(LIMIT));
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		
		int reservationCnt = 0;
		
		for (ReservationCheck reCheck : reservationList) {
			//"예약" 상태인 것 중에서
			if (reCheck.getStatus().equals("예약")) {
				//사용자가 선택한 날짜에 예약이 몇개 있는지
				if (reCheck.getDate().equals(date)) {
					reservationCnt++;
				}
			}
			
			
		}//for
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(LIMIT));
			
			int limit = Integer.parseInt(reader.readLine()); //max값 읽어오기
			
			if (limit > reservationCnt) {
				System.out.println("=============================");
				System.out.println("예약이 가능한 날짜입니다.");
				System.out.printf("남은 예약 가능 팀 : %d팀\n", limit - reservationCnt);
				System.out.println("=============================");
			}
			
			
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		
		pause();
		
		
	}

	
	/**
	 * 텃밭 방문 예약 목록 읽어서 ArrayList에 담기 위한 메소드
	 * @throws IOException reader.readLine()
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
	 * 회원목록 ArrayList에 담기 위한 메소드
	 * @throws IOException reader.readLine()
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
	private static void pause() { 
		System.out.println("엔터를 누르시면 다음으로 진행합니다.");
		scan.nextLine(); //Block 
	}
	
	

}
