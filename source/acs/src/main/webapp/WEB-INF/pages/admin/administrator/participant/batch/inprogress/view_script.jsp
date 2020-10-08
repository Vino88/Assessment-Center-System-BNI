<script>
$(document).ready(function() { 
    $('#entityTable').DataTable( { 
        "processing": true,
        "serverSide": true,
        "sort": false,
        "ajax": "${ pageContext.request.contextPath}/controller/rest/admin/administrator/participant/batch/listinprogress",
        
        
        "aoColumnDefs": [
        	{  "mDataProp": "id", "bSearchable": true, "aTargets": [0] },
     		{  "mDataProp": "description", "bSearchable": true, "aTargets": [1] },
     		{  "mData" : function ( source, type, val ) { 
               	 return moment(new Date(source.assessementFirstHalfStartTime)).format('DD/MM/YYYY HH:mm');
                }, "bSearchable": true, "aTargets": [2] },
            {  "mData" : function ( source, type, val ) { 
              	 return moment(new Date(source.assessementFirstHalfEndTime)).format('DD/MM/YYYY HH:mm');
               }, "bSearchable": true, "aTargets": [3] },
               
           {  "mData" : function ( source, type, val ) { 
             	 return moment(new Date(source.assessementSecondHalfStartTime)).format('DD/MM/YYYY HH:mm');
              }, "bSearchable": true, "aTargets": [4] },
              
           
              {  "mData" : function ( source, type, val ) { 
                	 return moment(new Date(source.assessementFinishTime)).format('DD/MM/YYYY HH:mm');
                 }, "bSearchable": true, "aTargets": [5] },
                 {  "mData" : function ( source, type, val ) { 
                   	 return '<a href=\"${ pageContext.request.contextPath}/controller/pages/admin/administrator/participant/batch/participant/view/' + source.id + '\" title="manage participant"><i class="fa fa-users"></i></a>';
                    }, "bSearchable": true, "aTargets": [6] },
            {  "mData" : function ( source, type, val ) { 
           	 return '&nbsp;<a href=\"${ pageContext.request.contextPath}/controller/pages/admin/administrator/participant/batch/inprogress/edit/' + source.id + '\" title="edit"><i class="fa fa-pencil"></i></a>'
           	 
           	 +'&nbsp;&nbsp;&nbsp;<a href="javascript: onClickDel('+source.id+')" title="delete"><i class="fa fa-remove"></i></a>';
            }, "bSearchable": true, "aTargets": [7] }
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
					window.location.href = "${ pageContext.request.contextPath}/controller/pages/admin/administrator/participant/batch/delete/" + id;
				}
			}
    );
}
</script>