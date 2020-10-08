
    <!-- jQuery -->
    <script src="${initParam.staticfiles}/static/themes/vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="${initParam.staticfiles}/static/themes/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="${initParam.staticfiles}/static/themes/vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="${initParam.staticfiles}/static/themes/vendors/nprogress/nprogress.js"></script>
    <!-- iCheck -->
    <script src="${initParam.staticfiles}/static/themes/vendors/iCheck/icheck.min.js"></script>
    <!-- validator -->
    <script src="${initParam.staticfiles}/static/themes/vendors/validator/validator.js"></script>

    <!-- Custom Theme Scripts -->
    <script src="${initParam.staticfiles}/static/themes/build/js/custom.min.js"></script>
    
    
    <!-- Datatables -->
    <script src="${initParam.staticfiles}/static/themes/vendors/datatables.net/js/jquery.dataTables.min.js"></script>
    <script src="${initParam.staticfiles}/static/themes/vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
    <script src="${initParam.staticfiles}/static/themes/vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
    <script src="${initParam.staticfiles}/static/themes/vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
    <script src="${initParam.staticfiles}/static/themes/vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
    <script src="${initParam.staticfiles}/static/themes/vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
    <script src="${initParam.staticfiles}/static/themes/vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
    <script src="${initParam.staticfiles}/static/themes/vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
    <script src="${initParam.staticfiles}/static/themes/vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
    <script src="${initParam.staticfiles}/static/themes/vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
    <script src="${initParam.staticfiles}/static/themes/vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
    <script src="${initParam.staticfiles}/static/themes/vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>
    <script src="${initParam.staticfiles}/static/themes/vendors/jszip/dist/jszip.min.js"></script>
    <script src="${initParam.staticfiles}/static/themes/vendors/pdfmake/build/pdfmake.min.js"></script>
    <script src="${initParam.staticfiles}/static/themes/vendors/pdfmake/build/vfs_fonts.js"></script>
    
    <!-- bootstrap-daterangepicker -->
	<script src="${initParam.staticfiles}/static/themes/vendors/moment/min/moment.min.js"></script>
	<script src="${initParam.staticfiles}/static/themes/vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
	
	<script src="${initParam.staticfiles}/static/js/datetimepicker/build/jquery.datetimepicker.full.min.js"></script>
	<script src="${initParam.staticfiles}/static/js/alertify.js-0.3.11/lib/alertify.min.js"></script>
	
	<script>
		$(document).ready(
			function() {
				$('.date-picker').daterangepicker({
					locale : {
						format : 'DD-MM-YYYY'
					},
					singleDatePicker : true,
					calender_style : "picker_4"
				},
					function(start, end, label) {
						console.log(start.toISOString(),
						end.toISOString(), label);
					}
				);
				
				$('.datetimepicker').datetimepicker({
					format:'d-m-Y H:i',
				});
				
				$(document).ready(
					function() {
						$('.datetimepickermasterdata').datetimepicker({
							format:'d-m-Y',
							timepicker: false
						});
				});
				
		});
	</script>