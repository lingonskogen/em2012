<%@ include file="../views/includes/header.jsp"%>

<div class="row">
	<div class="span12">
		<section>
			<legend>Update Tournament Page</legend>

			<c:if test="${errorMessage}">
				<div class="errorMessage">${errorMessage}</div>
			</c:if>
			<c:if test="${successMessage}">
				<div class="successMessage">${successMessage}</div>
			</c:if>


			<!-- List tournament data -->
			<form:form action="/admin/tournamentupdatepage.html" class="form-horizontal"
				commandName="form">
				<fieldset>

					<table class="table table-striped">
						<tr>
							<th>Tournament id</th>
							<th>Tournament name</th>
							<th>Tournament winner</th>
							<th colspan="2" />
						</tr>
						<tr>
							<td>${form.id}
							<input type="hidden" name="id" value="${form.id}" />
							</td>
							<td>${form.name}
							<input type="hidden" name="name" value="${form.name}" />
							</td>
							<td><select id="winnerTeamId" name="winnerTeamId">
									<option value="">Välj</option>
									<c:forEach items="${availableTeams}" var="team">
										<option value="${team.value.id}"
											<c:if test="${form.winnerTeamId == team.value.id}">selected="selected"</c:if>>${team.value.name}</option>
									</c:forEach>
							</select></td>
							<td></td>
							<td><button class="btn btn-primary" type="submit">Uppdatera</button></td>
						</tr>
					</table>

				</fieldset>
			</form:form>
		</section>
	</div>
</div>
<%@ include file="../views/includes/footer.jsp"%>