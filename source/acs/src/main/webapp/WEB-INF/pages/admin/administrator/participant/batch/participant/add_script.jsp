<script>
$(document).ready(function() {
	$("#generatePassword").bind("click", function(e){
		var npp =$("#empNpp").val();
		var dob = $("#empDob").val();
		
		$("#password").val("");
		
		if(npp && dob)
		{
			var pass = "";
			
			var length = 4;
			if(npp.length < 4)
			{
				length = npp.length;
			}
			pass =pass + npp.substring(0, length);
			
			
			var dobMoment = moment(dob, "DD-MM-YYYY");
			
			pass = pass + dobMoment.format("DDMM");
			
			$("#password").val(pass);
		}
		
		
	});
	
	$("#nik").bind("keyup keydown mouseenter blur", function (e){
		var val = $("#nik").val();
		$("#fullName, #email, #workingArea, #currentPositionName, #empNpp, #empDob").val("");
		
		
		if(val.length > 3)
		{
			$.ajax({
				  type: "GET",
				  cache: false,
				  url: "${pageContext.request.contextPath}/controller/rest/admin/administrator/masterdata/employee/searchemployee?nik="+val,
				  dataType: "json",
				  success: function(data, textStatus)
				  {
					  if(data)
					  {
					  	
						  if(data.nik)
					  		{
					  			$("#empNpp").val(data.nik);
					  		}
						  
						  if(data.dateOfBirth)
					  		{
					  			$("#empDob").val(moment(data.dateOfBirth).format("DD-MM-YYYY"));
					  		}
						  
						  
						if(data.fullName)
				  		{
				  			$("#fullName").val(data.fullName);
				  		}
					  	
					  	if(data.email)
				  		{
				  			$("#email").val(data.email);
				  		}
					  	
					  	
					  	if(data.workingArea)
				  		{
				  			$("#workingArea").val(data.workingArea);
				  		}
					  	
					  	
					  	if(data.currentPositionName)
				  		{
				  			$("#currentPositionName").val(data.currentPositionName);
				  		}
					  }
					  
				  },
				
				});
		}
	})
});
</script>