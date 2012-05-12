<%@ include file="./views/includes/header.jsp"%>

<div class="content">
<form:form action="register.html" commandName="register">

	<table align="center">
		<tr>
			<td><form:select path="group">
					<form:options items="${groupList}" />
				</form:select></td>
		</tr>
		<tr>
			<td><fmt:message key="global.userName" />*:</td>
			<td><form:input path="userName" /> <form:errors path="userName" /></td>
		</tr>

		<tr>
			<td><fmt:message key="global.displayName" />*:</td>
			<td><form:input path="displayName" /> <form:errors
					path="displayName" /></td>
		</tr>
		<tr>
			<td><fmt:message key="global.password" />*:</td>
			<td><form:password path="password" /> <form:errors
					path="password" /></td>
		</tr>

		<tr>
			<td><fmt:message key="global.confirmPassword" />*:</td>
			<td><form:password path="confirmPassword" /> <form:errors
					path="confirmPassword" /></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Registrera" /></td>
		</tr>

	</table>

</form:form>

</div>
<%@ include file="./views/includes/footer.jsp"%>