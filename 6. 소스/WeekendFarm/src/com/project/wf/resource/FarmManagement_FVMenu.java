package com.project.wf.resource;

import java.util.Scanner;

/**
 * 
 * @author 왕지민
 * 원하는 농작물 타입별로 리스트 출력하는 매뉴 클래스
 *
 */
public class FarmManagement_FVMenu {
	private Scanner scan = new Scanner(System.in);
	/**
	 * 
	 * @param login 회원/직원/관리자 상태를 계속 확인할 수 있도록 하기 위한 매개변수
	 * @return login 회원/직원/관리자 상태를 계속 확인할 수 있도록 하기 위해 반환
	 */
	public String buyMenu(String login) {

		System.out.println("=========================");
		System.out.println("1.과채류(고추,토마토...)");
		System.out.println("2.근채류(상추,배추...");
		System.out.println("3.엽채류(무,당근...)");
		System.out.println("0.뒤로가기");
		System.out.println("=========================");
		System.out.print("번호입력: ");

		String num = scan.nextLine();

		switch (num) {
		case "1":
		case "2":
		case "3":
			FarmManagement_FVListup list = new FarmManagement_FVListup();
			list.fvListup(num);
			list.fvShowList(num);
			if (login.startsWith("X")) {
				FarmManagement_FVBuy buy = new FarmManagement_FVBuy();
				buy.fvBuyMenu(num, login);
			} else if (login.startsWith("Z") || login.startsWith("Y")) {
				FarmManagement_FVRegister register = new FarmManagement_FVRegister();
				register.fvRegisterMenu(num, login);
			}

			break;
		case "0":
			if (login.startsWith("X")) {
				FarmManagement_Menu menu = new FarmManagement_Menu();
				menu.FarmManagment_MainMenu(login);
			} else if (login.startsWith("Z") || login.startsWith("Y")) {
				FarmManagement_Menu_Admin menuAdmin = new FarmManagement_Menu_Admin();
				menuAdmin.FarmManagment_MainMenu_Admin(login);
			}
			
			break;
		default:
			System.out.println("[잘못 입력하셨습니다. 다시 입력해주세요.]");
			buyMenu(login);
		}
		return login;
	}
}
