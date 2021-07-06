package com.project.wf.resource;

/**
 * 
 * @author 왕지민
 * 농기구 대여 내역 리스트 클래스
 *
 */
public class FarmManagement_ToolRentalList {
	private String memNum;
	private String toolNum;
	private String toolName;
	private int toolRentalCount;
	private int toolStartPeriod;
	private int toolEndPeriod;
	private int toolRentalPrice;
	
	/**
	 * 농기구 대여 리스트 객체 초기값
	 * @param memNum	회원 번호
	 * @param toolNum	농기구 번호
	 * @param toolName	농기구 이름
	 * @param toolRentalCount	농기구 대여 수량
	 * @param fvStartPeriod		농기구 대여일
	 * @param fvEndPeriod		농기구 반납일
	 * @param toolRentalPrice	농기구 대여 가격
	 */
	public FarmManagement_ToolRentalList(int memNum, int toolNum, String toolName, int toolRentalCount,
			int fvStartPeriod, int fvEndPeriod, int toolRentalPrice) {

		this.memNum = String.format("X%03d", memNum);;
		this.toolNum = String.format("B%03d", toolNum);
		this.toolName = toolName;
		this.toolRentalCount = toolRentalCount;
		this.toolStartPeriod = fvStartPeriod;
		this.toolEndPeriod = fvEndPeriod;
		this.toolRentalPrice = toolRentalPrice;
	}
	
	public String getMemNum() {
		return memNum;
	}
	public void setMemNum(String memNum) {
		this.memNum = memNum;
	}
	public String getToolNum() {
		return toolNum;
	}
	public void setToolNum(String toolNum) {
		this.toolNum = toolNum;
	}
	public String getToolName() {
		return toolName;
	}
	public void setToolName(String toolName) {
		this.toolName = toolName;
	}
	public int getToolRentalCount() {
		return toolRentalCount;
	}
	public void setToolRentalCount(int toolRentalCount) {
		this.toolRentalCount = toolRentalCount;
	}
	public int gettoolStartPeriod() {
		return toolStartPeriod;
	}
	public void settoolStartPeriod(int toolStartPeriod) {
		this.toolStartPeriod = toolStartPeriod;
	}
	public int gettoolEndPeriod() {
		return toolEndPeriod;
	}
	public void settoolEndPeriod(int toolEndPeriod) {
		this.toolEndPeriod = toolEndPeriod;
	}
	public int getToolRentalPrice() {
		return toolRentalPrice;
	}
	public void setToolRentalPrice(int toolRentalPrice) {
		this.toolRentalPrice = toolRentalPrice;
	}
	@Override
	public String toString() {
		return "FarmManagement_ToolRentalList [memNum=" + memNum + ", toolNum=" + toolNum + ", toolName=" + toolName
				+ ", toolRentalCount=" + toolRentalCount + ", fvStartPeriod=" + toolStartPeriod + ", fvEndPeriod="
				+ toolEndPeriod + ", toolRentalPrice=" + toolRentalPrice + "]";
	}
	
	
}
