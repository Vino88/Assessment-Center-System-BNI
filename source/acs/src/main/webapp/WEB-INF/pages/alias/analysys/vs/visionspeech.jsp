<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages"/>
<style>
.centre {
    margin-left: auto;
    margin-right: auto;
    display: block
}
</style>
<div class="main_container" role="main" >
	<div class="right_col">
      	<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<form  method="POST" action="${pageContext.request.contextPath}/controller/pages/alias/analysys/detail/visionspeechvs" class="form-horizontal row-fluid">
					<input type="hidden" value="${analysys.id}" name="analysysId" />
               		<input type="hidden" value="${participantAnalysys.id}" name="participantAnalysysId" />
                  
	           		 		
	           		 		
                    <div class="x_panel">
	               		<div class="x_title">
	                    	<h2>Simulasi Presentasi Visi <i class="fa fa-video-camera"></i></h2>
	                    	<div class="clearfix"></div>
	                  	</div>
		              	<div class="x_content">
		                  	
		                  	<p>Rekamlah video Anda selama maksimal <b>3 (tiga) menit</b>, dan hanya dalam <b>2 (dua) kali</b> kesempatan. Untuk itu Anda diminta untuk membuat draft penjelasan sebelum merekam.</p>			
	           		 		
		              	</div>
	              	</div>
	              	
	              	
	              	  <div class="col-md-8 col-xs-12">
	           		 			<div class="x_panel">
	               		<div class="x_title">
	                    	<h2>Draft Presentasi</h2>
	                    	<div class="clearfix"></div>
	                  	</div>
		              	<div class="x_content">
		              		<textarea rows="26" class="form-control col-md-7 col-xs-12 resizable_textarea" readonly="readonly">${participantAnalysys.answer}</textarea>
           		 	
	                    	
		              	</div>
		              	
		              	</div>
	           		 </div>
	           		 		
	           		 	<div class="col-md-4 col-xs-12" id="" >
	           		 		<div class="x_panel">
	               		<div class="x_title">
	                    	<h2>Presentasi Visi</h2>
	                    	<div class="clearfix"></div>
	                  	</div>
		              	<div class="x_content">
		              		<div>
	           		 			<video id="gum" autoplay muted style="width:100%"></video>
							    <video id="recorded" autoplay style="display:none"></video>
								<div>
								      <a id="record" class="btn btn-success">Rekam</a>
								      <a id="play" class="btn btn-success" style="visibility:hidden;display:none">Putar</a>
								      <a id="save" class="btn btn-success" style="visibility:hidden">Unggah Video</a>
							    </div>
						    </div>
						    
						    <div class="module-head">
								<p id="paragraphTimerVisionSpeech" style="display:none">
								    Sisa waktu perekaman: <span id="timerVisionSpeech"></span>
								</p>
								
								<p id="paragraphInfoVisionSpeech" style="display:none">
									<i class="fa fa-caret-right"></i> Untuk mengirim hasil rekaman ke server silakan klik tombol "<b>Unggah Video</b>".
									<br/><br/><i class="fa fa-caret-right"></i> Untuk mengulang rekaman silakan klik tombol "<b>Rekam</b>".
									<br/><br/><i class="fa fa-caret-right"></i> Anda dapat melihat hasil rekaman setelah video diunggah ke server.
								</p>
								<p id="paragraphPlay" style="display:none;">
									<a href="${pageContext.request.contextPath}/controller/alias/visionspeech/play" target="_action" class="btn btn-success">Putar video dari server</a>
								</p>
							</div>
		              	</div>
		              	
		              	</div>
	           		 		</div>	
                    
	           		 		
	           		 		
	           		 		
                 	<div class="x_panel">
	              		<div class="x_title"></div>
	              		<div class="x_content">
	              			<div class="form-group">
	                        	<div class="col-md-6 col-sm-6 col-xs-12">
		                          
		                          <a class="btn btn-primary" href="${pageContext.request.contextPath}/controller/pages/alias/home">Beranda</a>
		                          
	                        	</div>
	                      	</div>
	              		</div>
	              	</div>
	        	</form>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
</div>
