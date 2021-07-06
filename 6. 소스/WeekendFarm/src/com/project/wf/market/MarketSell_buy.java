package com.project.wf.market;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.Scanner;

import com.project.wf.login.mainclass;
/**
 * 거래소 구매 객체
 * @author 4조
 *
 */
public class MarketSell_buy {


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
		DATA4 = "dat\\1. MemberList.dat"; // 회원리스트 
		DATA5 = "dat\\9. MarketinfoList.dat";// 거래가 완료된 식물데이터 

		// 메인에서 가져오는 회원번호/직원번호
		 input = mainclass.inputwho;

	}
	/**
	 * 농작물 구매메소드
	 */
	
	public static void buy() {
		boolean loop = true;
		while (loop) {
			System.out.println("1. 구매하기");
			System.out.println("0. 뒤로가기");

			String choose = scan.nextLine();
			if (choose.equals("1")) {

				System.out.println("[1. 농작물 구매하기]");
				System.out.println("[구매 가능한 목록]");

				try {
					// 1. 목록보여주기
					BufferedReader reader = new BufferedReader(new FileReader(DATA2));
					System.out.println("[번호]\t[농작물이름]\t[구매가능한수량(KG)]\t[등급]\t[1kg당가격]");
					String line = "";

					while ((line = reader.readLine()) != null) {

						String[] temp = line.split("★");

						if (temp[2].length() <= 5) {
							System.out.printf("%s\t%s\t\t%s\t\t\t%3s\t%s\n", temp[0], temp[2], temp[5], temp[8],
									temp[11]);
						} else {
							System.out.printf("%s\t%s\t%s\t\t\t%3s\t%s\n", temp[0], temp[2], temp[5], temp[8],
									temp[11]);
						}
					}
					reader.close();

					// 2. 목록선택하기
					System.out.print("구매할 농작물 번호:  ");
					String number = scan.nextLine(); // 구매할 농작물 번호 변수
					System.out.print("구매할 농작물 수량 : ");
					String among = scan.nextLine(); // 구매할 농작물 수량 변수

					System.out.print("1. 카드   2. 무통장입금  3. 계좌이체  4. 직거래 : ");
					String account = scan.nextLine(); // 결제 방법 변수

					if (account.equals("3")) {
						System.out.print("은행이름: ");
						String bankname = scan.nextLine(); // 은행 이름 변수
						System.out.print("계좌번호: ");
						String bankcount = scan.nextLine(); // 계좌번호 변수

					}

					reader = new BufferedReader(new FileReader(DATA2));
					String result = ""; // 누적 변수
					line = "";
					String lineresult = ""; // 선택 작물 구매 누적변수
					String lineresult2 = "";// 선택 작물 판매 누적변수
					Calendar now = Calendar.getInstance(); // 날짜 클래스
					while ((line = reader.readLine()) != null) {

						String[] temp = line.split("★");

						if ((Integer.parseInt(temp[5]) - Integer.parseInt(among)) < 0) {
							System.out.println("[판매 수량이 구매수량보다 더 적습니다. 판매수량보다 적게 입력해주세요.]");
							break;
						} else {

							if (temp[0].equals(number)) {
								System.out.println("[번호]\t[농작물이름]\t[구매수량(KG)]\t[등급]\t[금액]\t[거래방식]");
								if (temp[2].length() <= 5) {
									temp[10] = temp[4];
									temp[4] = input;
									lineresult += temp[0] + "★" + temp[1] + "★" + temp[2] + "★" + temp[3] + "★"
											+ temp[4] + "★" + among + "★" + temp[6] + "★" + temp[7] + "★" + temp[8]
											+ "★" + temp[10] + "★"
											+ Integer.parseInt(temp[11]) * Integer.parseInt(among) * -1 + "★"
											+ String.format("%tF", now) + "\r\n";

									System.out.printf("%s\t%s\t\t%s\t\t%s\t%s\t%s\n", temp[0], temp[2], among, temp[8],
											Integer.parseInt(temp[11]) * Integer.parseInt(among),Integer.parseInt(account)==1?"카드":Integer.parseInt(account)==2?"무통장입금":Integer.parseInt(account)==3?"계좌송금":"직거래");
								} else {

									temp[10] = temp[4];
									temp[4] = input;
									lineresult += temp[0] + "★" + temp[1] + "★" + temp[2] + "★" + temp[3] + "★"
											+ temp[4] + "★" + among + "★" + temp[6] + "★" + temp[7] + "★" + temp[8]
											+ "★" + temp[10] + "★"
											+ Integer.parseInt(temp[11]) * Integer.parseInt(among) * -1 + "★"
											+ String.format("%tF", now) + "\r\n";

									System.out.printf("%s\t%s\t%s\t\t%s\t%s\t%s\n", temp[0], temp[2], among, temp[8],
											Integer.parseInt(temp[11]) * Integer.parseInt(among),Integer.parseInt(account)==1?"카드":Integer.parseInt(account)==2?"무통장입금":Integer.parseInt(account)==3?"계좌송금":"직거래");

								}

							}

						}
					}
					
					// 선택한 작물 리스트에 올리기
					BufferedWriter writer = new BufferedWriter(new FileWriter(DATA5, true));
					writer.write(lineresult);
					writer.close();
					reader.close();

					// 선택한 작물 제외한 리스트
					reader = new BufferedReader(new FileReader(DATA2));
					result = ""; // 누적 변수
					line = "";

					while ((line = reader.readLine()) != null) {

						String[] temp = line.split("★");

						if (temp[0].equals(number) && Integer.parseInt(temp[5]) - Integer.parseInt(among) > 0) {

							result += temp[0] + "★" + temp[1] + "★" + temp[2] + "★" + temp[3] + "★" + temp[4] + "★"
									+ (Integer.parseInt(temp[5]) - Integer.parseInt(among)) + "★" + temp[6] + "★"
									+ temp[07] + "★" + temp[8] + "★" + temp[9] + "★" + temp[10] + "★" + temp[11]
									+ "\r\n";
							System.out.println("[구매완료하였습니다.]");
							System.out.println("[회원가입시 입력해주신 주소로 배송됩니다.]");

						
						} else if(temp[0].equals(number) && Integer.parseInt(temp[5]) - Integer.parseInt(among) < 0) {
							break;
						} else if (!temp[0].equals(number)) {
							result += line + "\r\n";
						}

					}
					writer = new BufferedWriter(new FileWriter(DATA2));
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
	 * 일시정지메소드
	 */
	private static void pause() {
		System.out.println();
		System.out.println("엔터를 누르시면 이전페이지로 이동합니다.");
		scan.nextLine();// Block
	}


}
