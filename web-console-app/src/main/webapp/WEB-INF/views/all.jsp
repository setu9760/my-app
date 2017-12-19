<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@include file="include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${ title }</title>
</head>
<body>
	<%@include file="header.jsp"%>

	<c:if test="${!empty students }">
		<c:forEach items="${students}" var="student">
			<li>${student}</li>
			<br />
		</c:forEach>
	</c:if>
	<c:if test="${!empty subjects }">
		<c:forEach items="${subjects}" var="subject">
			<li>${subject}</li>
			<br />
		</c:forEach>
	</c:if>
	<c:if test="${ !empty tutors }">
		<c:forEach items="${tutors}" var="tutor">
			<li>${tutor}</li>
			<br />
		</c:forEach>
	</c:if>

	<%@include file="footer.jsp"%>
</body>
</html>