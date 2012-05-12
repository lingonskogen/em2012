<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Bootstrap, from Twitter</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href="/css/bootstrap.css" rel="stylesheet">
<style type="text/css">
body {
	padding-top: 320px;
	padding-bottom: 40px;
}
</style>

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- Le fav and touch icons -->
<link rel="shortcut icon" href="/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="/ico/apple-touch-icon-57-precomposed.png">
</head>

<body>

	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> </a> <a
					class="brand" href="#"><img src="/img/euro2012.png"/></a>
            <!-- 
				<div class="nav-collapse">
					<ul class="nav">
						<li class="active"><a href="#">Home</a></li>
						<li><a href="#about">About</a></li>
						<li><a href="#contact">Contact</a></li>
					</ul>
				</div>
             -->
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>

	<div class="container">

		<div class="row">
			<div class="span4">
				<p>yada yada yada em2012 yada yada</p>
			</div>
			<div class="span8">
				<form:form cssClass="form-horizontal" action="register.html"
					commandName="register">
					<fieldset>
						<div class="control-group">
							<label for="group" class="control-label"><fmt:message key="global.group" />*:</label>
							<div class="controls">
								<form:select path="group" id="selectError">
									<form:options items="${groupList}" />
								</form:select>
								<span class="help-inline">Woohoo!</span>
							</div>
						</div>
						<div class="control-group">
							<label for="userName" class="control-label"><fmt:message key="global.userName" />*:</label>
							<div class="controls">
								<form:input path="userName" id="userName" />
								<span class="help-inline"><form:errors path="userName" />
								</span>
							</div>
						</div>
						<div class="control-group">
							<label for="displayName" class="control-label"><fmt:message key="global.displayName" />*:</label>
							<div class="controls">
								<form:input path="displayName" id="displayName" />
								<span class="help-inline"><form:errors path="displayName" />
								</span>
							</div>
						</div>
						<div class="control-group">
							<label for="password" class="control-label"><fmt:message key="global.password" />*:</label>
							<div class="controls">
								<form:password path="password" id="password" />
								<span class="help-inline"><form:errors path="password" />
								</span>
							</div>
						</div>
						<div class="control-group">
							<label for="confirmPassword" class="control-label"><fmt:message key="global.comfirmePassword" />*:</label>
							<div class="controls">
								<form:password path="confirmPassword" id="confirmPassword" />
								<span class="help-inline"><form:errors path="confirmPassword" />
								</span>
							</div>
						</div>
						<div class="form-actions">
							<button class="btn btn-primary" type="submit">Registrera</button>
						</div>
					</fieldset>
				</form:form>
			</div>
		</div>
		<hr>

		<footer>
			<p>&copy; Company 2012</p>
		</footer>

	</div>
	<!-- /container -->

	<!-- Le javascript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="/js/jquery.js"></script>
	<script src="/js/bootstrap-transition.js"></script>
	<script src="/js/bootstrap-alert.js"></script>
	<script src="/js/bootstrap-modal.js"></script>
	<script src="/js/bootstrap-dropdown.js"></script>
	<script src="/js/bootstrap-scrollspy.js"></script>
	<script src="/js/bootstrap-tab.js"></script>
	<script src="/js/bootstrap-tooltip.js"></script>
	<script src="/js/bootstrap-popover.js"></script>
	<script src="/js/bootstrap-button.js"></script>
	<script src="/js/bootstrap-collapse.js"></script>
	<script src="/js/bootstrap-carousel.js"></script>
	<script src="/js/bootstrap-typeahead.js"></script>

</body>
</html>
