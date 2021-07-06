package com.project.wf.market.manage;

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

public class MarketSell_managesell {

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
		DATA = "dat\\3. PlanListDummy.dat"; // 식물더미데이터 초기화
		DATA2 = "dat\\9. Marketinfo.dat"; // 거래소게 있는 식물데이터 초기화
		DATA4 = "dat\\1. MemberList.dat"; // 회원리스트 초기화
		DATA5 = "dat\\9. MarketinfoList.dat";// 거래가 완료된 식물데이터 초기화

		// 메인에서 가져오는 회원번호/직원번호
		 input = mainclass.inputwho;

	}
	/**
	 * 회원 판매내역
	 */
	public static void memberallsell() { 

		System.out.println("[1. 회원 판매내역]");

		try {
			BufferedReader reader = new BufferedReader(new FileReader(DATA5)); // 거래가 완료된 식물데이터
			BufferedReader reader2 = new BufferedReader(new FileReader(DATA4)); // 회원리스트 데이터
			BufferedReader reader3 = new BufferedReader(new FileReader(DATA4)); // 회원리스트 데이터

			System.out.println("[날짜]\t\t[판매자번호]\t[구매자번호]\t[농작물이름]\t[농작물수량]\t[등급]\t[가격]\t\t[판매/구매]");
			String line = "", line2 = "", line3 = "";
			String result = "";
			while ((line = reader.readLine()) != null) {
				String[] temp = line.split("★");
					if(temp[2].length()<5) {

							result = String.format("%s\t%s\t\t%s\t\t%3s\t\t%s\t\t%2s\t%s\t\t%s\n", temp[11], temp[4],
									temp[9], temp[2], temp[5], temp[8], Integer.parseInt(temp[10])*-1, "판매");
							System.out.println(result);
					}}
			
			System.out.println(result);
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
