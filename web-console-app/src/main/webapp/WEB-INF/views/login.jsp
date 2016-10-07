<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>Login Page</title>
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

#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
</style>
</head>
<body onload='document.loginForm.username.focus();'>

	<h1>Spring Security Login Form (Database Authentication)</h1>

	<div id="login-box">

		<h3>Login with Username and Password</h3>
		<c:url value="/login" var="loginProcessingUrl" />
		<form name='loginForm' action="${loginProcessingUrl}" method="post">
			<fieldset>
				<legend>Please Login</legend>
				<!-- use param.error assuming FormLoginConfigurer#failureUrl contains the query parameter error -->
				<c:if test="${param.error != null}">
					<div class="error">
						Failed to login.
						<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
                  Reason: <c:out
								value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
						</c:if>
					</div>
				</c:if>
				<!-- the configured LogoutConfigurer#logoutSuccessUrl is /login?logout and contains the query param logout -->
				<c:if test="${param.logout != null}">
					<div class="msg">You have been logged out.</div>
				</c:if>
				<p>
					<label for="username">Username</label> <input type="text" id="username" name="username" />
				</p>
				<p>
					<label for="password">Password</label> <input type="password" id="password" name="password" />
				</p>
				
				<div>
					<button type="submit" class="btn">Log in</button>
				</div>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</fieldset>
		</form>



	</div>

</body>
</html>