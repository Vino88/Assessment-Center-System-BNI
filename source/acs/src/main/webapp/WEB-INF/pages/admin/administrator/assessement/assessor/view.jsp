<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages" />

<div class="main_container" role="main">
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

		<div class="page-title">
			<div class="title_left">
				<a class="btn btn-app"
					href="${ pageContext.request.contextPath}/controller/pages/admin/home"
					style="margin: 0px 0px 10px 0px;"><i class="fa fa-home"></i>Home</a>
			</div>


		</div>
		
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>Assessor Record</h2>

						<div class="clearfix"></div>
					</div>
					<div class="x_content">
 					<form method="POST" id="demo-form2" data-parsley-validate="" class="form-horizontal form-label-left" >
					  
 					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Assessors</label>
						<div class="col-md-3 col-sm-3 col-xs-12">
						<div>
							<textarea id="btnBrowseAssessor" rows="4" readonly="readonly"
										class="form-control col-md-12 col-xs-12 resizable_textarea" placeholder="Click to choose assessor"
										></textarea>
						</div>
						
						
							
							
                        </div>
					 </div>
					 
					 <div class="form-group">
						<label class="control-label col-md-3 col-sm-1 col-xs-12" for="first-name">Session II Start Time, from</label>
						<div class="col-md-3 col-sm-3 col-xs-12">
							<input type="text"  name="dateFrom" id="dateFrom" required="required" class="form-control col-md-7 col-xs-12 datetimepicker">
                        </div>
                        
					 </div>
					 
					 <div class="form-group">
						
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">to</label>
						<div class="col-md-3 col-sm-3 col-xs-12">
							<input type="text" name="dateTo" id="dateTo" required="required" class="form-control col-md-7 col-xs-12 datetimepicker">
                        </div>
					 </div>
					 <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">Status</label>
						<div class="col-md-3 col-sm-3 col-xs-12">
						
						 <select name="assessDone" id="assessDone" class="form-control">
							<option value="2">ALL</option>
							<option value="0">INPROGRESS</option>
							<option value="1">DONE</option>
						</select>
							
                        </div>
					 </div>
					 
					
					 
					<div class="ln_solid"></div>
                      <div class="form-group">
                        <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                          
                          <input id="btnSearchAssessor" class="btn btn-success" type="button" value="Search" />
                          <input id="btnClearAssessor" class="btn btn-primary" type="button" value="Clear" />
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
					
					<div class="x_content">
 					
						<table id="entityTable" class="table table-striped table-bordered">
							<thead>
								<tr>
									<th>Assessor Name</th>
									
									<th>Batch Name</th>
									
									<th>Participant Name</th>
									
									<th>Sesion I, Start Time</th>
									<th>Sesion I, End Time</th>
									<th>Sesion II, Start Time</th>
									<th>Sesion II, End Time</th>
									
									<th>Testing Tools</th>
									<th>Status</th>
									
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
