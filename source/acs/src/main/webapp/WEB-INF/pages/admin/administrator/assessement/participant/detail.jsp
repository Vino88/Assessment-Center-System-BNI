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
                    <h2>Detail Participant</h2>
                    
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <br>
                    
                    <form method="POST" id="demo-form2" data-parsley-validate="" class="form-horizontal form-label-left">
                      <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">ID</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${participant.id}" readonly="readonly" class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
                      
                     <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Nama Lengkap</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${participant.fullName}" readonly="readonly" class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
	                  <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">NPP</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${participant.nik}" readonly="readonly"  class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
	                  <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">E-Mail</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${participant.email}" readonly="readonly"  class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
					 
                      <div class="ln_solid"></div>
                      
                      <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Created Date </label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${participant.commonFields.createdDate}" readonly="readonly"  class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
                      
                      <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Created By </label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${participant.commonFields.createdBy}" readonly="readonly"  class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
					 
					  <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">First Time Login </label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${participantLogDuring.fistTimeLogin}" readonly="readonly"  class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
                      <div class="ln_solid"></div>
					 
                      <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Batch </label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${batch.description}" readonly="readonly"  class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
					 
					  <div class="ln_solid"></div>
                     
                     <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">PK End Time </label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" id="pkEndTime" value='<fmt:formatDate value="${participantLogDuring.capEndTime}" pattern="dd-MM-yyy HH:mm"/>' class="form-control col-md-7 col-xs-12 datetimepicker">
                        </div>
					 </div>
					 
					 <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Career Battery End Time </label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" id="lcbEndTime"  value='<fmt:formatDate value="${participantLogDuring.lcbEndTime}" pattern="dd-MM-yyy HH:mm"/>' class="form-control col-md-7 col-xs-12 datetimepicker">
                        </div>
					 </div>
					 
					 
					 <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Aspiration End Time </label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" id="aspirationEndTime"  value='<fmt:formatDate value="${participantLogDuring.aspirationEndTime}" pattern="dd-MM-yyy HH:mm"/>' class="form-control col-md-7 col-xs-12 datetimepicker">
                        </div>
					 </div>
					 
					 <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Read Memo End Time </label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" id="inbasketReadMemoEndTime"  value='<fmt:formatDate value="${participantLogDuring.inbasketReadMemoEndTime}" pattern="dd-MM-yyy HH:mm"/>'  class="form-control col-md-7 col-xs-12 datetimepicker">
                        </div>
					 </div>
					 
					 <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">In basket End Time </label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" id="inbasketEndTime"  value='<fmt:formatDate value="${participantLogDuring.inbasketEndTime}" pattern="dd-MM-yyy HH:mm"/>'  class="form-control col-md-7 col-xs-12 datetimepicker">
                        </div>
					 </div>
					 
					 <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Analysis End Time </label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" id="analysysEndTime"  value='<fmt:formatDate value="${participantLogDuring.analysysEndTime}" pattern="dd-MM-yyy HH:mm"/>'  class="form-control col-md-7 col-xs-12 datetimepicker">
                        </div>
					 </div>
					 
					 <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Vision End Time </label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" id="visionEndTime"  value='<fmt:formatDate value="${participantLogDuring.visionEndTime}" pattern="dd-MM-yyy HH:mm"/>'  class="form-control col-md-7 col-xs-12 datetimepicker">
                        </div>
                        <div class="col-md-3 col-sm-3 col-xs-12">
                        	<a class="btn btn-primary" target="_blank" href="/staticfiles/acs/visionspeech/${participant.id}.webm" >Video</a>
                        </div>
					 </div>
					 
					 <div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">Log Status</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
							<select name="logDuringStatus" id="logDuringStatus" required="required" class="form-control">
								<option value="FACTSHEET" <c:if test='${ participantLogDuring.logDuringStatus == "FACTSHEET"}'>selected="selected"</c:if>>FACTSHEET</option>
								<option value="CAP" <c:if test='${ participantLogDuring.logDuringStatus == "CAP"}'>selected="selected"</c:if>>CAP</option>
								<option value="LCB" <c:if test='${ participantLogDuring.logDuringStatus == "LCB"}'>selected="selected"</c:if>>LCB</option>
								<option value="ASPIRATION" <c:if test='${ participantLogDuring.logDuringStatus == "ASPIRATION"}'>selected="selected"</c:if>>ASPIRATION</option>
								<option value="WAITING_SECOND_SESSION" <c:if test='${ participantLogDuring.logDuringStatus == "WAITING_SECOND_SESSION"}'>selected="selected"</c:if>>WAITING_SECOND_SESSION</option>
								<option value="INBASKET" <c:if test='${ participantLogDuring.logDuringStatus == "INBASKET"}'>selected="selected"</c:if>>INBASKET</option>
								<option value="ANALYSYS" <c:if test='${ participantLogDuring.logDuringStatus == "ANALYSYS"}'>selected="selected"</c:if>>ANALYSYS</option>
								<option value="VISION_SPEECH" <c:if test='${ participantLogDuring.logDuringStatus == "VISION_SPEECH"}'>selected="selected"</c:if>>VISION_SPEECH</option>
								<option value="FINISH" <c:if test='${ participantLogDuring.logDuringStatus == "FINISH"}'>selected="selected"</c:if>>FINISH</option>
		
								
							</select>
	                        </div>
						 </div>
						 
						 <div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">Participant Status</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
							<select name="participantStatus" id="participantStatus" required="required" class="form-control">
								<option value="REGISTERED" <c:if test='${ participant.participantStatus == "REGISTERED"}'>selected="selected"</c:if>>REGISTERED</option>
								<%-- <option value="INVITATION_SENT" <c:if test='${ participant.participantStatus == "INVITATION_SENT"}'>selected="selected"</c:if>>INVITATION_SENT</option>
								<option value="INVITATION_ACCEPTED" <c:if test='${ participant.participantStatus == "INVITATION_ACCEPTED"}'>selected="selected"</c:if>>INVITATION_ACCEPTED</option>
								
								<option value="INVITATION_REJECTED" <c:if test='${ participant.participantStatus == "INVITATION_REJECTED"}'>selected="selected"</c:if>>INVITATION_REJECTED</option>
								<option value="DECLINED_BEFORE_TEST" <c:if test='${ participant.participantStatus == "DECLINED_BEFORE_TEST"}'>selected="selected"</c:if>>DECLINED_BEFORE_TEST</option>
								<option value="DECLINE_DURING_TEST" <c:if test='${ participant.participantStatus == "DECLINE_DURING_TEST"}'>selected="selected"</c:if>>DECLINE_DURING_TEST</option>
								 --%>
								<option value="SESSION_I" <c:if test='${ participant.participantStatus == "SESSION_I"}'>selected="selected"</c:if>>SESSION_I</option>
								<option value="SESSION_II" <c:if test='${ participant.participantStatus == "SESSION_II"}'>selected="selected"</c:if>>SESSION_II</option>
								<option value="PARTICIPANT_FINISH" <c:if test='${ participant.participantStatus == "PARTICIPANT_FINISH"}'>selected="selected"</c:if>>PARTICIPANT_FINISH</option>
								
								<option value="ASSIGNED_TO_ASSESSOR" <c:if test='${ participant.participantStatus == "ASSIGNED_TO_ASSESSOR"}'>selected="selected"</c:if>>ASSIGNED_TO_ASSESSOR</option>
								<option value="PARTIAL_RATING" <c:if test='${ participant.participantStatus == "PARTIAL_RATING"}'>selected="selected"</c:if>>PARTIAL_RATING</option>
								<option value="ASSESSEMENT_COMPLETE" <c:if test='${ participant.participantStatus == "ASSESSEMENT_COMPLETE"}'>selected="selected"</c:if>>ASSESSEMENT_COMPLETE</option>
								
								
							</select>
	                        </div>
						 </div>
						 
						  <div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category"></label>
							<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="button" class="btn btn-primary" id="btnModifyLog" value="Modify" />
	                        </div>
						 </div>
					 
                      
                     <div class="ln_solid"></div>
                     
                     <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">PK Start Time </label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${participantLogDuring.capStartTime}" readonly="readonly"  class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
					 
					  <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">PK Finish Time </label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${participantLogDuring.capFinishTime}" readonly="readonly"  class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
                       <div class="ln_solid"></div>
					  
                     <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Career Battery Start Time </label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${participantLogDuring.lcbStartTime}" readonly="readonly"  class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
					 
					  <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Career Battery Finish Time </label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${participantLogDuring.lcbFinishTime}" readonly="readonly"  class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
					 
					 
					 
					 <!-- 
					 <c:if test="${not empty  participantLogDuring.lcbFinishTime}">
					 	<div class="form-group">
	                        <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
	                          <a href="${pageContext.request.contextPath}/controller/pages/admin/administrator/assessement/participant/detail/lcb/${participant.id}" class="btn btn-primary">Participant Inventory Answer Detail</a>
	                        </div>
	                      </div>
					 </c:if>
					  -->
                       <div class="ln_solid"></div>
                       
                       
                       
                        <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">In basket Start Time </label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${participantLogDuring.inbasketStartTime}" readonly="readonly"  class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
					 
					  <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">In basket Finish Time </label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${participantLogDuring.inbasketFinishTime}" readonly="readonly"  class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
                       <div class="ln_solid"></div>
                       
                       <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Analysis Start Time </label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${participantLogDuring.analysysStartTime}" readonly="readonly"  class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
					 
					  <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Analysis Finish Time </label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${participantLogDuring.analysysFinishTime}" readonly="readonly"  class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
                       <div class="ln_solid"></div>
                       
                       <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Assess Start Time </label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${participantLogPost.assessStartTime}" readonly="readonly"  class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
					 
					 
					 <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Assessor PK </label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${participantLogPost.capAssessor.fullname}" readonly="readonly"  class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
					 
					  <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Assess PK Finish Time </label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${participantLogPost.assessCapFinishTime}" readonly="readonly"  class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
					 
					 <br/>
					 
					 <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Assessor In Basket  </label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${participantLogPost.simulationAssessor.fullname}" readonly="readonly"  class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
					  <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Assess In Basket Finish Time </label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${participantLogPost.assessSimulationFinishTime}" readonly="readonly"  class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
					 
					 
					 <br/>
					 
					 <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Assessor Analysis  </label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${participantLogPost.analysysAssessor.fullname}" readonly="readonly"  class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
					 
					 <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Assess Analysis Finish Time </label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${participantLogPost.assessAnalysisFinishTime}" readonly="readonly"  class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
					 
					 
					 <br/>
					 
					 <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Assessor Integration Grid  </label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${participantLogPost.integrationGridAssessor.fullname}" readonly="readonly"  class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
					 
					 <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Assess Integration Grid Finish Time </label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${participantLogPost.assessIntegrationGridTime}" readonly="readonly"  class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
					 
					 <br/>
					 
					 <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Reviewer </label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${participantLogPost.reviewer.fullname}" readonly="readonly"  class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
					 
					 <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Reviewer Finish Time </label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${participantLogPost.assessReviewerFinishTime}" readonly="readonly"  class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
                       
                       <c:if test="${not empty participantLogPost.assessReviewerFinishTime}">
                       <div class="ln_solid"></div>
                        
                        <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Individual Report </label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="button" id="generateReportBtnNew" class="btn btn-app" value="PDF" />
							&nbsp;&nbsp;&nbsp;
							<input type="button" id="generateReportOdtBtn" class="btn btn-app" value="DOCX" />
							&nbsp;&nbsp;&nbsp;
							<input type="button" id="emaiLReportBtn" class="btn btn-app" value="EMAIL" />
                        </div>
					 </div>
                       
                       </c:if>
                        
                       
                        <div class="ln_solid"></div>
                      <div class="form-group">
                        <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                          <a href="javascript:window.history.back();" class="btn btn-primary">Back</a>
                        </div>
                      </div>

                    </form>
                  </div>
                </div>
              </div>
            </div>
            
            </div>
</div>
