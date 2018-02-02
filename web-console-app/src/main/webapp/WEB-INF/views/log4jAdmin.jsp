<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="org.apache.log4j.Level"%>
<%@ page import="org.apache.log4j.LogManager"%>
<%@ page import="org.apache.log4j.Logger"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Enumeration"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.Arrays"%>
<%
	/* This was originally suggested by Nelz on http://nelz.net/2008/04/08/log4j-runtime-configuration */
	long beginPageLoadTime = System.currentTimeMillis();
%>

<html>
<head>
<title>Log4J Administration</title>
<style type="text/css">
<!--
#content {
	margin: 0px;
	padding: 0px;
	text-align: center;
	background-color: #ccc;
	border: 1px solid #000;
	width: 100%;
}

body {
	position: relative;
	margin: 10px;
	padding: 0px;
	color: #333;
}

h1 {
	margin-top: 20px;
	font: 1.5em Verdana, Arial, Helvetica sans-serif;
}

h2 {
	margin-top: 10px;
	font: 0.75em Verdana, Arial, Helvetica sans-serif;
	text-align: left;
}

a, a:link, a:visited, a:active {
	color: red;
	text-decoration: none;
	text-transform: uppercase;
}

table {
	width: 100%;
	background-color: #000;
	border-spacing: 1; padding : 3px;
	border: 0px;
	padding: 3px;
}

th {
	font-size: 0.75em;
	background-color: #ccc;
	color: #000;
	padding-left: 5px;
	text-align: center;
	border: 1px solid #ccc;
	white-space: nowrap;
}

td {
	font-size: 0.75em;
	background-color: #fff;
	white-space: nowrap;
}

td.center {
	font-size: 0.75em;
	background-color: #fff;
	text-align: center;
	white-space: nowrap;
}

.filterForm {
	font-size: 0.9em;
	background-color: #000;
	color: #fff;
	padding-left: 5px;
	text-align: left;
	border: 1px solid #000;
	white-space: nowrap;
}

.filterText {
	font-size: 0.75em;
	background-color: #fff;
	color: #000;
	text-align: left;
	border: 1px solid #ccc;
	white-space: nowrap;
}

.filterButton {
	font-size: 0.75em;
	background-color: #000;
	color: #fff;
	padding-left: 5px;
	padding-right: 5px;
	text-align: center;
	border: 1px solid #ccc;
	width: 100px;
	white-space: nowrap;
}
-->
</style>
</head>

<div id="content">
	<h1>Log4J Administration</h1>

	<table>
		<thead>
			<tr>
				<th width="25%">Logger</th>
				<th width="25%">Parent Logger</th>
				<th width="15%">Effective Level</th>
				<th width="35%">Change Log Level To</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${loggerList}" var="logger">
				<tr>
					<td><c:out value="${logger.name}" default="null" /></td>
					<td><c:out value="${logger.parent}" default="null" /></td>
					<td><c:out value="${logger.level}" default="null" /></td>
					<td align="center">
						<c:url value="/log4jAdmin?loggerName=${logger.name}&changeLevelTo=" var="url"/>
						<a href="${url}DEBUG" >[DEBUG]</a> | 
						<a href="${url}INFO" >[INFO]</a> | 
						<a href="${url}WARN" >[WARN]</a> | 
						<a href="${url}ERROR" >[ERROR]</a> | 
						<a href="${url}FATAL" >[FATAL]</a> | 
						<a href="${url}OFF" >[OFF]</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<h2>
		Revision: 1.0<br /> Page Load Time (Millis):
		<%=(System.currentTimeMillis() - beginPageLoadTime)%>
	</h2>
</div>
</body>
</html>