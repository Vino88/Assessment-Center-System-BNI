<!-- jQuery Smart Wizard -->
    <script src="${initParam.staticfiles}/static/themes/vendors/jQuery-Smart-Wizard/js/jquery.smartWizard.js"></script>
    <script src="${initParam.staticfiles}/static/themes/build/js/custom.min.js"></script>
<script>
	$(document).ready(function() {
		$('#wizard_lcb').smartWizard({
			displayFinishButton : false,
			enableAllSteps : true
		});

		$('.buttonNext').addClass('btn btn-success');
		$('.buttonPrevious').addClass('btn btn-primary');
		$('.buttonFinish').addClass('btn btn-default');
		
		$RIGHT_COL.css("min-height",$(window).height())
	});
</script>