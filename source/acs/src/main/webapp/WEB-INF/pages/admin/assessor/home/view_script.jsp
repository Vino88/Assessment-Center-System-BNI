<script>
$(document).ready(function() {
    $('#newsDatatable').DataTable( {
        "processing": true,
        "serverSide": true,
        "sort": false,
        "ajax": "${pageContext.request.contextPath}/controller/rest/admin/administrator/assessement/participant/assingtome/${sessionScope.session_auth_user_admin.id}",
        
        
        "aoColumnDefs": [

    		{ "mDataProp": "id", "bSearchable": true, "aTargets": [0] },
    		{ "mDataProp": "batch.description", "bSearchable": true, "aTargets": [1] },
            { "mData" : function ( source, type, val ) {
           	 return '<a href=\"${pageContext.request.contextPath}/controller/pages/admin/assessor/assess/home/view/' + source.id + '\" title="assess"><i class="fa fa-edit"></i></a>';
            }, "bSearchable": true, "aTargets": [2] }
            ],
         
       
    } );
} );

function doNothing(){}

</script>