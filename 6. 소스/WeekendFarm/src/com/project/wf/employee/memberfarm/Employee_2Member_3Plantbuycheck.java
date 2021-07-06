package com.project.wf.employee.memberfarm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

/**
 * 직원, 관리자가 전체회원의 농자재 구매내역을 조회할 수 있는 클래스
 */
public class Employee_2Member_3Plantbuycheck {
	//직원] 2. 회원 농장 조회 - 2.3회원 농자재 구매내역
	
			private static Scanner scan;
			private static String DATA;
			private static String DATA1;
			private static String membernum;
			
			
			static {
				membernum = ""; //두 파일간 공유할 회원번호들
				scan = new Scanner(System.in);
				DATA = "dat\\1. MemberList.dat";
				DATA1 = "dat\\4. MemberSeed.dat";
			}
			
			/**
			 * 직원, 관리자가 전체회원의 농자재 구매내역을 확인 후, 원하는 회원의 구매내역만 조회할 수 있게 하는 메소드
			 */
			public void Memberplantbuylist() {
			
				boolean loop = true;
				while(loop) {
					
					list();
					System.out.println();
					
					// 뒤로가기 버튼
					System.out.println("1.회원 선택\t\t\t\t0.뒤로가기");
					System.out.print("번호 입력: ");
					String back = scan.nextLine();
					
					
					boolean loop2 = true;
					//조회할 회원 번호
					while(loop2) {
					
						if(back.equals("1")) {
							System.out.print("조회할 회원 번호: ");
							String member = scan.nextLine();
							membercheck(member.toUpperCase());
							
							System.out.println("1.회원 선택\t\t\t\t0.뒤로가기");
							System.out.print("번호 입력: ");
							back = scan.nextLine();
							
						} else if (back.equals("0")) {
							System.out.println("[종료]");
							loop2 = false;
						}
					}//while
					
					if(back.equals("0")) {
						loop = false;
					}
					
				}//while

			
			}

			/**
			 * 직원, 관리자가 전체회원의 농자재 구매내역 목록을 확인할 수 있는 메소드
			 */
			private void list() {
				// 회원 농자재 구매내역 목록
				
				try {

					BufferedReader reader = new BufferedReader(new FileReader(DATA));
					
					String line = "", line1 = "";

					System.out.println("[회원 농자재 구매내역 목록]");
					System.out.println("[회원 번호]  [회원 이름]\t [회원 전화번호]\t\t[회원 생년월일]\t[농자재]\t[구매 가격]\t[구매 날짜]");
					
						while ((line = reader.readLine()) != null) {

							String[] temp = line.split("★");
							membernum = temp[0];
							
								BufferedReader reader1 = new BufferedReader(new FileReader(DATA1));
								while ((line1 = reader1.readLine()) != null) {
								
									String[] temp1 = line1.split("★");
									
									if (membernum.equals(temp1[0])) { //같은 회원번호인 사람 읽어오기
										System.out.printf("  %s\t     %s\t %s\t\t%5s\t%s\t%,d\t\t%s\n"
												, temp[0]
												, temp[1]
												, temp[3]
												, temp[2]
												, temp1[2]
												, Integer.parseInt(temp1[4])
												, temp1[5]
												); 		
											}
									}
									reader1.close();
							}
							reader.close();
								

				} catch (Exception e) {
					System.out.println(e);
				}
				
			}//메소드
			
			/**
			 * 직원, 관리자가 원하는 회원 번호를 입력하여 해당 회원의 농자재 구매내역만을 확인할 수 있는 메소드
			 * @param member 조회할 회원 번호
			 */
			private void membercheck(String member) {
				//입력한 회원번호만 조회
				
				try {

					BufferedReader reader = new BufferedReader(new FileReader(DATA));
					
					String line = "", line1 = "";

					System.out.println("[회원 농자재 구매내역 목록]");
					System.out.println("[회원 번호]  [회원 이름]\t [회원 전화번호]\t\t[회원 생년월일]\t[농자재]\t[구매 가격]\t[구매 날짜]");
					
						while ((line = reader.readLine()) != null) {

							String[] temp = line.split("★");
							membernum = temp[0];
							
							if(member.equals(membernum)) {
							
								BufferedReader reader1 = new BufferedReader(new FileReader(DATA1));
								while ((line1 = reader1.readLine()) != null) {
								
									String[] temp1 = line1.split("★");
									
									if (membernum.equals(temp1[0])) { //같은 회원번호인 사람 읽어오기
										System.out.printf("  %s\t     %s\t %s\t\t%5s\t%s\t%,d\t\t%s\n"
												, temp[0]
												, temp[1]
												, temp[3]
												, temp[2]
												, temp1[2]
												, Integer.parseInt(temp1[4])
												, temp1[5]
												); 		
											}
									}
									reader1.close();
									
								}
							}
							reader.close();
								

				} catch (Exception e) {
					System.out.println(e);
				}
				
				
				
			}
			
			

}
