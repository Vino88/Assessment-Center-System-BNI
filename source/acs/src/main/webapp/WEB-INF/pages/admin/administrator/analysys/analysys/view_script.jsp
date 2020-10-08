<script>
$(document).ready(function() { 
    $('#entityTable').DataTable( { 
        "processing": true,
        "serverSide": true,
        "sort": false,
        "ajax": "${ pageContext.request.contextPath}/controller/rest/admin/administrator/analysys/analysys/list",
        
        
        "aoColumnDefs": [
     		{  "mDataProp": "number", "bSearchable": true, "aTargets": [0] },
     		{  "mDataProp": "title", "bSearchable": true, "aTargets": [1] },
     		{  "mDataProp": "analysysType", "bSearchable": true, "aTargets": [2] },
     		{  "mDataProp": "question", "bSearchable": true, "aTargets": [3] },
     		
            {  "mData" : function ( source, type, val ) { 
           	 return '<a href=\"${ pageContext.request.contextPath}/controller/pages/admin/administrator/analysys/analysys/edit/' + source.id + '\" >Edit</a>'
           	 
           		+'&nbsp;&nbsp;&nbsp;<a href="javascript: onClickDel('+source.id+')" title="delete"><i class="fa fa-remove"></i></a>';
            }, "bSearchable": true, "aTargets": [4] }
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
					window.location.href = "${ pageContext.request.contextPath}/controller/pages/admin/administrator/analysys/analysys/delete/" + id;
				}
			}
    );
}
</script>