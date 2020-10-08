<div id="dialog-confirm" title="User Admin" style="display:none">
  <table id="userAdminTable" class="table table-striped table-bordered" style="width:1180px">
     <thead>
       <tr>
         <th>Username</th>
         <th>Full Name</th>
         <th>E-mail</th>
         <th>Choose</th>
       </tr>
     </thead>


     <tbody>
       <tr>
         
       </tr>
     </tbody>
   </table>
</div>

<div id="">

</div>

<script src="${initParam.staticfiles}/static/js/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
<script src="${initParam.staticfiles}/static/js/jszip/jszip.min.js"></script>
<script>
var ASSESSOR_IDS = [];
var ASSESSOR_NAMES = [];
var ASSESSOR_DETAIL_DATA_TABLE = null;
function choose(id, name)
{
	if($("#assessor_"+id).is(':checked'))
		{
			ASSESSOR_IDS.push(id);
			ASSESSOR_NAMES.push(name);
		}
	else
		{
		var index = ASSESSOR_IDS.indexOf(id);
		ASSESSOR_IDS.splice(index, 1);
		ASSESSOR_NAMES.splice(index, 1);
		
		}
	
	$("#btnBrowseAssessor").text(ASSESSOR_NAMES.toString().replace(/,/g, '\n'));

}

$(document).ready(function() { 
    $("#btnBrowseAssessor").click(function(e){
    	$("#userAdminTable").css("width","1180px");
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
    });
    
 	$("#btnSearchAssessor").click(function(e){
    	var assessorIds = ASSESSOR_IDS.toString();
    	var dateFrom = $("#dateFrom").val();
    	var dateTo = $("#dateTo").val();
    	
    	var assessDone = $("#assessDone").val();
    	
    	var startTime = "";
    	var endTime = "";
    	
    	var startMoment = moment(dateFrom, "DD-MM-YYYY HH:mm");
    		var endMoment = moment(dateTo, "DD-MM-YYYY HH:mm");
    		
    		if(dateFrom != "" && dateTo == "")
    			{
    			alertify.alert("You have to choose 'To' Date (because you have choose 'From' Date)");
    			}
    		else if(dateFrom == "" && dateTo != "")
    			{
    			alertify.alert("You have to choose 'From' Date (because you have choose 'To' Date)");
    			}
    		else if(startMoment.isAfter(endMoment))
    		{
    			alertify.alert("'From' Date must be after 'To' Date");
    			return;
    		}
    		else
    		{
    			
    			if(dateFrom != null && dateFrom != "" && dateFrom.length > 0)
        		{
        		startTime = startMoment.format("DDMMYYYYHHmm");
        		}
        	
	        	if(dateTo != null && dateTo != "" && dateTo.length > 0)
	    		{
	        		endTime = endMoment.format("DDMMYYYYHHmm");
	    		}
	        	
	        	
	        	ASSESSOR_DETAIL_DATA_TABLE.ajax.url("${ pageContext.request.contextPath}/controller/rest/admin/administrator/assessement/assessor/list?assessDone="+assessDone+"&startTime="+startTime+"&endTime="+endTime+"&assessorIds="+assessorIds);
	        	ASSESSOR_DETAIL_DATA_TABLE.ajax.reload();
    		}
    	
    });
 	
 	$("#btnClearAssessor").click(function(e){
 		$("#dateFrom").val("");
 		$("#dateTo").val("");
 		
 		$("#assessDone option").removeAttr("selected");
 		$("#assessDone option[value=2]").attr("selected", "selected");
 		ASSESSOR_IDS = [];
 		ASSESSOR_NAMES=[];
 		$("#userAdminTable :checkbox").removeAttr("checked");
 		$("#btnBrowseAssessor").text("");
 		ASSESSOR_DETAIL_DATA_TABLE.ajax.url("${ pageContext.request.contextPath}/controller/rest/admin/administrator/assessement/assessor/list?assessDone=2&startTime=&endTime=&assessorIds=");
 		ASSESSOR_DETAIL_DATA_TABLE.ajax.reload();
 	})
 	$('#userAdminTable').DataTable( {
        "processing": true,
        "serverSide": true,
        "sort": false,
		"ajax": "${ pageContext.request.contextPath}/controller/rest/admin/administrator/credential/useradmin/listassessor",
        
        
        "aoColumnDefs": [
     		{  "mDataProp": "username", "bSearchable": true, "aTargets": [0] },
     		{  "mDataProp": "fullname", "bSearchable": true, "aTargets": [1] },
     		{  "mDataProp": "email", "bSearchable": true, "aTargets": [2] },
            { "mData" : function ( source, type, val ) {
            	var index = ASSESSOR_IDS.indexOf(source.id);
            	if(index > -1)
            		{
            		return '<input type="checkbox" checked="checked" id="assessor_'+source.id+'" onchange="choose('+source.id+','+'\''+source.username +'\')" />';
            		}
            	else
            		{
            		return '<input type="checkbox" id="assessor_'+source.id+'" onchange="choose('+source.id+','+'\''+source.username +'\')" />';
            		}
            	
            }, "bSearchable": true, "aTargets": [3] }
            ],
         
       
    } );
 	ASSESSOR_DETAIL_DATA_TABLE = $('#entityTable').DataTable( { 
 		dom: 'Blrtip',
        buttons: [
        	 {
                 extend: 'excel',
                 title: 'Assessor Report'
             }
        ],
        "lengthMenu": [[10, 25, 50, 100, 250, 500, 1000], [10, 25, 50, 100, 250, 500, 1000]],
 	
 		"processing": true,
        "serverSide": true,
        "sort": false,
        "searching": false, 
        "ajax": "${ pageContext.request.contextPath}/controller/rest/admin/administrator/assessement/assessor/list?assessDone=2&startTime=&endTime=&assessorIds=",
        
        
        "aoColumnDefs": [
     		{  "mDataProp": "assessorName", "bSearchable": true, "aTargets": [0] },
     		{  "mDataProp": "batchName", "bSearchable": true, "aTargets": [1] },
     		{  "mDataProp": "participantName", "bSearchable": true, "aTargets": [2] },
     		{  "mData" : function ( source, type, val ) { 
              	 return moment(new Date(source.assessementFirstHalfStartTime)).format('DD/MM/YYYY HH:mm');
               }, "bSearchable": true, "aTargets": [3] },
               
               {  "mData" : function ( source, type, val ) { 
                	 return moment(new Date(source.assessementFirstHalfEndTime)).format('DD/MM/YYYY HH:mm');
                 }, "bSearchable": true, "aTargets": [4] },
                 
                 {  "mData" : function ( source, type, val ) { 
                  	 return moment(new Date(source.assessementSecondHalfStartTime)).format('DD/MM/YYYY HH:mm');
                   }, "bSearchable": true, "aTargets": [5] },
               
            {  "mData" : function ( source, type, val ) { 
             	 return moment(new Date(source.assessementFinishTime)).format('DD/MM/YYYY HH:mm');
              }, "bSearchable": true, "aTargets": [6] },
               
     		
     		{  "mDataProp": "testTools", "bSearchable": true, "aTargets": [7] },
     		 {  "mData" : function ( source, type, val ) { 
             	 if(source.assessDone != null && source.assessDone == 1)
             		 {
             		  return "DONE";
             		 }
             	 else
             		 {
             		 return "";
             		 }
              }, "bSearchable": true, "aTargets": [8] }
     		
            ],
         
       
    } );
} );

</script>