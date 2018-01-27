<%@page import="java.awt.print.Printable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/taglib.jsp" />
<html>
<head>
<script type="text/javascript" src="${ctx }/js/jquery-3.2.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	function checkFileExt(file) {
		var flag = false; //状态
		var arr = [ "sql" ];
		//取出上传文件的扩展名
		var index = file.lastIndexOf(".");
		var ext = file.substr(index + 1);
		if (ext == "sql") {
			flag = true;
		}
		//条件判断
		if (!flag) {
			alert("请上传sql文件");
		}
	}
</script>
</head>
<body>
	<div>
		<%--文件上传的话需要enctype="multipart/form-data"--%>
		<form id="myform" action="${ctx }/index/upload" method="post"
			enctype="multipart/form-data">
			<table align="center">
				<tr>
					<td>文件:<input type="file" name="file"
						onchange="checkFileExt(this.value)"></td>
					<td><input type="submit" value="提交sql文件并执行" id="mysubmit"></td>
				</tr>
			</table>
		</form>
	</div>
	<div>---------</div>
	
	<script type="text/javascript">
		$('#mysubmit').click(function() {
			var $s = $("input[name='file']").val();
			if ($s == null || $s == "") {
				alert("请选择文件");
				return false;
			}
		});
	</script>
</body>
</html>