<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages"/>

<div class="main_container" role="main" >
	<div class="right_col">
    	<div class="row">
			<div class="col-lg-32">
				<c:if test="${ not empty errorMessage}" >
					<div class="alert alert-danger">
	                	<fmt:message key="${ errorMessage}"></fmt:message>     
	                 </div>	
				</c:if>
				
				<c:if test="${ not empty plainErrorMessage}" >
					<div class="alert alert-danger">
	                	${plainErrorMessage}     
	                 </div>	
				</c:if>
		
	            <c:if test="${ not empty plainSuccessMessage}" >
	            	<div class="alert alert-success">
	                	${plainSuccessMessage}     
	                 </div>
	            </c:if>
	            
	             <c:if test="${ not empty successMessage}" >
	            	<div class="alert alert-success">
	            		<fmt:message key="${successMessage}"></fmt:message>  
	                 </div>
	            </c:if>
			</div>
		</div>
        <a class="btn btn-app" href="${pageContext.request.contextPath}/controller/pages/admin/assessor/home" style="margin: 0px 0px 10px 0px;"><i class="fa fa-arrow-circle-left"></i>Back</a>
        <div class="row">
        	<div class="col-md-12 col-sm-12 col-xs-12">
            	<div class="x_panel">
                	<div class="x_title">
	                    <h2>Evaluation Page</h2>
	                    <div class="clearfix"></div>
                	</div>
               		<div class="x_content">
	                    <br>
	                    <form method="POST" id="demo-form2" data-parsley-validate="" class="form-horizontal form-label-left">
	                     	<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Participant ID</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input type="text" value="${participant.id}" readonly="readonly" class="form-control col-md-7 col-xs-12">
		                        </div>
							 </div>
							
						 	<div class="ln_solid"></div>
	                       	<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Assess Start Time</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input type="text" value='<fmt:formatDate value="${participantLogPost.assessStartTime}" pattern="dd-MM-yyyy HH:mm:ss"/>' readonly="readonly"  class="form-control col-md-7 col-xs-12">
		                        </div>
						 	</div>
						 	
	                        <div class="ln_solid"></div>
	                        <div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">PK Assigned To</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<c:choose>
										<c:when test="${participantLogPost.capAssessor.id == sessionScope.session_auth_user_admin.id && empty participantLogPost.assessCapFinishTime}">
											<a href="${pageContext.request.contextPath}/controller/pages/admin/assessor/assess/cap/list/${participant.id}" class="btn btn-warning form-control col-md-7 col-xs-12">${participantLogPost.capAssessor.fullname}</a>
										</c:when>
										<c:otherwise>
											<input type="text" value="${participantLogPost.capAssessor.fullname}" readonly="readonly"  class="form-control col-md-7 col-xs-12">
										</c:otherwise>
									</c:choose>
		                        </div>
	                        </div>
	                        
	                        <div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">PK Assess Finish Time</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input type="text" value='<fmt:formatDate value="${participantLogPost.assessCapFinishTime}"  pattern="dd-MM-yyyy HH:mm:ss" />' readonly="readonly"  class="form-control col-md-7 col-xs-12">
		                        </div>
							</div>
							
	                        <div class="ln_solid"></div>
	                        <div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">SPM Assigned To</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<c:choose>
										<c:when test="${participantLogPost.simulationAssessor.id == sessionScope.session_auth_user_admin.id && empty participantLogPost.assessSimulationFinishTime}">
											<a href="${pageContext.request.contextPath}/controller/pages/admin/assessor/assess/simulation/view/${participant.id}" class="btn btn-warning form-control col-md-7 col-xs-12">${participantLogPost.simulationAssessor.fullname}</a>
										</c:when>
										<c:otherwise>
											<input type="text" value="${participantLogPost.simulationAssessor.fullname}" readonly="readonly"  class="form-control col-md-7 col-xs-12">
										</c:otherwise>
									</c:choose>
		                        </div>
	                        </div>
	                        
	                        <div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">SPM Assess Finish Time</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input type="text" value='<fmt:formatDate value="${participantLogPost.assessSimulationFinishTime}"  pattern="dd-MM-yyyy HH:mm:ss" />' readonly="readonly"  class="form-control col-md-7 col-xs-12">
		                        </div>
							</div>
							
	                        <div class="ln_solid"></div>
		                   	<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">SRB Assigned To</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<c:choose>
										<c:when test="${participantLogPost.analysysAssessor.id == sessionScope.session_auth_user_admin.id && empty participantLogPost.assessAnalysisFinishTime}">
											<a href="${pageContext.request.contextPath}/controller/pages/admin/assessor/assess/analysis/view/${participant.id}" class="btn btn-warning form-control col-md-7 col-xs-12">${participantLogPost.analysysAssessor.fullname}</a>
										</c:when>
										<c:otherwise>
											<input type="text" value="${participantLogPost.analysysAssessor.fullname}" readonly="readonly"  class="form-control col-md-7 col-xs-12">
										</c:otherwise>
									</c:choose>
		                        </div>
		                  	</div>
	                        
	                        <div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">SRB Assess Finish Time</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input type="text" value='<fmt:formatDate value="${participantLogPost.assessAnalysisFinishTime}"  pattern="dd-MM-yyyy HH:mm:ss" />' readonly="readonly"  class="form-control col-md-7 col-xs-12">
		                        </div>
							</div>
	                        
	                        <div class="ln_solid"></div>
	                        <div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Integration Grid Assigned To</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<c:choose>
										<c:when test="${participantLogPost.integrationGridAssessor.id == sessionScope.session_auth_user_admin.id && empty participantLogPost.assessIntegrationGridTime && not empty participantLogPost.assessCapFinishTime && not empty participantLogPost.assessSimulationFinishTime  && not empty participantLogPost.assessAnalysisFinishTime }">
											<a href="${pageContext.request.contextPath}/controller/pages/admin/assessor/assess/integrationgrid/view/${participant.id}" class="btn btn-warning form-control col-md-7 col-xs-12">${participantLogPost.integrationGridAssessor.fullname}</a>
										</c:when>
										<c:when test="${participantLogPost.integrationGridAssessor.id == sessionScope.session_auth_user_admin.id && empty participantLogPost.assessIntegrationGridTime}">
											<input type="button" id="btnIgWaitingOthers" class="btn btn-danger form-control col-md-7 col-xs-12" value="${participantLogPost.integrationGridAssessor.fullname}">
										</c:when>
										<c:otherwise>
											<input type="text" value="${participantLogPost.integrationGridAssessor.fullname}" readonly="readonly"  class="form-control col-md-7 col-xs-12">
										</c:otherwise>
									</c:choose>
		                        </div>
	                        </div>
	                        
	                        <div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Integration Grid Finish Time</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input type="text" value='<fmt:formatDate value="${participantLogPost.assessIntegrationGridTime}"  pattern="dd-MM-yyyy HH:mm:ss" />' readonly="readonly"  class="form-control col-md-7 col-xs-12">
		                        </div>
							</div>
	                        
	                        <div class="ln_solid"></div>
	                        <div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Reviewer Assigned To</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<c:choose>
										<c:when test="${participantLogPost.reviewer.id == sessionScope.session_auth_user_admin.id && empty participantLogPost.assessReviewerFinishTime  && not empty participantLogPost.assessIntegrationGridTime }">
											<a href="${pageContext.request.contextPath}/controller/pages/admin/assessor/assess/reviewer/view/${participant.id}" class="btn btn-warning form-control col-md-7 col-xs-12">${participantLogPost.reviewer.fullname}</a>
										</c:when>
										<c:when test="${participantLogPost.reviewer.id == sessionScope.session_auth_user_admin.id && empty participantLogPost.assessReviewerFinishTime}">
											<input type="button" id="btnReviewerWaitingOthers" class="btn btn-danger form-control col-md-7 col-xs-12" value="${participantLogPost.reviewer.fullname}">
										</c:when>
										<c:otherwise>
											<input type="text" value="${participantLogPost.reviewer.fullname}" readonly="readonly"  class="form-control col-md-7 col-xs-12">
										</c:otherwise>
									</c:choose>
		                        </div>
	                        </div>
	                        
	                        <div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Assess Finish Time</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input type="text" value='<fmt:formatDate value="${participantLogPost.assessReviewerFinishTime}"  pattern="dd-MM-yyyy HH:mm:ss" />' readonly="readonly"  class="form-control col-md-7 col-xs-12">
		                        </div>
							</div>
	                        <div class="ln_solid"></div>
	                        
	                      	<div class="form-group">
	                        	<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
	                          		<a href="${pageContext.request.contextPath}/controller/pages/admin/assessor/home" class="btn btn-primary">Back</a>
	                        	</div>
	                      	</div>
	                    </form>
	              	</div>
				</div>
			</div>
		</div>            
	</div>
</div>
