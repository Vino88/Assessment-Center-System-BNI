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
			if($("#rating").val() == '' || $("#rating").val() == "0" || $("#rating").val() == 0)
			{
				alertify.alert("Please Choose Rating this competency");
			}
			else
			{
				$("#assessForm").submit();
				
			}
			
		});
		
		$("tr[id^='clBehaviour']").css( "display", "none" );
		<c:choose>
			<c:when test="${participantCompetencyLibraryFinalResult.capRating > 0 }">
				$("tr[id^='clBehaviour']:nth-child(${participantCompetencyLibraryFinalResult.capRating })").css( "display", "" );
				$( "#starRating a:lt(${participantCompetencyLibraryFinalResult.capRating })" ).removeClass("fa-star-o");
				$( "#starRating a:lt(${participantCompetencyLibraryFinalResult.capRating })" ).addClass("fa-star");
				
				$( "tr[id^='clBehaviour']:nth-child(${participantCompetencyLibraryFinalResult.capRating})").css("background-color", "#337ab7" );
				$( "tr[id^='clBehaviour']:nth-child(${participantCompetencyLibraryFinalResult.capRating})").css("color", "#fff" );
				
				$("tr[id^='clBehaviour']:gt(${participantCompetencyLibraryFinalResult.capRating - 1})").css( "display", "" );
			</c:when>
			
			<c:otherwise>
				$("tr[id^='clBehaviour']:nth-child(4)").css( "display", "" );
			</c:otherwise>
		
		</c:choose>
		
	
	
	});
	
	function notMeetBehaviourLevel(level, id)
	{
		$( "#starRating a" ).removeClass("fa-star");
		$( "#starRating a" ).removeClass("fa-star-o");
		$( "#starRating a" ).addClass("fa-star-o");
		$("#rating").val('');
		$("#ratingDisplay").text('');
		$( "#clBehaviour"+id ).prev().css( "display", "" );
		
		$("tr[id^='clBehaviour']").css( "background-color", "" );
		$("tr[id^='clBehaviour']").css( "color", "#73879C" );
	}

	function chooseBehaviourLevel(level, id)
	{
		$( "#starRating a" ).removeClass("fa-star");
		$( "#starRating a" ).removeClass("fa-star-o");
		$( "#starRating a" ).addClass("fa-star-o");
		
		$( "#starRating a:lt("+level+")" ).removeClass("fa-star-o");
		$( "#starRating a:lt("+level+")" ).addClass("fa-star");
		
		$("#rating").val(level);
		$("#ratingDisplay").text(level);
		$( "#clBehaviour"+id ).next().css( "display", "" );
		
		$("tr[id^='clBehaviour']").css( "background-color", "" );
		$("tr[id^='clBehaviour']").css( "color", "#73879C" );
		
		$( "#clBehaviour"+id ).css("background-color", "#337ab7" );
		$( "#clBehaviour"+id ).css("color", "#fff" );
		
	}
</script>