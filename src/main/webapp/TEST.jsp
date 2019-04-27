<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form id="add" class="form-horizontal" enctype="multipart/form-data" action="./uploadImages" method="post">
		上传图片1：<input  type="file" name="file"/>
		上传图片2：<input  type="file" name="file"/>
		上传图片3：<input  type="file" name="file"/>
		
		<input type="submit" value="提交"/>
	</form>


</body>
</html>