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
           
            
            
            <div >
              
              
              
              
              <div >
                <div class="">
                  <h2>Group Report</h2>
                  <div class="x_content">
                    
                    <table id="batchReport" class="table table-striped table-bordered">
                      <thead>
                        <tr>
                          <th>NPP</th>
                          
                         <c:forEach  items="${competencyLibraries}" var="competencyLibrary" >
                         <th>${competencyLibrary.competencyName }</th>
                         </c:forEach>
                        </tr>
                      </thead>


                      <tbody>
                      <c:set var="partId" value="" />
                      <c:forEach  items="${list}" var="report" varStatus="count" >
                      	<c:if test="${partId ne report.participantId && count.index > 0}">
                      	</tr>
                      	</c:if>
                      	
                      	<c:if test="${partId ne report.participantId }">
                      	<tr>
                      		<td>${report.nik }</td>
                      	</c:if>
                      	<c:set var="partId" value="${report.participantId}" />
                      	
                      	
                      	<td>${report.finalRating }</td>
                      	
                      	
                      	 
                       </c:forEach>
                       
                       <c:if test="${not empty partId }"></tr></c:if>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
              </div>
            
            </div>
</div>

<!-- jQuery -->
    <script src="${initParam.staticfiles}/static/themes/vendors/jquery/dist/jquery.min.js"></script>
    
    
<!-- Datatables -->
    <script src="${initParam.staticfiles}/static/themes/vendors/datatables.net/js/jquery.dataTables.min.js"></script>
    <script src="${initParam.staticfiles}/static/themes/vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
    <script src="${initParam.staticfiles}/static/themes/vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
    <script src="${initParam.staticfiles}/static/themes/vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
    <script src="${initParam.staticfiles}/static/themes/vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
    <script src="${initParam.staticfiles}/static/themes/vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
    <script src="${initParam.staticfiles}/static/themes/vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
    <script src="${initParam.staticfiles}/static/themes/vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
    <script src="${initParam.staticfiles}/static/themes/vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
    <script src="${initParam.staticfiles}/static/themes/vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
    <script src="${initParam.staticfiles}/static/themes/vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
    <script src="${initParam.staticfiles}/static/themes/vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>
    <script src="${initParam.staticfiles}/static/themes/vendors/jszip/dist/jszip.min.js"></script>
    <script src="${initParam.staticfiles}/static/themes/vendors/pdfmake/build/pdfmake.min.js"></script>
    <script src="${initParam.staticfiles}/static/themes/vendors/pdfmake/build/vfs_fonts.js"></script>
    
    <script>
		$(document).ready(
			function() {
				$('#batchReport').DataTable( {
			        dom: 'Bfrtip',
			      
			        search:     false,
			        buttons: [
			            'excel'
			        ]
			    } );
				
		});
	</script>