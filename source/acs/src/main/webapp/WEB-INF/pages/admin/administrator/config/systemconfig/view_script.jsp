<script>
$(document).ready(function() { 
    $('#entityTable').DataTable( { 
        "processing": true,
        "serverSide": true,
        "sort": false,
        "ajax": "${ pageContext.request.contextPath}/controller/rest/admin/administrator/config/systemconfig/list",
        
        
        "aoColumnDefs": [
     		{  "mDataProp": "module", "bSearchable": true, "aTargets": [0] },
     		{  "mDataProp": "systemCode", "bSearchable": true, "aTargets": [1] },
     		{  "mDataProp": "systemValue", "bSearchable": true, "aTargets": [2] },
     		
            {  "mData" : function ( source, type, val ) { 
           	 return '<a href=\"${ pageContext.request.contextPath}/controller/pages/admin/administrator/config/systemconfig/edit/' + source.id + '\" title="edit"><i class="fa fa-pencil"></i></a>'
           	 
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
					window.location.href = "${ pageContext.request.contextPath}/controller/pages/admin/administrator/config/systemconfig/delete/" +id;
				}
			}
    );
}

</script>