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
		
		
		<c:forEach items="${listParticipantCapCompetencyLibraryCapDto}" var="dto">
			$("#competencyLibrary${dto.competencyLibrary.id} tr[id^='clBehaviour']:nth-child(${dto.participantCompetencyLibraryFinalResult.capRating })").css( "display", "" );
			$( "#competencyLibrary${dto.competencyLibrary.id} #starRating a:lt(${dto.participantCompetencyLibraryFinalResult.capRating })" ).removeClass("fa-star-o");
			$( "#competencyLibrary${dto.competencyLibrary.id} #starRating a:lt(${dto.participantCompetencyLibraryFinalResult.capRating })" ).addClass("fa-star");
			
			$( "#competencyLibrary${dto.competencyLibrary.id} tr[id^='clBehaviour']:nth-child(${dto.participantCompetencyLibraryFinalResult.capRating})").css("background-color", "#337ab7" );
			$( "#competencyLibrary${dto.competencyLibrary.id} tr[id^='clBehaviour']:nth-child(${dto.participantCompetencyLibraryFinalResult.capRating})").css("color", "#fff" );
			
		</c:forEach>
	
	
	});
	
</script>