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
                    <h2>Edit Batch</h2>
                    
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <br>
                    
                    <form method="POST" id="demo-form2" data-parsley-validate="" class="form-horizontal form-label-left" action="${ pageContext.request.contextPath}/controller/pages/admin/administrator/participant/batch/inprogress/edit">
					  <input type="hidden" name="id" value="${ dataForm.id}" />
					  
					  <%-- <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">Inprogress <span class="required red">*</span></label>
						<div class="col-md-6 col-sm-6 col-xs-12">
						
						<select name="inprogress" required="required" class="form-control">
							
							<option value="false">FALSE</option>
							<option value="true" <c:if test='${ dataForm.inprogress == true}'>selected="selected"</c:if>>TRUE</option>
						
							
						</select>
							
                        </div>
					 </div> --%>
					 
                       						 <div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">Batch Name <span class="required red">*</span></label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" name="description" required="required" class="form-control col-md-7 col-xs-12" value="${ dataForm.description}"/>
	                        </div>
						 </div>
						 
					 
					 
					 
					 <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">Max Quota <span class="required red">*</span></label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="number" name="maxQuota" required="required" class="form-control col-md-7 col-xs-12" value="${ dataForm.maxQuota}">
                        </div>
					 </div>
					 
					 <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Additional Information
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <textarea class="form-control" rows="5" name="additionalInformation" placeholder="Additional Information">${dataForm.additionalInformation }</textarea>
                        </div>
                      </div>
                      
                      <div class="ln_solid"></div>
                      <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">Location Session I <span class="required red">*</span></label>
						<div class="col-md-6 col-sm-6 col-xs-12">
						
						<select name="location" required="required" class="form-control">
							<option value="Mataram" <c:if test='${ dataForm.location == "Mataram"}'>selected="selected"</c:if>>Mataram</option>
							<option value="Lokasi Masing-masing" <c:if test='${ dataForm.location == "Lokasi Masing-masing"}'>selected="selected"</c:if>>Lokasi Masing-masing</option>
						</select>
							
                        </div>
					 </div>
                      
												 <div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">Session I, Start Time <span class="required red">*</span></label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" name="assessementFirstHalfStartTimeStr" id="assessementFirstHalfStartTimeStr" required="required" class="form-control col-md-7 col-xs-12 datetimepicker" value='<fmt:formatDate  value="${dataForm.assessementFirstHalfStartTime }" pattern="dd-MM-yyyy HH:mm" />'/>
	                        </div>
						 </div>
												 <div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category"> Session I, End Time <span class="required red">*</span></label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" name="assessementFirstHalfEndTimeStr" id="assessementFirstHalfEndTimeStr" required="required" class="form-control col-md-7 col-xs-12 datetimepicker" value='<fmt:formatDate  value="${dataForm.assessementFirstHalfEndTime }" pattern="dd-MM-yyyy HH:mm" />'/>
	                        </div>
						 </div>
						 
						 
						 <div class="ln_solid"></div>
						 <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">Location Sesion II<span class="required red">*</span></label>
						<div class="col-md-6 col-sm-6 col-xs-12">
						
						<select name="locationSecond" required="required" class="form-control">
							<option value="Mataram" <c:if test='${ dataForm.locationSecond == "Mataram"}'>selected="selected"</c:if>>Mataram</option>
							<option value="Lokasi Masing-masing" <c:if test='${ dataForm.locationSecond == "Lokasi Masing-masing"}'>selected="selected"</c:if>>Lokasi Masing-masing</option>
						</select>
							
                        </div>
					 </div>
												 <div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category"> Session II, Start Time <span class="required red">*</span></label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" name="assessementSecondHalfStartTimeStr" id="assessementSecondHalfStartTimeStr" required="required" class="form-control col-md-7 col-xs-12 datetimepicker" value='<fmt:formatDate  value="${dataForm.assessementSecondHalfStartTime }" pattern="dd-MM-yyyy HH:mm" />'/>
	                        </div>
						 </div>
												 <div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category"> Assessment Finish Time <span class="required red">*</span></label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" readonly="readonly" name="assessementFinishTimeStr" id="assessementFinishTimeStr" required="required" class="form-control col-md-7 col-xs-12 datetimepicker" value='<fmt:formatDate  value="${dataForm.assessementFinishTime }" pattern="dd-MM-yyyy HH:mm" />'/>
	                        </div>
						 </div>
						 
						 
						 <div class="ln_solid"></div>
						 
						 <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">Operator <span class="required red">*</span></label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							
							<input type="text" name="operatorUserName" id="operatorUserName" readonly="readonly" required="required" class="form-control col-md-7 col-xs-12"value="${dataForm.userAdmin.fullname}">
							<input type="hidden" name="userId" id="userId" required="required" class="form-control col-md-7 col-xs-12 value="${dataForm.userAdmin.id}"">
							
							<input type="button" onclick="browseUserAdmin()" class="btn btn-success" value="Browse Operator User" />
                        </div>
					 </div>
					
						                     
                      
                      <div class="ln_solid"></div>
                      <div class="form-group">
                        <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                          <a href="${ pageContext.request.contextPath}/controller/pages/admin/administrator/participant/batch/inprogress/view" class="btn btn-primary">Cancel</a>
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
