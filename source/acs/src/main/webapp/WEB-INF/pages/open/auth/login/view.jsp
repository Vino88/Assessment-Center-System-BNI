<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages" />

<c:if test="${not empty aunthentication_failed}">
	<div class="alert alert-danger">
		<fmt:message key="${aunthentication_failed}"></fmt:message>
	</div>
</c:if>
<c:if test="${not empty session_unauthorized_message}">
	<div class="alert alert-danger">
		<fmt:message key="${session_unauthorized_message}"></fmt:message>
	</div>
	<c:remove var="session_unauthorized_message"/>
</c:if>

<div>
	<div class="login_wrapper">
		<div class="animate form login_form">
			<section class="login_content">
				<form method="POST" action="">
					<h1>Login</h1>
					<div>
						<input type="text" class="form-control" placeholder="P012345" name="nik" id="nik" />
					</div>
					<div>
						<input type="password" class="form-control" placeholder="Password" name="password" id="password" />
					</div>
					<div>
						<button type="submit" id="submitLogin" class="btn btn-default submit">Login</button>
					</div>
					<div class="clearfix"></div>
					<div class="separator">
						<div class="clearfix"></div>
						<br />
						<div>
							<h1>
								<img src="${initParam.staticfiles}/static/images/bnv-logo.png" alt="BNV" />
							</h1>
							<p>&copy; 2017 All rights reserved.</p>
						</div>
					</div>
				</form>
			</section>
		</div>
	</div>
</div>
