<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages"/>

<c:if test="${not empty aunthentication_failed}" >
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
            <form class="form-vertical" action="${pageContext.request.contextPath}/controller/open/auth/admin/forgotpassword" method="POST">
              <h1>Forgot Password</h1>
              
              
		         <c:if test="${not empty submit_failed}" >
					<div class="alert alert-danger">
						<fmt:message key="${submit_failed}"></fmt:message>
		                </div>	
				</c:if>
				<c:if test="${not empty submit_success}" >
					<div class="alert alert-success">
						<fmt:message key="${submit_success}"></fmt:message>
		                </div>	
				</c:if>
				<div class="control-group">
					<div class="controls row-fluid">
						<input class="form-control" type="text" name="username" id="username" placeholder="Username">
					</div>
				</div>
							
							
							
              
              <div>
                <button type="submit" id="submitLogin" class="btn btn-default submit">Send</button>
              </div>
				
              <div class="clearfix"></div>
				<a href="${pageContext.request.contextPath}/controller/pages/open/auth/admin/login">Login</a>
              <div class="separator">
                

                <div class="clearfix"></div>
                <br />

                <div>
                  <h1><img src="${initParam.staticfiles}/static/images/bnv-logo.png" alt="BNV"/></h1>
                  <p> &copy; 2017 All rights reserved.</p>
                </div>
              </div>
            </form>
          </section>
        </div>

        
      </div>
    </div>
