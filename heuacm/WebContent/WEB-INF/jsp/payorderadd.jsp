<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>

<script src="static/js/jquery/2.0.0/jquery.min.js"></script>
<link href="static/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<link href="static/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<script src="static/js/bootstrap/3.3.6/bootstrap.min.js"></script>
<script src="static/htmleditor/ckeditor/ckeditor.js"></script>
<script src="static/js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>

<title>发布课程</title>
<script type="text/javascript">
function validate_form2()
{
if (document.getElementById("ordernum").value=="")
  {ordernum.focus();alert("订单号不能为空");return false}
return true;

}

</script>

</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-xs-1 ">
        </div>
        <div class="col-xs-8 ">
        <h1>add支付</h1>
        <form action="payorderAddAction" onsubmit="return validate_form2();" method="post">
			<span>订单号:</span><input type="text" class="form-control" id="ordernum" name="ordernum"><br>
			<input type="submit" class="form-control" value="提交" >
		</form>
		</div>
    </div>
</div>

</body>
</html>