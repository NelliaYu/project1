package com.project.wf.resource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author 왕지민
 * 농기구 리스트를 보여주기 위한 클래스
 *
 */
public class FarmManagement_ToolListup {

	/**
	 *
	 * 현재 저장되어있는 농기구 리스트 출력
	 * @return 다른 페이지에서 원하는 농기구 배열을 return 값으로 지정
	 * 
	 */
	public ArrayList<FarmManagement_ToolList> toolListup() {
		ArrayList<FarmManagement_ToolList> toolList = new ArrayList<FarmManagement_ToolList>();
		String path = "dat\\5. FarmToolList.dat";

		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line = "";

			while ((line = reader.readLine()) != null) {
				String[] temp = line.split("★");
				toolList.add(new FarmManagement_ToolList(Integer.parseInt(temp[0].substring(1,4)), temp[1], Integer.parseInt(temp[2]), Integer.parseInt(temp[3])));

			}
			reader.close();

		} catch (IOException e) {
			System.out.println(e);
		}

		System.out.println("=========================");
		System.out.println("농기구 목록");
		System.out.println("=========================");
		System.out.println("[농기구 번호]\t[농기구 이름]\t[가격]\t\t[남은 수량]");
		for (int i = 0; i < toolList.size(); i++) {
			System.out.println(String.format("%s\t\t%s\t\t%s\t\t%s", toolList.get(i).getToolNum(), toolList.get(i).getToolName(),
					toolList.get(i).getToolRentalPrice(), toolList.get(i).getToolRestCount()));
		}
		System.out.println("=========================");
		return toolList;
	}

}
