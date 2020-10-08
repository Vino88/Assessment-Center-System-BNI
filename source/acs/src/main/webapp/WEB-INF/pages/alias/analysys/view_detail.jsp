<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages"/>
<style>
.centre {
    margin-left: auto;
    margin-right: auto;
    display: block
}
</style>
<div class="main_container" role="main" >
	<div class="right_col">
      	<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
           		
           		
           		<div class="x_panel">
           		
           		
                  
                  <form  method="POST" action="${pageContext.request.contextPath}/controller/pages/alias/analysys/detail" class="form-horizontal row-fluid">
                  
                      	<input type="hidden" value="${analysys.id}" name="analysysId" />
               			<input type="hidden" value="${participantAnalysys.id}" name="participantAnalysysId" />
                      
                      <div class="x_panel">
	               		<div class="x_title">
	               			<ul class="nav navbar-right panel_toolbox">
		                      <li><input type="button" class="btn btn-success" style="color: #fff;" onclick="changeFrame()" value="Latar Belakang Organisasi"></li>
		                      <li><input type="button" class="btn btn-success" style="color: #fff;" onclick="hideFrame()" value="Hide"></li>
		                    </ul>	
		                     <div class="clearfix"></div>
	               		</div>
	               		
	               		<div class="x_content">
	              			<iframe style="display:none" id="frameSrb" frameborder="0" src="${initParam.staticfiles}/static/articulate/gsi/20170330/story_html5.html" width="100%" height="600px"></iframe>
	              			
	              			</div>
	                  	<div class="x_content">
	                    	<p class="font-gray-dark"> 
	                    		${analysys.question}
	                    	</p>
	                    	
	                    	<textarea class="form-control col-md-7 col-xs-12 resizable_textarea"  name="answer">${participantAnalysys.answer}</textarea>
           		 	
	                    	
	                    	<div class="ln_solid"></div>
	                    </div>
	              	</div>
                     
                  
                  <div class="x_panel">
	              		<div class="x_title">
	              		</div>
	              		<div class="x_content">
	              			<div class="form-group">
	                        	<div class="col-md-6 col-sm-6 col-xs-12">
		                          
		                          <c:choose>
	                        	<c:when test="${prevId == 0 }">
	                        	<a class="btn btn-primary" href="${pageContext.request.contextPath}/controller/pages/alias/analysys"><fmt:message key="btn.prev"></fmt:message></a>
	                        	</c:when>
	                        	
	                        	<c:otherwise>
	                        	<a class="btn btn-primary" href="${pageContext.request.contextPath}/controller/pages/alias/analysys/detail/${prevId}"><fmt:message key="btn.prev"></fmt:message></a>
	                        	</c:otherwise>
	                        	
	                        	</c:choose>
					           	
	                        		<input type="hidden" value="${nextId}" name="nextId" />
	                        	
		                          	<button type="submit" class="btn btn-success"><fmt:message key="btn.next"></fmt:message></button>
	                        	</div>
	                      	</div>
	              		</div>
	              	</div>
	              	
	              	</form>
	              	</div>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
</div>
