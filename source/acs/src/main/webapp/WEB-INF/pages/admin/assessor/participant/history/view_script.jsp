<script>
$(document).ready(function() {
    $('#entityDatatable').DataTable( {
        "processing": true,
        "serverSide": true,
        "sort": false,
        "ajax": "${pageContext.request.contextPath}/controller/rest/admin/administrator/assessement/participant/participanthistory",
        
        
        "aoColumnDefs": [

    		{ "mDataProp": "participantId", "bSearchable": true, "aTargets": [0] },
    		{ "mDataProp": "batchDescription", "bSearchable": true, "aTargets": [1] },
    		{  "mData" : function ( source, type, val ) { 
              	 return moment(new Date(source.reviewerFinishTime)).format('DD/MM/YYYY HH:mm');
               }, "bSearchable": true, "aTargets": [2] },
               
    		{ "mDataProp": "reviewerName", "bSearchable": true, "aTargets": [3] },
    		
            { "mData" : function ( source, type, val ) {
           	 return '<a href=\"${pageContext.request.contextPath}/controller/pages/admin/assessor/participant/home/view/' + source.participantId + '\" title="assess"><i class="fa fa-edit"></i></a>';
            }, "bSearchable": true, "aTargets": [4] }
            ],
         
       
    } );
} );

function doNothing(){}

</script>