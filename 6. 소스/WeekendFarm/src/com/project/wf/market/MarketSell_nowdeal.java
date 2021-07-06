package com.project.wf.market;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Calendar;
import java.util.Scanner;

import com.project.wf.login.mainclass;
/**
 * 거래소 거래현황 객체
 * @author 4조
 *
 */

public class MarketSell_nowdeal {

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
	 * 농작물거래현황
	 */
	public static void nowdeal() { // 농작물 거래현황

		System.out.println("[4. 농작물 거래현황]");

		try {
			BufferedReader reader = new BufferedReader(new FileReader(DATA5));// 거래가 완료된 식물데이터
			BufferedReader reader2 = new BufferedReader(new FileReader(DATA4));// 회원리스트 데이터
			BufferedReader reader3 = new BufferedReader(new FileReader(DATA4));// 회원리스트 데이터
			Calendar now = Calendar.getInstance();

			String nowdate = String.format("%tF", now);

			System.out.println("[날짜]\t\t[판매자이름]\t[구매자이름]\t[농작물이름]\t[농작물수량]\t[등급]\t[가격]\t[주소]\t\t\t\t[판매/구매]\t\t[상태]");
			String line = "", line2 = "", line3 = "";
			String result = "";
			String result2 = "";
			String result3 = "";
			String delivery = "";
			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split("★");
				if (temp[11].compareTo(nowdate) == 0) {
					delivery = "배송준비중";
				} else if (temp[11].compareTo(nowdate) < 0) {
					delivery = "배송완료";
				}
				if (input.equals(temp[4])) {
					while ((line3 = reader2.readLine()) != null) {
						String[] temp2 = line3.split("★");
						if (temp[4].equals(temp2[0])) {
							while ((line2 = reader3.readLine()) != null) {
								String[] temp3 = line2.split("★");
								if (temp[9].equals(temp3[0])) {
									if ((Integer.parseInt(temp[10])) < 0) {
										result+= String.format("%s\t%s\t\t%s\t\t%3s\t\t%s\t\t%2s\t%s\t%s\t\t%s\t\t\t%s\n",
												temp[11], temp3[1], temp2[1], temp[2], temp[5], temp[8], temp[10],
												temp2[4], "구매", delivery);
										
										System.out.println(result);
									} 
									if ((Integer.parseInt(temp[10])) > 0) {
										result += String.format("%s\t%s\t\t%s\t\t%3s\t\t%s\t\t%2s\t%s\t%s\t\t%s\t\t\t%s\n",
												temp[11], temp3[1], temp2[1], temp[2], temp[5], temp[8], temp[10],
												temp2[4], "판매", delivery);
										
										System.out.println(result);
									}
								}
							}
						}

					}
				}
			}
		
			
			reader.close();
			reader2.close();
			reader3.close();
	

		} catch (Exception e) {
			System.out.println(e);
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
