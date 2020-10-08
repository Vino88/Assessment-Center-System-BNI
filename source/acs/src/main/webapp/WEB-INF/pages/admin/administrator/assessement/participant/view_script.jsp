<script>
$(document).ready(function() {
    $('#newsDatatable').DataTable( {
        "processing": true,
        "serverSide": true,
        "sort": false,
        "ajax": "${pageContext.request.contextPath}/controller/rest/admin/administrator/assessement/participant/list",
        
        
        "aoColumnDefs": [

        	{ "mDataProp": "id", "bSearchable": true, "aTargets": [0] },

    		{ "mDataProp": "nik", "bSearchable": true, "aTargets": [1] },
    		{ "mDataProp": "fullName", "bSearchable": true, "aTargets": [2] },
    		{ "mDataProp": "participantStatus", "bSearchable": true, "aTargets": [3] },
    		{ "mDataProp": "logDuringStatus", "bSearchable": true, "aTargets": [4] },
    		{ "mDataProp": "batchDescription", "bSearchable": true, "aTargets": [5] },
            { "mData" : function ( source, type, val ) {
           	 return '<a href=\"${pageContext.request.contextPath}/controller/pages/admin/administrator/assessement/participant/detail/' + source.id + '\" title="detail"><i class="fa fa-info-circle"></i></a>';
            }, "bSearchable": true, "aTargets": [6] }

            ],
         
       
    } );
} );

function doNothing(){}

</script>