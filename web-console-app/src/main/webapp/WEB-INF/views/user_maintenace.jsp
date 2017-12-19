<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	width: 450px;
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
		<c:url value="/admin/register-user" var="registerUserUrl" />
		<form name='registerForm' action="${registerUserUrl}" method="post"	onsubmit="return checkPasswords()">
			<fieldset>
				<table>
					<tbody>
						<tr>
							<td><label for="firstName">First Name</label></td>
							<td><input type="text" id="firstName" name="firstName" /></td>
						</tr>
						<tr>
							<td><label for="lastName">lastName</label></td>
							<td><input type="text" id="lastName" name="lastName" /></td>
						</tr>
						<tr>
							<td><label for="username">Username</label></td>
							<td><input type="text" id="username" name="username" /></td>
						</tr>
						<tr>
							<td><label for="password1">Password</label></td>
							<td><input type="password" id="password1" name="password1" /></td>
						</tr>
						<tr>
							<td><label for="password2">Re-type Password</label></td>
							<td><input type="password" id="password2" name="password2" /></td>
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
		</form>
	</div>
	<div id="box">
		<h3>Reset user sign-on status</h3>
		<c:url value="/admin/resetSignOnStatus" var="resetSignOnStatusUrl" />
		<form name="resetSignOnStatus" action="${resetSignOnStatusUrl}" method="post">
			<fieldset>
				<table>
					<tbody>
						<tr>
							<td><label for="useId">User id</label></td>
							<td><input type="text" id="userId" name="userId" /></td>
						</tr>
						<tr>
							<td><input type="submit" value="Submit" /></td>
						</tr>
					</tbody>
				</table>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</fieldset>
		</form>
	</div>
	<div id="box">
	<h2>User id list</h2>
		<c:forEach items="${users}" var="user">
			<p><c:out value="${user.id}" /></p>
		</c:forEach>
	</div>
</body>
</html>