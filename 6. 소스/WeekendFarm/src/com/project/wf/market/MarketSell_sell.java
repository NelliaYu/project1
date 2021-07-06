package com.project.wf.market;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import com.project.wf.login.mainclass;
/**
 * 거래소 판매 메소드
 * @author 4조
 *
 */
public class MarketSell_sell {

	private static Scanner scan; 
	private static String DATA; 
	private static String DATA2; 
	private static String DATA4;
	private static String DATA5; 
	public static String input; 

	static {

		scan = new Scanner(System.in);
		DATA = "dat\\3. PlanListDummy.dat"; // 식물더미데이터 초기화
		DATA2 = "dat\\9. Marketinfo.dat"; // 거래소게 있는 식물데이터 초기화
		DATA4 = "dat\\1. MemberList.dat"; // 회원리스트 초기화
		DATA5 = "dat\\9. MarketinfoList.dat";// 거래가 완료된 식물데이터 초기화

		// 메인에서 가져오는 회원번호/직원번호
		 input = mainclass.inputwho;
		//input = "X324";

	}
	/**
	 * 판매 메소드
	 */
	public static void sell() {

		boolean loop = true;
		while (loop) {
			System.out.println("1. 판매하기");
			System.out.println("0. 뒤로가기");

			String choose = scan.nextLine();
			if (choose.equals("1")) {

				System.out.println("[1. 농작물 판매하기]");
				System.out.println("[내 농작물 목록]");

				try {
					// 1. 목록보여주기
					BufferedReader reader = new BufferedReader(new FileReader(DATA));

					System.out.println("[번호]\t[농작물이름]\t[재배수량(KG)]\t[등급]");
					String line = "";

					while ((line = reader.readLine()) != null) {

						String[] temp = line.split("★");

						if (temp[4].equals(input)) {

							if (temp[2].length() <= 5) {
								System.out.printf("%s\t%s\t\t%s\t\t%3s\n", temp[0], temp[2], temp[5], temp[8]);
							} else {
								System.out.printf("%s\t%s\t%s\t\t%3s\n", temp[0], temp[2], temp[5], temp[8]);

							}

						}

					}

					reader.close();

					// 목록선택하기
					System.out.print("판매할 농작물 번호:  ");
					String number = scan.nextLine();
					System.out.print("판매할 농작물 수량 : ");
					String among = scan.nextLine();
					System.out.print("1kg당 판매 금액(원) : ");
					String money = scan.nextLine();
					reader = new BufferedReader(new FileReader(DATA));
					String result = ""; // 누적 변수
					line = "";// 파일 누적변수
					String lineresult = ""; // 선택 작물 누적변수
					while ((line = reader.readLine()) != null) {

						String[] temp = line.split("★");

						if (temp[0].equals(number)) {
							System.out.println("[번호]\t[농작물이름]\t[판매수량(KG)]\t[등급]\t[금액]");
							if (temp[2].length() <= 5) {
								lineresult += line + "★" + money + "\r\n";
								System.out.printf("%s\t%s\t\t%s\t\t%3s\t%s\n", temp[0], temp[2], among, temp[8], money);
							} else {
								lineresult += line + "★" + money + "\r\n";
								System.out.printf("%s\t%s\t%s\t\t%3s\t%s\n", temp[0], temp[2], among, temp[8], money);

							}

						}
					}
					System.out.println("[거래소에 등록되었습니다.]");
					// 선택한 작물 판매소에 올리기
					BufferedWriter writer = new BufferedWriter(new FileWriter(DATA2, true));

					writer.write(lineresult);

					writer.close();

					reader.close();

					// 선택한 작물 제외한 리스트
					reader = new BufferedReader(new FileReader(DATA));
					result = ""; // 누적 변수
					line = "";

					while ((line = reader.readLine()) != null) {

						String[] temp = line.split("★");

						if (temp[0].equals(number) && Integer.parseInt(temp[5]) - Integer.parseInt(among) != 0) {

							result += temp[0] + "★" + temp[1] + "★" + temp[2] + "★" + temp[3] + "★" + temp[4] + "★"
									+ (Integer.parseInt(temp[5]) - Integer.parseInt(among)) + "★" + temp[6] + "★"
									+ temp[07] + "★" + temp[8] + "★" + temp[9] + "★" + money + "\r\n";
						} else {
							result += line + "\r\n";

						}

					}
					writer = new BufferedWriter(new FileWriter(DATA));
					writer.write(result);
					writer.close();

				} catch (Exception e) {
					System.out.println(e);
				}
			} else if (choose.equals("0")) {
				loop = false;
			}
		}
		pause();
	}
	/**
	 * 일시정지 메소드
	 */
	private static void pause() {
		System.out.println();
		System.out.println("엔터를 누르시면 이전페이지로 이동합니다.");
		scan.nextLine();// Block
	}
}
