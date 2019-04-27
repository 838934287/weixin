<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Spring MVC Form Handling</title>
</head>
<body>

	<h2>User Information</h2>

	<form method="POST" action="./receiveAdvice2">
		<table>
			<tr>
				<td><label>employeeId</label></td>
				<td><input type="text" name="employeeId" /></td>
			</tr>

			<tr>
				<td><label>adviceContent</label></td>
				<td><input type="textarea" name="adviceContent" rows="5"
					cols="30" /></td>
			</tr>

			<tr>
				<td><label>attitudeCode</label></td>
				<td><input type="radio" name="attitudeCode" value="10"
					checked="true" />非常满意 <input type="radio" name="attitudeCode"
					value="5" />满意 <input type="radio" name="attitudeCode" value="-5" />不满意
					<input type="radio" name="attitudeCode" value="-10" />非常不满意</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form>
</body>
</html>