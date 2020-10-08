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
			<c:if test='${sessionScope.session_participant_log_during.logDuringStatus == "ASPIRATION" }' >
			labelFinish : 'Aspirasi',
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
	    	<c:if test='${sessionScope.session_participant_log_during.logDuringStatus == "ASPIRATION" }' >
			window.location.href = "${pageContext.request.contextPath}/controller/pages/view/aspiration";
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
				
				<c:choose>
            	<c:when test="${empty session_participant_log_during.inbasketReadMemoStartTime || session_participant_log_during.articulateSpmRead == false || session_participant_log_during.articulateMemoRead == false}" >
            		labelFinish : 'Latar Belakang',
            	</c:when>
            	<c:otherwise>
            		labelFinish : 'Simulasi Pemecahan Masalah',
		        </c:otherwise>
		        		
		        </c:choose>
            	
	    	</c:if>
			<c:if test='${sessionScope.session_participant_log_during.logDuringStatus == "ANALYSYS" }' >
			labelFinish : 'Simulasi Rencana Bisnis',
	    	</c:if>
			
			<c:if test='${sessionScope.session_participant_log_during.logDuringStatus == "VISION_SPEECH" }' >
			labelFinish : 'Simulasi Presentasi Visi',
    	</c:if>
			
			labelNext: '<fmt:message key="btn.next"></fmt:message>',
			labelPrevious: '<fmt:message key="btn.prev"></fmt:message>',
			
			onFinish : function()
			{
				
				
				
		    	
		    	<c:if test='${sessionScope.session_participant_log_during.logDuringStatus == "INBASKET" }' >
		    	
		    	<c:choose>
            	<c:when test="${empty session_participant_log_during.inbasketReadMemoStartTime || session_participant_log_during.articulateSpmRead == false || session_participant_log_during.articulateMemoRead == false}" >
            	alertify.set({ labels: {
		    	    ok     : '<fmt:message key="btn.confirm.ok"></fmt:message>',
		    	    cancel : '<fmt:message key="btn.confirm.cancel"></fmt:message>'
		    	} });
		    	alertify.confirm('Penting bagi Anda untuk membaca dan memahami semua informasi dalam latar belakang organisasi dikarenakan akan digunakan untuk menjawab semua pertanyaan dalam Simulasi Pemecahan Masalah dan Simulasi Rencana Bisnis. Untuk itu kami sarankan Anda menggunakan waktu selama <b>60 (enam puluh) menit</b> secara optimal mempelajarinya.<br/> Apakah Anda yakin akan memulai slide informasi?', 
		    			function(e){
		    				if(e)
		    				{
		    					$("body").loading({
		    						  stoppable: true
		    					});
		    					window.location.href = "${pageContext.request.contextPath}/controller/pages/view/inbasket/background";
		    				}
		    				
		    			}
		        );
            	</c:when>
            	
            	<c:otherwise>
            		window.location.href = "${pageContext.request.contextPath}/controller/pages/view/inbasket";
		        </c:otherwise>
		        		
		        </c:choose>
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	</c:if>
		    	
				<c:if test='${sessionScope.session_participant_log_during.logDuringStatus == "ANALYSYS" }' >
				$("body").loading({
					  stoppable: true
				});
				
				<c:choose>
                <c:when test="${empty session_participant_log_during.analysysStartTime }" >
                	window.location.href = "${pageContext.request.contextPath}/controller/pages/view/analysys/home";
                	</c:when>
                	<c:when test="${empty session_participant_log_during.analysysFinishTime }" >
                	window.location.href = "${pageContext.request.contextPath}/controller/pages/view/analysys";
                        
                	</c:when>
                	
                </c:choose>
                
				
		    	</c:if>
		    	
		    	
		    	<c:if test='${sessionScope.session_participant_log_during.logDuringStatus == "VISION_SPEECH" }' >
				$("body").loading({
					  stoppable: true
				});
				window.location.href = "${pageContext.request.contextPath}/controller/pages/view/visionspeech/home";
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

						