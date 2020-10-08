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
            <a href="javascript:window.history.back();" class="btn btn-app" style="margin: 0px 0px 10px 0px;"><i class="fa fa-arrow-circle-left"></i>Back</a>
           
            
            
            <div class="row">
              
              
              
              
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Aggregate Report</h2>
                    
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    
                    <table id="batchReport" class="table table-striped table-bordered">
                      <thead>
                        <tr>
                          <th>Competency</th>
                          <th style="color:red">Below</th>
                          <th style="font-weight: bolder;">Meet</th>
                          <th style="color:green">Above</th>
                          
                          <th style="color:red">Below (%)</th>
                          <th style="font-weight: bolder;">Meet (%)</th>
                          <th style="color:green">Above (%)</th>
                        </tr>
                      </thead>


                      <tbody>
                      <c:set var="catg" value="" />
                      <c:forEach  items="${list}" var="report" >
                      	<c:if test="${catg ne report.competencyLibrary.category }">
                      	<tr>
                      		<td colspan="7" style="background-color:orange;color:black;font-weight: bolder;">
                      			${report.competencyLibrary.category }
                      		</td>
                      	</tr>
                      	</c:if>
                        <tr>
                          <td>
                          	${report.competencyLibrary.competencyName}
                          </td>
                          
                          <td style="color:red">
                          	${report.below}
                          </td>
                          
                          <td style="font-weight: bolder;">
                          	${report.meet}
                          </td>
                          
                          <td style="color:green">
                          	${report.above}
                          </td>
                          
                          <td style="color:red">
                          	<fmt:formatNumber pattern="###.##"  value="${report.belowPercentage}"  /> %
                          </td>
                          
                          <td style="font-weight: bolder;">
                          	<fmt:formatNumber pattern="###.##"  value="${report.meetPercentage}"  /> %
                          </td>
                          
                          <td style="color:green">
                          	<fmt:formatNumber pattern="###.##"  value="${report.abovePercentage}"  /> %
                          </td>
                          
                        </tr>
                         <c:set var="catg" value="${report.competencyLibrary.category }" />
                       </c:forEach>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
              </div>
            
            </div>
</div>
