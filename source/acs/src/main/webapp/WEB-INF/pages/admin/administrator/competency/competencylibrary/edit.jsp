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
                    <h2>Edit Competency Library</h2>
                    
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <br>
                    
                    <form method="POST" id="demo-form2" data-parsley-validate="" class="form-horizontal form-label-left" action="${pageContext.request.contextPath}/controller/pages/admin/administrator/competency/competencylibrary/edit">
					  <input type="hidden" name="id" value="${dataForm.id}" />
                      
                      <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="category">Category<span class="required">*</span></label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" name="category" required="required" class="form-control col-md-7 col-xs-12" value="${dataForm.category }"/>
                        </div>
					 </div>
					 
                      <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="competencyName">Competency Name <span class="required">*</span></label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" name="competencyName" required="required" class="form-control col-md-7 col-xs-12" value="${dataForm.competencyName }"/>
                        </div>
					 </div>
					 <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="competencyNameBahasa">Competency Name Bahasa</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" name="competencyNameBahasa" class="form-control col-md-7 col-xs-12" value="${dataForm.competencyNameBahasa }" />
                        </div>
					 </div>
					 
					 <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="title">Description
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <textarea rows="15" class="form-control" name="competencyDescription" placeholder="">${dataForm.competencyDescription }</textarea>
                        </div>
                      </div>
                     
                      <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12" for="competencyNameBahasa">Display Order</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="number" name="displayOrder" class="form-control col-md-7 col-xs-12" value="${dataForm.displayOrder }">
                        </div>
					 </div>
					 
                      <div class="ln_solid"></div>
                      <div class="form-group">
                        <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                          <a href="${pageContext.request.contextPath}/controller/pages/admin/administrator/competency/competencylibrary/view" class="btn btn-primary">Cancel</a>
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
