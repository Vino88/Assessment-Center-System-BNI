<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages"/>

<script>
	$(document).ready(function() {
		$('#wizard_memo').smartWizard({		
			
			labelFinish : 'Pertanyaan',
			labelNext: '<fmt:message key="btn.next"></fmt:message>',
			labelPrevious: '<fmt:message key="btn.prev"></fmt:message>',
			
			onFinish : function()
			{
				window.location.href = "${pageContext.request.contextPath}/controller/pages/view/inbasket/home";
                
			},
			
			
			transitionEffect : 'slide',
			enableAllSteps : false
		});
		
		$('.buttonNext').addClass('btn btn-success');
		$('.buttonPrevious').addClass('btn btn-primary');
		$('.buttonFinish').addClass('btn btn-default');
	});
</script>