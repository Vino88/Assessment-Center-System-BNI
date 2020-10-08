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
				<form  method="POST" action="${pageContext.request.contextPath}/controller/pages/view/analysys/detail/visionspeech" class="form-horizontal row-fluid">
					<input type="hidden" value="${analysys.id}" name="analysysId" />
               		<input type="hidden" value="${participantAnalysys.id}" name="participantAnalysysId" />
                      
	           		 		
                    <div class="x_panel">
	               		<div class="x_title">
	                    	<h2>Simulasi Presentasi Visi <i class="fa fa-video-camera"></i></h2>
	                    	<div class="clearfix"></div>
	                  	</div>
		              	<div class="x_content">
		                  	<p>Berdasarkan Rencana Bisnis yang telah anda susun, buatlah video presentasi visi agar seluruh karyawan dan pimpinan GSI mendukung rencana bisnis tersebut. Gunakan kedua pertanyaan dibawah untuk menyusun draft rekaman Anda :</p>
		                  	<ul>
			                  	<c:forEach items="${sessionScope.session_vision_speech_list}" var="vs">
			                  		<li>
			                    		<p>${vs.question}</p>
			                    	</li>
			                  	</c:forEach>
		                  	</ul>
		                  	<p>Rekamlah video Anda selama maksimal <b>3 (tiga) menit</b>, dan hanya dalam <b>2 (dua) kali</b> kesempatan. Untuk itu Anda diminta untuk membuat draft penjelasan sebelum merekam.</p>			
		              	</div>
	              	</div>
	              	<div class="x_panel">
	               		<div class="x_title">
	                    	<h2>Draft Presentasi</h2>
	                    	<div class="clearfix"></div>
	                  	</div>
		              	<div class="x_content">
		              		<textarea rows="20" name="answer" class="form-control col-md-7 col-xs-12 resizable_textarea">${participantAnalysys.answer}</textarea>
           		 		</div>
		            </div>
	           		<div class="x_panel">
	              		<div class="x_title"></div>
	              		<div class="x_content">
	              			<div class="form-group">
	                        	<div class="col-md-6 col-sm-6 col-xs-12">
		                          
		                         <a class="btn btn-primary" href="${pageContext.request.contextPath}/controller/pages/view/analysys/detail/${prevId}"><fmt:message key="btn.prev"></fmt:message></a>
		                          <button type="submit" class="btn btn-success">Lanjut</button>
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
