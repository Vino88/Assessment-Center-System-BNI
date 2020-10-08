<script>
$(document).ready(function() {
    $('#newsDatatable').DataTable( {
        "processing": true,
        "serverSide": true,
        "sort": false,
        "ajax": "${pageContext.request.contextPath}/controller/rest/admin/administrator/lcb/list",
        
        
        "aoColumnDefs": [

    		{ "mDataProp": "number", "bSearchable": true, "aTargets": [0] },
    		{ "mDataProp": "question", "bSearchable": true, "aTargets": [1] },
            { "mData" : function ( source, type, val ) {
           	 return '<a href=\"${pageContext.request.contextPath}/controller/pages/admin/administrator/lcb/edit/' + source.id + '\" title="edit"><i class="fa fa-pencil"></i></a>';
            }, "bSearchable": true, "aTargets": [2] }
            ],
         
       
    } );
} );

function doNothing(){}

</script>