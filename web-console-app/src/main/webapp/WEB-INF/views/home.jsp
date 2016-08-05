<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="include.jsp"%>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<!-- link rel="stylesheet" type="text/css" href="views/scripts/style.css"-->
<script type="text/css">

html,body {
	height: 100%;
}

.wrapper {
	min-height: 100%;
	height: auto !important;
	height: 100%;
	margin: 0 auto -155px;
	/* the bottom margin is the negative value of the footer's height */
}

.footer,.push {
	height: 155px; /* .push must be the same height as .footer */
}

.footer {
	position: relative;
	width: 700px;
	margin: 0 auto;
}

.footer a {
	color: #0060b9;
}

.footer p {
	padding: 95px 0 0;
	color: #fff;
	font: 1em helvetica, arial, sans-serif;
	text-align: center;
}

.footer p.copyright {
	padding: 1em 0 0;
	color: #0060b9;
	font-size: 0.8em;
}

</script>
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