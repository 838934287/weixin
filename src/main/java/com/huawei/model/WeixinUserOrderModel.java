package com.huawei.model;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class WeixinUserOrderModel {
    private Integer id;

    private String customerOrder;

    private String orderRequestDate;

    private String orderDate;

    private String area;
    private String area2;

 
	private String phoneNo;

    private String userWeixinId;
    private MultipartFile[] pics;
    

	

	public MultipartFile[] getPics() {
		return pics;
	}

	public void setPics(MultipartFile[] pics) {
		this.pics = pics;
	}

	public String getArea2() {
 		return area2;
 	}

 	public void setArea2(String area2) {
 		this.area2 = area2;
 	}

    
    public String getLandlordName() {
		return landlordName;
	}

	public void setLandlordName(String landlordName) {
		this.landlordName = landlordName;
	}

	public String getLandlordPhoneNo() {
		return landlordPhoneNo;
	}

	public void setLandlordPhoneNo(String landlordPhoneNo) {
		this.landlordPhoneNo = landlordPhoneNo;
	}

	private String userName;
    
    private String landlordName;
    private String landlordPhoneNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(String customerOrder) {
        this.customerOrder = customerOrder == null ? null : customerOrder.trim();
    }

    public String getOrderRequestDate() {
        return orderRequestDate;
    }

    public void setOrderRequestDate(String orderRequestDate) {
        this.orderRequestDate = orderRequestDate == null ? null : orderRequestDate.trim();
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate == null ? null : orderDate.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? null : phoneNo.trim();
    }

    public String getUserWeixinId() {
        return userWeixinId;
    }

    public void setUserWeixinId(String userWeixinId) {
        this.userWeixinId = userWeixinId == null ? null : userWeixinId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }
}