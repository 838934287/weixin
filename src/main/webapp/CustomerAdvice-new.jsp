<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="com.huawei.model.CustomerAdviceModel"%>

<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/bootstrap.min.css" />


<script src="js/jquery-3.2.1.min.js" type="text/javascript"
	charset="utf-8"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script src="js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>


<title>请对此次服务进行评价</title>
</head>
<body>
	<br />
	<br />
	<br />
	<div class="container">
		<h3 class="text-center">请对此次服务进行评价</h3>
		<form class="form-horizontal" id="CustomerAdviceForm" method="post" action="./receiveAdviceNew">
			<div id="EmployeeId" class="form-group">
				<label
					style="color: #000000; font-family: '微软雅黑'; font-size: larger;">上门维修师傅ID:<span
					style="color: red;">*</span></label> <br /> <input
					name="employeeId" type="text"
					class="form-control col-lg-10" placeholder="多个师傅ID,请用英文逗号分开." />
			</div>
			<div class="form-group">
				<label
					style="color: #000000; font-family: '微软雅黑'; font-size: larger;">您对此次服务是否满意:<span
					style="color: red;">*</span></label> <br /> <label class="radio-inline">
					<input id="radio1" type="radio"
					name="attitudeCode" value="10"
					checked="checked">非常满意
				</label> <label class="radio-inline"> <input id="radio2"
					type="radio" name="attitudeCode" value="5">满意
				</label> <label class="radio-inline"> <input id="radio3"
					type="radio" name="attitudeCode" value="-5">不满意
				</label> <label class="radio-inline"> <input id="radio4"
					type="radio" name="attitudeCode" value="-10">非常不满意
				</label>
			</div>

			<!--<select required="required" class="form-control" name="">
						<option value="10">非常满意</option>
						<option value="5">满意</option>
						<option value="-5">不满意</option>
						<option value="-10">非常不满意</option>
					</select>-->

			<div class="form-group">
				<label
					style="color: #000000; font-family: '微软雅黑'; font-size: larger;">建议及意见:<span
					style="color: red;">*</span></label>
				<textarea name="adviceContent" 
					class="form-control" rows="3"></textarea>

			</div>
			<div class="form-group">
				<div id="">
					<button type="submit" class="btn btn-primary col-lg-12">提交评价</button>
				</div>

			</div>
		</form>


	</div>
</body>
</html>