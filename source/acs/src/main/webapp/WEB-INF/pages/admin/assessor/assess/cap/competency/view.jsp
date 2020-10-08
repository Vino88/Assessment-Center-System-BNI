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
            <a class="btn btn-app" href="${pageContext.request.contextPath}/controller/pages/admin/assessor/assess/cap/view/${participant.id}" style="margin: 0px 0px 10px 0px;"><i class="fa fa-arrow-circle-left"></i>Back</a>
            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Competency "${competencyLibrary.competencyName}" for PK</h2>
                    
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <br>
                    
                    <div class="form-horizontal form-label-left">
                       <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12">Participant ID</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${participant.id}" readonly="readonly" class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
					 <div class="ln_solid"></div>

                    </div>
                    <br/>
                    
                    <div>
                    	<h2 class="font-gray-dark">${cap.question}</h2>
										
						<br/>
						<div >
						<p>${cap.qSituation } </p>
						<textarea rows="15" readonly="readonly" disabled="disabled"
							class="form-control col-md-7 col-xs-12 resizable_textarea textareaTinyMce"
							name="listParticipantCapDto[${count.index}].ansSituation">${participantCap.ansSituation}</textarea>
						</div>
						
						<br/>
						<div >
						<p>${cap.qAction } </p>
						<textarea rows="15" readonly="readonly"
							class="form-control col-md-7 col-xs-12 resizable_textarea textareaTinyMce"
							name="listParticipantCapDto[${count.index}].ansAction">${participantCap.ansAction}</textarea>
						</div>
						
						<br/>
						<div >
						<p>${cap.qResult } </p>
						<textarea rows="15" readonly="readonly"
							class="form-control col-md-7 col-xs-12 resizable_textarea textareaTinyMce"
							name="listParticipantCapDto[${count.index}].ansResult">${participantCap.ansResult}</textarea>
						</div>
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
							Participant Level (<span id="ratingDisplay">${participantCompetencyLibraryFinalResult.inbasketRating }</span>)
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
	              		 <form id="assessForm" method="POST" id="demo-form2" data-parsley-validate="" class="form-horizontal form-label-left" action="${pageContext.request.contextPath}/controller/pages/admin/assessor/assess/cap/competency/assess" >
	                       <div class="form-group"> 
                        <label class="col-md-3 col-sm-3 col-xs-12 control-label">Flag 
                        </label>

                        <div class="col-md-9 col-sm-9 col-xs-12">
                          
                           <div class="radio">
                            <label>
                            
                              <input type="radio" class="flat" name="asessmentFlag" <c:if test="${participantCompetencyLibraryFinalResult.capFlag == 'NOT_SURE' }">checked="checked"</c:if>  value="NOT_SURE"> I'm not sure
                            </label>
                          </div>
                          
                           <div class="radio">
                            <label>
                              <input type="radio" class="flat" name="asessmentFlag" <c:if test="${participantCompetencyLibraryFinalResult.capFlag == 'ANSWER_CANT_ASSESSED' }">checked="checked"</c:if> value="ANSWER_CANT_ASSESSED"> Participant Answer can't be assessed
                            </label>
                          </div>
                          
                          <div class="radio">
                            <label>
                              <input type="radio" class="flat" name="asessmentFlag" <c:if test="${participantCompetencyLibraryFinalResult.capFlag == 'NO_FLAG' }">checked="checked"</c:if> value="NO_FLAG"> No Flag
                            </label>
                          </div>
                          
                        </div>
                      </div>
                      
                      <br/>
	                       
	                       <div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">Assessor Notes<span class="required red">*</span></label>
							
							
							<div class="col-md-6 col-sm-6 col-xs-12">
								<textarea rows="15"
							class="form-control col-md-7 col-xs-12 resizable_textarea"
							name="notes" id="notes" required="required">${participantCompetencyLibraryFinalResult.capNotes }</textarea>
	                        </div>
						 </div>
						 <input type="hidden" name="rating" id="rating" value="${participantCompetencyLibraryFinalResult.capRating }">
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
		                          
		                        	<a href="${pageContext.request.contextPath}/controller/pages/admin/assessor/assess/cap/view/${participant.id}" class="btn btn-primary">Back</a>
	                        	
		                          	<button id="sbmtAssess" type="button" class="btn btn-success">Submit</button>
	                        	</div>
	                      	</div>
	              		</div>
	              	</div>
              </div>
            </div>
            
            </div>
</div>
