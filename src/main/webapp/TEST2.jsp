<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>File Upload!</title>
<script type="text/javascript"
	 src="<c:url value="js/jquery.js"/>"></script>
<script type="text/javascript">
	 
</script>
</head>
<body>
	 
	<!-- multiUploadFiles -->
	 
	<form:form action="./uploadImages" modelAttribute="multiFileUploadForm" method="post" enctype="multipart/form-data">
  		<div id="addFileDiv">
	 		<input type="text" name="itemname"> 
			<br /> 
		    <input type="text" name="description"> 
		    <br />    
		    <input type="file" name="file" /> 
		    <br />    
		    <input type="file" name="file" />
		    <br/>  
		</div>

  		<input type="submit" value="上传" />
 	</form:form>
</body>
</html>