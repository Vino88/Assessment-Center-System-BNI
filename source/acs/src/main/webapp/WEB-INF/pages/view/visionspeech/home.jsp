<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages"/>

<div class="main_container" role="main" >
	<div class="right_col">
      	<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
           		<div class="x_panel">
	           		<div class="x_title">
	           			<h2>Simulasi Presentasi Visi
							</h2>
							
	                   	<div class="clearfix"></div>
	           		</div>
	           		<div class="x_content">
                      	<p>Dalam simulasi ini Anda diminta untuk merekam video berisikan penjelasan mengenai rencana bisnis Anda selama maksimal ${sessionScope.session_system_config_timer.TIMER_VISION_SPEECH_RECORD} menit. Anda memiliki waktu <b>${sessionScope.session_system_config_timer.TIMER_VISION_SPEECH_ALL} menit</b> untuk menyelesaikan seluruh tugas ini.<br/><br/>
                      		Jika Anda sudah siap, silakan klik tombol "Mulai" untuk memulai simulasi ini.
                      	</p>

                      	<a href="javascript:beginTest()" type="button" class="btn btn-success" role="button">Mulai</a>
                      	
					</div>
              	</div>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
</div>
