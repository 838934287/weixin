<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>头像</title>
</head>
<body>
	<c:forEach items="${fullFilePath}" var="image">
		<img src="${image}">
		<br/>
	</c:forEach>
</body>


</html>

