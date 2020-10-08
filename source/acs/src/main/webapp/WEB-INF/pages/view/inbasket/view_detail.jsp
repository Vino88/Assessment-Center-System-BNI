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

.popup-link{
	color: red;
}

.x_panel_backgroundinfo{
    position: relative;
    width: 100%;
    margin-bottom: 10px;
    padding: 0px 5px 0px 6px;
    display: inline-block;
    background: #fff;
    -webkit-column-break-inside: avoid;
    -moz-column-break-inside: avoid;
    column-break-inside: avoid;
    opacity: 1;
    transition: all .2s ease;
}
</style>
<c:set value="http://10.212.101.185/acsstatic" var="staticfiles2" scope="request"></c:set>
<div class="main_container" role="main" >
	<div class="right_col">
   		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
           		<div class="x_panel">
           			<div class="x_title" id="x_title_main">
           				<h2>${inbasketInbox.title }<span id="timer" style="display:none"></span></h2>
           				<div class="clearfix"></div>
           			</div>
           			
                  	<form method="POST" action="${pageContext.request.contextPath}/controller/pages/view/inbasket/detail" class="form-horizontal row-fluid">
                  		<c:forEach items="${listParticipantIiiq}" var="participantIiiq" varStatus="count">
                  			<input type="hidden" value="${inbasketInbox.id }" name="inbasketInboxId" />
                      		<input type="hidden" value="${participantIiiq.inbasketInboxInbasketQuestion.id}" name="listParticipantInbasketDto[${count.index}].iiqId" />
                      		<input type="hidden" value="${participantIiiq.id}" name="listParticipantInbasketDto[${count.index}].participantIiqId" />
                       		
                       		<div id="framepanel${count.index}" class="x_panel_backgroundinfo" <c:if test="${count.index > 0}">style="display:none"</c:if>>
	              				<div class="x_content">
			              			<iframe style="display:none" id="frameLatar${count.index}" frameborder="0" src="${staticfiles}/static/articulate/articulate3/20170309/story.html" width="100%" height="550px"></iframe>
			              			<iframe style="display:none" id="frameMemo${count.index}" frameborder="0" src="${staticfiles}/static/memo/articulate/20170329/noflag/story.html" width="100%" height="550px"></iframe>
			              			<iframe style="display:none" id="frameSrb${count.index}" frameborder="0" src="${staticfiles}/static/articulate/gsi/20170330/story.html" width="100%" height="600px"></iframe>
			              		</div>
		              		</div>
                      
                      		<div class="x_panel">
	               				<div class="x_title">
	               					<h2>${inbasketInbox.title }</h2>
			                    	<ul class="nav navbar-right panel_toolbox">
			                      		<li><input type="button" class="btn btn-success" style="color: #fff;" onclick="changeFrame(${count.index}, 'peran')" value="Peran Anda"></li>
			                      		<li><input type="button" class="btn btn-success" style="color: #fff;" onclick="changeFrame(${count.index}, 'email')" value="Email"></li>
			                      		<li><input type="button" class="btn btn-success" style="color: #fff;" onclick="changeFrame(${count.index}, 'latar')" value="Latar Belakang Organisasi"></li>
			                      		<li><input type="button" class="btn btn-success" style="color: #fff;" onclick="hideFrame(${count.index})" value="Hide"></li>
			                    	</ul>
			                    	<div class="clearfix"></div>
			                  	</div>
		                  
		                  		<div class="x_content"></div>
                  
		                  		<div class="x_content">
			                    	<p class="font-gray-dark"> 
			                    		${participantIiiq.inbasketInboxInbasketQuestion.inbasketQuestion.question}
			                    	</p>
		                    		<textarea class="form-control col-md-7 col-xs-12 resizable_textarea" id="txtInbasket${count.index}" name="listParticipantInbasketDto[${count.index}].answer">${participantIiiq.answer}</textarea>
		                    		<div class="ln_solid"></div>
		                    	</div>
	              			</div>
                      	</c:forEach>
                  
                  		<div class="x_panel">
	              			<div class="x_title"></div>
	              			<div class="x_content">
		              			<div class="form-group">
		                        	<div class="col-md-6 col-sm-6 col-xs-12">
		                        	<c:choose>
		                        	<c:when test="${prevId == 0 }">
		                        	<a class="btn btn-primary" href="${pageContext.request.contextPath}/controller/pages/view/inbasket/background"><fmt:message key="btn.prev"></fmt:message></a>
		                        	</c:when>
		                        	
		                        	<c:otherwise>
		                        	<a class="btn btn-primary" href="${pageContext.request.contextPath}/controller/pages/view/inbasket/detail/${prevId}"><fmt:message key="btn.prev"></fmt:message></a>
		                        	
		                        	
		                        	</c:otherwise>
		                        	
		                        	</c:choose>
						           		
			                          	<button type="submit" class="btn btn-success"><fmt:message key="btn.next"></fmt:message></button>
		                        	</div>
		                      	</div>
	              			</div>
	              		</div>
	              	</form>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
</div>