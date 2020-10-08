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
			  height : 200,
			  readonly : 1
			});
		
		
		$("tr[id^='clBehaviour']:nth-child(${participantCompetencyLibraryFinalResult.inbasketRating })").css( "display", "" );
		$( "#starRating a:lt(${participantCompetencyLibraryFinalResult.inbasketRating })" ).removeClass("fa-star-o");
		$( "#starRating a:lt(${participantCompetencyLibraryFinalResult.inbasketRating })" ).addClass("fa-star");
		
		$( "tr[id^='clBehaviour']:nth-child(${participantCompetencyLibraryFinalResult.inbasketRating})").css("background-color", "#337ab7" );
		$( "tr[id^='clBehaviour']:nth-child(${participantCompetencyLibraryFinalResult.inbasketRating})").css("color", "#fff" );
		
		$("tr[id^='clBehaviour']:gt(${participantCompetencyLibraryFinalResult.inbasketRating - 1})").css( "display", "" );
	
	});
</script>