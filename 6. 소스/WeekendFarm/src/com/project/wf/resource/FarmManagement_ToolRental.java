package com.project.wf.resource;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 * 
 * @author 왕지민
 * 농기구 대여를 위한 클래스
 *
 */
public class FarmManagement_ToolRental {
	private static String historyPath = "dat\\5. FarmToolRentalList.dat";
	private Scanner scan = new Scanner(System.in);
	/**
	 * 농기구 대여를 위한 매뉴 메소드
	 * @param toolList  농기구 리스트를 받기위한 매개변수
	 * @param login 회원/직원/관리자 상태를 계속 확인할 수 있도록 하기 위한 매개변수
	 */
	
	public void tool(ArrayList<FarmManagement_ToolList> toolList,String login) {

		FarmManagement_Menu menu = new FarmManagement_Menu();
		String num = "0";
		System.out.println("1.대여하기");
		System.out.println("0.뒤로가기");
		System.out.println("=========================");
		System.out.print("번호입력: ");
		num = scan.nextLine();

		switch (num) {
		case "1":
			rentalTool(toolList,login);
			
			break;
		case "0":
			menu.FarmManagment_MainMenu(login);
			break;
		default:
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			tool(toolList,login);
		}
	}

	/**
	 * 농기구를 선택하여 대여하기 위한 행위 메소드
	 * @param toolList  농기구 리스트를 받기위한 매개변수
	 * @param login 회원/직원/관리자 상태를 계속 확인할 수 있도록 하기 위한 매개변수
	 */
	private void rentalTool(ArrayList<FarmManagement_ToolList> toolList,String login) {
		int rentalToolNum;
		int rentalToolCount, rentalToolPrice = 0;
		int index = 0;
		
		System.out.print("선택할 농기구 번호: ");
		rentalToolNum = Integer.parseInt(scan.nextLine());
		System.out.print("대여 수량: ");
		rentalToolCount = Integer.parseInt(scan.nextLine());
		
		for (int i = 0; i < toolList.size(); i++) {
			if (rentalToolNum == Integer.parseInt(toolList.get(i).getToolNum().substring(1,4))) {
				rentalToolPrice = rentalToolCount * toolList.get(i).getToolRentalPrice();
			}
		}
		
		if (rentalToolPrice == 0) {
			System.out.println("농기구 번호 혹은 수량이 잘못됐습니다. 다시 입력해주세요.");
			tool(toolList,login);
		}

		System.out.printf("결재 금액: %d원\n", rentalToolPrice);
		
		//대여 수량 수정
		FarmManagement_ToolRegister rental = new FarmManagement_ToolRegister();
		index = rentalToolNum-1;
		
		rental.toolLoad();
		rental.toolAddSet(toolList.get(index).getToolName(), rentalToolNum, 
				toolList.get(index).getToolRentalPrice(), -rentalToolCount, true);
		rental.toolWrite();
		
		//대여 내역 저장
		Calendar now = Calendar.getInstance();

		String date = String.format("%tF",now);
		date = String.format("%s"+"%s"+"%s",date.substring(0,4),date.substring(5,7),date.substring(8,10));
		
		rental.toolAddSet(Integer.parseInt(login.substring(1,4)), rentalToolNum, toolList.get(index).getToolName(), rentalToolCount, Integer.parseInt(date), 0, rentalToolPrice);
		rental.toolWrite(historyPath);
		
		//완료 후 매뉴 출력
		FarmManagement_Menu menu  = new FarmManagement_Menu();
		menu.FarmManagment_MainMenu(login);
	}
}
