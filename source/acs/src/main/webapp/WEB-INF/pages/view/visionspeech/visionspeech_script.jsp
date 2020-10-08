<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages"/>

<span id="timer" style="display:none"></span>
<script src='${staticfiles}/static/js/tinymce/tinymce.min.js'></script>

<script>
var GLOBAL_ALREADY_UPLOAD =false;
function nextPageVs()
{
	<c:choose>
	<c:when test="${sessionScope.session_participant_log_during.visionUploaded }">
	window.location.href = "${pageContext.request.contextPath}/controller/pages/view/visionspeech/beforefinish";
	</c:when>
	<c:otherwise>
	if(GLOBAL_ALREADY_UPLOAD)
	{
	window.location.href = "${pageContext.request.contextPath}/controller/pages/view/visionspeech/beforefinish";
	}
else
	{
	alertify.alert("Mohon unggah hasil rekaman Anda.");
	}
	</c:otherwise>

</c:choose>

	
	
	
}

	$(document).ready(function() {		
		tinymce.init({
			  selector: 'textarea',
			  menubar: false,
			  statusbar : false,
			  readonly : 1,
			
			  toolbar: 'undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | outdent indent ',
			  height : 285
			});
		
		$RIGHT_COL.css("min-height",$(window).height());
		
		var startTime = new Date(${sessionScope.session_participant_log_during.visionStartTime.time });
		var endTime = new Date(${sessionScope.session_participant_log_during.visionEndTime.time });
var startMoment = moment(startTime);
var endMoment = moment(endTime);



		
		
		 
		

		var remain = ${remainingTime};
		var d2 =moment().add(remain,'seconds').toDate();
		
		if(remain <= 0)
		{
			alertify.error("Waktu telah habis");
			$("body").loading();
			window.location.href = "${pageContext.request.contextPath}/controller/pages/view/visionspeech/expired";
		}
		
		var myToast = $.toast({
		    heading: 'Waktu tersedia',
		    text: '0:00:00',
		    icon: 'info',
		    allowToastClose: false,
		    position: 'top-center',
		    hideAfter: false
		});
		
		$('#timer').tinyTimer({ 
			to: d2, 
			onTick: function (val) {
				
				
				
				if(val.M == 10 && val.s == 0)
					{
					alertify.error("Waktu tersisa 10 Menit Lagi");
					}
				else if(val.M == 5 && val.s == 0)
				{
				alertify.error("Waktu tersisa 5 Menit Lagi");
				}
				else if(val.M == 0 && val.s == 30)
				{
				alertify.error("Dalam 30 detik, system akan mengunci jawaban Anda.");
				}
				else if(val.M == 0 && val.s == 1)
				{
					window.location.href = "${pageContext.request.contextPath}/controller/pages/view/visionspeech/finish";
				}
				
				myToast.update({
					heading: 'Waktu tersedia',
				    text: $('#timer').text(),
				    icon: 'info',
				    allowToastClose: false,
				    position: 'top-center',
				    
				    hideAfter: false
			    });
				
		    },
			format: '%h:%0m:%0s' 
			
		});
		
		
		
		
	});
</script>

<script>


$( function() {
   var progressTimer,
      progressbar = $( "#progressbar" ),
      progressLabel = $( ".progress-label" ),
      dialogButtons = [{
        text: "Cancel",
        click: closeDownload
      }],
      dialog = $( "#dialog" ).dialog({
        autoOpen: false,
        closeOnEscape: false,
        resizable: false,
        buttons: dialogButtons,
        open: function() {
          progressTimer = setTimeout( progress, 5000 );
        },
        beforeClose: function() {
          downloadButton.button( "option", {
            disabled: false,
            label: "Unggah Video"
          });
        }
      }),
      downloadButton = $( "#save" )
        .button();
 
    progressbar.progressbar({
      value: false,
      change: function() {
        progressLabel.text( "Current Progress: " + progressbar.progressbar( "value" ) + "%" );
      },
      complete: function() {
        progressLabel.text( "Complete!" );
        dialog.dialog( "option", "buttons", [{
          text: "Close",
          click: closeDownload
        }]);
        $(".ui-dialog button").last().trigger( "focus" );
      }
    });
 
    function progress() {
      var val = progressbar.progressbar( "value" ) || 0;
 
      progressbar.progressbar( "value", val + Math.floor( Math.random() * 3 ) );
 
      if ( val <= 85 ) {
        progressTimer = setTimeout( progress, 5000 );
      }
    }
 
    function closeDownload() {
      clearTimeout( progressTimer );
      dialog
        .dialog( "option", "buttons", dialogButtons )
        .dialog( "close" );
      progressbar.progressbar( "value", false );
      progressLabel
        .text( "Proses unggah sedang berlangsung..." );
      downloadButton.trigger( "focus" );
    }
});   
    function doneUpload()
    {
        $("#progressbar").progressbar( "value", 100 );
        
        $("#save").removeAttr("disabled");
        $("#paragraphPlay").css("display","block");
        
        clearTimeout( progressTimer );
        GLOBAL_ALREADY_UPLOAD = true;
    }
  

