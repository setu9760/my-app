<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<head>
<title>User maintenance</title>
<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#box {
	width: 350px;
	padding: 10px;
	margin: 10px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
</style>
<script type="text/javascript">
	function checkPasswords() {
		var password1 = document.forms["registerForm"]["password1"].value;
		var password2 = document.forms["registerForm"]["password2"].value;
		var ok = true;
		if (password1 != password2) {
			alert("Passwords Do not match");
			document.forms["registerForm"]["password1"].style.borderColor = "#E34234";
			document.forms["registerForm"]["password2"].style.borderColor = "#E34234";
			ok = false;
		} else {
			//alert("Passwords Match!!!");
		}
		return ok;
	}
</script>
</head>
<body onload='document.registerForm.firstName.focus();'>

	<h2>User Maintenance</h2>
	<c:url value="/logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h3>
				User : ${pageContext.request.userPrincipal.name} | <a href="javascript:formSubmit()"> Logout</a>
			</h3>
		</c:if>
	<c:if test="${error != null || msg != null}">
		<div id="box">
			<c:if test="${error != null}">
				<div class="error">
					Failed to Create new user.
					<p>
						Reason:
						<c:out value="${error}" />
					</p>

				</div>
			</c:if>
			<c:if test="${msg != null}">
				<div class="msg">${msg}</div>
			</c:if>
		</div>
	</c:if>
	<div id="box">
		<h3>Register User</h3>
		<c:url value="${pageContext.request.contextPath}/admin/register-user" var="registerUserUrl" />
		<form:form name="registerForm" action="${registerUserUrl}" method="post" modelAttribute="person" onsubmit="return checkPassword()">
			<fieldset>
				<table>
					<tbody>
						<tr>
							<td><label for="id">User id</label></td>
							<td><form:input path="id" type="text" id="id" name="id" /></td>
						</tr>
						<tr>
							<td><label for="firstName">First Name</label></td>
							<td><form:input path ="firstname" type="text" id="firstName" name="firstName" /></td>
						</tr>
						<tr>
							<td><label for="lastName">lastName</label></td>
							<td><form:input path ="lastname"  type="text" id="lastName" name="lastName" /></td>
						</tr>
						<tr>
							<td><label for="address">Address</label></td>
							<td><form:input path ="address"  type="text" id="address" name="address" /></td>
						</tr>
						<tr>
							<td><label for="adminUser">Admin user</label></td>
							<td><input type="checkbox" id="adminUser" name="adminUser" /></td>
						</tr>
						<tr>
							<td><input type="submit" value="Submit" /></td>
						</tr>
					</tbody>
				</table>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</fieldset>
		</form:form>
	</div>
	<div id="box">
	<h2>User list</h2>
		<form  method="post">
			<fieldset>
			<table style="width:100%;" border="1">
				<c:forEach items="${users}" var="user">
					<c:url value="/admin/delete-user/${user.id}" var="deleteUserUrl" />
					<c:url value="/admin/reset-signin-status/${user.id}" var="resetSignOnStatusUrl" />
					<tr>
						<td style="width:33%;"><label for="useId"><c:out value="${user.id}" /></label></td>
						<td style="width:33%;"><input type="submit" value="Delete" formaction="${deleteUserUrl}" <c:if test="${pageContext.request.userPrincipal.name == user.id}"><c:out value="disabled='disabled'"/></c:if>/></td>
						<td style="width:33%;"><input type="submit" value="Reset" formaction="${resetSignOnStatusUrl}"<c:if test="${pageContext.request.userPrincipal.name == user.id}"><c:out value="disabled='disabled'"/></c:if>/></td>
					</tr>
				</c:forEach>
			</table>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</fieldset>
		</form>
	</div>
</body>
</html>