package com.project.wf.resource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author 왕지민
 * 새로운 농기구을 등록 또는 수량 및 가격을 수정하기 위한 클래스
 *
 */
public class FarmManagement_ToolRegister {
	
	private ArrayList<FarmManagement_ToolList> toolList = new ArrayList<FarmManagement_ToolList>();
	private ArrayList<FarmManagement_ToolRentalList> toolRentalList = new ArrayList<FarmManagement_ToolRentalList>();
	private Scanner scan = new Scanner(System.in);
	private static String path = "dat\\5. FarmToolList.dat";
	private static String historyPath = "dat\\5. FarmToolRentalList.dat";

	/**
	 * 농기구 등록을 위한 매뉴 출력 메소드
	 * @param login 회원/직원/관리자 상태를 계속 확인할 수 있도록 하기 위한 매개변수
	 */
	public void toolRegisterMenu(String login) {

		System.out.println("1.농기구 등록하기");
		System.out.println("0.뒤로가기");
		System.out.println("=========================");
		System.out.print("번호입력: ");

		switch (scan.nextLine()) {
		case "1":
			toolLoad();
			toolRegister();
			break;
		case "0":
			FarmManagement_Menu_Admin menuAdmin = new FarmManagement_Menu_Admin();
			menuAdmin.FarmManagment_MainMenu_Admin(login);
			break;
		default:
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			toolRegisterMenu(login);
		}	
		FarmManagement_Menu_Admin menuAdmin = new FarmManagement_Menu_Admin();
		menuAdmin.FarmManagment_MainMenu_Admin(login);
	}

	/**
	 * 농기구 등록을 위한 행위 메소드
	 */
	private void toolRegister() {
		
		String name = "";
		
		int num,price, count;
		boolean flag = false;
		
		System.out.print("농기구 번호: ");
		num = Integer.parseInt(scan.nextLine());
		System.out.print("이름: ");
		name = scan.nextLine();
		System.out.print("가격: ");
		price = Integer.parseInt(scan.nextLine());
		System.out.print("수량: ");
		count = Integer.parseInt(scan.nextLine());
		
		if(num == 0 || name.equals("") || price == 0 || count == 0) {
			System.out.println("확인 후 다시 입력바랍니다.");
		} else {
			toolAddSet(name, num, price, count, flag);
			toolWrite();
		}

	}

	/**
	 * 농기구 추가사항을 파일에 저장 메소드
	 */
	public void toolWrite() {
		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			
			for(FarmManagement_ToolList list : toolList) {
				writer.write(String.format("%s★%s★%d★%d\n", list.getToolNum(), list.getToolName(), list.getToolRentalPrice(),
						list.getToolRestCount()));
			}
			
			writer.close();
			
		} catch (IOException e) {
			System.out.println(e);
		}
		System.out.println("완료됐습니다.");
	}
	
	/**
	 * 농기구 대여 내역 파일에 저장 메소드 
	 */
	public void toolWrite(String path) {
		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			
			for(FarmManagement_ToolRentalList list : toolRentalList) {
				writer.write(String.format("%s★%s★%s★%d★%d★%d★%d\n", list.getMemNum(), list.getToolNum(), list.getToolName(),
						list.getToolRentalCount(),list.gettoolStartPeriod(),list.gettoolEndPeriod(),list.getToolRentalPrice()));
			}
			
			writer.close();
			
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	/**
	 * 농기구 객체를 생성하여 타입에 맞는 농작물 ArrayList에 저장을 위한 메소드
	 * 농기구 리스트 파일에 저장하기 위함
	 * @param name 농기구 이름
	 * @param num 농기구 번호
	 * @param price 농기구 대여 가격
	 * @param count 농기구가 잔여 수량
	 * @param flag 농기구 추가등록인지, 수정사항인지를 알기위한 변수
	 */
	public void toolAddSet(String name, int num, int price, int count, boolean flag) {
		
		FarmManagement_ToolList tool = new FarmManagement_ToolList(num, name, price, count);
		
		for(int i=0;i <toolList.size();i++) {
			if(tool.getToolNum().equals(toolList.get(i).getToolNum()))
			{
				toolList.get(i).setToolPrice(price);
				toolList.get(i).setToolRestCount(toolList.get(i).getToolRestCount()+count);
				flag = true;
			} 
		}
		
		if(flag == false) {
			toolList.add(tool);
		}
	}
	
	/**
	 * 농기구 객체를 생성하여 타입에 맞는 농작물 ArrayList에 저장을 위한 메소드
	 * 농기구 대여 내역 파일에 저장하기 위함
	 * @param memNum	회원번호
	 * @param num		농기구 번호
	 * @param name		농기구 이름
	 * @param count		농기구 대여 수량
	 * @param startPeriod	농기구 대여일
	 * @param endPeriod		농기구 반납일
	 * @param price		농기구 대여 가격
	 */
	public void toolAddSet(int memNum, int num, String name, int count, int startPeriod, 
			int endPeriod, int price) {
		
		FarmManagement_ToolRentalList tool = new FarmManagement_ToolRentalList(memNum, num, name, count,startPeriod,endPeriod,price);
		
		for(int i=0;i <toolRentalList.size();i++) {
			if(tool.getToolNum().equals(toolRentalList.get(i).getToolNum()))
			{
				toolRentalList.get(i).setToolRentalPrice(price);
				toolRentalList.get(i).setToolRentalCount(count);
			} 
		}
		
		toolRentalList.add(tool);
	}

	/**
	 * 파일에 저장되어있는 농기구 리스트를 ArrayList에 옮겨서 저장을 위한 메소드
	 */
	public void toolLoad() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			BufferedReader rentalReader = new BufferedReader(new FileReader(historyPath));
			String line = "";

			while ((line = reader.readLine()) != null) {
				String[] temp = line.split("★");
				FarmManagement_ToolList list = new FarmManagement_ToolList(Integer.parseInt(temp[0].substring(1,4)), temp[1], 
						Integer.parseInt(temp[2]),Integer.parseInt(temp[3]));
				toolList.add(list);

			}
			reader.close();
			
			while ((line = rentalReader.readLine()) != null) {
				String[] temp = line.split("★");
				FarmManagement_ToolRentalList list = new FarmManagement_ToolRentalList(Integer.parseInt(temp[0].substring(1, 4)),
						Integer.parseInt(temp[1].substring(1, 4)),temp[2], Integer.parseInt(temp[3]), Integer.parseInt(temp[4]), Integer.parseInt(temp[5]),
						Integer.parseInt(temp[6]));
				toolRentalList.add(list);

			}

			rentalReader.close();

		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
