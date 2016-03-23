<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${ title }</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<!--form:form modelAttribute="student" method="post"-->
	<form:form modelAttribute="student">
		<label for="Id"> ID: </label>
		<form:input path="Id" id="Id" />
		<form:errors path="Id" />
		<input type="submit" value="submit" />
	</form:form>
	<%@include file="footer.jsp"%>
</body>
</html>