<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages"/>

<div class="main_container" role="main" >
	<div class="right_col">
      	<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
           		<div class="x_panel">
	           		<div class="x_title">
	           			<h2>Simulasi Pemecahan Masalah <span id="timer" style="display:none"></span>
							</h2>
							
	                   	<div class="clearfix"></div>
	           		</div>
	           		<div class="x_content">
                      	<ul class="list-unstyled">
			           		<c:forEach items="${list}" var="inbox" varStatus="count">
				           		<li><i class="fa fa-inbox"></i> 
				               		<a href="${pageContext.request.contextPath}/controller/pages/view/inbasket/detail/${inbox.id}">
					                	${inbox.title }</a> 
					                	
					                	<c:forEach items="${map}" var="m" varStatus="count">
					                		<c:if test="${m.key == inbox.id && m.value == true }">
					                		<i class="fa fa-check"></i>
					                	</c:if>
					                	</c:forEach>
					                	
					          	</li>
							</c:forEach>
						</ul>
					</div>
              	</div>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
</div>
