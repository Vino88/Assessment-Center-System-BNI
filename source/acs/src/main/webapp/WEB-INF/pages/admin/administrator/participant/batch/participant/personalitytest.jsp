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
<form method="POST" id="demo-form2" data-parsley-validate="" class="form-horizontal form-label-left" action="${pageContext.request.contextPath}/controller/pages/admin/administrator/participant/batch/participant/personalitytest/${batchId}/${id}">		
		
            <div class="row">
			<div>
				<div class="x_panel">
	              		<div class="x_title">
	              			<h2>Personality Test</h2>
                 			<div class="clearfix"></div>
	              		</div>
	              		<div class="x_content">
	              		
	              			<table id="entityTable" class="table table-striped table-bordered">
		                    	<thead>
		                       		<tr>
		                       			<th>No.</th>
			                        	<th>Competency Library</th>
	                                    
	                                    <th>Personality Test</th>
		                        	</tr>
		                      	</thead>
		                      	<tbody>
		                       		<c:set var="igIndex" value="0" scope="request"/>

<c:set var="clCategory" value="" scope="request"/>

		                      		<c:forEach items="${competencyLibraries }" var="competencyLibrary" varStatus="status">
		                      			
<c:choose>


<c:when test="${clCategory ne competencyLibrary.category && competencyLibrary.category ne 'CORE'}">
<tr>
<td colspan="8" style="background-color:#f0ad4e; color:black">
${competencyLibrary.category}
</td>

</tr>
<c:set var="clCategory" value="${competencyLibrary.category}" scope="request"/>

</c:when>

<c:otherwise>

</c:otherwise>
</c:choose>
									<input type="hidden" name="personalityTestList[${igIndex}].competencyId" value="${competencyLibrary.id}" />
				                      	<tr align="center" <c:if test="${competencyLibrary.category eq 'CORE'}">style="background-color:#5bc0de; color:black"</c:if>>
				                      		<td>${status.count }</td>
				                      		<td align="left" >
				                      		
				                      			${competencyLibrary.competencyName}
				                      		</td>
				                          	
				                          	<td>
				                          	<c:choose>
				                          	<c:when test="${empty dataForm || empty dataForm[igIndex].otherRating}">
				                          	
				                          	<select name="personalityTestList[${igIndex}].otherRating" required="required">
			                          				<option value="">N/A</option>
			                          				<option value="1">1</option>
			                          				<option value="2">2</option>
			                          				<option value="3">3</option>
			                          				<option value="4">4</option>
			                          				<option value="5">5</option>
			                          			</select>
				                          	
				                          	</c:when>
				                          	
				                          	<c:otherwise>
				                          	<select name="personalityTestList[${igIndex}].otherRating" required="required">
			                          				<option value="1" <c:if test="${dataForm[igIndex].otherRating == 1}">selected="selected"</c:if>>1</option>
			                          				<option value="2" <c:if test="${dataForm[igIndex].otherRating == 2}">selected="selected"</c:if>>2</option>
			                          				<option value="3" <c:if test="${dataForm[igIndex].otherRating == 3}">selected="selected"</c:if>>3</option>
			                          				<option value="4" <c:if test="${dataForm[igIndex].otherRating == 4}">selected="selected"</c:if>>4</option>
			                          				<option value="5" <c:if test="${dataForm[igIndex].otherRating == 5}">selected="selected"</c:if>>5</option>
			                          			</select>
				                          	</c:otherwise>
				                          		
				                          	</c:choose>
			                          			
				                          	</td>
		                        		</tr>
		                        		<c:set var="igIndex" value="${igIndex +1 }" scope="request"/>
		                      		</c:forEach>
		                      	</tbody>
		                    </table>
		                    
		                    <div class="form-group">
                        <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                          <a href="${ pageContext.request.contextPath}/controller/pages/admin/administrator/participant/batch/participant/view/${batchId}" class="btn btn-primary">Cancel</a>
                          <button type="submit" class="btn btn-success">Submit</button>
                        </div>
                      </div>
	              		</div>
	              	</div>
			</div>
		</div>
            </form>
            </div>
</div>
