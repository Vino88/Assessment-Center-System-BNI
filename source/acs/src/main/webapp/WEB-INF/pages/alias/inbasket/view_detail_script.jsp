<%@include file="view_footscript.jsp" %>

<script>

function changeFrame(index, isMemo)
{
	if(isMemo)
	{
		$("#frameMemo"+index).css("display","");
		$("#frameSrb"+index).css("display","none");
		
		$("#framepanel"+index).css("display","");
		$("#txtInbasket"+index).focus();
		$("#x_title_main").css("display","");
	}
	else
	{
		$("#frameMemo"+index).css("display","none");
		$("#frameSrb"+index).css("display","");
		
		$("#framepanel"+index).css("display","");
		$("#txtInbasket"+index).focus();
		$("#x_title_main").css("display","");
	}
		
}

function hideFrame(index)
{
	$("#frameMemo"+index).css("display","none");
	$("#frameSrb"+index).css("display","none");
	
	$("#framepanel"+index).css("display","none");
	$("#x_title_main").css("display","none");
}
</script>