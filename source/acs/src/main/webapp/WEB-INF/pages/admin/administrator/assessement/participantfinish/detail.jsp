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
			<!-- /.col-lg-32 -->
		</div>
            <a class="btn btn-app" href="javascript:window.history.back();" style="margin: 0px 0px 10px 0px;"><i class="fa fa-arrow-circle-left"></i>Back</a>
            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Assign To Assessor</h2>
                    
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                  
                    <form method="POST" id="demo-form2" data-parsley-validate="" class="form-horizontal form-label-left" action="${ pageContext.request.contextPath}/controller/pages/admin/administrator/assessement/participantfinish/assign">
                      <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">ID</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${participant.id}" name="participantId" readonly="readonly" class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
                      
                     <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Nama Lengkap</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${participant.fullName}" name="participantName" readonly="readonly" class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
	                  
	                  <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">E-mail</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${participant.email}" name="participantEmail" readonly="readonly"  class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
					 
                      <div class="ln_solid"></div>
                      <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">PK Assessor <span class="required red">*</span></label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" name="capAssessorUserName" id="capAssessorUserName" readonly="readonly" required="required" class="form-control col-md-7 col-xs-12" value="${participantLogPost.capAssessor.fullname }">
							<input type="hidden" name="capAssessorUserId" id="capAssessorUserId" required="required" class="form-control col-md-7 col-xs-12" value="${participantLogPost.capAssessor.id }">
							
							<c:choose>
								<c:when test="${empty participantLogPost.assessCapFinishTime}">
									<input type="button" onclick="browseUserAdmin('capAssessorUserName','capAssessorUserId')" class="btn btn-success" value="Browse Assessor" />
								</c:when>
								<c:otherwise>
									<input type="text" value='<fmt:formatDate value="${participantLogPost.assessCapFinishTime}"  pattern="dd-MM-yyyy HH:mm:ss" />' readonly="readonly"  class="form-control col-md-7 col-xs-12">
								</c:otherwise>
							</c:choose>
							
							
                        </div>
					 </div>
					 
					 <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">SPM Assessor <span class="required red">*</span></label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" name="simulationAssessorUserName" id="simulationAssessorUserName" readonly="readonly" required="required" class="form-control col-md-7 col-xs-12" value="${participantLogPost.simulationAssessor.fullname }">
							<input type="hidden" name="simulationAssessorUserId" id="simulationAssessorUserId" required="required" class="form-control col-md-7 col-xs-12" value="${participantLogPost.simulationAssessor.id }">
							
							
							<c:choose>
								<c:when test="${empty participantLogPost.assessSimulationFinishTime}">
									<input type="button" onclick="browseUserAdmin('simulationAssessorUserName','simulationAssessorUserId')" class="btn btn-success" value="Browse Assessor" />
								</c:when>
								<c:otherwise>
									<input type="text" value='<fmt:formatDate value="${participantLogPost.assessSimulationFinishTime}"  pattern="dd-MM-yyyy HH:mm:ss" />' readonly="readonly"  class="form-control col-md-7 col-xs-12">
								</c:otherwise>
							</c:choose>
                        </div>
					 </div>
					 
					 <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">SRB Assessor <span class="required red">*</span></label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" name="analysysAssessorUserName" id="analysysAssessorUserName" readonly="readonly" required="required" class="form-control col-md-7 col-xs-12" value="${participantLogPost.analysysAssessor.fullname }">
							<input type="hidden" name="analysysAssessorUserId" id="analysysAssessorUserId" required="required" class="form-control col-md-7 col-xs-12" value="${participantLogPost.analysysAssessor.id }">
							
							
							<c:choose>
								<c:when test="${empty participantLogPost.assessAnalysisFinishTime}">
									<input type="button" onclick="browseUserAdmin('analysysAssessorUserName','analysysAssessorUserId')" class="btn btn-success" value="Browse Assessor" />
								</c:when>
								<c:otherwise>
									<input type="text" value='<fmt:formatDate value="${participantLogPost.assessAnalysisFinishTime}"  pattern="dd-MM-yyyy HH:mm:ss" />' readonly="readonly"  class="form-control col-md-7 col-xs-12">
								</c:otherwise>
							</c:choose>
                        </div>
					 </div>
					 
					 <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">Integration Grid Assessor (same with PK assessor) <span class="required red">*</span></label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" name="integrationGridAssessorUserName" id="integrationGridAssessorUserName" readonly="readonly" required="required" class="form-control col-md-7 col-xs-12" value="${participantLogPost.integrationGridAssessor.fullname }">
							<input type="hidden" name="integrationGridAssessorUserId" id="integrationGridAssessorUserId" required="required" class="form-control col-md-7 col-xs-12" value="${participantLogPost.integrationGridAssessor.id }">
							
							<c:choose>
								<c:when test="${empty participantLogPost.assessIntegrationGridTime}">
									
								</c:when>
								<c:otherwise>
									<input type="text" value='<fmt:formatDate value="${participantLogPost.assessIntegrationGridTime}"  pattern="dd-MM-yyyy HH:mm:ss" />' readonly="readonly"  class="form-control col-md-7 col-xs-12">
								</c:otherwise>
							</c:choose>
                        </div>
					 </div>
					 
					 
					 <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">Reviewer</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" name="reviewerAssessorUserName" id="reviewerAssessorUserName" readonly="readonly" required="required" class="form-control col-md-7 col-xs-12" value="${participantLogPost.reviewer.fullname }">
							<input type="hidden" name="reviewerAssessorUserId" id="reviewerAssessorUserId" required="required" class="form-control col-md-7 col-xs-12" value="${participantLogPost.reviewer.id }">
							
							
							<c:choose>
								<c:when test="${empty participantLogPost.assessReviewerFinishTime}">
									<input type="button" onclick="browseUserAdmin('reviewerAssessorUserName','reviewerAssessorUserId')" class="btn btn-success" value="Browse Reviewer" />
								</c:when>
								<c:otherwise>
									<input type="text" value='<fmt:formatDate value="${participantLogPost.assessReviewerFinishTime}"  pattern="dd-MM-yyyy HH:mm:ss" />' readonly="readonly"  class="form-control col-md-7 col-xs-12">
								</c:otherwise>
							</c:choose>
                        </div>
					 </div>
					 
                      <div class="ln_solid"></div>
					 
                      <div class="form-group">
                        <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                          <a href="javascript:window.history.back();" class="btn btn-primary">Back</a>
                          <button type="submit" class="btn btn-success">Submit</button>
                        </div>
                      </div>

                    </form>
                  </div>
                </div>
              </div>
            </div>
            
            </div>
</div>
