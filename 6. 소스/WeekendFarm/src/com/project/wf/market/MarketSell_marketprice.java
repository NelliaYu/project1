package com.project.wf.market;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import com.project.wf.login.mainclass;
/**
 * 거래소 농작물 시세 객체
 * @author 4조
 *
 */
public class MarketSell_marketprice {
	private static Scanner scan; 
	private static String DATA; 
	private static String DATA2; 
	private static String DATA4;
	private static String DATA5; 
	/**
	 * 회원/직원번호
	 */
	public static String input; 

	static {

		scan = new Scanner(System.in);
		DATA = "dat\\3. PlanListDummy.dat"; // 식물더미데이터 
		DATA2 = "dat\\9. Marketinfo.dat"; // 거래소게 있는 식물데이터 
		DATA4 = "dat\\1. MemberList.dat"; // 회원리스트 초기화
		DATA5 = "dat\\9. MarketinfoList.dat";// 거래가 완료된 식물데이터

		// 메인에서 가져오는 회원번호/직원번호
		 input = mainclass.inputwho;

	}
	/**
	 * 농작물 시세메소드
	 */
	public static void marketprice() { // 농작물 시세 메소드
		System.out.println("[3. 농작물 시세확인하기");
		System.out.println("[지역별 농작물 시세]");

		try {
			boolean loop = true;
			while (loop) {
				System.out.println("1. 농작물 시세 검색하기");
				System.out.println("2. 지역별 시세 검색하기");
				System.out.println("0. 뒤로가기");

				String choose = scan.nextLine();
				if (choose.equals("1")) {
					// 1. 목록보여주기
					BufferedReader reader = new BufferedReader(new FileReader(DATA));

					System.out.println("[농작물이름]\t[지역이름]\t\t[1kg당 가격]");
					String line = "";

					while ((line = reader.readLine()) != null) {

						String[] temp = line.split("★");

						if (temp[2].length() <= 5) {

							System.out.printf("%s\t\t%s\t\t%s\n", temp[2], temp[9], temp[10]);
						} else {
							System.out.printf("%s\t%s\t\t%s\n", temp[2], temp[9], temp[10]);

						}
					}

					reader.close();
					System.out.println();
					// 2. 목록선택하기
					System.out.print("농산물이름:");
					String area = scan.nextLine();

					line = "";
					reader = new BufferedReader(new FileReader(DATA));
					System.out.println("[농작물이름]\t[지역이름]\t\t[1kg당 가격]");
					while ((line = reader.readLine()) != null) {

						String[] temp = line.split("★");

						if (temp[2].equals(area)) {

							if (temp[2].length() <= 5) {
								System.out.printf("%s\t\t%s\t\t%s\n", temp[2], temp[9], temp[10]);
							} else {
								System.out.printf("%s\t%s\t\t%s\n", temp[2], temp[9], temp[10]);

							}
						}

					}

				} else if (choose.equals("2")) {

					// 1. 목록보여주기
					BufferedReader reader = new BufferedReader(new FileReader(DATA));

					System.out.println("[지역]\t[농작물이름]\t\t[1kg당 가격]");
					String line = "";

					while ((line = reader.readLine()) != null) {

						String[] temp = line.split("★");

						if (temp[2].length() <= 5) {
							System.out.printf("%s\t\t%s\t\t%s\n", temp[9], temp[2], temp[10]);
						} else {
							System.out.printf("%s\t\t%s\t%s\n", temp[9], temp[2], temp[10]);

						}
					}

					reader.close();
					System.out.println();
					// 2. 목록선택하기
					System.out.print("지역입력:");
					String area = scan.nextLine();

					line = "";
					reader = new BufferedReader(new FileReader(DATA));
					System.out.println("[지역]\t[농작물이름]\t\t[1kg당 가격]");
					while ((line = reader.readLine()) != null) {

						String[] temp = line.split("★");

						if (temp[9].equals(area)) {

							if (temp[2].length() <= 5) {
								System.out.printf("%s\t\t%s\t\t%s\n", temp[9], temp[2], temp[10]);
							} else {
								System.out.printf("%s\t\t%s\t%s\n", temp[9], temp[2], temp[10]);

							}
						}

					}

				} else if (choose.equals("0")) {
					loop = false;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		pause();
	}
	/**
	    * 일시정지메소드
	    * @param pause
	    * @return
	    */
	private static void pause() {
		System.out.println();
		System.out.println("엔터를 누르시면 이전페이지로 이동합니다.");
		scan.nextLine();// Block
	}
}
