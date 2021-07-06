package com.project.wf.resource;

import java.util.Scanner;
/**
 * 
 * @author 4조 왕지민
 * 회원 농자재 매뉴 클래스
 * 
 */
public class FarmManagement_Menu {
	private Scanner scan = new Scanner(System.in);
	/**
	 * 회원 로그인 상태에서 보여지는 매뉴 메소드
	 * @param login 회원/직원/관리자 상태를 계속 확인할 수 있도록 하기 위한 매개변수
	 */
	public void FarmManagment_MainMenu(String login) {

		System.out.println("=========================");
		System.out.println("1.농작물 구매");
		System.out.println("2.농기구 대여");
		System.out.println("0.뒤로가기");
		System.out.println("=========================");
		System.out.print("번호 입력: ");

//		scan.close();
		
		switch(scan.nextLine()){
		case "1":
			FarmManagement_FVMenu fvList = new FarmManagement_FVMenu();
			fvList.buyMenu(login);
			break;
		case "2":
			FarmManagement_ToolListup toolList = new FarmManagement_ToolListup();
			FarmManagement_ToolRental toolRental = new FarmManagement_ToolRental();
			toolRental.tool(toolList.toolListup(),login);
			
			break;
		case "0":
			break;
		default:
			System.out.println("[잘못 입력하셨습니다. 다시 입력해주세요.]");
			FarmManagment_MainMenu(login);
		}
	}
}
