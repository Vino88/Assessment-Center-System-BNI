<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages"/>

<div id="dialog-confirm" title="Assessor Notes" style="display:none">
   <div><textarea id="dialogNotes" rows="15" readonly="readonly"
										class="form-control col-md-12 col-xs-12 resizable_textarea"
										></textarea></div>
</div>

<script src="${initParam.staticfiles}/static/js/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
<script src="${initParam.staticfiles}/static/themes/vendors/Chart.js/dist/Chart.min.js"></script>

<script>

	$(document).ready(function() {
		
		
		var options = {
		          legend: false,
		          responsive: false
		        };

		        new Chart(document.getElementById("canvas1"), {
		          type: 'doughnut',
		          tooltipFillColor: "rgba(51, 51, 51, 0.55)",
		          data: {
		            labels: [
		              "I'm not sure",
		              "can't be assessed",
		              "No Flag",
		              "Empty"
		            ],
		            datasets: [{
		              data: [${spmFlag.notSure },${spmFlag.answer }, ${spmFlag.noFlag }, ${spmFlag.emptyFlag }],
		              backgroundColor: [
		            	  
		            	  "#f0ad4e",
		            	  "#E74C3C",
		                "#BDC3C7",
		                
		                "#26B99A"
		              ],
		              hoverBackgroundColor: [
		            	  
		            	  "#eea236",
		            	  "#E95E4F",
		                "#CFD4D8",
		                
		                "#36CAAB"
		              ]
		            }]
		          },
		          options: options
		        });
		        
		        
		        
		        new Chart(document.getElementById("canvas2"), {
			          type: 'doughnut',
			          tooltipFillColor: "rgba(51, 51, 51, 0.55)",
			          data: {
			            labels: [
			              "I'm not sure",
			              "can't be assessed",
			              "No Flag",
			              "Empty"
			            ],
			            datasets: [{
			            	data: [${srbFlag.notSure },${srbFlag.answer }, ${srbFlag.noFlag }, ${srbFlag.emptyFlag }],
			              backgroundColor: [
			            	  
			            	  "#f0ad4e",
			            	  "#E74C3C",
			                "#BDC3C7",
			                
			                "#26B99A"
			              ],
			              hoverBackgroundColor: [
			            	  
			            	  "#eea236",
			            	  "#E95E4F",
			                "#CFD4D8",
			                
			                "#36CAAB"
			              ]
			            }]
			          },
			          options: options
			        });
		        
		        
		        
		        new Chart(document.getElementById("canvas3"), {
			          type: 'doughnut',
			          tooltipFillColor: "rgba(51, 51, 51, 0.55)",
			          data: {
			            labels: [
			              "I'm not sure",
			              "can't be assessed",
			              "No Flag",
			              "Empty"
			            ],
			            datasets: [{
			            	data: [${capFlag.notSure },${capFlag.answer }, ${capFlag.noFlag }, ${capFlag.emptyFlag }],
			              backgroundColor: [
			            	  
			            	  "#f0ad4e",
			            	  "#E74C3C",
			                "#BDC3C7",
			                
			                "#26B99A"
			              ],
			              hoverBackgroundColor: [
			            	  
			            	  "#eea236",
			            	  "#E95E4F",
			                "#CFD4D8",
			                
			                "#36CAAB"
			              ]
			            }]
			          },
			          options: options
			        });
	});
	
	
	function displayNotes(prefix, id)
	{
		var notes = $("#"+prefix+id).text();
		
		$("#dialogNotes").text(notes);
		
		$( "#dialog-confirm" ).dialog({
		      resizable: false,
		      height: "auto",
		      width: 800,
		      modal: true,
		      buttons: {
		        "Close": function() {
		          $( this ).dialog( "close" );
		        }
		      }
		    });
	}

</script>