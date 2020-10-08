<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages"/>
<script>

$(document).ready(function() {
	$("#btnFactsheetSbmt").click(function(e)
	{
		var phoneNumber = $("#phoneNumber").val().trim();
		var currentResponsibility = $("#currentResponsibility").val().trim();
		var careerAspiration = $("#careerAspiration").val().trim();
		
		var validPhoneNumber = false;
		var validCurrentResponsibility = false;
		var validCareerAspiration = false;
		
		if(phoneNumber.length > 1){
			validPhoneNumber = true;
		}
		if(currentResponsibility.length > 1){
			validCurrentResponsibility = true;
		}
		if(careerAspiration.length > 1){
			validCareerAspiration = true;
		}
		
		if(validPhoneNumber){
			if(validCurrentResponsibility){
				if(validCareerAspiration){
					alertify.set({ labels: {
					    ok     : '<fmt:message key="btn.confirm.ok"></fmt:message>',
					    cancel : '<fmt:message key="btn.confirm.cancel"></fmt:message>'
					} });
					
					alertify.confirm('<p>Sistem akan mengunci jawaban Anda</p> <p>Anda tidak bisa mengubah kembali jawaban Anda</p> <p>Anda akan diarahkan ke halaman <i>Prestasi Karir</i></p> <p> Apakah Anda yakin akan mengirimkan jawaban Anda sekarang ?</p>', function (e) {
					    if (e) {
					    	$("body").loading();
					    	$("#factsheetform").submit();
					    } else {
					        // user clicked "cancel"
					    }
					});
				}else{
					alertify.set({ labels: {
					    ok     : '<fmt:message key="btn.confirm.ok"></fmt:message>',
					    cancel : '<fmt:message key="btn.confirm.cancel"></fmt:message>'
					} });
					
					alertify.alert("Mohon isi aspirasi karir Anda")
				}
			}else{
				alertify.set({ labels: {
				    ok     : '<fmt:message key="btn.confirm.ok"></fmt:message>',
				    cancel : '<fmt:message key="btn.confirm.cancel"></fmt:message>'
				} });
				
				alertify.alert("Mohon isi tanggung jawab utama Anda")
			}
		}else
			{
			alertify.set({ labels: {
			    ok     : '<fmt:message key="btn.confirm.ok"></fmt:message>',
			    cancel : '<fmt:message key="btn.confirm.cancel"></fmt:message>'
			} });
			
			alertify.alert("Mohon isi no. HP Anda")
			
			}
	});
});

function beginTest()
{
	
}
</script>