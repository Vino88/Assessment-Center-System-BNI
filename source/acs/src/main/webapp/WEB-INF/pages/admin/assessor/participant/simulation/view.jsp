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
            <a class="btn btn-app" href="${pageContext.request.contextPath}/controller/pages/admin/assessor/participant/home/view/${participant.id}" style="margin: 0px 0px 10px 0px;"><i class="fa fa-arrow-circle-left"></i>Back</a>
            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Competency for SPM (History)</h2>
                    
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <br>
                    
                    <form method="POST" id="demo-form2" data-parsley-validate="" class="form-horizontal form-label-left">
                       <div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12">Participant ID</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" value="${participant.id}" readonly="readonly" class="form-control col-md-7 col-xs-12">
                        </div>
					 </div>
					 	<div class="ln_solid"></div>
					 	
					 	<c:set var="finishAll" scope="request" value="true" />
					 	<c:forEach items="${listParticipantCompetencyResultDisplayDto}" var="dto">
					 		<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">${dto.competencyLibrary.competencyName} <span class="required red">*</span></label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<a href="${pageContext.request.contextPath}/controller/pages/admin/assessor/participant/simulation/competency/${participant.id}/${dto.competencyLibrary.id}" class="btn btn-success form-control col-md-7 col-xs-12">${dto.competencyLibrary.competencyName}</a>
		                        </div>
							 	</div>
							 	<div class="ln_solid"></div>
					 	</c:forEach>
                        
                      <div class="form-group">
                        <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
							<a href="${pageContext.request.contextPath}/controller/pages/admin/assessor/participant/home/view/${participant.id}" class="btn btn-primary form-control col-md-7 col-xs-12">Back</a>
                        </div>
                      </div>

                    </form>
                  </div>
                </div>
              </div>
            </div>
            
            </div>
</div>
