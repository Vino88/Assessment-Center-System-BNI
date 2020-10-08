<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages"/>
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
								  async : true,
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
		
		
		var remain = ${remainingTime};
		var d2 =moment().add(remain,'seconds').toDate();
		
		if(remain <= 0)
		{
			alertify.error("Waktu telah habis");
			$("body").loading();
			window.location.href = "${pageContext.request.contextPath}/controller/pages/view/inbasket/expired";
		}
		
		var myToast = $.toast({
		    heading: 'Sisa Waktu',
		    text: '0:00:00',
		    icon: 'info',
		    allowToastClose: false,
		    position: 'top-center',
		    hideAfter: false
		});
		
		$('#timer').tinyTimer({ 
			to: d2, 
			onTick: function (val) {
				
				var inbasketVideoLinkOpened = '${session_participant_log_during.inbasketVideoLinkOpened}';
				var inbasketWarning = '${session_participant_log_during.inbasketWarning}';
				var inbasketRedirect = '${session_participant_log_during.inbasketRedirect}';
				
				$("#minuteCounter").val(val.M);
				
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
				alertify.error("Sisa waktu 30 detik");
				tinyMCE.triggerSave();
				$.ajax({
					  type: "POST",
					  cache: false,
					  async : true,
					  url: "${pageContext.request.contextPath}/controller/rest/view/inbasket/detail",
					  data: $("form").serializeArray(),
					  dataType: "json",
					  success: function(data, textStatus)
					  {
						  $("#GLOBAL_FLAG_DISABLED_SAVE").val("false");
						  
					  },
					
					});
				}
				else if(val.M == 0 && val.s == 10)
				{
				alertify.error("Sisa waktu 10 detik");
				tinyMCE.triggerSave();
				$.ajax({
					  type: "POST",
					  cache: false,
					  async : true,
					  url: "${pageContext.request.contextPath}/controller/rest/view/inbasket/detail",
					  data: $("form").serializeArray(),
					  dataType: "json",
					  success: function(data, textStatus)
					  {
						  $("#GLOBAL_FLAG_DISABLED_SAVE").val("false");
						  
					  },
					
					});
				}
				else if(val.M == 0 && val.s == 1)
				{
					tinyMCE.triggerSave();
					$.ajax({
						  type: "POST",
						  cache: false,
						  async : true,
						  url: "${pageContext.request.contextPath}/controller/rest/view/inbasket/detail",
						  data: $("form").serializeArray(),
						  dataType: "json",
						  success: function(data, textStatus)
						  {
							  $("#GLOBAL_FLAG_DISABLED_SAVE").val("false");
							  
						  },
						
						});
					
					alertify.error("Waktu Anda telah habis");
					$("body").loading();
					
					if($("form").length > 0)
						{
						$("body").loading();
						$("form").attr("action", "${pageContext.request.contextPath}/controller/pages/view/inbasket/finish");
						$("form").submit();
						}
					else {
						window.location.href = "${pageContext.request.contextPath}/controller/pages/view/inbasket/finish";
					}
					
				}
				
				
				if(val.s == 30 && val.m % 5 == 0)
				{
					tinyMCE.triggerSave();
					$.ajax({
						  type: "POST",
						  cache: false,
						  async : true,
						  url: "${pageContext.request.contextPath}/controller/rest/view/inbasket/detail",
						  data: $("form").serializeArray(),
						  dataType: "json",
						  success: function(data, textStatus)
						  {
							  $("#GLOBAL_FLAG_DISABLED_SAVE").val("false");
							  
						  },
					});
				}
				
				if(inbasketVideoLinkOpened == '')
				{
					if(inbasketWarning == '')
					{
						if(val.M == 60)
						{
							$.ajax({
							  method: "GET",
							  url: "${pageContext.request.contextPath}/controller/pages/view/inbasket/warningVideo"
							})
							.done(function( msg ) {
								  alertify.error("Terdapat surat atau <i>email</i> yang belum Anda isi, pastikan Anda menjawab semua pertanyaan. ");
								  inbasketWarning = '1234';
							 });
						}
					}else if (inbasketRedirect == '')
					{
						if(val.M == 81)
						{
							alertify.error("Anda belum mengerjakan Simulasi dengan Bawahan. Sistem akan mengarahkan ke halaman Simulasi dengan Bawahan.");
							
							if($("form").length > 0)
							{
								$("body").loading();
								$("form").attr("action", "${pageContext.request.contextPath}/controller/pages/view/inbasket/redirectToVideo");
								$("form").submit();
							}
						else {
							window.location.href = "${pageContext.request.contextPath}/controller/pages/view/inbasket/redirectToVideo";
						}
							
						}
					}
				}
				
				myToast.update({
					heading: 'Sisa Waktu',
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