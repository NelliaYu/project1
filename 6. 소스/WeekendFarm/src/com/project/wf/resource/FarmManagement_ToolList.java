package com.project.wf.resource;

/**
 * 
 * @author 왕지민
 * 농기구 품목 리스트 클래스
 *
 */
public class FarmManagement_ToolList {
	private String toolNum;
	private String toolName;
	private int toolRentalPrice;
	private int toolRestCount;
	
	/**
	 * 농기구 리스트 배열 초기값
	 * @param toolNum	농기구 번호
	 * @param toolName	농기구 이름
	 * @param toolPrice	농기구 대여 가격
	 * @param toolRestCount	농기구 잔여 수량
	 */
	public FarmManagement_ToolList(int toolNum, String toolName, int toolPrice, int toolRestCount) {
		this.toolNum = String.format("B%03d", toolNum);
		this.toolName = toolName;
		this.toolRentalPrice = toolPrice;
		this.toolRestCount = toolRestCount;
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
	public int getToolRentalPrice() {
		return toolRentalPrice;
	}
	public void setToolPrice(int toolPrice) {
		this.toolRentalPrice = toolPrice;
	}
	public int getToolRestCount() {
		return toolRestCount;
	}
	public void setToolRestCount(int toolRestCount) {
		this.toolRestCount = toolRestCount;
	}
	@Override
	public String toString() {
		return "FarmManagement_ToolList [toolNum=" + toolNum + ", toolName=" + toolName + ", toolPrice=" + toolRentalPrice
				+ ", toolRestCount=" + toolRestCount + "]";
	}
	
	
	
}
