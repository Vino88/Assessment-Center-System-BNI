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
                    <h2>Edit LCB</h2>
                    
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <br>
                    <form style="display:none" method="POST" id="imageForm" enctype="multipart/form-data" data-parsley-validate="" class="form-horizontal form-label-left" action="${pageContext.request.contextPath}/controller/rest/admin/administrator/image/upload">
                    	<input id="fileTemp" type="file" name="file" />
                    	<input type="submit" />
                    </form>
                    <form method="POST" id="demo-form2" data-parsley-validate="" class="form-horizontal form-label-left" action="${pageContext.request.contextPath}/controller/pages/admin/administrator/lcb/edit">
					  <input type="hidden" name="id" value="${dataForm.id}" />
                      
                      
                      <div class="form-group">
	                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Description</label>
	                    <div class="col-md-9 col-sm-9 col-xs-12">
	                      <textarea class="resizable_textarea form-control" name="question" placeholder="Question..">${dataForm.question}</textarea>
	                    </div>
	                  </div>
	                  
	                 <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="title">Choice A<span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <textarea class="resizable_textarea form-control" name="choiceA" placeholder="choiceA..">${dataForm.choiceA}</textarea>
                        </div>
                      </div>
                      
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="title">Choice B<span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <textarea class="resizable_textarea form-control" name="choiceB" placeholder="choiceB..">${dataForm.choiceB}</textarea>
                        </div>
                      </div>
                      
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="title">Choice C<span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <textarea class="resizable_textarea form-control" name="choiceC" placeholder="choiceC..">${dataForm.choiceC}</textarea>
                        </div>
                      </div>
                      
                      <c:if test="${not empty  dataForm.choiceD}">
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="title">Choice D<span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <textarea class="resizable_textarea form-control" name="choiceD" placeholder="choiceD..">${dataForm.choiceD}</textarea>
                        </div>
                      </div>
                      </c:if>
                      
                      <c:if test="${not empty  dataForm.choiceE}" >
                      <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="title">Choice E<span class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <textarea class="resizable_textarea form-control" name="choiceE" placeholder="choiceE..">${dataForm.choiceE}</textarea>
                        </div>
                      </div>
                      </c:if>
                      
                      <div class="ln_solid"></div>
                      <div class="form-group">
                        <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                          <a href="${pageContext.request.contextPath}/controller/pages/admin/administrator/lcb/view" class="btn btn-primary">Cancel</a>
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
