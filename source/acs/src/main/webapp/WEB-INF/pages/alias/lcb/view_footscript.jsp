<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages" />

<div id="dialog-confirm" title="Form Submission">
  <p id="confirmationText"></p>
</div>

<script>

function onEndSlide()
{
	if(validatebysection(1))
		{
		$('#wizard_lcb').smartWizard('goToStep',2);
		}
}


	$(document).ready(function() {
		$(".qlabel ").each(function(index){
			var txt = $(this).find("p:first").text();
			
			if(txt)
				{
				$(this).find("p:first").text((index+1) + ". " + txt);
				}
			else
				{
				$(this).prepend((index+1) +". ");
				}
			
		})
		$('#nextSlideSection1, #prevSlideSection1').click(function(e){
			$('html, body').animate({
		        scrollTop: $("#wizard_lcb").offset().top
		    }, 500);
		})
		
		$('#questionSection1Slick').slick({
			  slidesToShow: 1,
			  prevArrow : "#prevSlideSection1",
			  nextArrow : "#nextSlideSection1",
			  adaptiveHeight: false,
			     
			  arrows: true,
			  infinite : false
			});
	
		$('#wizard_lcb').smartWizard({
			displayFinishButton : true,
			enableAllSteps : false,
			
			labelFinish : '<fmt:message key="btn.submit"></fmt:message>',
			labelNext: '<fmt:message key="btn.next"></fmt:message>',
			labelPrevious: '<fmt:message key="btn.prev"></fmt:message>',
			onLeaveStep : function()
			{
				
				
				 var result =  validatebysection($('#wizard_lcb').smartWizard('currentStep'));
				if(result)
					{
					$.ajax({
						  type: "POST",
						  cache: false,
						  async : false,
						  url: "${pageContext.request.contextPath}/controller/rest/view/lcb/saveasdraft",
						  data: $("#lcbForm").serializeArray(),
						  dataType: "json",
						  success: function(data, textStatus)
						  {
							  alertify.success("Saved successfully");
						  },
						
						});
					
					$('html, body').animate({
				        scrollTop: $("#wizard_lcb").offset().top
				    }, 500);
					} 
					
				
					return result;
				
			},
			onFinish : function()
			{
				var isValid = validate();
				if(isValid)
					{
					
					alertify.confirm('<p>Sistem akan mengunci jawaban Anda</p> <p>Anda tidak bisa mengubah kembali jawaban Anda.</p> <p> Apakah Anda yakin akan mengirimkan jawaban Anda sekarang ?</p>', function (e) {
					    if (e) {
					    	$("body").loading();
					    	$("#lcbForm").attr("action", "${pageContext.request.contextPath}/controller/pages/alias/lcb");
					    	$("#lcbForm").submit();
					    } else {
					        // user clicked "cancel"
					    }
					});
					
					
					
					}
			}
		});

		$('.buttonNext').addClass('btn btn-success');
		$('.buttonPrevious').addClass('btn btn-primary');
		$('.buttonFinish').addClass('btn btn-default');
		
		
		
		 
		$RIGHT_COL.css("min-height",$(window).height())
	});
	
	
	function validate()
	{
		
		var result = true;
		  $( ".answerContainer" ).each(function() {
			 if($(this).find(":checked").length == 0)
			 {
				 $('#wizard_lcb').smartWizard('goToStep',$(this ).attr("data-section"));
				 alertify.alert("Mohon menjawab pertanyaan nomor " + $(this ).attr("data-index") + ", pada bagian "+$(this ).attr("data-section"))
					
				 $('#answerContainer'+$(this).attr("data-number")).attr("tabindex",$(this).attr("data-number")).focus();
				
		    	 
		    	 result = false;
		    	  return false;
			 }
	     }); 
		 
		 return result;
	}
	
	
	
	function validatebysection(sect)
	{
		
		var result = true;
		  $( "#step-"+sect+" .answerContainer" ).each(function() {
			 if($(this).find(":checked").length == 0)
			 {
				 alertify.alert("Mohon menjawab pertanyaan nomor " + $(this ).attr("data-index") + ", pada bagian "+$(this ).attr("data-section"))
					
				 $('#answerContainer'+$(this).attr("data-number")).attr("tabindex",$(this).attr("data-number")).focus();
		    	 
				
				 if($(this ).attr("data-section") == 1 || $(this ).attr("data-section") == "1")
				 {
					 $('#questionSection1Slick').slick('slickGoTo', $(this ).attr("data-index") - 1);
				 }
		    	 result = false;
		    	  return false;
			 }
	     }); 
		 
		 return result;
	}
</script>