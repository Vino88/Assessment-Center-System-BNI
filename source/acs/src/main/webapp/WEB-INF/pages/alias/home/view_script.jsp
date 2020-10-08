<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages"/>


<c:if test='${sessionScope.session_auth_participant.participantStatus == "SESSION_I" }'>
<script>
	$(document).ready(function() {
		$('#wizard_home').smartWizard({
		
			
			<c:if test='${sessionScope.session_participant_log_during.logDuringStatus == "FACTSHEET" }' >
			labelFinish : 'Data Diri',
    	</c:if>
			
			<c:if test='${sessionScope.session_participant_log_during.logDuringStatus == "CAP" }' >
			labelFinish : 'Prestasi Karir',
    	</c:if>
			
			<c:if test='${sessionScope.session_participant_log_during.logDuringStatus == "LCB" }' >
			labelFinish : 'Leadership Inventory',
    	</c:if>
			labelNext: '<fmt:message key="btn.next"></fmt:message>',
			labelPrevious: '<fmt:message key="btn.prev"></fmt:message>',
			
			onFinish : function()
			{
				
				$("body").loading();
				

			<c:if test='${sessionScope.session_participant_log_during.logDuringStatus == "FACTSHEET" }' >
				window.location.href = "${pageContext.request.contextPath}/controller/pages/view/factsheet";
	    	</c:if>
				
				<c:if test='${sessionScope.session_participant_log_during.logDuringStatus == "CAP" }' >
				window.location.href = "${pageContext.request.contextPath}/controller/pages/view/cap/home";
	    	</c:if>
				
				<c:if test='${sessionScope.session_participant_log_during.logDuringStatus == "LCB" }' >
				window.location.href = "${pageContext.request.contextPath}/controller/pages/view/lcb/home";
	    	</c:if>
				
		    	
                
			},
			transitionEffect : 'slide',
			enableAllSteps : false
		});
		
		$('.buttonNext').addClass('btn btn-success');
		$('.buttonPrevious').addClass('btn btn-primary');
		$('.buttonFinish').addClass('btn btn-default');
	});
</script>
</c:if>

<c:if test='${sessionScope.session_auth_participant.participantStatus == "SESSION_II" }'>
<script>
	$(document).ready(function() {
		$('#wizard_home').smartWizard({
		
			<c:if test='${sessionScope.session_participant_log_during.logDuringStatus == "INBASKET" }' >
				labelFinish : 'Simulasi Pemecahan Masalah',
	    	</c:if>
			<c:if test='${sessionScope.session_participant_log_during.logDuringStatus == "ANALYSYS" }' >
				labelFinish : 'Simulasi Rencana Bisnis',
	    	</c:if>
			
			labelNext: '<fmt:message key="btn.next"></fmt:message>',
			labelPrevious: '<fmt:message key="btn.prev"></fmt:message>',
			
			onFinish : function()
			{
				
				$("body").loading();
				
		    	
		    	<c:if test='${sessionScope.session_participant_log_during.logDuringStatus == "INBASKET" }' >
		    	window.location.href = "${pageContext.request.contextPath}/controller/pages/view/inbasket/yourrole";
		    	</c:if>
		    	
				<c:if test='${sessionScope.session_participant_log_during.logDuringStatus == "ANALYSYS" }' >
				window.location.href = "${pageContext.request.contextPath}/controller/pages/view/analysys/home";
		    	</c:if>
                
			},
			transitionEffect : 'slide',
			enableAllSteps : false
		});
		
		$('.buttonNext').addClass('btn btn-success');
		$('.buttonPrevious').addClass('btn btn-primary');
		$('.buttonFinish').addClass('btn btn-default');
	});
</script>
</c:if>