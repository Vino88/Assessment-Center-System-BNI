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
					<h1>Sesi Pertama Selesai</h1>
					<div>
						<p>
							Selamat, sesi pertama telah selesai.
						</p>
						
						<p>
							Pastikan Anda masuk kembali ke dalam sistem jika sesi kedua telah dimulai.
						</p>
						
						
						<p>
							Terima Kasih
						</p>
					</div>
					<div>
					<a style="color:#26B99A" href="${pageContext.request.contextPath}">Login</a> 
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
