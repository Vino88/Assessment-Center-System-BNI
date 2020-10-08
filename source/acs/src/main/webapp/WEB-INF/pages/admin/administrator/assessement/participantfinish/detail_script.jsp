<div id="dialog-confirm" title="Assessor" style="display:none">
  <table id="userAdminTable" class="table table-striped table-bordered" style="width:1180px">
     <thead>
       <tr>
         <th>Username</th>
         <th>Full Name</th>
         <th>E-mail</th>
         
         <th>Assign for PK/IG</th>
         <th>Assign for SPM</th>
         <th>Assign for SRB</th>
         
         <th>Assign for Reviewer</th>
         
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
		"ajax": "${ pageContext.request.contextPath}/controller/rest/admin/administrator/credential/useradmin/listasignassessor",
        
        
        "aoColumnDefs": [
     		{  "mDataProp": "username", "bSearchable": true, "aTargets": [0] },
     		{  "mDataProp": "fullname", "bSearchable": true, "aTargets": [1] },
     		{  "mDataProp": "email", "bSearchable": true, "aTargets": [2] },
     		
     		{  "mDataProp": "numberOfTaskIg", "bSearchable": true, "aTargets": [3] },
     		{  "mDataProp": "numberOfTaskS1", "bSearchable": true, "aTargets": [4] },
     		{  "mDataProp": "numberOfTaskS2", "bSearchable": true, "aTargets": [5] },
     		{  "mDataProp": "numberOfTaskRv", "bSearchable": true, "aTargets": [6] },
     		
            { "mData" : function ( source, type, val ) {
           	 return '<a href=\"javascript:choose(' + source.id + ', \''+ source.fullname+'\')\" >Choose</a>';
            }, "bSearchable": true, "aTargets": [7] }
            ],
         
       
    } );
    
    
    
} );


var userName = '';
var userId = '';
function browseUserAdmin(uname, uid)
{
	userName = uname;
	userId = uid;
	
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
	$("#"+userId).val(id);	
	$("#"+userName).val(name);
	
	if(userId == 'capAssessorUserId')
	{
		$("#integrationGridAssessorUserId").val(id);	
		$("#integrationGridAssessorUserName").val(name);
	}
	$("#dialog-confirm" ).dialog( "close" );
}
</script>