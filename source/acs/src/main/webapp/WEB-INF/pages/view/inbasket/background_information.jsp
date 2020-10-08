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
	           			<h2>Latar Belakang Organisasi</h2>
	                    <div class="clearfix"></div>
	           		</div>
	           		<div class="x_content">
	           		
	           		<div id="wizard_memo" class="form_wizard wizard_horizontal">
		                      <ul class="wizard_steps">
		                        <li>
		                          <a href="#step-1">
		                            <span class="step_no">1</span>
		                            <span class="step_descr">Peran Anda</span>
		                          </a>
		                        </li>
		                        
		                        <li>
		                          <a href="#step-2">
		                            <span class="step_no">2</span>
		                            <span class="step_descr">GSI</span>
		                          </a>
		                        </li>
		                        
		                        <li>
		                          <a href="#step-3">
		                            <span class="step_no">3</span>
		                            <span class="step_descr">Email</span>
		                          </a>
		                        </li>
		                        
		                      </ul>
		                      
		                      <div id="step-1">
		                      	<h2 class="StepTitle">Peran Anda</h2>
	           					<div>
	           					<iframe frameborder="0" src="${staticfiles}/static/articulate/articulate3/20170309/story.html"  width="950px" height="545px"></iframe>
	           					</div>
		                      </div>
		                      
		                      <div id="step-2">
		                      	<h2 class="StepTitle">Latar Belakang Organisasi</h2>
		                        <p>Anda dapat menggunakan informasi yang terdapat dalam latar belakang perusahaan, untuk menjawab keseluruhan simulasi hari ini.</p>
	           					<div>
	           					<iframe frameborder="0" src="${staticfiles}/static/articulate/gsi/20170330/story.html" width="950px" height="600px"></iframe>
	           					</div>
		                      </div>
		                      
		                      <div id="step-3">
		                        <h2 class="StepTitle">Email</h2>
		                        <p>Bacalah <b>10 (sepuluh)</b> email berikut, sebelum Anda menjawab pertanyaan.</p>
	           					<div>
	           						<iframe frameborder="0" src="${staticfiles}/static/memo/articulate/20170329/noflag/story.html" width="950px" height="567px"></iframe>
	           					</div>
	           					
		                      </div>
		                      
                    </div>
                    		
	           		</div>
               	</div>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
</div>
