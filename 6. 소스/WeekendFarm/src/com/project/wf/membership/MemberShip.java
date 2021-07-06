package com.project.wf.membership;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class MemberShip {
	private static Scanner scan;
	private static String DATA;
	static {

		scan = new Scanner(System.in);
		DATA = "dat\\1. MemberList.dat";;
	}
	/**
	 * 회원등록 메소드
	 */
	public static void membership() {
		
		System.out.println("[회원 등록]");
		System.out.print("이름 : ");
		String name = scan.nextLine();
		System.out.print("생년월일(yyyymmdd) : ");
		String birth = scan.nextLine();
		System.out.print("전화번호(010-xxxx-xxxx) : ");
		String phone = scan.nextLine();
		System.out.print("주소: ");
		String address = scan.nextLine();
		System.out.print("은행이름xxxx-xxxx-xxxx : ");
		String bank = scan.nextLine();
		System.out.print("계약시작일(yyyymmdd) : ");
		String startfarm = scan.nextLine();
		System.out.print("계약종료일(yyyymmdd) : ");
		String endfarm = scan.nextLine();
		System.out.print("계약면적(평) : ");
		String land = scan.nextLine();
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(DATA));
			String line = "";
			int num = 0;
			while ((line = reader.readLine()) != null) {

				String[] temp = line.split("★");
				for(int i=0;i<temp[0].length();i++) {
				if(Integer.parseInt(temp[0].substring(1, 4)) > num) {
					num = Integer.parseInt(temp[0].substring(1, 4));
				}
				}				
			}
			String membernum = "X"+(num+1);//회원번호 
			String farmnum = "C"+(num+1);//텃밭번호
			
			reader.close();
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(DATA, true));
			
			
			String result = membernum+"★"+name+"★"+birth+"★"+phone+"★"+address+"★"+bank+"★"+startfarm
							+"★"+endfarm+"★"+farmnum+"★"+land+"★"+Integer.parseInt(land)*3000 + "\r\n";
			
			System.out.println("=====[계약내용]=====");
			System.out.printf("회원번호  \t: %s\n이름  \t: %s\n생년월일  \t: %s\n전화번호  \t: %s\n주소  \t: %s\n은행\t: %s\n계약시작일 : %s\n계약종료일 : %s\n텃밭주소 \t: %s\n텃밭면적 \t: %s\n텃밭가격 \t: %s\n" 
								,membernum,name,birth,phone,address,bank,startfarm,endfarm,farmnum,land,Integer.parseInt(land)*3000);
			System.out.println("[등록이 완료되었습니다.]");
			
			
			writer.write(result);
			
			writer.close();
			
			
			
			pause();
			
			
			
			
		} catch (Exception e) {
			
		}
		
		
	}
	/**
	 * 일시정지메소드
	 */
	private static void pause() {
		System.out.println();
		System.out.println("엔터를 누르시면 초기화면으로 돌아갑니다.");
		scan.nextLine();// Block
	}

}

