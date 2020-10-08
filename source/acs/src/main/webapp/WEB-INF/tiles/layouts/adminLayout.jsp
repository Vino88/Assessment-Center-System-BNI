<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
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
				 	
				 	<!-- Datatables -->
		    		
		    		<script>
		      			$(document).ready(function() {
		        			var handleDataTableButtons = function() {
		          				if ($("#datatable-buttons").length) {
		            				$("#datatable-buttons").DataTable({
			              				dom: "Bfrtip",
			              				buttons: [
				                			{
							                	extend: "csv",
							                	className: "btn-sm"
							                },
							                {
							                  	extend: "excel",
							                  	className: "btn-sm"
							                },
		               					],
		              					responsive: true
		            				});
		          				}
		        			};
		
		        			TableManageButtons = function() {
		          				"use strict";
		          				return {
			            			init: function() {
			              				handleDataTableButtons();
			            			}
		          				};
		        			}();
		
					        $('#datatable').dataTable();
					
					        $('#datatable-keytable').DataTable({
					         	keys: true
					        });
		
		       	 				$('#datatable-responsive').DataTable();
		
		        				$('#datatable-scroller').DataTable({
						         	ajax: "js/datatables/json/scroller-demo.json",
						          	deferRender: true,
						          	scrollY: 380,
						          	scrollCollapse: true,
						          	scroller: true
		        				});
		
		        				$('#datatable-fixed-header').DataTable({
		          					fixedHeader: true
		        				});
		
		        			var $datatable = $('#datatable-checkbox');
		
		        			$datatable.dataTable({
					         	'order': [[ 1, 'asc' ]],
					          	'columnDefs': [
		            				{ orderable: false, targets: [0] }
		          				]
		        			});
		        			
		        			$datatable.on('draw.dt', function() {
		          				$('input').iCheck({
		            				checkboxClass: 'icheckbox_flat-green'
		          				});
		        			});
		
		        			TableManageButtons.init();
		      			});
		    		</script>
		    		<!-- /Datatables -->
				
					<tiles:insertAttribute name="footscript" />
				
				</div>
			</div>
		</div>
	</body>
</html>