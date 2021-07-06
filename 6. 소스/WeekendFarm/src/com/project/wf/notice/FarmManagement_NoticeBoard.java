package com.project.wf.notice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author 왕지민
 * 알림함 공지사항에 관련된 클래스
 *
 */
public class FarmManagement_NoticeBoard {
	
	private ArrayList<FarmManagement_NoticeBoardList> list = new ArrayList<FarmManagement_NoticeBoardList>();
	private static String path = "dat\\11. Noticeboard.dat";
	private Scanner scan = new Scanner(System.in);
	
	/**
	 * 공지사항 목록을 보여주는 메소드
	 */
	public void boardShowList() {
		System.out.println("=========================");
		System.out.println("공지사항 목록");
		System.out.println("=========================");
		System.out.println("[공지사항 번호]\t[공지사항 제목]");
		for(int i=0;i<list.size();i++) {
			System.out.println(String.format("%s\t\t%s ", list.get(i).getSeq(),list.get(i).getName()));
		}
		System.out.println("=========================");
		
	}

	/**
	 * 원하는 공지사항을 자세히 보기 위한 메소드
	 * @param login 회원/직원/관리자 상태를 계속 확인할 수 있도록 하기 위한 매개변수
	 */
	public void boardChoice(String login) {
		
		FarmManagement_NoticeMenu noticeMenu = new FarmManagement_NoticeMenu();
		FarmManagement_NoticeMenu_Admin noticeMenuAdmin = new FarmManagement_NoticeMenu_Admin();
		
		System.out.println("1.자세히 보기");
		System.out.println("0.뒤로가기");
		System.out.println("=========================");
		System.out.print("번호 입력: ");
		
		switch(scan.nextLine()) {
		case "1":
			System.out.print("선택할 공지사항 번호: ");
			int num = Integer.parseInt(scan.nextLine());
			System.out.println("[자세히 보기]");
			
			System.out.println(String.format("공지사항 번호: %s",list.get(num-1).getSeq()));
			System.out.println(String.format("공지사항 이름: %s",list.get(num-1).getName()));
			System.out.println(String.format("공지사항 기간: %s",list.get(num-1).getPriod()));
			System.out.println(String.format("내용 %s\n",list.get(num-1).getContent()));
			System.out.println("=========================");
			System.out.println("0.뒤로가기");
			System.out.print("번호입력: ");
			num = Integer.parseInt(scan.nextLine());
			
			if (login.startsWith("X")) {
				noticeMenu.noticeMenu(login);
			} else if (login.startsWith("Z") || login.startsWith("Y")) {
				noticeMenuAdmin.noticeMenu_Admin(login);
			}

			break;
		case "0":
			noticeMenu.noticeMenu(login);
			break;
		default:
			System.out.println("[잘못 입력하셨습니다. 다시 입력해주세요.]");
			boardChoice(login);
		}
	}
	
	/**
	 * 파일에 저장되어있는 공지사항글을 ArrayList에 객체생성하여 요소들 저장하는 메소드
	 * @return 공지사항글이 저장된 ArrayList를 반환
	 */
	public ArrayList<FarmManagement_NoticeBoardList> boardListup() {

		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			String line = "";
			FarmManagement_NoticeBoardList noticeBoardList;
			String seq="",  name="", priod="",  content="";
			
			while ((line = reader.readLine()) != null) {
				if(line.substring(0,2).equals("내용")) {
					String tempContent = "";
					while (!(line = reader.readLine()).equals("==========")) {
						tempContent += line + "\r\n";
					}
					content = tempContent;
					noticeBoardList = new FarmManagement_NoticeBoardList(seq,name,priod,content);
					list.add(noticeBoardList);
				} else if( line.substring(0,7).equals("공지사항 번호")) {
					seq=line.substring(9);
				} else if(line.substring(0,7).equals("공지사항 이름")) {
					name=line.substring(9);
				} else if(line.substring(0,2).equals("기간")) {
					priod=line.substring(4);
				} 
			}

			reader.close();
			return list;
		} catch (IOException e) {
			System.out.println(e);
		}
		return null;
	}
}
