<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages" />

<div class="main_container" role="main" >
	<div class="right_col">
      	<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
           		<div class="x_panel">
	           		<div class="x_title">
						<h2>Hasil Perekaman</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<c:set var="now" value="<%=new java.util.Date()%>" />
	           			<video autoplay src="${pageContext.request.contextPath}/controller/view/visionspeech/preview/${session_auth_participant.id}?now=${now.time}"></video>
					</div>
               	</div>
               	
               	<div class="x_panel">
	            	<div class="x_title"></div>
	              		<div class="x_content">
	              			<div class="form-group">
	                        	<div class="col-md-6 col-sm-6 col-xs-12">
	                        		<a class="btn btn-primary" href="${pageContext.request.contextPath}/controller/pages/view/visionspeech/detail/visionspeechvs/${sessionScope.session_vision_speech_first.id}">Kembali</a>
		                        	<a class="btn btn-success"  href="${pageContext.request.contextPath}/controller/pages/view/visionspeech/beforefinish">Lanjut</a>
	                        	</div>
	                      	</div>
	              		</div>
	              	</div>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
</div>