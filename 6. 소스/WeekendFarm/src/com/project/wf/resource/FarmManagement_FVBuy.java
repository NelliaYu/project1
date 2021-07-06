package com.project.wf.resource;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;


/**
 * 
 * @author 4조 왕지민
 * 농작물 구매를 위한 클래스 
 *
 */
public class FarmManagement_FVBuy {
	private Scanner scan = new Scanner(System.in);
	private FarmManagement_FVListup list = new FarmManagement_FVListup();
	private static String historyPath = "dat\\4. MemberSeed.dat";

	/**
	 * 농작물 구매를 위한 매뉴 메소드
	 * @param num  농작물 타입을 받기위한 매개변수
	 * @param login 회원/직원/관리자 상태를 계속 확인할 수 있도록 하기 위한 매개변수
	 */
	public void fvBuyMenu(String num,String login) {

		String menuNum = "";

		if (list.fvListup(num).equals(null)) {
			System.out.println("[등록된 상품이 없습니다.]");
		} else {

			System.out.println("1.구매하기");
			System.out.println("0.뒤로가기");
			System.out.println("=========================");
			System.out.print("번호 입력: ");
			menuNum = scan.nextLine();
			switch (menuNum) {
			case "1":
				fvBuy(list.fvListup(num),login,num);
				break;
			case "0":
				FarmManagement_FVMenu fvMenu = new FarmManagement_FVMenu();
				fvMenu.buyMenu(login);
				break;
			default:
				System.out.println("[잘못 입력하셨습니다. 다시 입력해주세요.]");
				fvBuyMenu(num,login);
			}
//			scan.close();
		}
	}

	/**
	 * 농작물 구매를 위한 행위 메소드
	 * @param fvList 전달받은 농작물 타입에 맞는 ArrayList. 농작물관련 객체 저장
	 * @param login 회원/직원/관리자 상태를 계속 확인할 수 있도록 하기 위한 매개변수
	 * @param num 농작물 타입을 받기위한 매개변수
	 */
	private void fvBuy(ArrayList<FarmManagement_FVList> fvList,String login, String num) {
		
		int fvNum;
		int seedCount;
		int price = 0;
		int index = 0;
		String type = "";

		System.out.print("선택할 농작물 번호: ");
		fvNum = Integer.parseInt(scan.nextLine());

		System.out.print("수량: ");
		seedCount = Integer.parseInt(scan.nextLine());

		for (int i = 0; i < fvList.size(); i++) {

			if (Integer.parseInt(fvList.get(i).getFvNum().substring(1, 4)) == (fvNum)) {
				price = fvList.get(i).getFvPrice() * seedCount;
				type = fvList.get(i).getFvType();
				break;
			}
		}

		if (price == 0) {

			System.out.println("[농작물 번호 혹은 수량이 잘못됐습니다. 다시 입력해주세요.]");
			fvBuyMenu(num,login);

		}

		System.out.printf("결제 금액: %d원\n", price);
		
		//구매 가능 수량 수정
		FarmManagement_FVRegister buy = new FarmManagement_FVRegister();
		index = fvNum-1;
		
		//배열의 초기값 때문에 보정필요, 더미데이터에서 A100 : 과채류, A200 : 엽채류 등 방법으로 나누면 좀더 효율적으로 보임
		if(type.equals("엽채류")) {
			index -= 13;
		} else if(type.equals("근채류")) {
			index -= 29;
		}
		
		buy.fvLoad();
		//농작물 목록 파일 저장
		buy.fvAddSet(fvNum,fvList.get(index).getFvType(), fvList.get(index).getFvName(), 
				fvList.get(index).getFvPrice(),fvList.get(index).getFvLevel(),fvList.get(index).getFvPeriod(), -seedCount, true);
		buy.fvWrite();
		
		//농작물 거래 내역 파일 저장
		Calendar now = Calendar.getInstance();

		String date = String.format("%tF",now);
		date = String.format("%s"+"%s"+"%s",date.substring(0,4),date.substring(5,7),date.substring(8,10));
		
		buy.fvAddSet(Integer.parseInt(login.substring(1,4)),fvList.get(index).getFvType(),fvList.get(index).getFvName(),
				seedCount,fvList.get(index).getFvPrice(),Integer.parseInt(date));
		buy.fvWrite(historyPath);
		
		//완료 후 매뉴 출력
		FarmManagement_FVMenu fvMenu = new FarmManagement_FVMenu();
		fvMenu.buyMenu(login);

	}

}
