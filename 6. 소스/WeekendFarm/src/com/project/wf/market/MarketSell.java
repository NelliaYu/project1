package com.project.wf.market;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

import com.project.wf.login.mainclass;
/**
 * 5. 거래소 메뉴 객체
 * @author 4조
 *
 */
public class MarketSell {

	private static Scanner scan; 	//입력받는 변수
	private static String DATA; 	//식물더미데이터 변수
	private static String DATA2; 	//거래소에 있는 식물데이터 변수
	private static String DATA4; 	//회원리스트데이터 변수
	private static String DATA5;	//거래가 완료된 식물데이터 변수
	/**
	 * 회원/직원번호
	 */
	public static String input;		//입력값

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
	 * 거래소 메뉴 메소드
	 */
		public static void Maketloop() {//거래소 메뉴 메소드

		boolean loop = true;

		while (loop) {

			String sel = menu();

			if (sel.equals("1")) {
				MarketSell_sell.sell();
			} else if (sel.equals("2")) {
				MarketSell_buy.buy();
			} else if (sel.equals("3")) {
				/**
				 * 농작물 시세 메소드
				 */
				MarketSell_marketprice.marketprice();
			} else if (sel.equals("4")) {
				MarketSell_nowdeal.nowdeal();
			} else if (sel.equals("5")) {
				MarketSell_profitloss.profitloss();;
			} else if (sel.equals("0")) {
				loop = false;
			}

		} // while
		System.out.println("[메인화면으로 갑니다.]");

	}

	

	private static String menu() {

		System.out.println("[5. 거래소]");

		System.out.println("==========");
		System.out.println("1. 농작물 판매하기");
		System.out.println("2. 농작물 구매하기");
		System.out.println("3. 농작물 시세확인하기");
		System.out.println("4. 농작물 거래현황");
		System.out.println("5. 농작물 장부");
		System.out.println("0. 메인화면으로 가기");
		System.out.println("==========");
		System.out.print("메뉴선택: ");

		String sel = scan.nextLine();

		return sel;

	}

	private static void pause() {
		System.out.println();
		System.out.println("엔터를 누르시면 이전페이지로 이동합니다.");
		scan.nextLine();// Block
	}

}
