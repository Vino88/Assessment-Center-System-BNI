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
						  async : true,
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
					
					alertify.confirm('<p>Sistem akan mengunci jawaban Anda</p> <p>Anda tidak bisa mengubah kembali jawaban Anda.</p><p>Anda akan diarahkan ke halaman Tanggung Jawab dan Aspirasi</p> <p> Apakah Anda yakin akan mengirimkan jawaban Anda sekarang ?</p>', function (e) {
					    if (e) {
					    	$("body").loading();
					    	
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
		
		var remain = ${remainingTime};
		var d2 =moment().add(remain,'seconds').toDate();
		
		if(remain <= 0)
		{
			alertify.error("Waktu telah habis");
			$("body").loading();
			window.location.href = "${pageContext.request.contextPath}/controller/pages/view/lcb/expired";
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
				alertify.error("Dalam 30 detik, system akan mengunci jawaban Anda.");
				}
				else if(val.M == 0 && val.s == 1)
				{
					$("body").loading();
					$("#lcbForm").submit();
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
					
				
		    	 
				
				 if($(this ).attr("data-section") == 1 || $(this ).attr("data-section") == "1")
				 {
					 var data_ind = $(this ).attr("data-index");
					 var data_index = 1;
					 try
					 {
						 data_index = parseInt(data_ind) -1;
					 }catch(e)
					 {
						 data_index =1;
					 }
					 
					 $('#questionSection1Slick').slick('slickGoTo',  data_index);
					 $('#questionSection1Slick').focus();
				 }
				 else
				 {
					 $('#answerContainer'+$(this).attr("data-number")).attr("tabindex",$(this).attr("data-number")).focus();
				 }
		    	 result = false;
		    	  return false;
			 }
	     }); 
		 
		 return result;
	}
</script>