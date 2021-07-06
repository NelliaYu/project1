package com.project.wf.notice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * @author 왕지민
 * 회원과 직원간의 메시지를 주고받기위한 클래스
 *
 */
public class FarmManagement_NoticeMessage {

	private ArrayList<FarmManagement_NoticeMessageList> list = new ArrayList<FarmManagement_NoticeMessageList>();
	private static String path = "dat\\7. Message";

	/**
	 * 메시지 리스트 출력 메소드
	 * @param login 회원/직원/관리자 상태를 계속 확인할 수 있도록 하기 위한 매개변수
	 */
	public void noticeMessageShowList(String login) {
//		path = String.format(path + "\\7. Message_%s.dat", login);
		
		
		System.out.println("=========================");
		System.out.println("메시지 목록");
		System.out.println("=========================");
		System.out.println("[메시지 번호]\t[날짜]\t\t\t[받은 메시지]\t\t\t\t\t[보낸 메시지]");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(String.format("%s\t\t%s\t%s\t\t\t\t\t%s", list.get(i).getSeq(), list.get(i).getPriod(),
					list.get(i).getReceiveContent(), list.get(i).getSendContent()));
		}
		System.out.println("=========================");

	}

	/**
	 * ArrayList에 메시지 객체를 생성하여, 파일로부터 읽어 객체 생성후, 배열에 저장하는 메소드
	 * @param login 회원/직원/관리자 상태를 계속 확인할 수 있도록 하기 위한 매개변수
	 * @param path 회원번호 별로 메시지함을 관리하기 위해, path를 설정하여 파일 경로를 변경
	 * @return 메시지들이 저장되어있는 ArrayList를 반환
	 */
	public ArrayList<FarmManagement_NoticeMessageList> noticeMessageListup(String login) {
		
		try {
			
			path = String.format(path + "\\7. Message_%s.dat", login);
			BufferedReader reader = new BufferedReader(new FileReader(path));
			FarmManagement_NoticeMessageList noticeMessageList;
			String line = "";
			String seq = "", priod = "", content = "";
			String tempContent = "";

			while ((line = reader.readLine()) != null) {

				if (line.substring(0, 5).equals("받은 내용")) {
					while (!(line = reader.readLine()).equals("==========")) {
						tempContent += line;
					}
					content = tempContent;
					noticeMessageList = new FarmManagement_NoticeMessageList(seq, priod, "없음", content, login);
					list.add(noticeMessageList);
					tempContent = "";

				} else if (line.substring(0, 5).equals("보낸 내용")) {
					while (!(line = reader.readLine()).equals("==========")) {
						tempContent += line;
					}
					content = tempContent;
					noticeMessageList = new FarmManagement_NoticeMessageList(seq, priod, content, "없음", login);
					list.add(noticeMessageList);
					tempContent = "";
				}

				else if (line.substring(0, 6).equals("메시지 번호")) {
					seq = line.substring(8);
				} else if (line.substring(0, 2).equals("기간")) {
					priod = line.substring(4);
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
