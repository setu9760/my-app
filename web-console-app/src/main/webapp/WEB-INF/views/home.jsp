<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="include.jsp"%>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<link type="text/css" href=<c:url value="resources/style.css"/> rel="stylesheet" >

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${ title }</title>
</head>
<body>

	<div class="wrapper">

		<%@include file="header.jsp"%>

		<br /> <br /> <a href="<%=request.getContextPath()%>/student">Student</a>
		<br /> <br /> <a href="<%=request.getContextPath()%>/tutor">Tutor</a>
		<br /> <br /> <a href="<%=request.getContextPath()%>/subject">Subject</a>


		<div class="push"></div>
		<!-- This pushes the footer off -->
	</div>

	<div class="footer">
		<p>Copyright © 2010</p>
		<p>
			<a href="http://ryanfait.com/" title="Las Vegas Web Design">Las-Vegas Web Design</a>
		</p>
	</div>

</body>
</html>