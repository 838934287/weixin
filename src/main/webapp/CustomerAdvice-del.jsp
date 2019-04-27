<%@ page language="java" contentType="text/html; charset=utf-8"
     pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import = "com.huawei.model.CustomerAdviceModel" %>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>客户评价</title>


</head>
<body>

<h2>User Information</h2>
<% CustomerAdviceModel customerAdviceModel = new CustomerAdviceModel(); 
   request.setAttribute("customerAdviceModel", customerAdviceModel);%>
<form:form method="POST" action="./receiveAdvice" commandName="customerAdviceModel">
   <table>
      <tr>
         <td><form:label path="employeeId">employeeId</form:label></td>
         <td><form:input path="employeeId" /></td>
      </tr>
      
      <tr>
         <td><form:label path="adviceContent">adviceContent</form:label></td>
         <td><form:textarea path="adviceContent" rows="5" cols="30" /></td>
      </tr>  

      <tr>
         <td><form:label path="attitudeCode">attitudeCode</form:label></td>
         <td>
            <form:radiobutton path="attitudeCode" value="10" checked = "true"/>非常满意
            <form:radiobutton path="attitudeCode" value="5" />满意
            <form:radiobutton path="attitudeCode" value="-5" />不满意
            <form:radiobutton path="attitudeCode" value="-10" />非常不满意
         </td>
      </tr>       
      <tr>
         <td colspan="2">
            <input type="submit" value="Submit"/>
         </td>
      </tr>
   </table>  
</form:form>
</body>
</html>