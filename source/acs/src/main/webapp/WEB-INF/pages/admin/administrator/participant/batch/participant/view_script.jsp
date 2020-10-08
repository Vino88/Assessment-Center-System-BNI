<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages"/>

<script src="${initParam.staticfiles}/static/js/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
<script src="${initParam.staticfiles}/static/js/jszip/jszip.min.js"></script>
<div id="dialog-confirm" title="Move to other batch" style="display:none">
  <table id="batchTable" class="table table-striped table-bordered" style="width:1180px">
     <thead>
       <tr>
          <th>Description</th>
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
<div style="display:none">
<form id="formMove" action="${pageContext.request.contextPath}/controller/pages/admin/administrator/participant/batch/participant/move" method="POST">
   	<input type="hidden" id="participantId" name="participantId" />
   	<input type="hidden" id="batchIdDestination" name="batchIdDestination" />
   </form>
  </div>
<script>
$(document).ready(function() {
    $('#participantDatatable').DataTable( {
    	dom: 'Bfrtip',
        buttons: [
        	 {
                 extend: 'excel',
                 title: '${dataForm.description}',
                 exportOptions: {
                     columns: [ 0, 1, 2,3,5,6,7,8,9,10,11,12 ]
                 }
             }
        ],
    	"processing": true,
        "serverSide": true,
        "sort": false,
        "ajax": "${pageContext.request.contextPath}/controller/rest/admin/administrator/assessement/participant/listbybatch/${dataForm.id}",
        
        
        "aoColumnDefs": [

    		{ "mDataProp": "nik", "bSearchable": true, "aTargets": [0] },
    		{ "mDataProp": "fullName", "bSearchable": true, "aTargets": [1] },
    		
    		{ "mDataProp": "participantStatus", "bSearchable": true, "aTargets": [2] },
    		{ "mDataProp": "logDuringStatus", "bSearchable": true, "aTargets": [3] },
            { "mData" : function ( source, type, val ) {
            	
            	var assingToAssessor = '';
            	
            	if(source.participantStatus == 'PARTICIPANT_FINISH' || (source.participantStatus == 'SESSION_II' && source.logDuringStatus == 'FINISH') )
           		{
            		assingToAssessor = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"${pageContext.request.contextPath}/controller/pages/admin/administrator/assessement/participantfinish/detail/' + source.id + '\" title="assign to assessor"><i class="fa fa-mail-forward green"></i></a>';
           		}else if(source.participantStatus == 'ASSIGNED_TO_ASSESSOR' || source.participantStatus == 'PARTIAL_RATING')
           		{
            		assingToAssessor = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"${pageContext.request.contextPath}/controller/pages/admin/administrator/assessement/participantfinish/detail/' + source.id + '\" title="assign to assessor"><i class="fa fa-mail-forward" style="color: #f0ad4e"></i></a>';
           		}else if(moment().isAfter( new Date(${dataForm.assessementFinishTime.time })))
           			{
           			assingToAssessor = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"${pageContext.request.contextPath}/controller/pages/admin/administrator/assessement/participantfinish/detail/' + source.id + '\" title="assign to assessor"><i class="fa fa-mail-forward green"></i></a>';
           			}
           	 return '<a href=\"${pageContext.request.contextPath}/controller/pages/admin/administrator/assessement/participant/detail/' + source.id + '\" title="detail"><i class="fa fa-info-circle"></i></a>'
           	+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"${pageContext.request.contextPath}/controller/pages/admin/administrator/participant/batch/participant/personalitytest/${batchId}/' + source.id + '\" title="Personality Test"><i class="fa fa-calculator"></i></a>'
           	 + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:onSendInvitation(' + source.id + ')\" title="send invitation"><i class="fa fa-envelope-square"></i></a>'
           	
           	+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:browseBatch('+source.id+')" title="move to other batch"><i class="fa fa-external-link"></i></a>'
           	+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:onClickDel('+source.id+')" title="delete"><i class="fa fa-remove"></i></a>'
           	+ assingToAssessor;
            }, "bSearchable": true, "aTargets": [4] },
            
            
            { "mData" : function ( source, type, val ) {
            	return "${dataForm.description}";
            }, "bVisible" : false, "bSearchable": true, "aTargets": [5] },
            
            { "mData" : function ( source, type, val ) {
            	return "${ dataForm.location}";
            }, "bVisible" : false, "bSearchable": true, "aTargets": [6] },
            
            { "mData" : function ( source, type, val ) {
            	return "${ dataForm.maxQuota}";
            }, "bVisible" : false, "bSearchable": true, "aTargets": [7] },
            
            { "mData" : function ( source, type, val ) {
            	return "${ dataForm.additionalInformation}";
            }, "bVisible" : false, "bSearchable": true, "aTargets": [8] },
            
            { "mData" : function ( source, type, val ) {
            	return "<fmt:formatDate  value="${dataForm.assessementFirstHalfStartTime }" pattern="dd/MM/yyyy HH:mm" />";
            }, "bVisible" : false, "bSearchable": true, "aTargets": [9] },
            
            { "mData" : function ( source, type, val ) {
            	return "<fmt:formatDate  value="${dataForm.assessementFirstHalfEndTime }" pattern="dd/MM/yyyy HH:mm" />";
            }, "bVisible" : false, "bSearchable": true, "aTargets": [10] },
            
            { "mData" : function ( source, type, val ) {
            	return "<fmt:formatDate  value="${dataForm.assessementSecondHalfStartTime }" pattern="dd/MM/yyyy HH:mm" />";
            }, "bVisible" : false, "bSearchable": true, "aTargets": [11] },
            
            { "mData" : function ( source, type, val ) {
            	return "<fmt:formatDate  value="${dataForm.assessementFinishTime }" pattern="dd/MM/yyyy HH:mm" />";
            }, "bVisible" : false, "bSearchable": true, "aTargets": [12] }
            
            ],
         
       
    } );
    
    
    $('#batchTable').DataTable( { 
        "processing": true,
        "serverSide": true,
        "sort": false,
        "ajax": "${ pageContext.request.contextPath}/controller/rest/admin/administrator/participant/batch/listinprogress",
        
        
        "aoColumnDefs": [
     		{  "mDataProp": "description", "bSearchable": true, "aTargets": [0] },
     		{  "mData" : function ( source, type, val ) { 
               	 return moment(new Date(source.assessementFirstHalfStartTime)).format('DD/MM/YYYY HH:mm');
                }, "bSearchable": true, "aTargets": [1] },
            {  "mData" : function ( source, type, val ) { 
              	 return moment(new Date(source.assessementFirstHalfEndTime)).format('DD/MM/YYYY HH:mm');
               }, "bSearchable": true, "aTargets": [2] },
               
           {  "mData" : function ( source, type, val ) { 
             	 return moment(new Date(source.assessementSecondHalfStartTime)).format('DD/MM/YYYY HH:mm');
              }, "bSearchable": true, "aTargets": [3] },
              
           
              {  "mData" : function ( source, type, val ) { 
                	 return moment(new Date(source.assessementFinishTime)).format('DD/MM/YYYY HH:mm');
                 }, "bSearchable": true, "aTargets": [4] },
            {  "mData" : function ( source, type, val ) { 
            	
            	if(source.id == ${batchId})
            		{
            		return "Current Batch";
            		}else
            			{
            			return '<a href=\"javascript:chooseBatch(' + source.id + ')" >Move To This Batch</a>';
            			}
           	 
            }, "bSearchable": true, "aTargets": [5] }
            ],
         
       
    } );
} );

function doNothing(){}

function onClickDel(id)
{
	
	alertify.confirm('Are you sure you want to delete the data?', 
	function(e){
		if(e)
		{
			window.location.href = "${pageContext.request.contextPath}/controller/pages/admin/administrator/participant/batch/participant/delete/${batchId}/" + id;
		}
	});
}

function onSendInvitation(id)
{
	
	alertify.confirm('Are you sure you want to send the email invitation ?', 
	function(e){
		if(e)
		{
			window.location.href = "${pageContext.request.contextPath}/controller/pages/admin/administrator/participant/batch/participant/sendinvitation/${batchId}/" + id;
		}
	});
}
function chooseBatch(batchIdDestination)
{
	$("#formMove #batchIdDestination").val(batchIdDestination);
	
	alertify.confirm('Are you sure you want to move the participant?', 
			function(e){
				if(e)
				{
					$("#formMove").submit();
				}
			}
    );
}
function browseBatch(participantId)
{
	$("#formMove #participantId").val(participantId);
	$("#batchTable").css("width","1180px");
	$( "#dialog-confirm" ).dialog({
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
</script>