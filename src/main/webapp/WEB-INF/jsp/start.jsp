<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ include file="./views/includes/header.jsp"%>

<div class="content">
<c:if test="${message!=null}">
	<div class="message">${message}</div>
</c:if>
<form name='f' action='<c:url value='j_spring_security_check' />' method='POST'>
 <table>
    <tr><td>User:</td><td><input type='text' name='j_username' value=''></td></tr>
    <tr><td>Password:</td><td><input type='password' name='j_password'/></td></tr>
    <tr><td colspan='2'><input name="submit" type="submit" value="Login"/></td></tr>
  </table>
</form>
<div>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
</div>
<%@ include file="./views/includes/footer.jsp"%>