<script>
$(document).ready(function() { 
    $('#entityTable').DataTable( { 
        "processing": true,
        "serverSide": true,
        "sort": false,
        "ajax": "${ pageContext.request.contextPath}/controller/rest/admin/administrator/inbasket/inbasketinbox/list",
        
        
        "aoColumnDefs": [
     		{  "mDataProp": "inboxType", "bSearchable": true, "aTargets": [0] },
     		{  "mDataProp": "title", "bSearchable": true, "aTargets": [1] },
     		{  "mDataProp": "number", "bSearchable": true, "aTargets": [2] },
     		
            {  "mData" : function ( source, type, val ) { 
           	 return '<a href=\"${ pageContext.request.contextPath}/controller/pages/admin/administrator/inbasket/inbasketinbox/edit/' + source.id + '\" title="edit"><i class="fa fa-pencil"></i></a>'
           	 
           	+'&nbsp;&nbsp;&nbsp;<a href="javascript: onClickDel('+source.id+')" title="delete"><i class="fa fa-remove"></i></a>';
            }, "bSearchable": true, "aTargets": [3] }
            ],
         
       
    } );
} );

function doNothing(){ }

function onClickDel(id)
{ 
	alertify.confirm('Are you sure you want to delete the data?', 
			function(e){
				if(e)
				{
					window.location.href = "${ pageContext.request.contextPath}/controller/pages/admin/administrator/inbasket/inbasketinbox/delete/" + id;
				}
			}
    );
}
</script>