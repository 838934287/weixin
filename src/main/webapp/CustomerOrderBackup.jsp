<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import = "com.huawei.model.WeixinUserOrderModel" %>  
    
<!doctype html>
<html>
<head>
<title>在线下单</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="用户下订单" />
<!-- /css files -->
<link href="css/style.css" rel='stylesheet' type='text/css' media="all" />

<style type="text/css">
._citys {width:100%; height:85%;display: inline-block; position: relative;bottom:-470px;background-color: white;}
._citys span {color: #56b4f8; height: 15px; width: 15px; line-height: 15px; text-align: center; border-radius: 3px; position: absolute; right: 1em; top: 10px; border: 1px solid #56b4f8; cursor: pointer;}
._citys0 {width: 100%; height: 34px; display: inline-block; border-bottom: 2px solid #56b4f8; padding: 0; margin: 0;}
._citys0 li {float:left; height:34px;line-height: 34px;overflow:hidden; font-size: 15px; color: #888; width: 80px; text-align: center; cursor: pointer; }
.citySel {background-color: #56b4f8; color: #fff !important;}
._citys1 {width: 100%;height:80%; display: inline-block; padding: 10px 0; overflow: auto;}
._citys1 a {height: 35px; display: block; color: #666; padding-left: 6px; margin-top: 3px; line-height: 35px; cursor: pointer; font-size: 13px; overflow: hidden;}
._citys1 a:hover { color: #fff; background-color: #56b4f8;}
.ui-content{border: 1px solid #EDEDED;}
li{list-style-type: none;}
</style>

<!-- JS -->
<script src="js/jquery.min.js"></script>
<script src="js/common.js"></script>
<script src="js/Popt.js"></script>
<script src="js/cityJson.js"></script>
<script src="js/citySet.js"></script>

</head>
<body>
<h1 class="header-agileits w3 w3l agile-info">用户在线下单表</h1>
<% WeixinUserOrderModel weixinUserOrderModel = new WeixinUserOrderModel(); 
   request.setAttribute("weixinUserOrderModel", weixinUserOrderModel);%>
<div class="content-w3ls">
	<div class="form-w3layouts">
		<form:form method="POST" action="./receiveOrder" commandName="weixinUserOrderModel">
			<div class="form-control"> 
			    <form:label path="userName" class="header">姓名:<span>*</span></form:label>
                <form:input path="userName" type="text" id="name" name="name" placeholder="怎么称呼" title="" required="true" />
				
				<div class="clear"></div>
			</div>
			<div class="form-control"> 
			    <form:label path="phoneNo" class="header">电话号码:<span>*</span></form:label>
                <form:input path="phoneNo" type="text" id="name"  name="name"  placeholder="填写正确电话号码" title="" required="true"/>
				
				<div class="clear"></div>
			</div>
			<div class="form-control"> 
			    <form:label path="orderRequestDate" class="header">上门日期:<span>*</span></form:label>
                <form:input path="orderRequestDate" type="date" id="name"  name="name"  title="" required="true" />
				
				<div class="clear"></div>
			</div>	
			
			
			<div class="form-control"> 
			    <form:label path="landlordName" class="header">房东姓名:<span>*</span></form:label>
                <form:input path="landlordName" type="text" id="name"  name="name"  placeholder="房东姓名..." title="" required="true" />
				
				<div class="clear"></div>
			</div>
			<div class="form-control"> 
			    <form:label path="landlordPhoneNo" class="header">房东电话:<span>*</span></form:label>
                <form:input path="landlordPhoneNo" type="text" id="name"  name="name"  placeholder="填写房东电话号码" title="" required="true"/>
				
				<div class="clear"></div>
			</div>
			
			
			
			<div class="form-control">
			
			<form:label path="area" class="enquiry">地址:<span>*</span></form:label>
			
		    <form:input path="area" id="city"  name="city"   placeholder="城市选择特效" />
		    
		    
		    
		    
			<form:textarea path="area2" id="message" name="message" placeholder="请填写准确地址,便于尽快上门" required="true"></form:textarea>
				<div class="clear"></div>
			</div>
			<div class="form-control">
				<input type="submit" class="register" value="提交订单">
				<div class="clear"></div>
			</div>	
		</form:form>
	</div>
</div>

</body>
</html>