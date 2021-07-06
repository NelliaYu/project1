package com.project.wf.resource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author 왕지민
 * 농작물 리스트를 보여주기 위한 클래스
 *
 */
public class FarmManagement_FVListup {
	private ArrayList<FarmManagement_FVList> fvListA = new ArrayList<FarmManagement_FVList>();
	private ArrayList<FarmManagement_FVList> fvListB = new ArrayList<FarmManagement_FVList>();
	private ArrayList<FarmManagement_FVList> fvListC = new ArrayList<FarmManagement_FVList>();
	private static String path = "dat\\3. PlantList.dat";
	private static String seedPath = "dat\\4. Seed.dat";

	/**
	 * 현재 저장되어있는 씨앗 목록 리스트 출력
	 * @param num 농작물 타입을 받기위한 매개변수
	 */
	public void fvShowList(String num) {
		
		switch (num) {
		case "1":
			System.out.println("=========================");
			System.out.println("과채류 씨앗 목록");
			System.out.println("=========================");
			System.out.println("[농작물 번호]\t[농장물 종류]\t[농작물 이름]\t[가격]\t\t[난이도]\t\t[평균 재배 기간]\t\t[남은 수량]");
			for (int i = 0; i < fvListA.size(); i++) {
				System.out.println(String.format("%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t\t%,d", fvListA.get(i).getFvNum(),
						fvListA.get(i).getFvType(), fvListA.get(i).getFvName(), fvListA.get(i).getFvPrice(),
						fvListA.get(i).getFvLevel(), fvListA.get(i).getFvPeriod(), fvListA.get(i).getFvRestCount()));
			}
			System.out.println("=========================");
			break;
		case "2":
			System.out.println("=========================");
			System.out.println("근채류 씨앗 목록");
			System.out.println("=========================");
			System.out.println("[농작물 번호]\t[농장물 종류]\t[농작물 이름]\t[농작물 가격]\t\t[난이도]\t\t[농작물 재배 기간]\t\t[농작물 남은 수량]");
			for (int i = 0; i < fvListB.size(); i++) {
				System.out.println(String.format("%s\t\t%s\t\t%s\t\t%s\t\t\t%s\t\t%s\t\t\t%,d",
						fvListB.get(i).getFvNum(), fvListB.get(i).getFvType(), fvListB.get(i).getFvName(),
						fvListB.get(i).getFvPrice(), fvListB.get(i).getFvLevel(), fvListB.get(i).getFvPeriod(),
						fvListB.get(i).getFvRestCount()));
			}
			System.out.println("=========================");
			break;
		case "3":
			System.out.println("=========================");
			System.out.println("엽채류 씨앗 목록");
			System.out.println("=========================");
			System.out.println("[농작물 번호]\t[농장물 종류]\t[농작물 이름]\t[농작물 가격]\t\t[난이도]\t\t[농작물 재배 기간]\t\t[농작물 남은 수량]");
			for (int i = 0; i < fvListC.size(); i++) {
				System.out.println(String.format("%s\t\t%s\t\t%s\t\t%s\t\t\t%s\t\t%s\t\t\t%,d",
						fvListC.get(i).getFvNum(), fvListC.get(i).getFvType(), fvListC.get(i).getFvName(),
						fvListC.get(i).getFvPrice(), fvListC.get(i).getFvLevel(), fvListC.get(i).getFvPeriod(),
						fvListC.get(i).getFvRestCount()));
			}
			System.out.println("=========================");
			break;

		}
	}

	/**
	 * 씨앗의 타입별로 각 ArrayList에 객체생성하여 요소들 저장
	 * @param num 농작물 타입을 받기위한 매개변수
	 * @return 다른 페이지에서 원하는 씨앗 타입의 배열을 return 값으로 지정
	 */
	public ArrayList<FarmManagement_FVList> fvListup(String num) {
		try {

			BufferedReader reader = new BufferedReader(new FileReader(path));
			BufferedReader seedReader = new BufferedReader(new FileReader(seedPath));
			String line = "";

			// 씨앗정보에 난이도랑 등급이 없어서 수정 2개 파일 리드 필요
			while ((line = reader.readLine()) != null) {

				String[] temp = line.split("★");
				switch (temp[2]) {

				case "과채류":
					fvListA.add(new FarmManagement_FVList(Integer.parseInt(temp[0].substring(1, 4)), temp[2], temp[1],
							0, temp[4], Integer.parseInt(temp[5]), 0));
					break;
				case "근채류":
					fvListB.add(new FarmManagement_FVList(Integer.parseInt(temp[0].substring(1, 4)), temp[2], temp[1],
							0, temp[4], Integer.parseInt(temp[5]), 0));
					break;
				case "엽채류":
					fvListC.add(new FarmManagement_FVList(Integer.parseInt(temp[0].substring(1, 4)), temp[2], temp[1],
							0, temp[4], Integer.parseInt(temp[5]), 0));
					break;
				}

			}

			while ((line = seedReader.readLine()) != null) {
				String[] temp = line.split("★");
				switch (temp[0]) {
				case "과채류":
					for (int i = 0; i < fvListA.size(); i++) {
						if (fvListA.get(i).getFvNum().equals(temp[3])) {
							fvListA.get(i).setFvPrice(Integer.parseInt(temp[2]));
							fvListA.get(i).setFvRestCount(Integer.parseInt(temp[4]));
							break;
						}
					}

				case "근채류":
					for (int i = 0; i < fvListB.size(); i++) {
						if (fvListB.get(i).getFvNum().equals(temp[3])) {
							fvListB.get(i).setFvPrice(Integer.parseInt(temp[2]));
							fvListB.get(i).setFvRestCount(Integer.parseInt(temp[4]));
							break;
						}
					}

				case "엽채류":
					for (int i = 0; i < fvListC.size(); i++) {
						if (fvListC.get(i).getFvNum().equals(temp[3])) {
							fvListC.get(i).setFvPrice(Integer.parseInt(temp[2]));
							fvListC.get(i).setFvRestCount(Integer.parseInt(temp[4]));
							break;
						}
					}
				}
			}

			seedReader.close();
			reader.close();

			if (num.equals("1")) {
				return fvListA;
			} else if (num.equals("2")) {
				return fvListB;
			} else if (num.equals("3")) {
				return fvListC;
			}

		} catch (IOException e) {
			System.out.println(e);
		}
		return null;
	}
}
