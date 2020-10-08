<div id="dialog-confirm" title="Competency Library" style="display:none">
  <table id="competencyTable" class="table table-striped table-bordered">
     <thead>
       <tr>
         <th>Category</th>
         <th>Competency Name</th>
         <th>Action</th>
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
    $('#competencyTable').DataTable( {
        "processing": true,
        "serverSide": true,
        "sort": false,
        "ajax": "${pageContext.request.contextPath}/controller/rest/admin/administrator/competency/competencylibrary/list",
        
        
        "aoColumnDefs": [

    		{ "mDataProp": "category", "bSearchable": true, "aTargets": [0] },
    		{ "mDataProp": "competencyName", "bSearchable": true, "aTargets": [1] },
            { "mData" : function ( source, type, val ) {
           	 return '<a href=\"javascript:choose(' + source.id + ', \''+ source.competencyName + '\' )\" >Choose</a>';
            }, "bSearchable": true, "aTargets": [2] }
            ],
         
       
    } );
} );

function browseCompetency()
{
	$( "#competencyTable" ).css("width", "1180px")
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
	$("#competencyName").val(name);
	$("#competencyLibraryId").val(id);	
	$( "#dialog-confirm" ).dialog( "close" );
}
</script>