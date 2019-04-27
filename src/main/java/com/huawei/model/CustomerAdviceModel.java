package com.huawei.model;

public class CustomerAdviceModel {
	
	private Long id;
	private String employeeId;
	private String attitudeCode;
	private String adviceContent;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId == null ? null : employeeId.trim();
	}
	public String getAttitudeCode() {
		return attitudeCode;
	}
	public void setAttitudeCode(String attitudeCode) {
		this.attitudeCode = attitudeCode == null ? null : attitudeCode.trim();
	}
	public String getAdviceContent() {
		return adviceContent;
	}
	public void setAdviceContent(String adviceContent) {
		this.adviceContent = adviceContent == null ? null : adviceContent.trim();
	}

	
	
	
	

}
