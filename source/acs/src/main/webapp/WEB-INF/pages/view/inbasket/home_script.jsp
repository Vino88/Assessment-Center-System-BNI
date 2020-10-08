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
	alertify.confirm('Apakah Anda yakin akan memulai test?', 
			function(e){
				if(e)
				{
					window.location.href = "${pageContext.request.contextPath}/controller/pages/view/inbasket/detail/${list[0].id}";
				}
			}
    );
}
</script>