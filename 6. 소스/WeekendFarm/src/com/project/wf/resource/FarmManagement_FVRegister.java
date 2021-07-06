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
 * @author 왕지민 새로운 농작물을 등록 또는 수량 및 가격을 수정하기 위한 클래스
 *
 */
public class FarmManagement_FVRegister {

	private ArrayList<FarmManagement_FVList> fvList = new ArrayList<FarmManagement_FVList>();
	private ArrayList<FarmManagement_FVBuyList> fvBuyList = new ArrayList<FarmManagement_FVBuyList>();
	private Scanner scan = new Scanner(System.in);
	private static String path = "dat\\3. PlantList.dat";
	private static String seedPath = "dat\\4. Seed.dat";
	private static String historyPath = "dat\\4. MemberSeed.dat";

	/**
	 * 농작물 등록을 위한 매뉴 출력 메소드
	 * 
	 * @param num   농작물 타입을 받기위한 매개변수
	 * @param login 회원/직원/관리자 상태를 계속 확인할 수 있도록 하기 위한 매개변수
	 */
	public void fvRegisterMenu(String num, String login) {

		System.out.println("1.농작물 등록하기");
		System.out.println("0.뒤로가기");
		System.out.println("=========================");
		System.out.print("번호입력: ");

		switch (scan.nextLine()) {
		case "1":
			fvLoad();
			fvRegister(num);
			break;
		case "0":
			FarmManagement_FVMenu fvMenu = new FarmManagement_FVMenu();
			fvMenu.buyMenu(login);
			break;
		default:
			System.out.println("[잘못 입력하셨습니다. 다시 입력해주세요.]");
			fvRegisterMenu(num, login);
		}
		FarmManagement_Menu_Admin menuAdmin = new FarmManagement_Menu_Admin();
		menuAdmin.FarmManagment_MainMenu_Admin(login);
	}

	/**
	 * 농작물 등록을 위한 행위 메소드
	 * 
	 * @param num 농작물 타입을 받기위한 매개변수
	 */
	private void fvRegister(String num) {
		String type = "", name = "", level = "";
		int price, period, count, fvNum;
		boolean flag = false;

		if (num.equals("1")) {
			type = "과채류";
		} else if (num.equals("2")) {
			type = "근채류";
		} else if (num.equals("3")) {
			type = "엽채류";
		}

		System.out.printf("분류: %s\n", type);
		System.out.print("농작물 번호(숫자): ");
		fvNum = Integer.parseInt(scan.nextLine());
		System.out.print("이름(한영): ");
		name = scan.nextLine();
		System.out.print("수량(숫자): ");
		count = Integer.parseInt(scan.nextLine());
		System.out.print("가격(숫자): ");
		price = Integer.parseInt(scan.nextLine());
		System.out.print("난이도(한영): ");
		level = scan.nextLine();
		System.out.print("기간(숫자): ");
		period = Integer.parseInt(scan.nextLine());

		if (fvNum == 0 || type.equals(null) || name.equals("") || price == 0 || level.equals("") || period == 0
				|| count == 0) {
			System.out.println("확인 후 다시 입력바랍니다.");
		} else {
			fvAddSet(fvNum, type, name, price, level, period, count, flag);
			fvWrite();
		}
	}

	/**
	 * 농작물 추가사항을 파일에 저장 메소드
	 */
	public void fvWrite() {
		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			BufferedWriter seedWriter = new BufferedWriter(new FileWriter(seedPath));

			for (FarmManagement_FVList list : fvList) {
				writer.write(String.format("%s★%s★%s★%d★%s★%d\n", list.getFvNum(), list.getFvName(), list.getFvType(),
						0, list.getFvLevel(), list.getFvPeriod()));
			}

			writer.close();

			for (FarmManagement_FVList list : fvList) {
				seedWriter.write(String.format("%s★%s★%d★%s★%d\n", list.getFvType(), list.getFvName(),
						list.getFvPrice(), list.getFvNum(), list.getFvRestCount()));
			}

			seedWriter.close();

		} catch (IOException e) {
			System.out.println(e);
		}

