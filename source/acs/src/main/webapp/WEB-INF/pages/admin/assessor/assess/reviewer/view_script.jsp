<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages"/>

<div id="dialog-confirm" title="Assessor Notes" style="display:none">
   <div><textarea id="dialogNotes" rows="15" readonly="readonly"
										class="form-control col-md-12 col-xs-12 resizable_textarea"
										></textarea></div>
</div>
<div id="dialogWritingAssistance" title="Writing Assistance" style="display:none">
  <div class="row">
		<div class="col-md-6 col-sm-6 col-xs-12">
				
					<select name="writingCompetency"  id="writingCompetency" class="form-control">
						<option value="0">All</option>
						<c:forEach items="${competencyLibraries}" var="competencyLibrary">
						<option value="${competencyLibrary.id}">${competencyLibrary.competencyName}</option>
						</c:forEach>
					</select>
					
                </div>
                
                <div class="col-md-6 col-sm-6 col-xs-12">
				
					<select name="writinglevel" id="writinglevel" class="form-control">
						<option value="0">All</option>
						<option value="1">Level - 1</option>
						<option value="2">Level - 2</option>
						<option value="3">Level - 3</option>
						<option value="4">Level - 4</option>
						<option value="5">Level - 5</option>
						
					</select>
					
                </div>
	</div>
  <table id="writingAssistanceTable" class="table table-striped table-bordered" style="width:1180px">
     <thead>
       <tr>
            <th>Competency</th>
            <th>Level</th>
            <th>Description</th>
            
            
            <th>Action</th>
       </tr>
     </thead>


      <tbody>
     
      </tbody>
    </table>
   
</div>
<script src="${initParam.staticfiles}/static/js/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
<script src="${initParam.staticfiles}/static/themes/vendors/Chart.js/dist/Chart.min.js"></script>

<script>
var WRITING_DATA_TABLE = null;

function chooseWritingAssistance(description)
{
	
	$("#"+weakstrongid).val($("#"+weakstrongid).val() + ' ' + description);
	
	$( "#dialogWritingAssistance" ).dialog( "close" );
}
var weakstrongid = null;

function writingAssistance(id)
{
	weakstrongid = id;
	$('#writingAssistanceTable').css("width", "1180px");
	$( "#dialogWritingAssistance" ).dialog({
	      resizable: false,
	      height: "auto",
	      width: 1200,
	      modal: true,
	      buttons: {
	        "Close": function() {
	          $( this ).dialog( "close" );
	          
	        }
	      }
	    });
}

	$(document).ready(function() {
		$("#writingCompetency, #writinglevel").change(function(){
			var writingCompetency = $("#writingCompetency").val();
			var writinglevel = $("#writinglevel").val();
			
			WRITING_DATA_TABLE.ajax.url("${pageContext.request.contextPath}/controller/rest/admin/administrator/competency/competencylibrarywritingassistance/list/"+writingCompetency+"/"+writinglevel);
			WRITING_DATA_TABLE.ajax.reload();
		})
		WRITING_DATA_TABLE = $('#writingAssistanceTable').DataTable( { 
	        "processing": true,
	        "serverSide": true,
	        "sort": false,
	        "searching": false, 
	        "dom": '<"toolbar">frtip',
	        "ajax": "${ pageContext.request.contextPath}/controller/rest/admin/administrator/competency/competencylibrarywritingassistance/list/0/0",
	        
	        
	        "aoColumnDefs": [
	     		
	     		
	     		{  "mDataProp": "competencyLibrary.competencyName", "bSearchable": true, "aTargets": [0] },
	     		{  "mDataProp": "level", "bSearchable": true, "aTargets": [1] },
	     		{  "mDataProp": "description", "bSearchable": true, "aTargets": [2] },
	            {  "mData" : function ( source, type, val ) { 
	           	 return '<a href=\"javascript:chooseWritingAssistance(\'' + source.description + '\')\" title="edit">Choose</a>';
	            }, "bSearchable": true, "aTargets": [3] }
	            ],
	         
	       
	    } );
		$("div.toolbar").html('');
		$("#btnSaveAsDraft").click(function(e){
					
			$("#formIg").submit();
			
		});
		
		$("#btnFinishNow").click(function(e){
			
			alertify.confirm('Are you sure you want to finish now?', 
					function(e){
						if(e)
						{
							$("#formIg").attr("action", "${pageContext.request.contextPath}/controller/pages/admin/assessor/assess/reviewer/ratingandfinish/${participant.id}");
							$("#formIg").submit();
						}
					}
		    );
			
		});
		
		$("#btnUnfinished").click(function(e){
			alertify.alert('Please finish all competency rating, then you can finish the assessment.')
		});
		
		
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