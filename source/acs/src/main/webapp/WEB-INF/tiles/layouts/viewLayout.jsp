<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<c:set value="${initParam.staticfiles}" var="staticfiles" scope="request"></c:set>
<%-- <c:set value="http://10.212.101.185/acsstatic" var="staticfiles" scope="request"></c:set>
 --%>
<!DOCTYPE html>
<html>

	<head>
		<title><tiles:getAsString name="title" /></title>
		<tiles:insertAttribute name="head" />
	</head>
	
	<body class="nav-md">
		<div class="main_container">
			<div class="container body">
				<div class="">
					<header id="header">
						<tiles:insertAttribute name="header" />
					</header>
	
					<section id="site-content">
						<tiles:insertAttribute name="body" />
					</section>
	
					<!-- footer content -->
					<footer>
						<div>
			        		<span class="copyright">&copy; 2017 All rights reserved <img class="pull-right" src="${initParam.staticfiles}/static/images/bnv-logo-footer.png" alt="BNV"/></span>
			      		</div>
						<div class="clearfix"></div>
					</footer>
					<!-- /footer content -->
	
					<tiles:insertAttribute name="foot" />
					
					<!-- Autosize -->
					<script src="${initParam.staticfiles}/static/themes/vendors/autosize/dist/autosize.min.js"></script>
	
					<!-- bootstrap-daterangepicker -->
					<script src="${initParam.staticfiles}/static/themes/vendors/moment/min/moment.min.js"></script>
					<script src="${initParam.staticfiles}/static/themes/vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
					<script src="${initParam.staticfiles}/static/js/datetimepicker/build/jquery.datetimepicker.full.min.js"></script>
	
					<!-- bootstrap-daterangepicker -->
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
									});
								});
					</script>
					<!-- /bootstrap-daterangepicker -->
					<!-- jQuery Smart Wizard -->
	
					<script>
						$(document).ready(function() {
							$('.datetimepicker').datetimepicker({
								format:'d-m-Y',
								timepicker: false
							});
							$('#wizard').smartWizard({
								displayFinishButton : false
							});
	
							$('#wizard_verticle').smartWizard({
								displayFinishButton : false,
								transitionEffect : 'slide'
							});
							
							$('#wizard_verticle_openall').smartWizard({
								displayFinishButton : false,
								transitionEffect : 'slide',
								enableAllSteps : true
							});
						
							
							$('.buttonNext').addClass('btn btn-success');
							$('.buttonPrevious').addClass('btn btn-primary');
							$('.buttonFinish').addClass('btn btn-default');
						});
					</script>
	
					<script>
						$(document).ready(function() {
							autosize($('.resizable_textarea'));
						});
					</script>
					<script src="${initParam.staticfiles}/static/themes/build/js/custom.min.js"></script>
					<tiles:insertAttribute name="footscript" />
					<!-- Custom Theme Scripts -->
				</div>
			</div>
		</div>
	</body>
</html>