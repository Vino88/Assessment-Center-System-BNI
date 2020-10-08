<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages"/>
<script>

$(document).ready(function() {
	$("#btnFactsheetDraft").click(function(e)
	{
		$("body").loading();
    	$("#aspirationform").submit();
	});
	
	$("#btnFactsheetSbmt").click(function(e)
	{
		
		var currentPositionName  = $("#currentPositionName").val().trim();
		var currentPeriode  = $("#currentPeriode").val().trim();
		
		var currentResponsibility = $("#mainResponsibility").val().trim();
		var currentDirectSupervisor  = $("#currentDirectSupervisor").val().trim();
		
		
		var careerAspiration = $("#careerAspiration").val().trim();
		
		alertify.set({ labels: {
		    ok     : '<fmt:message key="btn.confirm.ok"></fmt:message>',
		    cancel : '<fmt:message key="btn.confirm.cancel"></fmt:message>'
		} });
		
		if(currentPositionName.length == 0){
			alertify.alert("Mohon isi Nama Posisi/Jabatan Anda");
		}else if(currentPeriode.length == 0){
			alertify.alert("Mohon isi Lama Menjabat (dalam tahun) Anda");
		}else if(currentResponsibility.length == 0){
			alertify.alert("Mohon isi tanggung jawab utama Anda");
		}else if(currentDirectSupervisor.length == 0){
			alertify.alert("Mohon isi Nama Atasan Langsung Anda");
		}else if(careerAspiration.length == 0){
			alertify.alert("Mohon isi aspirasi karir Anda");
		}else
		{
			alertify.confirm('<p>Sistem akan mengunci jawaban Anda</p> <p>Anda tidak bisa mengubah kembali jawaban Anda</p> <p>Anda akan diarahkan ke halaman Login</p> <p> Apakah Anda yakin akan mengirimkan jawaban Anda sekarang ?</p>', function (e) {
			    if (e) {
			    	$("body").loading();
			    	$("#aspirationform").attr("action","${pageContext.request.contextPath}/controller/pages/view/aspiration/finish");
			    	$("#aspirationform").submit();
			    } else {
			        // user clicked "cancel"
			    }
			});
		}
		
	});
	
	
	var remain = ${remainingTime};
	var d2 =moment().add(remain,'seconds').toDate();
	
	if(remain <= 0)
	{
		alertify.error("Waktu telah habis");
		$("body").loading();
    	$("#aspirationform").attr("action","${pageContext.request.contextPath}/controller/pages/view/aspiration/finish");
    	$("#aspirationform").submit();
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
			
			
			if(val.M == 5 && val.s == 0)
			{
			alertify.error("Waktu tersisa 5 Menit Lagi");
			}
			else if(val.M == 0 && val.s == 30)
			{
			alertify.error("Dalam 30 detik, system akan mengunci jawaban Anda.");
			}
			else if((val.M == 0 && val.s == 1) || val.S == 0)
			{
				$("body").loading();
		    	$("#aspirationform").attr("action","${pageContext.request.contextPath}/controller/pages/view/aspiration/finish");
		    	$("#aspirationform").submit();
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

function beginTest()
{
	
}
</script>