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
            <a href="${ pageContext.request.contextPath}/controller/pages/admin/administrator/participant/batch/view" class="btn btn-app" style="margin: 0px 0px 10px 0px;"><i class="fa fa-arrow-circle-left"></i>Back</a>
            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Batch Participant</h2>
                    <ul class="nav navbar-right panel_toolbox">
                      <li><a href="${ pageContext.request.contextPath}/controller/pages/admin/administrator/participant/batch/report/${dataForm.id}" class="btn btn-app"><i class="fa fa-bar-chart"></i>Aggregate Report</a></li>
                      <li><a href="${ pageContext.request.contextPath}/controller/pages/admin/administrator/participant/batch/reportgroup/${dataForm.id}" class="btn btn-app"><i class="fa fa-bar-chart"></i>Group Report</a></li>
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <br>
                    
                    <form method="POST" id="demo-form2" data-parsley-validate="" class="form-horizontal form-label-left" >
					  <input type="hidden" name="id" value="${ dataForm.id}" />
                       						 <div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">Description</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" name="description" required="required" readonly="readonly" class="form-control col-md-7 col-xs-12" value="${ dataForm.description}"/>
	                        </div>
						 </div>
						 
						 <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">Location</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" name="location" readonly="readonly" required="required" class="form-control col-md-7 col-xs-12" value="${ dataForm.location}">
                        </div>
					 </div>
					 
					 <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">Max Quota</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" name="maxQuota" readonly="readonly" required="required" class="form-control col-md-7 col-xs-12" value="${ dataForm.maxQuota}">
                        </div>
					 </div>
					 
					 <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Additional Information
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <textarea class="form-control" readonly="readonly" rows="5" name="additionalInformation" >${ dataForm.additionalInformation}</textarea>
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
                          <th>NPP</th>
                          <th>Full Name</th>
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
