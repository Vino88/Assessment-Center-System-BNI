<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages"/>

<script src="${initParam.staticfiles}/static/js/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
<script src='${initParam.staticfiles}/static/js/tinymce/tinymce.min.js'></script>
<script>
	$(document).ready(function() {
		tinymce.init({
			  selector: '.textareaTinyMce',
			  menubar: false,
			  statusbar : false,
			  height : 280,
			  readonly : 1
			});
		
		$("#sbmtAssess").click(function(e){
			
			var isvalidRating = false;
			var isvalidNotes = false;
			if(!validateRating())
			{
				alertify.alert("Please Choose Rating for this competency");
			}
			else if(!validateNotes())
			{
				alertify.alert("Please fill the Assessor Note for this competency");
			}else{
				$("#assessForm").submit();
			}
			
		});
		
		<c:forEach items="${listParticipantCapCompetencyLibraryCapDto}" var="dto">
		$("#competencyLibrary${dto.competencyLibrary.id} tr[id^='clBehaviour']").css( "display", "none" );
		<c:choose>
			<c:when test="${dto.participantCompetencyLibraryFinalResult.capRating > 0 }">
				$("#competencyLibrary${dto.competencyLibrary.id} tr[id^='clBehaviour']:nth-child(${dto.participantCompetencyLibraryFinalResult.capRating })").css( "display", "" );
				$( "#competencyLibrary${dto.competencyLibrary.id} #starRating a:lt(${dto.participantCompetencyLibraryFinalResult.capRating })" ).removeClass("fa-star-o");
				$( "#competencyLibrary${dto.competencyLibrary.id} #starRating a:lt(${dto.participantCompetencyLibraryFinalResult.capRating })" ).addClass("fa-star");
				
				$( "#competencyLibrary${dto.competencyLibrary.id} tr[id^='clBehaviour']:nth-child(${dto.participantCompetencyLibraryFinalResult.capRating})").css("background-color", "#337ab7" );
				$( "#competencyLibrary${dto.competencyLibrary.id} tr[id^='clBehaviour']:nth-child(${dto.participantCompetencyLibraryFinalResult.capRating})").css("color", "#fff" );
				
				$("#competencyLibrary${dto.competencyLibrary.id} tr[id^='clBehaviour']:gt(${dto.participantCompetencyLibraryFinalResult.capRating - 1})").css( "display", "" );
			</c:when>
			
			<c:otherwise>
				$("#competencyLibrary${dto.competencyLibrary.id} tr[id^='clBehaviour']:nth-child(3)").css( "display", "" );
			</c:otherwise>
		
		</c:choose>
		</c:forEach>
	
	
	});
	
	function validateRating()
	{
		var isvalidRating = true;
		<c:forEach items="${listParticipantCapCompetencyLibraryCapDto}" var="dto">
		
		if($("#rating${dto.competencyLibrary.id}").val() == '' || $("#rating${dto.competencyLibrary.id}").val() == "0" || $("#rating${dto.competencyLibrary.id}").val() == 0)
		{
			isvalidRating = false;
			
		}
		
		</c:forEach>
		
		return isvalidRating;
	}
	
	function validateNotes()
	{
		var isvalidNotes = true;
		<c:forEach items="${listParticipantCapCompetencyLibraryCapDto}" var="dto">
		
			if(!$.trim($("#notes${dto.competencyLibrary.id}").val())){
				isvalidNotes = false;
			}
		</c:forEach>
		
		return isvalidNotes;
	}
	
	function notMeetBehaviourLevel(cid, level, id)
	{
		$("#competencyLibrary" + cid +" " + "#starRating a" ).removeClass("fa-star");
		$("#competencyLibrary" + cid +" " + "#starRating a" ).removeClass("fa-star-o");
		$("#competencyLibrary" + cid +" " + "#starRating a" ).addClass("fa-star-o");
		$("#competencyLibrary" + cid +" " +"#rating"+cid).val('');
		$("#competencyLibrary" + cid +" " + "#ratingDisplay").text('');
		$("#competencyLibrary" + cid +" " + "#clBehaviour"+id ).prev().css( "display", "" );
		
		$("#competencyLibrary" + cid +" " +"tr[id^='clBehaviour']").css( "background-color", "" );
		$("#competencyLibrary" + cid +" " +"tr[id^='clBehaviour']").css( "color", "#73879C" );
	}

	function chooseBehaviourLevel(cid, level, id)
	{
		$("#competencyLibrary" + cid +" " + "#starRating a" ).removeClass("fa-star");
		$("#competencyLibrary" + cid +" " + "#starRating a" ).removeClass("fa-star-o");
		$("#competencyLibrary" + cid +" " + "#starRating a" ).addClass("fa-star-o");
		
		$("#competencyLibrary" + cid +" " + "#starRating a:lt("+level+")" ).removeClass("fa-star-o");
		$("#competencyLibrary" + cid +" " + "#starRating a:lt("+level+")" ).addClass("fa-star");
		
		$("#competencyLibrary" + cid +" " +"#rating"+cid).val(level);
		$("#competencyLibrary" + cid +" " +"#ratingDisplay").text(level);
		$("#competencyLibrary" + cid +" " + "#clBehaviour"+id ).next().css( "display", "" );
		
		$("#competencyLibrary" + cid +" " +"tr[id^='clBehaviour']").css( "background-color", "" );
		$("#competencyLibrary" + cid +" " +"tr[id^='clBehaviour']").css( "color", "#73879C" );
		
		$("#competencyLibrary" + cid +" " + "#clBehaviour"+id ).css("background-color", "#337ab7" );
		$("#competencyLibrary" + cid +" " + "#clBehaviour"+id ).css("color", "#fff" );
		
	}
	
	function clearRadio(indexRadio)
	{
		$("#containerRadio"+ indexRadio+" input:checked").iCheck('uncheck');
	}
	
</script>