<%@ include file="include.jsp"%>
<%!int pageCount = 0;

	void addCount() {
		pageCount++;
	}%>
<%
	addCount();
%>


<p>The time on the server is ${serverTime}.</p>
<p>
	You are visitor number:
	<%=pageCount%></p>
<br />
<br />

<table>
	<thead>
		<tr>
			<td><a href="<%=request.getContextPath()%>/home">Home</a></td>
			<td></td>
			<td><a href="<%=request.getContextPath()%>/student">Student</a></td>
			<td></td>
			<td><a href="<%=request.getContextPath()%>/tutor">Tutor</a></td>
			<td></td>
			<td><a href="<%=request.getContextPath()%>/subject">Subject</a></td>
			<td></td>
		</tr>
	</thead>
</table>

<br />
<br />
