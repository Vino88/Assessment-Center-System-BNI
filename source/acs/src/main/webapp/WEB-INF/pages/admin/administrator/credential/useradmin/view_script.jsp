<script>
$(document).ready(function() { 
    $('#entityTable').DataTable( { 
        "processing": true,
        "serverSide": true,
        "sort": false,
        "ajax": "${ pageContext.request.contextPath}/controller/rest/admin/administrator/credential/useradmin/list",
        
        
        "aoColumnDefs": [
     		{  "mDataProp": "username", "bSearchable": true, "aTargets": [0] },
     		{  "mDataProp": "fullname", "bSearchable": true, "aTargets": [1] },
     		{  "mDataProp": "email", "bSearchable": true, "aTargets": [2] },
     		{  "mDataProp": "password", "bSearchable": true, "aTargets": [3] },
     		{  "mDataProp": "role", "bSearchable": true, "aTargets": [4] },
     		
            {  "mData" : function ( source, type, val ) { 
           	 return '<a href=\"${ pageContext.request.contextPath}/controller/pages/admin/administrator/credential/useradmin/edit/' + source.id + '\" title="edit"><i class="fa fa-pencil"></i></a>'
           	 
           	 +'&nbsp;&nbsp;&nbsp;<a href="javascript: onClickDel('+source.id+')" title="delete"><i class="fa fa-remove"></i></a>';
            }, "bSearchable": true, "aTargets": [5] }
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
					window.location.href = "${ pageContext.request.contextPath}/controller/pages/admin/administrator/credential/useradmin/delete/" + id;
				}
			}
    );
}
</script>