		System.out.println("완료됐습니다.");
	}

	/**
	 * 농작물 구매 내역을 파일에 저장 메소드
	 */
	//오버로딩
	public  void fvWrite(String path) {
		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(path));

			for (FarmManagement_FVBuyList list : fvBuyList) {
				writer.write(String.format("%s★%s★%s★%d★%s★%d\n", list.getMemNum(), list.getFvType(), list.getFvName(),
						list.getFvCount(), list.getFvPrice(), list.getFvPeriod()));
			}

			writer.close();

		} catch (IOException e) {
			System.out.println(e);
		}

	}

	/**
	 * 농작물 객체를 생성하여 타입에 맞는 농작물 ArrayList에 저장을 위한 메소드
	 * 농작물 정보 파일에 저장을 위함
	 * @param num    농작물 번호
	 * @param type   농작물 타입
	 * @param name   농작물 이름
	 * @param price  농작물 가격
	 * @param level  농작물 재배 난이도
	 * @param period 농작물 재배 기간
	 * @param count  농장물 수량
	 * @param flag   ArrayList에 있는 객체를 수정할 경우, 리스트에 추가하지 않도록 제한
	 */
	public void fvAddSet(int num, String type, String name, int price, String level, int period, int count,
			boolean flag) {

		FarmManagement_FVList fv = new FarmManagement_FVList(num, type, name, price, level, period, count);

		for (int i = 0; i < fvList.size(); i++) {
			if (fv.getFvNum().equals(fvList.get(i).getFvNum())) {
				fvList.get(i).setFvPrice(price);
				fvList.get(i).setFvRestCount(fvList.get(i).getFvRestCount() + count);
				flag = true;
			}
		}

		if (flag == false) {
			fvList.add(fv);
		}
	}

	/**
	 * 농작물 객체를 생성하여 타입에 맞는 농작물 ArrayList에 저장을 위한 메소드
	 * 농작물 구매 내역 파일에 저장을 위함
	 * @param num	농작물 번호
	 * @param type	농작물 타입
	 * @param name	농작물 이름
	 * @param count	농작물 수량
	 * @param price	농작물 가격
	 * @param period	농작물 구입 날짜
	 */
	//오버로딩
	public void fvAddSet(int num, String type, String name, int count, int price, int period) {

		FarmManagement_FVBuyList fv = new FarmManagement_FVBuyList(num, type, name, count, price, period);

		for (int i = 0; i < fvBuyList.size(); i++) {
			if (fv.getMemNum().equals(fvBuyList.get(i).getMemNum())) {
				fvBuyList.get(i).setFvPrice(price);
				fvBuyList.get(i).setFvCount(count);
			}
		}
		fvBuyList.add(fv);
	}

	/**
	 * 파일에 저장되어있는 농작물 리스트를 ArrayList에 옮겨서 저장을 위한 메소드
	 */
	public void fvLoad() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			BufferedReader seedReader = new BufferedReader(new FileReader(seedPath));
			BufferedReader seedBuyReader = new BufferedReader(new FileReader(historyPath));
			String line = "";

			while ((line = reader.readLine()) != null) {
				String[] temp = line.split("★");
				FarmManagement_FVList list = new FarmManagement_FVList(Integer.parseInt(temp[0].substring(1, 4)),
						temp[2], temp[1], 0, temp[4], Integer.parseInt(temp[5]), 0);
				fvList.add(list);

			}

			reader.close();

			while ((line = seedReader.readLine()) != null) {
				String[] temp = line.split("★");
				for (int i = 0; i < fvList.size(); i++) {
					if (fvList.get(i).getFvNum().equals(temp[3])) {
						fvList.get(i).setFvPrice(Integer.parseInt(temp[2]));
						fvList.get(i).setFvRestCount(Integer.parseInt(temp[4]));
						break;
					}
				}

			}

			seedReader.close();

			while ((line = seedBuyReader.readLine()) != null) {
				String[] temp = line.split("★");
				FarmManagement_FVBuyList list = new FarmManagement_FVBuyList(Integer.parseInt(temp[0].substring(1, 4)),
						temp[1], temp[2], Integer.parseInt(temp[3]), Integer.parseInt(temp[4]),
						Integer.parseInt(temp[5]));
				fvBuyList.add(list);

			}

			seedBuyReader.close();

		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
