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

	<c:if test="${!empty student }">
		<form:form modelAttribute="student">
			<label for="id"> ID: </label>
			<form:input path="id" id="id" />
			<form:errors path="id" />
			<br />
			<br />
			<label for="name">Name</label>
			<form:input path="name" id="name" />
			<form:errors path="name" />
			<br />
			<br />
			<label for="age">Age</label>
			<form:input path="age" id="age" />
			<form:errors path="age" />
			<br />
			<br />
			<label for="address">Address</label>
			<form:input path="address" id="address" />
			<form:errors path="address" />
			<br />
			<br />
			<input type="submit" value="submit" />
		</form:form>
	</c:if>
	<c:if test="${!empty tutor }">
		<form:form modelAttribute="tutor">
			<label for="id"> ID: </label>
			<form:input path="id" id="id" />
			<form:errors path="id" />
			<br />
			<br />
			<label for="name">Name</label>
			<form:input path="name" id="name" />
			<form:errors path="name" />
			<br />
			<br />
			<label for="address">Address</label>
			<form:input path="address" id="address" />
			<form:errors path="address" />
			<br />
			<br />
			<input type="submit" value="submit" />
		</form:form>
	</c:if>
	<c:if test="${!empty subject }">
		<form:form modelAttribute="subject">
			<label for="id"> ID: </label>
			<form:input path="id" id="id" />
			<form:errors path="id" />
			<br />
			<br />
			<label for="name">Name</label>
			<form:input path="name" id="name" />
			<form:errors path="name" />
			<br />
			<br />
			<input type="submit" value="submit" />
		</form:form>
	</c:if>


	<%@include file="footer.jsp"%>
</body>
</html>