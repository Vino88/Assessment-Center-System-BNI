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

<script src="${initParam.staticfiles}/static/js/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>

<script>
$(document).ready(function() {
    $('#userAdminTable').DataTable( {
        "processing": true,
        "serverSide": true,
        "sort": false,
		"ajax": "${ pageContext.request.contextPath}/controller/rest/admin/administrator/credential/useradmin/listoperator",
        
        
        "aoColumnDefs": [
     		{  "mDataProp": "username", "bSearchable": true, "aTargets": [0] },
     		{  "mDataProp": "fullname", "bSearchable": true, "aTargets": [1] },
     		{  "mDataProp": "email", "bSearchable": true, "aTargets": [2] },
            { "mData" : function ( source, type, val ) {
           	 return '<a href=\"javascript:choose(' + source.id + ', \''+ source.fullname+'\')\" >Choose</a>';
            }, "bSearchable": true, "aTargets": [3] }
            ],
         
       
    } );
} );

function browseUserAdmin()
{
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
}
function choose(id, name)
{
	$("#userId").val(id);	
	$("#operatorUserName").val(name);
	$("#dialog-confirm" ).dialog( "close" );
}
</script>

<script>

$("#assessementSecondHalfStartTimeStr").change(function(){
	var assessementSecondHalfStartTimeStr = $("#assessementSecondHalfStartTimeStr").val();
	console.log('assessementFirstHalfEndTimeStr'+assessementFirstHalfEndTimeStr);
	
	var assessementSecondHalfStartTime = moment(assessementSecondHalfStartTimeStr,'DD-MM-YYYY HH:mm');
	
	var simulation2Time = ${timermodule.TIMER_SIMULATION_II};
	assessementSecondHalfStartTime.add(simulation2Time,'minutes');
	
	console.log('simulation2Time ' + simulation2Time);
	console.log('assessementSecondHalfStartTimeStr new'+assessementSecondHalfStartTime);
	$("#assessementFinishTimeStr").val(assessementSecondHalfStartTime.format('DD-MM-YYYY HH:mm'));
})
$("#entityForm").submit(function( event ) {
	  var assessementFirstHalfStartTimeStr = $("#assessementFirstHalfStartTimeStr").val();
	  var assessementSecondHalfStartTimeStr = $("#assessementSecondHalfStartTimeStr").val();
	  var assessementFirstHalfEndTimeStr = $("#assessementFirstHalfEndTimeStr").val();
	  var assessementFinishTimeStr = $("#assessementFinishTimeStr").val();
	  
	  console.log('assessementFirstHalfStartTimeStr'+assessementFirstHalfStartTimeStr);
	  console.log('assessementSecondHalfStartTimeStr'+assessementSecondHalfStartTimeStr);
	  console.log('assessementFirstHalfEndTimeStr'+assessementFirstHalfEndTimeStr);
	  console.log('assessementFinishTimeStr'+assessementFinishTimeStr);
	  
	  var assessementFirstHalfStartTime = moment(assessementFirstHalfStartTimeStr,'DD-MM-YYYY HH:mm');
	  var assessementFirstHalfEndTime = moment(assessementFirstHalfEndTimeStr,'DD-MM-YYYY HH:mm');
	  var assessementSecondHalfStartTime = moment(assessementSecondHalfStartTimeStr,'DD-MM-YYYY HH:mm');
	  var assessementFinishTime = moment(assessementFinishTimeStr,'DD-MM-YYYY HH:mm');
	  
	  if(moment().isAfter(assessementFirstHalfStartTime))
	  {
		  alertify.alert("First Half Start Time must be greater than current time " + moment().format('DD-MM-YYYY HH:mm'));
		  event.preventDefault();
		  return;
	  }
	  if(moment().isAfter(assessementFirstHalfEndTime))
	  {
		  alertify.alert("First Half End Time must be greater than current time " + moment().format('DD-MM-YYYY HH:mm'));
		  event.preventDefault();
		  return;
	  }
	  if(moment().isAfter(assessementSecondHalfStartTime))
	  {
		  alertify.alert("Second Half Start Time must be greater than current time " + moment().format('DD-MM-YYYY HH:mm'));
		  event.preventDefault();
		  return;
	  }
	  if(moment().isAfter(assessementFinishTime))
	  {
		  alertify.alert("Assessment Finish Time must be greater than current time " + moment().format('DD-MM-YYYY HH:mm'));
		  event.preventDefault();
		  return;
	  }
	  
	  if(assessementFirstHalfStartTime.isAfter(assessementFirstHalfEndTime)){
		  alertify.alert("First Half Start Time must be before First Half End Time");
		  event.preventDefault();
		  return;
	  }
	  
	  if(assessementFirstHalfStartTime.isAfter(assessementSecondHalfStartTime)){
		  alertify.alert("First Half Start Time must be before Second Half Start Time");
		  event.preventDefault();
		  return;
	  }
	  
	  if(assessementFirstHalfStartTime.isAfter(assessementFinishTime)){
		  alertify.alert("First Half Start Time must be before Assessment Finish Time");
		  event.preventDefault();
		  return;
	  }
	  
	  // first half end time
	  if(assessementFirstHalfEndTime.isAfter(assessementSecondHalfStartTime)){
		  alertify.alert("First Half End Time must be before Second Half Start Time");
		  event.preventDefault();
		  return;
	  }
	  
	  if(assessementFirstHalfEndTime.isAfter(assessementFinishTime)){
		  alertify.alert("First Half End Time must be before Assessment Finish Time");
		  event.preventDefault();
		  return;
	  }
	  
	  
	  // assessementSecondHalfStartTime
	  if(assessementSecondHalfStartTime.isAfter(assessementFinishTime)){
		  alertify.alert("Second Half Start Time must be before Assessment Finish Time");
		  event.preventDefault();
		  return;
	  }
	  
	  var capTime = ${timermodule.TIMER_CAP_MINUTES};
	  var lcbTime = ${timermodule.TIMER_LI_MINUTES};
	  
	  var capLcbTime = capTime + lcbTime;
	  
	  assessementFirstHalfStartTime.add(capLcbTime,'minutes');
	  
	  if(assessementFirstHalfStartTime.isAfter(assessementFirstHalfEndTime))
	  {
		  alertify.alert("First Half End Time minimun is " + assessementFirstHalfStartTime.format('DD-MM-YYYY HH:mm') + " (" + capLcbTime + " minutes after first half start time)");
		  $("#assessementFirstHalfEndTimeStr").val("");
		  
		  event.preventDefault();
		  return;
	  }
	 
	  var simulation2Time = ${timermodule.TIMER_SIMULATION_II};
	  assessementSecondHalfStartTime.add(simulation2Time,'minutes');
	  
	  if(assessementSecondHalfStartTime.isAfter(assessementFinishTime))
	  {
		  alertify.alert("Assessment Finish time minimun is " + assessementSecondHalfStartTime.format('DD-MM-YYYY HH:mm') + " ("+simulation2Time+" minutes after first half start time)");
		  $("#assessementFinishTime").val("");
		  
		  event.preventDefault();
		  return;
	  }
	});
</script>