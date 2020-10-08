<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages"/>
<script src="${staticfiles}/static/themes/vendors/moment/min/moment.min.js"></script>
					<script src="${staticfiles}/static/themes/vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
					<script src="${staticfiles}/static/js/datetimepicker/build/jquery.datetimepicker.full.min.js"></script>
<script>

var current_slide_index = 1;



	$(document).ready(function() {
		$("#btnPrevSlide").click(function(e){
			if(current_slide_index == 1)
			{
				window.location.href = "${pageContext.request.contextPath}/controller/pages/view/home";
			}else if(current_slide_index == 2)
			{
				$("#btnPrevSlide").hide();
				
				$("#step-1").show();
				$("#step-2").hide();
				$("#step-3").hide();
				
				current_slide_index = 1;
				
			}else if(current_slide_index == 3)
			{
				$("#step-1").hide();
				$("#step-2").show();
				$("#step-3").hide();
				current_slide_index = 2;
			}
		});
		
		$("#btnNextSlide").click(function(e){
			var checkStep1 = ${sessionScope.session_participant_log_during.articulateSpmRead};
			var checkStep2 = ${sessionScope.session_participant_log_during.articulateMemoRead};
			
			if(current_slide_index == 1)
			{
				$("#step-1").hide();
				$("#step-2").show();
				$("#step-3").hide();
				$("#btnPrevSlide").show();
				current_slide_index = 2;
			}
			
			else if(current_slide_index == 2)
				{
				
				if(checkStep1)
				{
					$("#step-1").hide();
					$("#step-2").hide();
					$("#step-3").show();
					
					
				current_slide_index = 3;
				}
				else if(isAllCheckedFlagArray())
				{
					$.ajax({
						  type: "GET",
						  cache: false,
						  async : true,
						  url: "${pageContext.request.contextPath}/controller/rest/view/inbasket/spmread",
						  
						  dataType: "json",
						  success: function(data, textStatus)
						  {
							  current_slide_index = 3;
						  },
						
						});
					$("#step-1").hide();
					$("#step-2").hide();
					$("#step-3").show();
				current_slide_index = 3;
				}
				else
				{
					alertify.set({ labels: {
					    ok     : '<fmt:message key="btn.confirm.ok"></fmt:message>',
					    cancel : '<fmt:message key="btn.confirm.cancel"></fmt:message>'
					} });
					
					alertify.alert("Mohon mengunjungi semua tautan dalam slide Latar Belakang Organisasi");
					return false;
				}
				}
			else if(current_slide_index == 3)
				{
				if(checkStep2)
				{
				
				window.location.href = "${pageContext.request.contextPath}/controller/pages/view/inbasket/home";
				}
				else if(isAllCheckedFlagMemoArray())
				{
					$.ajax({
						  type: "GET",
						  cache: false,
						  async : true,
						  url: "${pageContext.request.contextPath}/controller/rest/view/inbasket/memoread",
						  
						  dataType: "json",
						  success: function(data, textStatus)
						  {
							  current_slide_index = 4;
						  },
						
						});
					
					window.location.href = "${pageContext.request.contextPath}/controller/pages/view/inbasket/home";
				}
				else
				{
					alertify.set({ labels: {
					    ok     : '<fmt:message key="btn.confirm.ok"></fmt:message>',
					    cancel : '<fmt:message key="btn.confirm.cancel"></fmt:message>'
					} });
					
					alertify.alert("Mohon mengunjungi semua tautan dalam slide Memo");
					return false;
				}
				}
			
			else
				{
				alertify.alert("Terdapat kegagalan. Mohon merefresh page ini.");
				}
		})
		
		
		
		var remain = ${remainingTime};
		var d2 =moment().add(remain,'seconds').toDate();
		
		if(remain <= 0)
		{
			alertify.error("Waktu telah habis");
			$("body").loading();
			
			window.location.href = "${pageContext.request.contextPath}/controller/pages/view/inbasket";
		}else
		{
			var myToast = $.toast({
			    heading: 'Sisa Waktu',
			    text: '0:00:00',
			    icon: 'info',
			    allowToastClose: false,
			    position: 'bottom-right',
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
					alertify.error("Dalam 30 detik, system akan mengarahkan ke halaman berikutnya.");
					}
					else if(val.M == 0 && val.s == 1)
					{
						alertify.error("Waktu telah habis");
						$("body").loading();
						
						window.location.href = "${pageContext.request.contextPath}/controller/pages/view/inbasket";
						
					}

					else if(val.S == 0)
					{
						window.location.href = "${pageContext.request.contextPath}/controller/pages/view/inbasket";
						
					}

					
					
					myToast.update({
						heading: 'Sisa Waktu',
					    text: $('#timer').text(),
					    icon: 'info',
					    allowToastClose: false,
					    position: 'bottom-right',
					    hideAfter: false
				    });    
					
			    },
				format: '%h:%0m:%0s' 
				
			});
		}
		
		
		
	});
</script>