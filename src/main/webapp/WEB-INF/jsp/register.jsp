<%@ include file="./views/includes/header.jsp"%>

<div class="row">
	<div class="span8">
		<section>
			<c:choose>
				<c:when test="${registreradOk}">
				
				<div class="registreredMessageHeader">Välkommen!</div>
				<div class="registreredMessage">Du är nu registrerad och kan logga in!</div>
				</c:when>

				<c:otherwise>
					<legend>Registrera dig</legend>

					<form:form action="register.html" class="form-horizontal"
						commandName="register">
						<fieldset>

							<div class="control-group">
								<label class="control-label"><fmt:message
										key="global.group" />*:</label>
								<div class="controls">
									<form:select path="group" class="select">
										<form:options items="${groupList}" />
									</form:select>
								</div>
							</div>

							<div class="control-group">
								<label class="control-label"><fmt:message
										key="register.userName" />*:</label>
								<div class="controls">
									<form:input path="userName" />
									<form:errors path="userName" />
								</div>
							</div>

							<div class="control-group">
								<label class="control-label"><fmt:message
										key="global.displayName" />*:</label>
								<div class="controls">
									<form:input path="displayName" />
									<form:errors path="displayName" />
								</div>
							</div>

							<div class="control-group">
								<label class="control-label"><fmt:message
										key="global.password" />*:</label>
								<div class="controls">
									<form:password path="password" />
									<form:errors path="password" />
								</div>
							</div>

							<div class="control-group">
								<label class="control-label"><fmt:message
										key="global.confirmPassword" />*:</label>
								<div class="controls">
									<form:password path="confirmPassword" />
									<form:errors path="confirmPassword" />
								</div>
							</div>

							<div class="control-group">
								<div class="controls" style="margin-left: 300px">
									<button class="btn btn-primary" type="submit">Registrera</button>
								</div>
							</div>
					</form:form>

				</c:otherwise>
			</c:choose>
		</section>
	</div>
	<div class="span4">
		<section>
			<img alt="kram" src="/images/kram.jpg" />
			<div style="padding-bottom: 20px;"></div>
			<img alt="elamnder" src="/images/elmander.jpg" />

		</section>
	</div>
</div>


<%@ include file="./views/includes/footer.jsp"%>