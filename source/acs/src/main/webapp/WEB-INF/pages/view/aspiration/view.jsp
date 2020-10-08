<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages"/>

<div class="main_container" role="main" >
	<div class="right_col">
		<div class="row">
				<div class="col-lg-32">
				
				<c:if test="${ not empty param.draftErrorMessage}" >
					<div class="alert alert-danger">
	                	${param.draftErrorMessage}     
	                 </div>	
	                 <div style="display:none;">${param.draftErrorDescription}  </div>
				</c:if>
		
	            <c:if test="${ not empty param.draftSuccessMessage}" >
	            	<div class="alert alert-success">
	                	${param.draftSuccessMessage}     
	                 </div>
	            </c:if>
	            
	                 				
				</div>
				<!-- /.col-lg-32 -->
			</div>
			
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<form id="aspirationform" name="aspirationform" method="POST" action="${pageContext.request.contextPath}/controller/pages/view/aspiration" class="form-horizontal row-fluid">
					<div class="x_panel">
						<div class="x_title">
	                    	<h2>Posisi/Jabatan Saat ini <i class="fa fa-edit"></i></h2><span id="timer" style="display:none"></span>
	                    	<div class="clearfix"></div>
	                  	</div>
	                  	<div class="x_content">
	                    	<br />
	                    	<div class="form-group">
		                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Nama Posisi/Jabatan <span class="required red">*</span>
		                        </label>
		                       	<div class="col-md-6 col-sm-6 col-xs-12">
		                       		<input type="text" name="currentPositionName" value="${profile.currentPositionName}" id="currentPositionName"  class="form-control col-md-7 col-xs-12"  maxlength="255">
		                        </div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Lama Menjabat (dalam tahun) <span class="required red">*</span></label>
		                        <div class="col-md-6 col-sm-6 col-xs-12">
		                          	<input type="text" id="currentPeriode" name="currentPeriode" value="${profile.currentPeriode}"  class="form-control col-md-7 col-xs-12"  maxlength="255">
		                        </div>
		                   	</div>
		                   	<div class="form-group">
		                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Tanggung Jawab Utama <span class="required red">*</span></label>
		                        <div class="col-md-6 col-sm-6 col-xs-12">
		                          	<textarea class="form-control col-md-7 col-xs-12" rows="12" name="mainResponsibility" id="mainResponsibility">${profile.mainResponsibility}</textarea>
		                        </div>
		                   	</div>
		                   	<div class="form-group">
		                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Nama Atasan Langsung <span class="required red">*</span></label>
		                        <div class="col-md-6 col-sm-6 col-xs-12">
		                          	<input id="currentDirectSupervisor" class="form-control col-md-7 col-xs-12" type="text" id="currentDirectSupervisor" name="currentDirectSupervisor" value="${profile.currentDirectSupervisor}"  maxlength="255">
		                        </div>
		                   	</div>
		                   	
		                   		<div class="form-group">
		                        <label class="control-label col-md-3 col-sm-3 col-xs-12">E-mail Atasan Langsung </label>
		                        <div class="col-md-6 col-sm-6 col-xs-12">
		                          	<input id="currentSupervisorEmail" class="form-control col-md-7 col-xs-12" type="text" id="currentSupervisorEmail" name="currentSupervisorEmail" value="${profile.currentSupervisorEmail}"  maxlength="255">
		                        </div>
		                   	</div>
		                   	<div class="ln_solid"></div>
	                  	</div>
	                </div>
	                <div class="x_panel">
	               		<div class="x_title">
	                    	<h2>Aspirasi <i class="fa fa-edit"></i></h2>
	                    	<div class="clearfix"></div>
	                  	</div>
	                  	<div class="x_content">
	                    	<p class="font-gray-dark"> 
	                    		Bayangkan 2-3 tahun mendatang:
	                      		<ul>
	                        		<li>Di unit/bagian apa Anda melihat diri Anda? Apa posisi Anda saat itu? Mengapa - apa pertimbangan Anda?</li>
	                        		<li>Apa yang sudah Anda rencanakan untuk mencapai posisi tersebut?</li>
	                      		</ul>
	                    	</p>
	                    	
	                    	<textarea rows="15"  class="form-control col-md-7 col-xs-12 wysiwyg" name="careerAspiration" id="careerAspiration">${profile.careerAspiration}</textarea>
	                    	<div class="ln_solid"></div>
	                    </div>
	              	</div>
	              	<div class="x_panel">
	              		<div class="x_title">
	              		</div>
	              		<div class="x_content">
	              			<div class="form-group">
	                        	<div class="col-md-6 col-sm-6 col-xs-12">
		                          <a type="button" class="btn btn-primary" href="${pageContext.request.contextPath}/controller/pages/view/home">Beranda</a>
		                          <input type="button" id="btnFactsheetDraft" class="btn btn-success" value='Simpan'/>
		                          <input type="button" id="btnFactsheetSbmt" class="btn btn-success" value='<fmt:message key="btn.submit"></fmt:message>'/>
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
