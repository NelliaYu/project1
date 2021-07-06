package com.project.wf.notice;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class FarmManagement_NoticeMessageSend_Admin {
	private ArrayList<FarmManagement_NoticeMessageList> list = new ArrayList<FarmManagement_NoticeMessageList>();
	private static String path = "dat\\7. Message";
	private Scanner scan = new Scanner(System.in);

	/**
	 * 메시지 관련 매뉴 출력 메소드
	 * @param login 회원/직원/관리자 상태를 계속 확인할 수 있도록 하기 위한 매개변수
	 */
	public void messageMenu(String login) {
		path = String.format(path + "\\7. Message_%s.dat", login);
		String num = "0";
		
		System.out.println("1.메시지 보내기");
		System.out.println("0.뒤로가기");
		System.out.println("=========================");
		System.out.print("번호 입력: ");
		num = scan.nextLine();
		switch (num) {
		case "1":
			FarmManagement_NoticeMessage message = new FarmManagement_NoticeMessage();
			list = message.noticeMessageListup(login);
			noticeMessageSend(login);
			messageWrite();
			break;
		case "0":
			break;
		}
	}

	/**
	 * 메시지를 파일에 저장하는 메소드
	 */
	private void messageWrite() {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			for (FarmManagement_NoticeMessageList message : list) {
				writer.write(String.format("메시지 번호: %s\n기간: %s\n보낸 내용\n%s\n==========\n",
						message.getSeq(), message.getPriod(), message.getSendContent(), "", ""));
			}
			writer.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/**
	 * 메시지를 작성하여 ArrayList에 추가하는 메소드
	 * @param login 회원/직원/관리자 상태를 계속 확인할 수 있도록 하기 위한 매개변수
	 */
	private void noticeMessageSend(String login) {
		FarmManagement_NoticeMessageList message;
		
		System.out.println("보낼 메시지 내용 (exit 입력시 종료)");
		String content = "";

		while (true) {
			String temp = scan.nextLine();
			if (temp.equals("exit")) {
				break;
			}
			content += temp;
		}

		Calendar now = Calendar.getInstance();

		message = new FarmManagement_NoticeMessageList(String.format("M%03d",getSeq()), String.format("%tF %tT", now, now), content,"",
				login);

		list.add(message);

	}

	/**
	 * 메시지 글번호를 최근번호로 출력하는 메소드
	 * @return 메시지 글번호
	 */
	private int getSeq() {

		int max = 0;

		for (FarmManagement_NoticeMessageList m : list) {
			if (Integer.parseInt(m.getSeq().substring(1, 4)) > max) {
				max = Integer.parseInt(m.getSeq().substring(1, 4));
			}
		}

		return (max + 1);
	}
}