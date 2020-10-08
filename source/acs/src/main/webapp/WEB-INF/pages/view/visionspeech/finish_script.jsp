<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages"/>
<script>
function beginTest()
{
	alertify.set({ labels: {
	    ok     : '<fmt:message key="btn.confirm.ok"></fmt:message>',
	    cancel : '<fmt:message key="btn.confirm.cancel"></fmt:message>'
	} });
	alertify.confirm('<p>Sistem akan mengunci jawaban Anda</p> <p>Anda tidak bisa mengubah kembali jawaban Anda</p> <p>Simulasi telah selesai. Anda akan diarahkan ke halaman login.</p> <p> Apakah Anda yakin akan mengirimkan jawaban Anda sekarang ?</p>', function (e) {
				if(e)
				{
					window.location.href = "${pageContext.request.contextPath}/controller/pages/view/visionspeech/finish";
				}
			}
    );
}
</script>