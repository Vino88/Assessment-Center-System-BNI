<script>
$(document).ready(function() {
    $('#newsDatatable').DataTable( {
        "processing": true,
        "serverSide": true,
        "sort": false,
        "ajax": "${pageContext.request.contextPath}/controller/rest/admin/administrator/assessement/participant/participantfinish",
        
        
        "aoColumnDefs": [

    		{ "mDataProp": "email", "bSearchable": true, "aTargets": [0] },
    		{ "mDataProp": "fullName", "bSearchable": true, "aTargets": [1] },
    		{ "mDataProp": "batch.description", "bSearchable": true, "aTargets": [2] },
            { "mData" : function ( source, type, val ) {
           	 return '<a href=\"${pageContext.request.contextPath}/controller/pages/admin/administrator/assessement/participantfinish/detail/' + source.id + '\" title="assign assessor"><i class="fa fa-user"></i></a>';
            }, "bSearchable": true, "aTargets": [3] }
            ],
         
       
    } );
} );

function doNothing(){}
</script>