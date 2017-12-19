<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@include file="include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${ title }</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<br />
	<a href="<%=request.getContextPath()%>/${ title }/insert">Insert ${ title }</a>
	<br />
	<br />
	<a href="<%=request.getContextPath()%>/${ title }/search">Find ${ title }</a>
	<br />
	<br />
	<a href="<%=request.getContextPath()%>/${ title }/all">All ${ title }</a>
	<br />
	<br />
	<%@include file="footer.jsp"%>
</body>
</html>