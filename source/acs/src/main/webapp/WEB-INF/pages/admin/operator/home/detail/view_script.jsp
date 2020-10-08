<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages"/>

<script>
$(document).ready(function() {
	
	$("#btnStartSessionI").click(function(){
		alertify.confirm('<p>Are you sure you want to start Session I? </p>', function (e) {
		    if (e) {
		    	window.location.href = "${pageContext.request.contextPath}/controller/pages/admin/operator/home/start/1/${dataForm.id}";
		    } else {
		        // user clicked "cancel"
		    }
		});
	})
	
	$("#btnStartSessionII").click(function(){
		alertify.confirm('<p>Are you sure you want to start Session II? </p>', function (e) {
		    if (e) {
		    	window.location.href = "${pageContext.request.contextPath}/controller/pages/admin/operator/home/start/2/${dataForm.id}";
		    } else {
		        // user clicked "cancel"
		    }
		});
	})
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
    		{ "mDataProp": "password", "bSearchable": true, "aTargets": [2] },
    		
    		{ "mDataProp": "participantStatus", "bSearchable": true, "aTargets": [3] },
    		{ "mDataProp": "logDuringStatus", "bSearchable": true, "aTargets": [4] },
    		
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
    
    
    
} );

function doNothing(){}

</script>