function download() {
	alertify.set({ labels: {
	    ok     : '<fmt:message key="btn.confirm.ok"></fmt:message>',
	    cancel : '<fmt:message key="btn.confirm.cancel"></fmt:message>'
	} });
	
	if(GLOBAL_NUM_OF_TRY_VS <= 1)
		{
		alertify.confirm('Apakah Anda yakin akan unggah video pertama Anda? Jika ya, Anda tidak dapat melakukan rekaman yang kedua', 
				function(e){
					if(e)
					{
						var blob = new Blob(recordedBlobs, {type: 'video/webm'});
						GLOBAL_NUM_OF_TRY_VS = GLOBAL_NUM_OF_TRY_VS + 1;
						  $("#save").attr("disabled","disabled");
						  $("#dialog").dialog( "open" );
						  GLOBAL_ALREADY_UPLOAD = true;
						  var fd = new FormData();
						  fd.append('file', blob);
						   $.ajax({
						      type: 'POST',
						      url: '${pageContext.request.contextPath}/controller/pages/view/visionspeech/play',
						      data: fd,
						      processData: false,
						      contentType: false
						  }).done(function(data) {
						         console.log(data);
						         doneUpload();
						         GLOBAL_ALREADY_UPLOAD = true;
						         $("#numOfRecord").text(GLOBAL_NUM_OF_TRY_VS);
						  });
					}
				}
	    );
		
		}else
			{
			var blob = new Blob(recordedBlobs, {type: 'video/webm'});
			GLOBAL_NUM_OF_TRY_VS = GLOBAL_NUM_OF_TRY_VS + 1;
			  $("#save").attr("disabled","disabled");
			  $("#dialog").dialog( "open" );
			  GLOBAL_ALREADY_UPLOAD = true;
			  var fd = new FormData();
			  fd.append('file', blob);
			   $.ajax({
			      type: 'POST',
			      url: '${pageContext.request.contextPath}/controller/pages/view/visionspeech/play',
			      data: fd,
			      processData: false,
			      contentType: false
			  }).done(function(data) {
			         console.log(data);
			         doneUpload();
			         GLOBAL_ALREADY_UPLOAD = true;
			         $("#numOfRecord").text(GLOBAL_NUM_OF_TRY_VS);
			  });
			
			}
	
	
	
	
	  
	  
}



var GLOBAL_TINY_TIMER_VS = null;
var d = new Date();
d.setMinutes(d.getMinutes()+ 3);
GLOBAL_TINY_TIMER_VS = $('#timerVisionSpeech').tinyTimer({ 
	to: d,
	onEnd : function (val) {
		toggleRecording();
		alertify.alert('Waktu Anda untuk vision speech habis (3 menit). Anda bisa tekan tombol "Unggah" untuk kirim ke server')
	}

});
$('#timerVisionSpeech').data('tinyTimer').stop();

function onStartRecordingAction(){
	var d = new Date();
	d.setMinutes(d.getMinutes() + 3);
	$('#timerVisionSpeech').text('');
	$('#timerVisionSpeech').data('tinyTimer').restart(d);
	$("#paragraphTimerVisionSpeech").css("display","block");
	$("#paragraphInfoVisionSpeech").css("display","none");
	
}
function onStopRecordingAction(){
	$("#paragraphTimerVisionSpeech").css("display","none");
	$("#paragraphInfoVisionSpeech").css("display","block");
	
	$('#timerVisionSpeech').data('tinyTimer').stop();
	
	
}
</script>
<style>
  #progressbar {
    margin-top: 20px;
  }
 
  .progress-label {
    font-weight: bold;
    text-shadow: 1px 1px 0 #fff;
  }
 
  .ui-dialog-titlebar-close {
    display: none;
  }
  </style>
  <div id="dialog" title="File Upload">
	  <div class="progress-label">Proses unggah sedang berlangsung...</div>
	  <div id="progressbar"></div>
	</div>
<script src="${staticfiles}/static/vs/adapter.js"></script>
<script src="${staticfiles}/static/vs/main5.js"></script>
<script>
GLOBAL_NUM_OF_TRY_VS = ${sessionScope.session_participant_log_during.visionCount }
</script>
