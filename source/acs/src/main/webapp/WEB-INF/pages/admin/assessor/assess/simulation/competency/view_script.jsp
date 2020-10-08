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
		$("#sbmtAssess").click(function(e){
			if($("#rating").val() == '' || $("#rating").val() == "0" || $("#rating").val() == 0)
			{
				alertify.alert("Please Choose Rating this competency");
			}
			else if(!$.trim($("#notes").val()))
			{
				alertify.alert("Please fill the Assessor Note for this competency");
			}
			else 
			{
				$("#assessForm").submit();
				
			}
			
		});
		$("#writingAssistanceBtn").click(function(e){
			$( "#dialog-confirm" ).dialog({
			      resizable: false,
			      height: "auto",
			      width: 1200,
			      modal: true,
			      buttons: {
			        "Close": function() {
			          $( this ).dialog( "close" );
			        }
			      }
			    });
		});
		
		
		$("tr[id^='clBehaviour']").css( "display", "none" );
		<c:choose>
		<c:when test="${participantCompetencyLibraryFinalResult.inbasketRating > 0 }">
		$("tr[id^='clBehaviour']:nth-child(${participantCompetencyLibraryFinalResult.inbasketRating })").css( "display", "" );
		$( "#starRating a:lt(${participantCompetencyLibraryFinalResult.inbasketRating })" ).removeClass("fa-star-o");
		$( "#starRating a:lt(${participantCompetencyLibraryFinalResult.inbasketRating })" ).addClass("fa-star");
		
		$( "tr[id^='clBehaviour']:nth-child(${participantCompetencyLibraryFinalResult.inbasketRating})").css("background-color", "#337ab7" );
		$( "tr[id^='clBehaviour']:nth-child(${participantCompetencyLibraryFinalResult.inbasketRating})").css("color", "#fff" );
		
		$("tr[id^='clBehaviour']:gt(${participantCompetencyLibraryFinalResult.inbasketRating - 1})").css( "display", "" );
	</c:when>
	
	<c:otherwise>
		$("tr[id^='clBehaviour']:nth-child(3)").css( "display", "" );
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
	
	function clearRadio()
	{
		$("#containerRadio input:checked").iCheck('uncheck');
	}
</script>