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
            <a href="${ pageContext.request.contextPath}/controller/pages/admin/home" class="btn btn-app" style="margin: 0px 0px 10px 0px;"><i class="fa fa-arrow-circle-left"></i>Back</a>
            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Batch Participant</h2>
                    
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <br>
                    
                    <form method="POST" id="demo-form2" data-parsley-validate="" class="form-horizontal form-label-left" >
					  
                       						 <div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">Batch Name</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" name="description" required="required" readonly="readonly" class="form-control col-md-7 col-xs-12" value="${ dataForm.description}"/>
	                        </div>
						 </div>
						 
						 <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">Location Session I</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" name="location" readonly="readonly" required="required" class="form-control col-md-7 col-xs-12" value="${ dataForm.location}">
                        </div>
					 </div>
												 <div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">Session I, Start Time</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" name="assessementFirstHalfStartTime" required="required" readonly="readonly" class="form-control col-md-7 col-xs-12 datetimepicker" value='<fmt:formatDate  value="${dataForm.assessementFirstHalfStartTime }" pattern="dd/MM/yyyy HH:mm" />'/>
	                        </div>
						 </div>
												 <div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category"> Session I, End Time</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" name="assessementFirstHalfEndTime" required="required" readonly="readonly" class="form-control col-md-7 col-xs-12 datetimepicker" value='<fmt:formatDate  value="${dataForm.assessementFirstHalfEndTime }" pattern="dd/MM/yyyy HH:mm" />'/>
	                        </div>
						 </div>
						 
						 <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">Location Session II</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" name="locationSecond" readonly="readonly" required="required" class="form-control col-md-7 col-xs-12" value="${ dataForm.locationSecond}">
                        </div>
					 </div>
												 <div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category"> Session II, Start Time</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" name="assessementSecondHalfStartTime" required="required" readonly="readonly" class="form-control col-md-7 col-xs-12 datetimepicker" value='<fmt:formatDate  value="${dataForm.assessementSecondHalfStartTime }" pattern="dd/MM/yyyy HH:mm" />'/>
	                        </div>
						 </div>
												 <div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category"> Assessment Finish Time</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" name="assessementFinishTime" required="required" readonly="readonly" class="form-control col-md-7 col-xs-12 datetimepicker" value='<fmt:formatDate  value="${dataForm.assessementFinishTime }" pattern="dd/MM/yyyy HH:mm" />'/>
	                        </div>
						 </div>
						 
						 <div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">Operator</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" name="operatorUserName" required="required" readonly="readonly" class="form-control col-md-7 col-xs-12 datetimepicker" value='${dataForm.userAdmin.username }'/>
	                        </div>
						 </div>
						 
						 <div class="ln_solid"></div>
	                      <div class="form-group">
	                        <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
	                        	<c:choose>
	                        		<c:when test="${dataForm.assessementFirstHalfStart == false }">
	                        			 <button type="button" class="btn btn-success" id="btnStartSessionI">Start Session I</button>
	                          			 <button type="button" class="btn btn-success" disabled="disabled">Start Session II</button>
	                        		</c:when>
	                        		<c:when test="${dataForm.assessementSecondHalfStart == false }">
	                        			<button type="button" class="btn btn-success" disabled="disabled">Session I, Started</button>
	                          			<button type="button" class="btn btn-success" id="btnStartSessionII">Start Session II</button>
	                        		</c:when>
	                        		<c:otherwise>
	                        			<button type="button" class="btn btn-success" disabled="disabled">Session I, Started</button>
	                          			<button type="button" class="btn btn-success" disabled="disabled">Session II, Started</button>
	                        		</c:otherwise>
	                        	</c:choose>
	                         
	                        </div>
	                        
	                        
	                      </div>

                    </form>
                  </div>
                </div>
              </div>
            </div>
            
            
            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Participant</h2>
                    
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    
                    <table id="participantDatatable" class="table table-striped table-bordered">
                      <thead>
                        <tr>
                          <th>NIK</th>
                          <th>Full Name</th>
                          
                          <th>Password</th>
                          
                          <th>Participant Status</th>
                          <th>Test Status</th>
                          
                           <th>Batch Description</th>
                          <th>Location</th>
				          <th>Max Quota</th>
				          <th>Additional Information</th>
				          
				          
				          <th>Session I, Start Time</th>
				          <th>Session I, End Time</th>
				          <th>Session II, Start Time</th>
				          <th>Assessment Finish Time</th>
                        </tr>
                      </thead>


                      <tbody>
                        <tr>
                          
                        </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
              </div>
            
            </div>
</div>
