package com.project.wf.login;

import java.io.IOException;
import java.util.Scanner;

import com.project.wf.employee.memberfarm.Employee_2Member_0Menu;
import com.project.wf.farm.FarmSale;
import com.project.wf.farm.FarmSaleManage;
import com.project.wf.market.MarketSell;
import com.project.wf.market.manage.MarketSell_manage;
import com.project.wf.member.myfarm.Member_1Myfarm_0menu;
import com.project.wf.membership.MemberShip;
import com.project.wf.notice.FarmManagement_NoticeMenu;
import com.project.wf.notice.FarmManagement_NoticeMenu_Admin;
import com.project.wf.reservation.Reservation;
import com.project.wf.reservation.ReservationManage;
import com.project.wf.resource.FarmManagement_Menu;
import com.project.wf.resource.FarmManagement_Menu_Admin;

/**
 * 메인화면 객체
 * 
 * @author 4조
 *
 */
public class mainclass {

	private static Scanner scan;
	/**
	 * 회원/직원/관리자번호 입력
	 */
	public static String inputwho; // ID입력번호
	static {
		scan = new Scanner(System.in);
		// 이미지 불러오는 클래스
		Image i = new Image();
		i.imagepicture();

	}

	/**
	 * 초기화면 메인메소드
	 * 
	 * @param args ID유효성검사, 메인메뉴 및 각 메뉴호출
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		while (true) {

			System.out.print("회원번호/직원번호 : ");
			inputwho = scan.nextLine().toUpperCase(); // ID입력번호

			// 유효성검사1) 글자수는 4글자
			if (inputwho.length() == 4) {

				char a = inputwho.charAt(0);
				char b = inputwho.charAt(1);
				char c = inputwho.charAt(2);
				char d = inputwho.charAt(3);

				// 유효성검사2) 1번째는 알파벳(X,Y,Z) & 2~4번째는 숫자
				if ((a == 'X' || a == 'Y' || a == 'Z' || a == 'x' || a == 'y' || a == 'z') && (b >= '0' && b <= '9')
						&& (c >= '0' && c <= '9') && (d >= '0' && d <= '9')) {

					Login login = new Login();
					login.setCheck(inputwho.toUpperCase());
					System.out.print(login.getCheck() + "님" + "\r\n");

					boolean loop = true;

					while (loop) {

						if (inputwho.startsWith("X")) { // 회원화면
							String sel = menu();

							if (sel.equals("1")) {
								// 내 농장 조회
								Member_1Myfarm_0menu.membermenu();
							} else if (sel.equals("2")) {
								// 방문예약관리
								Reservation.getMenu(inputwho);
							} else if (sel.equals("3")) {
								// 텃밭분양
								FarmSale.farmSale(inputwho);
							} else if (sel.equals("4")) {
								FarmManagement_Menu fmMenu = new FarmManagement_Menu();
								fmMenu.FarmManagment_MainMenu(inputwho.toUpperCase());

							} else if (sel.equals("5")) {
								// 5번 거래소 메소드
								MarketSell.Maketloop();
							} else if (sel.equals("6")) {
								FarmManagement_NoticeMenu noticeMenu = new FarmManagement_NoticeMenu();
								noticeMenu.noticeMenu(inputwho.toUpperCase());

							} else if (sel.equals("0")) {
								loop = false;
							}
						}
						if (inputwho.startsWith("Y") || inputwho.startsWith("Z")) {// 직원 or 관리자 화면
							String sel = menu2();

							if (sel.equals("1")) {
								MemberShip.membership();
							} else if (sel.equals("2")) {
								// 회원 농장 조회
								Employee_2Member_0Menu.employeemenu();

							} else if (sel.equals("3")) {
								// 방문 예약 관리
								ReservationManage.getMenu(inputwho);
							} else if (sel.equals("4")) {
								// 회원 텃밭 관리
								FarmSaleManage.getStatus(inputwho);
							} else if (sel.equals("5")) {
								// 농자재 관리
								FarmManagement_Menu_Admin fmMenuAdmin = new FarmManagement_Menu_Admin();
								fmMenuAdmin.FarmManagment_MainMenu_Admin(inputwho.toUpperCase());
							} else if (sel.equals("6")) {
								// 날씨

							} else if (sel.equals("7")) {
								// 병해충

							} else if (sel.equals("8")) {
								// 거래소
								MarketSell_manage.Maketloop();
							} else if (sel.equals("9")) {
								// 알림함
								FarmManagement_NoticeMenu_Admin noticeMenuAdmin = new FarmManagement_NoticeMenu_Admin();
								noticeMenuAdmin.noticeMenu_Admin(inputwho.toUpperCase());

							} else if (sel.equals("10")) {
								// 인사관리

							} else if (sel.equals("0")) {
								// 뒤로가기
								loop = false;
							}
						}

					} // while
					System.out.println("프로그램 종료");
					break;

				} else {
					System.out.println("\"ID의 첫글자는 알파벳(회원:X, 직원:Y, 관리자:Z), 2~4번째는 숫자로 입력하세요.\"");
				}

			} else {
				System.out.println("\"ID는 부여받은 고유번호 4글자로 입력하세요.\"");
			}

		} // while_loopmain

	}// main

	/**
	 * 회원전용 초기화면
	 * 
	 * @return sel 번호선택값 반환
	 */
	private static String menu() {// 회원전용화면

		System.out.println("======목록을 선택해주세요.======");
		System.out.println("1. 내 농장 조회");
		System.out.println("2. 방문 예약");
		System.out.println("3. 텃밭 분양");
		System.out.println("4. 농자재");
		System.out.println("5. 거래소");
		System.out.println("6. 알림함");
		System.out.println("0. 로그아웃");
		System.out.println("============================");
		System.out.print("메뉴선택: ");

		String sel = scan.nextLine();

		return sel;

	}

	/**
	 * 직원전용 초기화면
	 * 
	 * @return sel 번호선택값 반환
	 */
	private static String menu2() { // 직원 전용화면

		System.out.println("[직원입니다.]");

		System.out.println("======목록을 선택해주세요.======");
		System.out.println("1. 회원 정보 등록");
		System.out.println("2. 회원 농장 조회");
		System.out.println("3. 방문 예약 관리");
		System.out.println("4. 회원 텃밭 관리");
		System.out.println("5. 농자재 관리");
		System.out.println("6. 날씨");
		System.out.println("7. 병해충");
		System.out.println("8. 거래소");
		System.out.println("9. 알림함");
		System.out.println("10. 인사관리");
		System.out.println("0. 로그아웃");
		System.out.println("====================");
		System.out.print("번호 입력: ");

		String sel = scan.nextLine();

		return sel;

	}

}