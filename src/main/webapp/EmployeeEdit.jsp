<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑页面</title>
</head>
<body>
	欢迎你：${currentEmployee}
	<hr>
	<form:form action="${pageContext.request.contextPath}/employeeSave" method="post" modelAttribute="returnEmployee">
		员工编号:<form:input path="employeeid" /><br>
		用户名:<form:input path="username" /><br>
		密码:<form:input path="password" /><br>
		微信OPEN_ID:<form:input path="weixinId" /><br>
		角色:<form:input path="role" /><br>
		
			<input type="submit" value="提交"/>
	</form:form>
</body>
</html>

