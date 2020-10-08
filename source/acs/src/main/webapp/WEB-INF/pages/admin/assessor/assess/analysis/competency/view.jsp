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
            <a class="btn btn-app" href="${pageContext.request.contextPath}/controller/pages/admin/assessor/assess/analysis/view/${participant.id}" style="margin: 0px 0px 10px 0px;"><i class="fa fa-arrow-circle-left"></i>Back</a>
            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Competency "${competencyLibrary.competencyName}" for SRB</h2>
                    
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <br>
                    
                    <div class="form-horizontal form-label-left">
                       <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Participant ID</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${participant.id}" readonly="readonly" class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
	                  
					 	<div class="ln_solid"></div>

                    </div>
                    <br/>
                    
                    <div>
                    	<c:forEach items="${listParticipantClAnalysis }" var="participantClAnalysis">
                    	
						<div >
							<h2 class="font-gray-dark">${participantClAnalysis.analysys.question}</h2>
							<c:choose>
								<c:when test="${participantClAnalysis.analysys.analysysType == 'VIDEO' }">
									<textarea rows="12" readonly="readonly"
										class="form-control col-md-7 col-xs-12 resizable_textarea textareaTinyMce"
										name="">${participantClAnalysis.answer}</textarea>
										<br/>
									<video width="350" height="350" controls="controls" src="/staticfiles/acs/visionspeech/${participant.id}.webm"></video>
								</c:when>
								
								<c:otherwise>
									
							
									<textarea rows="12" readonly="readonly"
										class="form-control col-md-7 col-xs-12 resizable_textarea textareaTinyMce"
										name="">${participantClAnalysis.answer}</textarea>
								</c:otherwise>
							</c:choose>
							
						</div>
						<br/>
                    	</c:forEach>
                    	
										
						
						
						
                    </div>
                   
                    
                  </div>
                </div>
                
                <div class="x_panel">
	              		<div class="x_title">
	              		</div>
	              		<div class="x_content">
	              		 <div class="form-horizontal form-label-left">
	                       <div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name"></label>
							<div class="col-md-6 col-sm-6 col-xs-12">
							Participant Level (<span id="ratingDisplay">${participantCompetencyLibraryFinalResult.analysisRating }</span>)
							<div class="starrr stars-existing" id="starRating">
								<a href="#" class="fa-star-o fa"></a>
								<a href="#" class="fa-star-o fa"></a>
								<a href="#" class="fa-star-o fa"></a>
								<a href="#" class="fa-star-o fa"></a>
								<a href="#" class="fa-star-o fa"></a>
							</div>
							 
								
								
	                        </div>
						 </div>
	
	                    </div>
                    
	              			 <table id="entityTable" class="table table-striped table-bordered">
		                      <thead>
		                        <tr>
            						  <th>Level</th>
                                      <th>Description</th>
                                      <th>Action</th>
		                        </tr>
		                      </thead>
		
		
		                      <tbody>
		                      <c:forEach items="${competencyLibraryBehaviours }" var="clBehaviour">
		                      	<tr id="clBehaviour${clBehaviour.id}" >
		                          	<td>
		                          		${clBehaviour.behaviourLevel}
		                          	</td>
		                          	<td>
		                          		${clBehaviour.behaviour}
		                          	</td>
		                          	<td>
		                          		<a href="javascript:notMeetBehaviourLevel(${clBehaviour.behaviourLevel}, ${clBehaviour.id})" class="btn btn-warning" >Not Meet</a>
		                          		&nbsp;&nbsp;
		                          		<a href="javascript:chooseBehaviourLevel(${clBehaviour.behaviourLevel}, ${clBehaviour.id})" class="btn btn-success" >&nbsp;&nbsp;Meet&nbsp;&nbsp;</a>
		                          	</td>
		                        </tr>
		                      </c:forEach>
		                        
		                      </tbody>
		                    </table>
	              		</div>
	              	</div>
	              	
	              	
	              	<div class="x_panel">
	              		<div class="x_title">
	              		</div>
	              		<div class="x_content">
	              		 <form id="assessForm" method="POST" id="demo-form2" data-parsley-validate="" class="form-horizontal form-label-left" action="${pageContext.request.contextPath}/controller/pages/admin/assessor/assess/analysis/competency/assess" >
	                       <div class="form-group"> 
                        <label class="col-md-3 col-sm-3 col-xs-12 control-label">Flag 
                        </label>

                        <div class="col-md-9 col-sm-9 col-xs-12" id="containerRadio${count.index}">
                           <div class="radio">
                            <label>
                              <input type="radio" class="flat" name="asessmentFlag" <c:if test="${participantCompetencyLibraryFinalResult.analysisFlag == 'NOT_SURE' }">checked="checked"</c:if>  value="NOT_SURE"> I'm not sure
                            </label>
                          </div>
                          
                           <div class="radio">
                            <label>
                              <input type="radio" class="flat" name="asessmentFlag" <c:if test="${participantCompetencyLibraryFinalResult.analysisFlag == 'ANSWER_CANT_ASSESSED' }">checked="checked"</c:if> value="ANSWER_CANT_ASSESSED"> Participant Answer can't be assessed
                            </label>
                          </div>
                          <div class="radio">
                            <label>
                              <button type="button" onclick="clearRadio()" class="btn btn-success">Clear</button>
                            </label>
                          </div>
                          
                        </div>
                      </div>
                      
                      <br/>
	                       
	                       <div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Assessor Notes <span class="required red">*</span></label>
							
							
							<div class="col-md-6 col-sm-6 col-xs-12">
								<textarea rows="15"
							class="form-control col-md-7 col-xs-12 resizable_textarea"
							name="notes" id="notes" >${participantCompetencyLibraryFinalResult.analysisNotes }</textarea>
	                        </div>
						 </div>
						 <input type="hidden" name="rating" id="rating" value="${participantCompetencyLibraryFinalResult.analysisRating }">
						 <input type="hidden" name="participantId" id="participantId" value="${participant.id }">
						 <input type="hidden" name="competencyId" id="competencyId" value="${competencyLibrary.id }">
	
	                    </form>
                    
	              			
	              		</div>
	              	</div>
	              	
                
                <div class="x_panel">
	              		<div class="x_title">
	              		</div>
	              		<div class="x_content">
	              			<div class="form-group">
	                        	<div class="col-md-6 col-sm-6 col-xs-12">
		                          
		                        	<a href="${pageContext.request.contextPath}/controller/pages/admin/assessor/assess/analysis/view/${participant.id}" class="btn btn-primary">Back</a>
	                        	
		                          	<button id="sbmtAssess" type="button" class="btn btn-success">Submit</button>
	                        	</div>
	                      	</div>
	              		</div>
	              	</div>
              </div>
            </div>
            
            </div>
</div>
