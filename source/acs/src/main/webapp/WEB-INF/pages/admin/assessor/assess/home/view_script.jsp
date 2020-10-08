<form action="${pageContext.request.contextPath}/controller/pages/admin/assessor/assess/cap/finish/${participant.id}" method="POST" id="formFinish" syle="display:none">

</form>
<script>
	$(document).ready(function() {
		
		$("#btnIgWaitingOthers").click(function(e){
			alertify.alert('Please wait until others assessors have finish the assessment.')
		});
		
		$("#btnReviewerWaitingOthers").click(function(e){
			alertify.alert('Please wait until others assessors have finish the assessment.')
		});
	});

</script>