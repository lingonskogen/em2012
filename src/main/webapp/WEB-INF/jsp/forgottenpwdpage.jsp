<%@ include file="./views/includes/header.jsp"%>

<section>

	<form:form class="form-horizontal" action="forgotten-password.html"
		commandName="form">
		<fieldset>
			<legend>
				<fmt:message key="pwdPage.newPwd" />
			</legend>
			
			<c:if test="${newPwdSent}">
				<div class="control-group">
					<div class="controls" style="margin-left: 315px">
						${newPwdSent}</div>
				</div>
			</c:if>

			<div class="control-group">
				<label class="control-label"><fmt:message
						key="pwdPage.email" />:</label>
				<div class="controls">
					<form:input path="userName" />
					<form:errors path="userName" />
				</div>
			</div>
			<div class="control-group">
				<div class="controls" style="margin-left: 300px">
					<button class="btn btn-primary" type="submit">Skicka</button>
				</div>
			</div>
		</fieldset>
	</form:form>

</section>
<%@ include file="./views/includes/footer.jsp"%>