<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages"/>
<style>
th {
	text-align:center;
}
</style>
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
        <a class="btn btn-app" href="${pageContext.request.contextPath}/controller/pages/admin/assessor/assess/home/view/${participant.id}" style="margin: 0px 0px 10px 0px;"><i class="fa fa-arrow-circle-left"></i>Back</a>
		<div class="row">
		
		<div class="col-md-4 col-sm-6 col-xs-12">
                <div class="x_panel fixed_height_320">
                  <div class="x_title">
                    <h2>SPM</h2>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                  <table class="" style="width:100%">
                    <tbody><tr>
                      <th style="width:37%;">
                        <p>Chart</p>
                      </th>
                      <th>
                        <div class="col-lg-7 col-md-7 col-sm-7 col-xs-7">
                          <p class="">Assessor Flag</p>
                        </div>
                        <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
                          <p class="">Count</p>
                        </div>
                      </th>
                    </tr>
                    <tr>
                      <td><iframe class="chartjs-hidden-iframe" style="width: 100%; display: block; border: 0px; height: 0px; margin: 0px; position: absolute; left: 0px; right: 0px; top: 0px; bottom: 0px;"></iframe>
                        <canvas id="canvas1" height="140" width="140" style="margin: 15px 10px 10px 0px; width: 85px; height: 85px;"></canvas>
                      </td>
                      <td>
                        <table class="tile_info">
                          <tbody><tr>
                            <td>
                              <p><i class="fa fa-square" style="color: #f0ad4e"></i>I'm not sure </p>
                            </td>
                            <td>${spmFlag.notSure }</td>
                          </tr>
                          <tr>
                            <td>
                              <p><i class="fa fa-square red"></i>can't be assessed </p>
                            </td>
                            <td>${spmFlag.answer }</td>
                          </tr>
                          
                          <tr>
                            <td>
                              <p><i class="fa fa-square green"></i>Empty </p>
                            </td>
                            <td>${spmFlag.emptyFlag }</td>
                          </tr>
                          
                        </tbody></table>
                      </td>
                    </tr>
                  </tbody></table>
                  </div>
                </div>
              </div>

              <div class="col-md-4 col-sm-6 col-xs-12">
                <div class="x_panel fixed_height_320">
                  <div class="x_title">
                    <h2>SRB</h2>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                  <table class="" style="width:100%">
                    <tbody><tr>
                      <th style="width:37%;">
                        <p>Chart</p>
                      </th>
                      <th>
                        <div class="col-lg-7 col-md-7 col-sm-7 col-xs-7">
                          <p class="">Assessor Flag</p>
                        </div>
                        <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
                          <p class="">Count</p>
                        </div>
                      </th>
                    </tr>
                    <tr>
                      <td><iframe class="chartjs-hidden-iframe" style="width: 100%; display: block; border: 0px; height: 0px; margin: 0px; position: absolute; left: 0px; right: 0px; top: 0px; bottom: 0px;"></iframe>
                        <canvas id="canvas2" height="140" width="140" style="margin: 15px 10px 10px 0px; width: 85px; height: 85px;"></canvas>
                      </td>
                      <td>
                        <table class="tile_info">
                          <tbody><tr>
                            <td>
                              <p><i class="fa fa-square" style="color: #f0ad4e"></i>I'm not sure </p>
                            </td>
                            <td>${srbFlag.notSure }</td>
                          </tr>
                          <tr>
                            <td>
                              <p><i class="fa fa-square red"></i>can't be assessed </p>
                            </td>
                            <td>${srbFlag.answer }</td>
                          </tr>
                          
                          <tr>
                            <td>
                              <p><i class="fa fa-square green"></i>Empty </p>
                            </td>
                            <td>${srbFlag.emptyFlag }</td>
                          </tr>
                          
                        </tbody></table>
                      </td>
                    </tr>
                  </tbody></table>
                  </div>
                </div>
              </div>
              
              
              <div class="col-md-4 col-sm-6 col-xs-12">
                <div class="x_panel fixed_height_320">
                  <div class="x_title">
                    <h2>Prestasi Karir</h2>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                  <table class="" style="width:100%">
                    <tbody><tr>
                      <th style="width:37%;">
                        <p>Chart</p>
                      </th>
                      <th>
                        <div class="col-lg-7 col-md-7 col-sm-7 col-xs-7">
                          <p class="">Assessor Flag</p>
                        </div>
                        <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
                          <p class="">Count</p>
                        </div>
                      </th>
                    </tr>
                    <tr>
                      <td><iframe class="chartjs-hidden-iframe" style="width: 100%; display: block; border: 0px; height: 0px; margin: 0px; position: absolute; left: 0px; right: 0px; top: 0px; bottom: 0px;"></iframe>
                        <canvas id="canvas3" height="140" width="140" style="margin: 15px 10px 10px 0px; width: 85px; height: 85px;"></canvas>
                      </td>
                      <td>
                        <table class="tile_info">
                          <tbody><tr>
                            <td>
                              <p><i class="fa fa-square" style="color: #f0ad4e"></i>I'm not sure </p>
                            </td>
                            <td>${capFlag.notSure }</td>
                          </tr>
                          <tr>
                            <td>
                              <p><i class="fa fa-square red"></i>can't be assessed </p>
                            </td>
                            <td>${capFlag.answer }</td>
                          </tr>
                          
                          <tr>
                            <td>
                              <p><i class="fa fa-square green"></i>Empty </p>
                            </td>
                            <td>${capFlag.emptyFlag }</td>
                          </tr>
                          
                        </tbody></table>
                      </td>
                    </tr>
                  </tbody></table>
                  </div>
                </div>
              </div>
              
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_title">
                    	<h2>Integration Grid</h2>
                    	<div class="clearfix"></div>
                  	</div>
                  	<div class="x_content">
                    	<br>
	                    <div class="form-horizontal form-label-left">
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
                    	</div>
                 	</div>
               	</div>
                
				<form method="POST" id="formIg" action="${pageContext.request.contextPath}/controller/pages/admin/assessor/assess/integrationgrid/rating/${participant.id}">
                	<div class="x_panel">
	              		<div class="x_title">
	              			<i class="fa fa-question-circle" style="color: #f0ad4e"></i> Assessor Not Sure<br/>
				                          				
                 				<i class="fa fa-warning red"></i> Participant Answer can't be Assessed <br/>
                 				
                 				<i class="fa fa-check-circle-o green"></i> No Flag / Empty <br/>
                 				<div class="clearfix"></div>
	              		</div>
	              		<div class="x_content">
	              		
	              			<table id="entityTable" class="table table-striped table-bordered">
		                    	<thead>
		                       		<tr>
		                       			<th>No.</th>
			                        	<th>Competency Library</th>
	            						
	                                    <th>SPM</th>
	                                    <th>SRB</th>
	                                    
	                                    <th>LI</th>
	                                    <th>Prestasi Karir</th>
	                                    
	                                    <th>Personality Test</th>
	                                    
	                                    <th>Final Rating</th>
		                        	</tr>
		                      	</thead>
		                      	<tbody>
		                       		<c:set var="igIndex" value="0" scope="request"/>

