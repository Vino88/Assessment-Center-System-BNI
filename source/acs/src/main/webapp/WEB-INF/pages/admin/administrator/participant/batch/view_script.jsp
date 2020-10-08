<script>
$(document).ready(function() { 
    $('#entityTable').DataTable( { 
        
    	"processing": true,
        "serverSide": true,
        "sort": false,
        "ajax": "${ pageContext.request.contextPath}/controller/rest/admin/administrator/participant/batch/list",
        
        
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
           	 return '&nbsp;<a href=\"${ pageContext.request.contextPath}/controller/pages/admin/administrator/participant/batch/edit/' + source.id + '\" title="edit"><i class="fa fa-pencil"></i></a>'
           	 
           	 +'&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"${ pageContext.request.contextPath}/controller/pages/admin/administrator/participant/batch/detail/' + source.id + '\" title="detail"><i class="fa fa-info-circle"></i></a>';
            }, "bSearchable": true, "aTargets": [6] }
            ],
         
       
    } );
} );

function doNothing(){ }

</script>