<form action="${pageContext.request.contextPath}/controller/pages/admin/assessor/assess/cap/finish/${participant.id}" method="POST" id="formFinish" syle="display:none">

</form>
<script>
	$(document).ready(function() {
		$("#btnFinishNow").click(function(e){
					
			alertify.confirm('Are you sure you want to finish now?', 
					function(e){
						if(e)
						{
							$("#formFinish").submit();
						}
					}
		    );
			
		});
		
		$("#btnUnfinished").click(function(e){
			alertify.alert('Please finish all competency rating, then you can finish the assessment.')
		});
	});

</script>