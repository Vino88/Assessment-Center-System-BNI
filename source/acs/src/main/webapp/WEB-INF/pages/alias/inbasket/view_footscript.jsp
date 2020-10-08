<script src='${initParam.staticfiles}/static/js/tinymce/tinymce.min.js'></script>
<input type="hidden" id="GLOBAL_FLAG_DISABLED_SAVE" value="false" />
<script>
	$(document).ready(function() {
		try
		{
			$("#headerMenuSimulasi ul").show();
			$("#headerMenuSimulasi").addClass("active").removeClass("active-sm");
			//$("#headerMenuAnalysisDetail").addClass("active").removeClass("active-sm");
			
		}catch(e){}
		
		tinymce.init({
			  selector: 'textarea',
			  menubar: false,
			  statusbar : false,
			
			  toolbar: 'undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | outdent indent | saveasdraft ',
			  
			  height : 285,
			  
			  setup: function(editor) {
				  
				    
				    function saveAsDraft() {
				    	var GLOBAL_FLAG_DISABLED_SAVE = $("#GLOBAL_FLAG_DISABLED_SAVE").val();
				    	if(GLOBAL_FLAG_DISABLED_SAVE == "true" || GLOBAL_FLAG_DISABLED_SAVE == true)
			    		{
			    			alertify.alert("Mohon menunggu..")
			    		}
				    	else
			    		{
				    		tinyMCE.triggerSave();
				    		$("#GLOBAL_FLAG_DISABLED_SAVE").val("true");
				    		$.ajax({
								  type: "POST",
								  cache: false,
								  async : false,
								  url: "${pageContext.request.contextPath}/controller/rest/view/inbasket/detail",
								  data: $("form").serializeArray(),
								  dataType: "json",
								  success: function(data, textStatus)
								  {
									  $("#GLOBAL_FLAG_DISABLED_SAVE").val("false");
									  alertify.success("Saved successfully");
								  },
								
								});
			    		}
				    	
				    	
				    }

				    editor.addButton('saveasdraft', {
				      icon: 'save',
				      //image: 'http://p.yusukekamiyamane.com/icons/search/fugue/icons/calendar-blue.png',
				      tooltip: "Save As Draft",
				      onclick: saveAsDraft
				    });
				  }
			});
		
		$RIGHT_COL.css("min-height",$(window).height());
		
		
		
		
		
		
	});
</script>