<c:set var="clCategory" value="" scope="request"/>

		                      		<c:forEach items="${listParticipantCompetencyLibraryFinalResult }" var="participantCompetencyLibraryFinalResult" varStatus="status">
		                      			
<c:choose>


<c:when test="${clCategory ne participantCompetencyLibraryFinalResult.competencyLibrary.category && participantCompetencyLibraryFinalResult.competencyLibrary.category ne 'CORE'}">
<tr>
<td colspan="8" style="background-color:#f0ad4e; color:black">
${participantCompetencyLibraryFinalResult.competencyLibrary.category}
</td>

</tr>
<c:set var="clCategory" value="${participantCompetencyLibraryFinalResult.competencyLibrary.category}" scope="request"/>

</c:when>

<c:otherwise>

</c:otherwise>
</c:choose>
<input type="hidden" name="igRating[${igIndex}].competencyId" value="${participantCompetencyLibraryFinalResult.competencyLibrary.id}" />
				                      	<tr align="center" <c:if test="${participantCompetencyLibraryFinalResult.competencyLibrary.category eq 'CORE'}">style="background-color:#5bc0de; color:black"</c:if>>
				                      		<td>${status.count }</td>
				                      		<td align="left" >
				                      		
				                      			${participantCompetencyLibraryFinalResult.competencyLibrary.competencyName}
				                      		</td>
				                          	
				                          	<td>
				                          		
				                          		<c:if test="${participantCompetencyLibraryFinalResult.inbasketRating > 0}">
				                          			<span id="inbasketNotes${participantCompetencyLibraryFinalResult.competencyLibrary.id}" style="display:none"><c:out value="${participantCompetencyLibraryFinalResult.inbasketNotes}" escapeXml="true" /></span>
				                          			<a href="javascript:displayNotes('inbasketNotes',${participantCompetencyLibraryFinalResult.competencyLibrary.id})">
				                          			${participantCompetencyLibraryFinalResult.inbasketRating}
				                          			<c:choose>
				                          				<c:when test="${participantCompetencyLibraryFinalResult.inbasketFlag == 'NOT_SURE' }">
				                          				<i class="fa fa-question-circle" style="color: #f0ad4e"></i>
				                          				</c:when>
				                          				<c:when test="${participantCompetencyLibraryFinalResult.inbasketFlag == 'ANSWER_CANT_ASSESSED' }">
				                          				<i class="fa fa-warning red"></i>
				                          				</c:when>
				                          				
				                          				<c:otherwise>
				                          				<i class="fa fa-check-circle-o green"></i>
				                          				</c:otherwise>
				                          			</c:choose>
				                          			</a>
				                          		</c:if>
				                          		
				                          	</td>
				                          	<td>
				                          		
				                          		<c:if test="${participantCompetencyLibraryFinalResult.analysisRating > 0}">
				                          			<span id="analysisNotes${participantCompetencyLibraryFinalResult.competencyLibrary.id}" style="display:none"><c:out value="${participantCompetencyLibraryFinalResult.analysisNotes}" escapeXml="true" /></span>
				                          			<a href="javascript:displayNotes('analysisNotes',${participantCompetencyLibraryFinalResult.competencyLibrary.id})">
				                          			${participantCompetencyLibraryFinalResult.analysisRating}
				                          			
				                          			<c:choose>
				                          				<c:when test="${participantCompetencyLibraryFinalResult.analysisFlag == 'NOT_SURE' }">
				                          				<i class="fa fa-question-circle" style="color: #f0ad4e"></i>
				                          				</c:when>
				                          				<c:when test="${participantCompetencyLibraryFinalResult.analysisFlag == 'ANSWER_CANT_ASSESSED' }">
				                          				<i class="fa fa-warning red"></i>
				                          				</c:when>
				                          				<c:otherwise>
				                          				<i class="fa fa-check-circle-o green"></i>
				                          				</c:otherwise>
				                          			</c:choose>
				                          			</a>
				                          		</c:if>
				                          		
				                          	</td>
				                          	
				                          	<td>
				                          		<c:if test="${participantCompetencyLibraryFinalResult.lcbRating > 0}">
				                          			${participantCompetencyLibraryFinalResult.lcbRating}
				                          		</c:if>
				                          	</td>
				                          	<td>
				                          		
				                          		<c:if test="${participantCompetencyLibraryFinalResult.capRating > 0}">
				                          		
				                          			<span id="capNotes${participantCompetencyLibraryFinalResult.competencyLibrary.id}" style="display:none"><c:out value="${participantCompetencyLibraryFinalResult.capNotes}" escapeXml="true" /></span>
				                          			<a href="javascript:displayNotes('capNotes',${participantCompetencyLibraryFinalResult.competencyLibrary.id})">
				                          			${participantCompetencyLibraryFinalResult.capRating}
				                          			<c:choose>
				                          				<c:when test="${participantCompetencyLibraryFinalResult.capFlag == 'NOT_SURE' }">
				                          				<i class="fa fa-question-circle" style="color: #f0ad4e"></i>
				                          				</c:when>
				                          				<c:when test="${participantCompetencyLibraryFinalResult.capFlag == 'ANSWER_CANT_ASSESSED' }">
				                          				<i class="fa fa-warning red"></i>
				                          				</c:when>
				                          				<c:otherwise>
				                          				<i class="fa fa-check-circle-o green"></i>
				                          				</c:otherwise>
				                          			</c:choose>
				                          			</a>
				                          		</c:if>
				                          		
				                          	</td>
				                          	
				                          	
				                          	<td>
				                          	
				                          	<c:if test="${participantCompetencyLibraryFinalResult.otherRating > 0}">
				                          			${participantCompetencyLibraryFinalResult.otherRating}
				                          		</c:if>
			                          			
				                          	</td>
				                          	
				                          	
				                          	<td>
				                          	<c:choose>
				                          	<c:when test="${participantCompetencyLibraryFinalResult.finalRating == 0}">
				                          	
				                          	<select name="igRating[${igIndex}].finalRating">
			                          				<option <c:if test="${defaultFinalResultList[igIndex] == 1}">selected="selected"</c:if>>1</option>
			                          				<option <c:if test="${defaultFinalResultList[igIndex] == 2}">selected="selected"</c:if>>2</option>
			                          				<option <c:if test="${defaultFinalResultList[igIndex] == 3}">selected="selected"</c:if>>3</option>
			                          				<option <c:if test="${defaultFinalResultList[igIndex] == 4}">selected="selected"</c:if>>4</option>
			                          				<option <c:if test="${defaultFinalResultList[igIndex] == 5}">selected="selected"</c:if>>5</option>
			                          			</select>
				                          	
				                          	</c:when>
				                          	
				                          	<c:otherwise>
				                          	<select name="igRating[${igIndex}].finalRating">
			                          				<option <c:if test="${participantCompetencyLibraryFinalResult.finalRating == 1}">selected="selected"</c:if>>1</option>
			                          				<option <c:if test="${participantCompetencyLibraryFinalResult.finalRating == 2}">selected="selected"</c:if>>2</option>
			                          				<option <c:if test="${participantCompetencyLibraryFinalResult.finalRating == 3}">selected="selected"</c:if>>3</option>
			                          				<option <c:if test="${participantCompetencyLibraryFinalResult.finalRating == 4}">selected="selected"</c:if>>4</option>
			                          				<option <c:if test="${participantCompetencyLibraryFinalResult.finalRating == 5}">selected="selected"</c:if>>5</option>
			                          			</select>
				                          	</c:otherwise>
				                          		
				                          	</c:choose>
			                          			
				                          	</td>
				                          	
				                          	
				                          	
				                          	
		                        		</tr>
		                        		<c:set var="igIndex" value="${igIndex +1 }" scope="request"/>
		                      		</c:forEach>
		                      	</tbody>
		                    </table>
	              		</div>
	              	</div>
	              	
	              	<div class="x_panel">
	              		<div class="x_title">
	              			<h2>Career Aspiration</h2>
		                    <div class="clearfix"></div>
	              			</div>
	              		<div class="x_content">
	              			<div class="form-group">
	                        	<div class="col-md-12 col-sm-6 col-xs-12">
		                          	
		                        	<textarea rows="8" readonly="readonly"
										class="form-control col-md-12 col-xs-12 resizable_textarea"
										name="careerAspiration">${profile.careerAspiration }</textarea>
								</div>
                        	</div>
                      	</div>
	              		<div class="x_content">
	              			<div class="x_title">
	              			<h2>Strength</h2>
	              			<ul class="nav navbar-right panel_toolbox">
		                      <li><a href="javascript:writingAssistance('participantStrong')" class="btn btn-success">Writing Assistance</a>
		                      </li>
		                      
		                    </ul>
		                    <div class="clearfix"></div>
	              			</div>
	              			
	              			<div class="form-group">
	                        	<div class="col-md-12 col-sm-6 col-xs-12">
		                          	
		                        	<textarea id="participantStrong" rows="8" 
										class="form-control col-md-12 col-xs-12 resizable_textarea"
										name="strong">${profile.strong }</textarea>
								</div>
                        	</div>
                      	</div>
                      	<div class="x_content">
                      	<div class="x_title">
	              			<h2>Weakness</h2>
	              			<ul class="nav navbar-right panel_toolbox">
		                      <li><a href="javascript:writingAssistance('participantWeakness')" class="btn btn-success">Writing Assistance</a>
		                      </li>
		                      
		                    </ul>
		                    <div class="clearfix"></div>
	              			</div>
	              			<div class="form-group">
	                        	<div class="col-md-12 col-sm-6 col-xs-12">
		                          	
		                        	<textarea rows="8" id="participantWeakness"
										class="form-control col-md-12 col-xs-12 resizable_textarea"
										name="weakness">${profile.weakness }</textarea>
								</div>
                        	</div>
                      	</div>
                      	<div class="x_content">
                      	<div class="x_title">
	              			<h2>Personal Competency</h2>
	              			<ul class="nav navbar-right panel_toolbox">
		                      <li><a href="javascript:writingAssistance('personalCompetency')" class="btn btn-success">Writing Assistance</a>
		                      </li>
		                      
		                    </ul>
		                    <div class="clearfix"></div>
	              			</div>
	              			<div class="form-group">
	                        	<div class="col-md-12 col-sm-6 col-xs-12">
		                          	
		                        	<textarea rows="8" id="personalCompetency"
										class="form-control col-md-12 col-xs-12 resizable_textarea"
										name="personalCompetency">${profile.personalCompetency }</textarea>
								</div>
                        	</div>
                      	</div>
                      	<div class="x_content">
                      	<div class="x_title">
	              			<h2>Leadership Competency</h2>
	              			<ul class="nav navbar-right panel_toolbox">
		                      <li><a href="javascript:writingAssistance('leadershipCompetency')" class="btn btn-success">Writing Assistance</a>
		                      </li>
		                      
		                    </ul>
		                    <div class="clearfix"></div>
	              			</div>
	              			<div class="form-group">
	                        	<div class="col-md-12 col-sm-6 col-xs-12">
		                          	
		                        	<textarea rows="8" id="leadershipCompetency"
										class="form-control col-md-12 col-xs-12 resizable_textarea"
										name="leadershipCompetency">${profile.leadershipCompetency }</textarea>
								</div>
                        	</div>
                      	</div>
                      	<div class="x_content">
                      	<div class="x_title">
	              			<h2>Functional Competency</h2>
	              			<ul class="nav navbar-right panel_toolbox">
		                      <li><a href="javascript:writingAssistance('functionalCompetency')" class="btn btn-success">Writing Assistance</a>
		                      </li>
		                      
		                    </ul>
		                    <div class="clearfix"></div>
	              			</div>
	              			<div class="form-group">
	                        	<div class="col-md-12 col-sm-6 col-xs-12">
		                          	
		                        	<textarea rows="8" id="functionalCompetency"
										class="form-control col-md-12 col-xs-12 resizable_textarea"
										name="functionalCompetency">${profile.functionalCompetency }</textarea>
								</div>
                        	</div>
                      	</div>
              		</div>
	          	</form>
             	<div class="x_panel">
             		<div class="x_title"></div>
              		<div class="x_content">
              			<div class="form-group">
                        	<div class="col-md-6 col-sm-6 col-xs-12">
	                        	<a href="${pageContext.request.contextPath}/controller/pages/admin/assessor/assess/home/view/${participant.id}" class="btn btn-primary">Back</a>
                        		<button id="btnSaveAsDraft" type="button" class="btn btn-warning">Save As Draft</button>
	                          	<button id="btnFinishNow" type="button" class="btn btn-success">Submit and Finish</button>
                        	</div>
                      	</div>
              		</div>
              	</div>
         	</div>
		</div>
	</div>
</div>
