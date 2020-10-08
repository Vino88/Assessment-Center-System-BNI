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
                    <h2>Edit Writing Assistance</h2>
                    
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <br>
                    
                    <form method="POST" id="demo-form2" data-parsley-validate="" class="form-horizontal form-label-left" action="${ pageContext.request.contextPath}/controller/pages/admin/administrator/competency/competencylibrarywritingassistance/edit">
					  <input type="hidden" name="id" value="${dataForm.id}" />
                       						 <div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">Description <span class="required red">*</span></label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<textarea rows="10" class="resizable_textarea form-control" name="description" placeholder="description">${dataForm.description}</textarea>
	                        </div>
						 </div>
												 <div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">Level <span class="required red">*</span></label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="number" name="level" required="required" class="form-control col-md-7 col-xs-12" value="${ dataForm.level}"/>
	                        </div>
						 </div>
												 <div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">Competency <span class="required red">*</span></label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" readonly="readonly" name="competencyName" id="competencyName"  required="required" class="form-control col-md-7 col-xs-12" value="${ dataForm.competencyLibrary.competencyName}">
								<input type="hidden" name="competencyLibraryId" id="competencyLibraryId"  required="required" class="form-control col-md-7 col-xs-12" value="${ dataForm.competencyLibrary.id}"/>
								<input type="button" onclick="browseCompetency()" class="btn btn-success" value="Browse Competency" />
	                        </div>
						 </div>
						                     
                      
                      <div class="ln_solid"></div>
                      <div class="form-group">
                        <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                          <a href="${ pageContext.request.contextPath}/controller/pages/admin/administrator/competency/competencylibrarywritingassistance/view" class="btn btn-primary">Cancel</a>
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
