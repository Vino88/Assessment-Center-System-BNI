<%@include file="/WEB-INF/pages/alias/analysys/view_detail_script.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
<c:when test="${analysys.analysysType == 'VIDEO' }">
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
          progressTimer = setTimeout( progress, 2000 );
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
        progressTimer = setTimeout( progress, 500 );
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
       
    }
  

function download() {
	  var blob = new Blob(recordedBlobs, {type: 'video/webm'});
	
	  $("#save").attr("disabled","disabled");
	  $("#dialog").dialog( "open" );
	  
	  var fd = new FormData();
	  fd.append('file', blob);
	   $.ajax({
	      type: 'POST',
	      url: '${pageContext.request.contextPath}/controller/view/visionspeech/play',
	      data: fd,
	      processData: false,
	      contentType: false
	  }).done(function(data) {
	         console.log(data);
	         doneUpload();
	  });
	  
	  
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
<script src="${initParam.staticfiles}/static/vs/adapter.js"></script>
<script src="${initParam.staticfiles}/static/vs/main2.js"></script>
</c:when>

</c:choose>

