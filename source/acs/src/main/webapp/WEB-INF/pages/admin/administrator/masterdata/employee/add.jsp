
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
            
            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Add Employee</h2>
                    
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <br>
                    
                    <form method="POST" id="entityForm" data-parsley-validate="" class="form-horizontal form-label-left" action="${ pageContext.request.contextPath}/controller/pages/admin/administrator/masterdata/employee/add">
			         					 <div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">NPP <span class="required red">*</span></label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" name="nik" required="required" class="form-control col-md-7 col-xs-12" value="${ dataForm.nik}" placeholder="P012345"/>
	                        </div>
						 </div>
												 <div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">Full Name <span class="required red">*</span></label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" name="fullName" required="required" class="form-control col-md-7 col-xs-12" value="${ dataForm.fullName}"/>
	                        </div>
						 </div>
												 <div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">Place of Birth <span class="required red">*</span></label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" name="placeOfBirth" required="required" class="form-control col-md-7 col-xs-12" value="${ dataForm.placeOfBirth}"/>
	                        </div>
						 </div>
												 <div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">Date of Birth <span class="required red">*</span></label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" name="dateOfBirthStr" required="required" class="form-control col-md-7 col-xs-12 datetimepickermasterdata" value='<fmt:formatDate value="${ dataForm.dateOfBirth}" pattern="dd-MM-yyyy" />'/>
	                        </div>
						 </div>
												 <div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">Phone </label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" name="phone" class="form-control col-md-7 col-xs-12" value="${ dataForm.phone}"/>
	                        </div>
						 </div>
												 <div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">E-mail <span class="required red">*</span></label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" name="email" required="required" class="form-control col-md-7 col-xs-12" value="${ dataForm.email}"/>
	                        </div>
						 </div>
												 <div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">Wilayah/Unit</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" name="workingArea" class="form-control col-md-7 col-xs-12" value="${ dataForm.workingArea}"/>
	                        </div>
						 </div>
						                     
                      <div class="form-group">
		                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Position
		                        </label>
		                       	<div class="col-md-6 col-sm-6 col-xs-12">
		                       		<input type="text" name="currentPositionName" value="${dataForm.currentPositionName}" id="first-name" class="form-control col-md-7 col-xs-12">
		                        </div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Length of Service</label>
		                        <div class="col-md-6 col-sm-6 col-xs-12">
		                          	<input type="number" id="last-name" name="currentPeriode" value="${dataForm.currentPeriode}" class="form-control col-md-7 col-xs-12">
		                        </div>
		                   	</div>
		                   	<div class="form-group">
		                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Main Responsibility</label>
		                        <div class="col-md-6 col-sm-6 col-xs-12">
		                          	<textarea class="form-control col-md-7 col-xs-12" name="currentResponsibility">${dataForm.currentResponsibility}</textarea>
		                        </div>
		                   	</div>
		                   	<div class="form-group">
		                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Direct Report Name</label>
		                        <div class="col-md-6 col-sm-6 col-xs-12">
		                          	<input id="middle-name" class="form-control col-md-7 col-xs-12" type="text" name="currentDirectSupervisor" value="${dataForm.currentDirectSupervisor}">
		                        </div>
		                   	</div>
		                   	
		                   	<div class="form-group">
		                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Direct Report Email</label>
		                        <div class="col-md-6 col-sm-6 col-xs-12">
		                          	<input class="form-control col-md-7 col-xs-12" type="text" name="currentSupervisorEmail" value="${dataForm.currentSupervisorEmail}">
		                        </div>
		                   	</div>
					 
								        
					 
                      <div class="ln_solid"></div>
                      <div class="form-group">
                        <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                          <a href="${ pageContext.request.contextPath}/controller/pages/admin/administrator/masterdata/employee/view" class="btn btn-primary">Cancel</a>
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
