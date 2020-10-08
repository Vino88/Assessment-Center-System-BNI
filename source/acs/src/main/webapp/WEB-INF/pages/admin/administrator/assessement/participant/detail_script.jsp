<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages"/>

	



<div id="dialogCompetency" title="Competency" style="display:none">
  	<form method="POST" id="reportFormDownload" data-parsley-validate="" class="form-horizontal form-label-left" action="${pageContext.request.contextPath}/controller/pages/admin/administrator/assessement/participant/individualreport/downloaddata/${participant.id}">
  	<table class="table table-striped table-bordered" >
  	
  		<tr>
  			<th>No</th>
  			<th>Competency Name</th>	
  			<th>Competency Name (Text)</th>
  			<th>Displayed</th>
  		</tr>


		<c:set var="clCategory" value="" scope="request"/>  		
  		<c:forEach items="${listCompetency}" var="competency" varStatus="status">
  			<c:choose>
				<c:when test="${clCategory ne competency.category && competency.category ne 'CORE'}">
				<tr>
				<td colspan="8" style="background-color:#f0ad4e; color:black">
				${competency.category}
				</td>
				
				</tr>
				<c:set var="clCategory" value="${competency.category}" scope="request"/>
				
				</c:when>
				<c:otherwise>
				
				</c:otherwise>
			</c:choose>
	  		<tr>
	  			<td>
	  				${status.count}
	  			</td>
	  			
	  			<td>
	  				${competency.competencyName}
	  			</td>
	  			
	  			<td>
	  				<input type="text" name="individuReportDto[${status.index}].competencyName" style="width:250px" value="${competency.competencyName}" />
	  			</td>
	  			
	  			<td>
	  				<input type="hidden" name="individuReportDto[${status.index}].competencyId" value="${competency.id}"/>
	  				<input type="checkbox" name="individuReportDto[${status.index}].selectedForReport" checked="checked" />
	  			</td>
	  		</tr>
  		</c:forEach>
  	</table>
  	
  	<div class="form-group">
       <div class="col-md-6 col-sm-6 col-xs-12">
         <a href="javascript:generateCustom();" class="btn btn-primary" style="color:#fff;">Generate</a>
       </div>
     </div>
     
     </form>
</div>
<script src="${initParam.staticfiles}/static/js/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
<script>
var REPORT_TYPE="";

function generateCustom(){	
	$( "#dialogCompetency" ).dialog("close");
	if(REPORT_TYPE == "PDF")
		{
		alertify.prompt("Password", function (e, str) {
		    // str is the input text
		    if (e) {
		    	
		    	$.ajax({
		  		  type: "POST",
		  		  cache: false,
		  		  async : false,
		  		  url: "${pageContext.request.contextPath}/controller/rest/admin/administrator/assessement/participant/individualreport/downloaddata/${participant.id}",
		  		  data: $("#reportFormDownload").serializeArray(),
		  		  success: function(data)
		  		  {
		  			  var res = encodeURI('${pageContext.request.contextPath}/controller/pages/admin/administrator/assessement/participant/individualreport/download/${participant.id}?password='+str);
		  		      window.open(res,'_blank');
		  		  },
		  		
		  		});
		    } else {
		        // user clicked "cancel"
		    }
		}, '<fmt:formatDate value="${employee.dateOfBirth }" pattern="ddMMyyyy"/>');
		}
	else if(REPORT_TYPE == "DOCX")
	{
		alertify.confirm("Are you sure you want to generate report ?", function (e) {
		    // str is the input text
		    if (e) {
		    	$.ajax({
			  		  type: "POST",
			  		  cache: false,
			  		  async : false,
			  		  url: "${pageContext.request.contextPath}/controller/rest/admin/administrator/assessement/participant/individualreport/downloaddata/${participant.id}",
			  		  data: $("#reportFormDownload").serializeArray(),
			  		  success: function(data)
			  		  {
			  			var res = encodeURI('${pageContext.request.contextPath}/controller/pages/admin/administrator/assessement/participant/individualreport/downloadodt/${participant.id}');
				    	window.open(res,'_blank');
			  		  },
			  		
			  		});
		    	
		    } else {
		        // user clicked "cancel"
		    }
		});
	}
	else if(REPORT_TYPE == "EMAIL")
	{
		alertify.prompt("Email", function (e, str) {
		    // str is the input text
		    if (e) {
		    	$("#email").val(str);
		    	$.ajax({
			  		  type: "POST",
			  		  cache: false,
			  		  async : false,
			  		  url: "${pageContext.request.contextPath}/controller/rest/admin/administrator/assessement/participant/individualreport/downloaddata/${participant.id}",
			  		  data: $("#reportFormDownload").serializeArray(),
			  		  success: function(data)
			  		  {
			  			var res = encodeURI('${pageContext.request.contextPath}/controller/pages/admin/administrator/assessement/participant/individualreport/email/${participant.id}?email='+str);
				    	window.location.href = res;
			  		  },
			  		
			  		});
		    	
		    } else {
		        // user clicked "cancel"
		    }
		}, "email@mail.com");
	}
	
	
	
	
}

function displayModal()
{
	$( "#dialogCompetency" ).dialog({
	      resizable: false,
	      height: "auto",
	      width: 1200,
	      modal: true,
	      buttons: {
	        "Close": function() {
	          $( this ).dialog( "close" );
	          
	        }
	      }
	    });
	
	
}

$(document).ready(function() { 
	
	
	$("#generateReportBtnNew").click(function(e){
		REPORT_TYPE = "PDF";
		displayModal();
	});
	
	$("#generateReportOdtBtn").click(function(e){
		REPORT_TYPE = "DOCX";
		displayModal();
		
		
		
	});
	
	$("#emaiLReportBtn").click(function(e){
		REPORT_TYPE = "EMAIL";
		displayModal();
		
	});
	
	$("#btnModifyLog").click(function(e){
		var pkEndTime = $("#pkEndTime").val();
		var lcbEndTime = $("#lcbEndTime").val();
		var inbasketEndTime = $("#inbasketEndTime").val();
		var analysysEndTime = $("#analysysEndTime").val();
		
		
		var inbasketReadMemoEndTime = $("#inbasketReadMemoEndTime").val();
		var visionEndTime = $("#visionEndTime").val();
		var aspirationEndTime = $("#aspirationEndTime").val();
		var logDuringStatus = $("#logDuringStatus").val();
		var participantStatus = $("#participantStatus").val();
		
		
		
		$.ajax({
			  type: "POST",
			  cache: false,
			  async : false,
			  url: "${pageContext.request.contextPath}/controller/rest/admin/administrator/assessement/participant/modifylog/${participant.id}",
			  data : {
				  pkEndTime : pkEndTime,
				  lcbEndTime : lcbEndTime,
				  inbasketEndTime : inbasketEndTime,
				  analysysEndTime : analysysEndTime,
				  inbasketReadMemoEndTime : inbasketReadMemoEndTime,
				  visionEndTime : visionEndTime,
				  logDuringStatus : logDuringStatus,
				  aspirationEndTime : aspirationEndTime,
				  participantStatus : participantStatus
			  },
			  dataType: "json",
			  success: function(data, textStatus)
			  {
				  if(data == true)
					  {
					  alertify.success("Saved successfully");
					  }
				  else
					  {
					  alertify.alert("Error");
					  }
				  
			  },
			
			});
		
	});
	
});

</script>