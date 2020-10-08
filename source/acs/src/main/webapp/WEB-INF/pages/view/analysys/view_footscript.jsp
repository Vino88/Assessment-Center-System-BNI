<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages"/>
<input type="hidden" id="GLOBAL_FLAG_DISABLED_SAVE" value="false" />
<script src='${staticfiles}/static/js/tinymce/tinymce.min.js'></script>
<script>

function validateArticulate()
{
	var result = false;
	
	var checkStep = ${sessionScope.session_participant_log_during.articulateSrbRead};
	if(!checkStep)
	{
		if(isAllCheckedFlagArray())
		{
			$.ajax({
				  type: "GET",
				  cache: false,
				  async : true,
				  url: "${pageContext.request.contextPath}/controller/rest/view/inbasket/srbread",
				  
				  dataType: "json",
				  success: function(data, textStatus)
				  {
					
				  },
				
				});
			result = true;
			
		}
		else
		{
			result = false;
		}
		
	}
	
	else
	{
		result = true;
		
	}
	return result;
}

function gotoDetail(id)
{
	window.location.href = "${pageContext.request.contextPath}/controller/pages/view/analysys/detail/"+id;
}


	$(document).ready(function() {
		tinymce.init({
			  selector: 'textarea',
			  menubar: false,
			  statusbar : false,
			
			  toolbar: 'undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | outdent indent ',
			  height : 285
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
					window.location.href = "${pageContext.request.contextPath}/controller/pages/view/analysys/finish";
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