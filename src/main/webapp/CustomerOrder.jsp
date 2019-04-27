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
<link rel="stylesheet" type="text/css" href="css/area.css">
 <style>    
              .ui_button {
                  display: inline-block;
                  line-height: 45px;
                 padding: 0 70px;
                 color: #FFFFFF;
                 font-family: "微软雅黑";
                 font-weight: 700;
                 cursor: pointer;
             }
             .ui_button_primary {
                 background-color: #FF6600;
             }
             label.ui_button:hover {
                 background-color: #d46216;
             }
         </style>


</head>
<body>
<h1 class="header-agileits w3 w3l agile-info">用户在线下单表</h1>
<% WeixinUserOrderModel weixinUserOrderModel = new WeixinUserOrderModel(); 
   request.setAttribute("weixinUserOrderModel", weixinUserOrderModel);%>
   

<script type="text/javascript">

    //选择图片，马上预览
    function xmTanUploadImg(obj) {
 
        var fl=obj.files.length;
        for(var i=0;i<fl;i++){
            var file=obj.files[i];
            var reader = new FileReader();
 
            //读取文件过程方法
 						reader.onload = function (e) {
                console.log("成功读取....");
 
                var imgstr='<img style="width:80%;height:80%;" src="'+e.target.result+'"/>';
                var oimgbox=document.getElementById("imgboxid");
                var ndiv=document.createElement("div");
 
                ndiv.innerHTML=imgstr;
                ndiv.className="img-div";
                oimgbox.appendChild(ndiv);
               
            }
 
            reader.readAsDataURL(file);
//alert(1);
        }
 
    }
</script>
   
   
<div class="content-w3ls">
	<div class="form-w3layouts">
		<form:form method="POST" action="./receiveOrder" commandName="weixinUserOrderModel" enctype="multipart/form-data">
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
    			
           	<div class="img-box" id="imgboxid"></div>
           	<%--<label class="ui_button ui_button_primary" for="xdaTanFileImg">上传照片</label>
			<form:input path = "pics" type="file" id="xdaTanFileImg" style="visibility: hidden;"  name="pics" onchange="xmTanUploadImg(this)"/>
   		 	<input type="file" id="xdaTanFileImg" style="visibility: hidden;"  name="file" onchange="xmTanUploadImg(this)"/>
   		 --%>
   		 	<input class="ui_button ui_button_primary" value="上传照片" type="file" name="file" />	
   			
   			
			<div class="form-control">			
			<form:label path="area" class="enquiry">地址:<span>*</span></form:label>
			
		    <%-- <form:input path="area" id="city"  name="city"   placeholder="城市选择特效" /> --%>
	    	<form:input path="area" id="expressArea" name="area" placeholder="选择地址"/>
	    	
			<!-- expressArea -->
			<!--选择地区弹层-->
			<section id="areaLayer" class="express-area-box">
				<header>
					<h3>选择省市区</h3>
					<div class="selet-area-wrap">
						<p><span class="one"></span></p>
						<p><span class="two"></span></p>
						<p><span class="three"></span></p>
					</div>
				</header>
				<article id="areaBox">
					<ul id="areaList" class="area-list"></ul>
				</article>
			</section>
			
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


<script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery.area.js" type="text/javascript" charset="utf-8"></script>

</body>
</html>