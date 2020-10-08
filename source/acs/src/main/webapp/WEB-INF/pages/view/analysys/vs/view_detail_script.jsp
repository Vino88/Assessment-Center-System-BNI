<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages"/>
<span id="timer" style="display:none"></span>
<script src='${staticfiles}/static/js/tinymce/tinymce.min.js'></script>
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
			
			  toolbar: 'undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | outdent indent | saveasdraft',
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
								  async : true,
								  url: "${pageContext.request.contextPath}/controller/rest/view/analysys/detail/visionspeech",
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
		
		
		var remain = ${remainingTime};
		var d2 =moment().add(remain,'seconds').toDate();
		
		if(remain <= 0)
		{
			alertify.error("Waktu telah habis");
			$("body").loading();
			window.location.href = "${pageContext.request.contextPath}/controller/pages/view/analysys/expired";
		}
		
		var myToast = $.toast({
		    heading: 'Waktu tersedia',
		    text: '0:00:00',
		    icon: 'info',
		    allowToastClose: false,
		    position: 'top-center',
		    hideAfter: false
		});
		
		
		$('#timer').tinyTimer({ 
			to: d2, 
			onTick: function (val) {
				
				
				
				if(val.M == 10 && val.s == 0)
					{
					alertify.error("Waktu tersisa 10 Menit Lagi");
					}
				else if(val.M == 5 && val.s == 0)
				{
				alertify.error("Waktu tersisa 5 Menit Lagi");
				}
				else if(val.M == 0 && val.s == 30)
				{
				alertify.error("Dalam 30 detik, system akan mengunci jawaban Anda.");
				tinyMCE.triggerSave();
				$.ajax({
					  type: "POST",
					  cache: false,
					  async : true,
					  url: "${pageContext.request.contextPath}/controller/rest/view/analysys/detail/visionspeech",
					  data: $("form").serializeArray(),
					  dataType: "json",
					  success: function(data, textStatus)
					  {
						  $("#GLOBAL_FLAG_DISABLED_SAVE").val("false");
						  alertify.success("Saved successfully");
					  },
					
					});
				}
				else if(val.M == 0 && val.s == 1)
				{
					alertify.error("system akan mengunci jawaban Anda.");
					tinyMCE.triggerSave();
		    		$("#GLOBAL_FLAG_DISABLED_SAVE").val("true");
		    		$.ajax({
						  type: "POST",
						  cache: false,
						  async : true,
						  url: "${pageContext.request.contextPath}/controller/rest/view/analysys/detail/visionspeech",
						  data: $("form").serializeArray(),
						  dataType: "json",
						  success: function(data, textStatus)
						  {
							  $("#GLOBAL_FLAG_DISABLED_SAVE").val("false");
							  alertify.success("Saved successfully");
							  window.location.href = "${pageContext.request.contextPath}/controller/pages/view/analysys/finish";
						  },
						
						});
					
				}
				
				
				if(val.s == 0)
				{
					tinyMCE.triggerSave();
					$.ajax({
						  type: "POST",
						  cache: false,
						  async : true,
						  url: "${pageContext.request.contextPath}/controller/rest/view/analysys/detail/visionspeech",
						  data: $("form").serializeArray(),
						  dataType: "json",
						  success: function(data, textStatus)
						  {
							  $("#GLOBAL_FLAG_DISABLED_SAVE").val("false");
							  
						  },
						
					});
				}
				
				myToast.update({
					heading: 'Waktu tersedia',
				    text: $('#timer').text(),
				    icon: 'info',
				    allowToastClose: false,
				    position: 'top-center',
				    
				    hideAfter: false
			    });
				
		    },
			format: '%h:%0m:%0s' 
			
		});
		
		
		
		
	});
</script>