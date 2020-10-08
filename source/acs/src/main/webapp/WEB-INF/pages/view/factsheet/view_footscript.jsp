<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages"/>
<script>

$(document).ready(function() {
	$("#btnFactsheetDraft").click(function(e)
	{
		$("body").loading();
    	$("#factsheetform").submit();
	});
	
	$("#btnFactsheetSbmt").click(function(e)
	{
		var phoneNumber = $("#participantPhone").val().trim();
		
		var workingArea  = $("#workingArea").val().trim();
		var participantEmail  = $("#participantEmail").val().trim();
		
		
		alertify.set({ labels: {
		    ok     : '<fmt:message key="btn.confirm.ok"></fmt:message>',
		    cancel : '<fmt:message key="btn.confirm.cancel"></fmt:message>'
		} });
		
		if(phoneNumber.length == 0){
			alertify.alert("Mohon isi No. Telephone Genggam Anda");
		}else if(workingArea.length == 0){
			alertify.alert("Mohon isi Wilayah Anda");
		}else if(participantEmail.length == 0){
			alertify.alert("Mohon isi Email Anda");
		}else
		{
			alertify.confirm('<p>Sistem akan mengunci jawaban Anda</p> <p>Anda tidak bisa mengubah kembali jawaban Anda</p> <p>Anda akan diarahkan ke halaman Prestasi Karir</p> <p> Apakah Anda yakin akan mengirimkan jawaban Anda sekarang ?</p>', function (e) {
			    if (e) {
			    	$("body").loading();
			    	$("#factsheetform").attr("action","${pageContext.request.contextPath}/controller/pages/view/factsheet/finish");
			    	$("#factsheetform").submit();
			    } else {
			        // user clicked "cancel"
			    }
			});
		}
		
	});
});

function beginTest()
{
	
}
</script>