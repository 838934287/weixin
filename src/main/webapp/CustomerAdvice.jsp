<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import = "com.huawei.model.CustomerAdviceModel" %>  
    
<!doctype html>
<html>
<head>
<title>用户评价表</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="用户评价申请" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- css files -->
<link href="css/style.css" rel='stylesheet' type='text/css' media="all" />
<!-- /css files -->
<!-- font files -->



</head>
<body>
<h1 class="header-agileits w3 w3l agile-info">用户评价表</h1>
<% CustomerAdviceModel customerAdviceModel = new CustomerAdviceModel(); 
   request.setAttribute("customerAdviceModel", customerAdviceModel);%>
<div class="content-w3ls">
	<div class="form-w3layouts">
		<form:form method="POST" action="./receiveAdvice" commandName="customerAdviceModel">
			<div class="form-control"> 
			    <form:label path="employeeId" class="header">师傅ID: <span>*</span></form:label>
                <form:input path="employeeId" type="text" id="name" name="name" placeholder="请填入师傅ID" title="" required="true" />
				<div class="clear"></div>
			</div>
		
			<div class="form-control">	
		
		    <form:label path="attitudeCode"  class="header">是否满意: <span>*</span></form:label>
         
            <form:radiobutton path="attitudeCode" value="10" checked="true"/><label  style="color:#FFFFFF">非常满意</label>
            <form:radiobutton path="attitudeCode" value="5" /><label  style="color:#FFFFFF" >满意</label>
            <form:radiobutton path="attitudeCode" value="-5" /><label  style="color:#FFFFFF">不满意</label>
            <form:radiobutton path="attitudeCode" value="-10" /><label  style="color:#FFFFFF">非常不满意</label>
        
			<!--<label class="header">是否满意: <span>*</span> </label>
				<input type="radio" name="attitudeCode" value="10" checked="true"/><label  style="color:#FFFFFF"  for="radio">非常满意</label> 
				<input type="radio" name="attitudeCode" value="5" /><label  style="color:#FFFFFF"  for="radio">满意</label> 
				<input type="radio" name="attitudeCode" value="-5" /><label  style="color:#FFFFFF"  for="radio">不满意</label>
				<input type="radio" name="attitudeCode" value="-10" /><label  style="color:#FFFFFF"  for="radio">非常不满意</label>
		 -->
		
				<div class="clear"></div>
			</div>
	
			
			<div class="form-control">
			
			<form:label path="adviceContent" class="enquiry">建议及意见:<span>*</span></form:label>
            <form:textarea path="adviceContent" id="message" name="message" placeholder="欢迎提供您的宝贵建议" required="true"></form:textarea>
				<!-- <label class="enquiry">建议及意见:<span>*</span> </label>
				<textarea id="message" name="message" placeholder="欢迎提供您的宝贵建议" title="Please enter Your Queries"></textarea>
				 -->
				<div class="clear"></div>
			</div>
			<div class="form-control">
				<input type="submit" class="register" value="提交评价">
				<div class="clear"></div>
			</div>	
		</form:form>
	</div>
</div>

</body>
</html>