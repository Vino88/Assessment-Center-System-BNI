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
      
            <div class="page-title">
              <div class="title_left">
                <a class="btn btn-app" href="${ pageContext.request.contextPath}/controller/pages/admin/home" style="margin: 0px 0px 10px 0px;"><i class="fa fa-home"></i>Home</a>
              </div>

              
            </div>
			
            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Batch</h2>
                   
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    
                    <table id="entityTable" class="table table-striped table-bordered">
                      <thead>
                        <tr>
                                                  <th>ID</th>
                                                  <th>Batch Name</th>
                                                  <th>Session I, Start Time</th>
                                                  <th>Session I, End Time</th>
                                                  <th>Session II, Start Time</th>
                                                  <th>Assessment Finish Time</th>
                                                  <th>Action</th>
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
