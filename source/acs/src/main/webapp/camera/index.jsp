<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<c:set value="${initParam.staticfiles}" var="staticfiles" scope="request"></c:set>
<%-- <c:set value="http://10.212.101.185/acsstatic" var="staticfiles" scope="request"></c:set>
 --%>
<!DOCTYPE html>
<html>

	<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Favicon -->
    <link href="${staticfiles}/static/images/favicon.ico" rel="icon" type="image/gif" sizes="16x16">
    
   	<!-- Bootstrap -->
    <link href="${staticfiles}/static/themes/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="${staticfiles}/static/themes/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="${staticfiles}/static/themes/vendors/nprogress/nprogress.css" rel="stylesheet">
	<!-- bootstrap-daterangepicker -->
    <link href="${staticfiles}/static/themes/vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
    <!-- Custom Theme Style -->
    <link href="${staticfiles}/static/themes/build/css/custom.min.css" rel="stylesheet">
	<link href="${staticfiles}/static/js/jquery-ui-1.12.1.custom/jquery-ui.min.css" rel="stylesheet">
	<link href="${staticfiles}/static/js/datetimepicker/jquery.datetimepicker.css" rel="stylesheet">
	<link href="${staticfiles}/static/js/alertify.js-0.3.11/themes/alertify.core.css" rel="stylesheet">
    <link href="${staticfiles}/static/js/alertify.js-0.3.11/themes/alertify.default.css" rel="stylesheet">
    <link href="${staticfiles}/static/js/jquery-loading/dist/jquery.loading.min.css" rel="stylesheet">
    <link href="${staticfiles}/static/js/jquery-toast/dist/jquery.toast.min.css" rel="stylesheet">
    
    <link href="${staticfiles}/static/js/slick/slick.css" rel="stylesheet">
    <link href="${staticfiles}/static/js/slick/slick-theme.css" rel="stylesheet">
    
	</head>
	
	<body class="login">
		<div>
	<div class="login_wrapper">
		<div class="animate form login_form">
			<section class="login_content">
				<form method="POST" action="">
				<h1>Test Kamera</h1>
				<p>Jika Anda tidak bisa melihat hasil kamera dibawah ini, maka mohon mengganti perangkat Anda !!</p>
				<video id="gum" autoplay muted style="width:100%"></video>
							    <video id="recorded" autoplay style="display:none"></video>
								<div>
								      <a id="record" style="visibility:hidden;display:none" class="btn btn-success">Rekam</a>
								      <a id="play" class="btn btn-success" style="visibility:hidden;display:none">Putar</a>
								      <a id="save" class="btn btn-success" style="visibility:hidden">Unggah Video</a>
							    </div>
							    
							    
				<div class="clearfix"></div>
					<div class="separator">
						<div class="clearfix"></div>
						<br />
						<div>
							<h1>
								<img src="${initParam.staticfiles}/static/images/bnv-logo.png" alt="BNV" />
							</h1>
							<p>&copy; 2017 All rights reserved.</p>
						</div>
					</div>
					</form>
			</section>
		</div>
	</div>
</div>

		
		
		
					<!-- jQuery -->
    <script src="${staticfiles}/static/themes/vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="${staticfiles}/static/themes/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="${staticfiles}/static/themes/vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="${staticfiles}/static/themes/vendors/nprogress/nprogress.js"></script>
    <!-- validator -->
    <script src="${staticfiles}/static/themes/vendors/validator/validator.js"></script>
    
    
    <script src="${staticfiles}/static/themes/vendors/jquery-knob/dist/jquery.knob.min.js"></script>
  	<!-- jQuery Smart Wizard -->
    <script src="${staticfiles}/static/themes/vendors/jQuery-Smart-Wizard/js/jquery.smartWizard.js"></script>
    <script src="${staticfiles}/static/js/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
   	<script src="${staticfiles}/static/js/alertify.js-0.3.11/lib/alertify.min.js"></script>
   	<script src="${staticfiles}/static/js/tinytimer/jquery.tinytimer.min2.js"></script>
   	
   	<script src="${staticfiles}/static/js/jquery-loading/dist/jquery.loading.min.js"></script>
   	<script src="${staticfiles}/static/js/jquery-toast/dist/jquery.toast.min.js"></script>
   	
   	<script src="${staticfiles}/static/js/slick/slick.js"></script>
					
					<!-- Autosize -->
					<script src="${initParam.staticfiles}/static/themes/vendors/autosize/dist/autosize.min.js"></script>
	
					<!-- bootstrap-daterangepicker -->
					<script src="${initParam.staticfiles}/static/themes/vendors/moment/min/moment.min.js"></script>
					<script src="${initParam.staticfiles}/static/themes/vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
					<script src="${initParam.staticfiles}/static/js/datetimepicker/build/jquery.datetimepicker.full.min.js"></script>
	
					<script src="${initParam.staticfiles}/static/themes/build/js/custom.min.js"></script>
					
					<!-- Custom Theme Scripts -->
				</div>
			</div>

<span id="timer" style="display:none"></span>
<script src='${staticfiles}/static/js/tinymce/tinymce.min.js'></script>

<script>
var GLOBAL_ALREADY_UPLOAD =false;
function nextPageVs()
{
	
	
}
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

	</body>
</html>