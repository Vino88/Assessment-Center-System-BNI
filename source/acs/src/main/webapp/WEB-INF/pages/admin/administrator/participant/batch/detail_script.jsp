<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages"/>

<script src="${initParam.staticfiles}/static/js/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
<div id="dialog-confirm" title="Competency Library" style="display:none">
  <table id="batchTable" class="table table-striped table-bordered">
     <thead>
       <tr>
          <th>Description</th>
          <th>Session I, Start Time</th>
          <th>Session I, End Time</th>
          <th>Session II, Start Time</th>
          <th>Assessment Finish Time</th>
          
       </tr>
     </thead>


     <tbody>
       <tr>
         
       </tr>
     </tbody>
   </table>
   
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
                 title: '${dataForm.description}'
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
            	return "${dataForm.description}";
            }, "bVisible" : false, "bSearchable": true, "aTargets": [4] },
            
            { "mData" : function ( source, type, val ) {
            	return "${ dataForm.location}";
            }, "bVisible" : false, "bSearchable": true, "aTargets": [5] },
            
            { "mData" : function ( source, type, val ) {
            	return "${ dataForm.maxQuota}";
            }, "bVisible" : false, "bSearchable": true, "aTargets": [6] },
            
            { "mData" : function ( source, type, val ) {
            	return "${ dataForm.additionalInformation}";
            }, "bVisible" : false, "bSearchable": true, "aTargets": [7] },
            
            { "mData" : function ( source, type, val ) {
            	return "<fmt:formatDate  value="${dataForm.assessementFirstHalfStartTime }" pattern="dd/MM/yyyy HH:mm" />";
            }, "bVisible" : false, "bSearchable": true, "aTargets": [8] },
            
            { "mData" : function ( source, type, val ) {
            	return "<fmt:formatDate  value="${dataForm.assessementFirstHalfEndTime }" pattern="dd/MM/yyyy HH:mm" />";
            }, "bVisible" : false, "bSearchable": true, "aTargets": [9] },
            
            { "mData" : function ( source, type, val ) {
            	return "<fmt:formatDate  value="${dataForm.assessementSecondHalfStartTime }" pattern="dd/MM/yyyy HH:mm" />";
            }, "bVisible" : false, "bSearchable": true, "aTargets": [10] },
            
            { "mData" : function ( source, type, val ) {
            	return "<fmt:formatDate  value="${dataForm.assessementFinishTime }" pattern="dd/MM/yyyy HH:mm" />";
            }, "bVisible" : false, "bSearchable": true, "aTargets": [11] }
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
                 }, "bSearchable": true, "aTargets": [4] }
            ],
         
       
    } );
} );

function doNothing(){}

function onClickDel(id)
{
	var lanjut = confirm("Apakah Anda yakin ingin menghapus peserta?");
	if(lanjut)
	{
		window.location.href = "${pageContext.request.contextPath}/controller/pages/admin/administrator/participant/batch/participant/delete/${batchId}/" + id;
	}
}
function chooseBatch(batchIdDestination)
{
	$("#formMove #batchIdDestination").val(batchIdDestination);
	
	var lanjut = confirm("Apakah Anda yakin ingin memindahkan peserta?");
	if(lanjut)
	{
		$("#formMove").submit();
	}
}
function browseBatch(participantId)
{
	$("#formMove #participantId").val(participantId);
	
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