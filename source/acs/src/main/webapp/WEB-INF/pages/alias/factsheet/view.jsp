<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages"/>

<div class="main_container" role="main" >
	<div class="right_col">
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<form id="factsheetform" name="factsheetform" method="POST" action="${pageContext.request.contextPath}/controller/pages/alias/factsheet" class="form-horizontal row-fluid">
					<div class="x_panel">
						<div class="x_title">
	                    	<h2>Data Pribadi <i class="fa fa-edit"></i></h2>
	                    	<div class="clearfix"></div>
	                  	</div>
	                  	<div class="x_content">
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Nama Lengkap <span class="required red">*</span></label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input type="text" readonly="readonly" name="participantName" value="${employee.fullName}" id="first-name"  class="form-control col-md-7 col-xs-12">
		                        </div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" >Tempat Lahir <span class="required red">*</span></label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input type="text" readonly="readonly" name="placeOfBirth" value="${employee.placeOfBirth}" id="first-name"  class="form-control col-md-7 col-xs-12">
		                        </div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Tanggal Lahir <span class="required red">*</span></label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input type="text" readonly="readonly" name="dateOfBirth" value='<fmt:formatDate value="${employee.dateOfBirth}"  pattern="dd-MM-yyyy" />' id="first-name"  class="datetimepicker form-control col-md-7 col-xs-12">
		                        </div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Alamat E-mail <span class="required red">*</span></label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input type="text" readonly="readonly" name="participantEmail" value="${sessionScope.session_auth_participant.email}" id="first-name"  class="form-control col-md-7 col-xs-12">
		                        </div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" >Wilayah <span class="required red">*</span></label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input type="text" readonly="readonly" name="workingArea" value="${profile.workingArea}" id="first-name"  class="form-control col-md-7 col-xs-12">
		                        </div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" >No. HP <span class="required red">*</span></label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input type="text" name="phoneNumber" value="${employee.phone}" id="phoneNumber"  class="form-control col-md-7 col-xs-12">
		                        </div>
							</div>
						</div>
					</div>
					<div class="x_panel">
						<div class="x_title">
	                    	<h2>Posisi/Jabatan Saat ini <i class="fa fa-edit"></i></h2>
	                    	<div class="clearfix"></div>
	                  	</div>
	                  	<div class="x_content">
	                    	<br />
	                    	<div class="form-group">
		                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Nama Posisi/Jabatan <span class="required red">*</span>
		                        </label>
		                       	<div class="col-md-6 col-sm-6 col-xs-12">
		                       		<input type="text" readonly="readonly" name="currentPositionName" value="${profile.currentPositionName}" id="first-name"  class="form-control col-md-7 col-xs-12">
		                        </div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Lama Menjabat <span class="required red">*</span></label>
		                        <div class="col-md-6 col-sm-6 col-xs-12">
		                          	<input type="text" readonly="readonly" id="last-name" name="currentPeriode" value="${profile.currentPeriode}"  class="form-control col-md-7 col-xs-12">
		                        </div>
		                   	</div>
		                   	<div class="form-group">
		                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Tanggung Jawab Utama <span class="required red">*</span></label>
		                        <div class="col-md-6 col-sm-6 col-xs-12">
		                          	<textarea class="form-control col-md-7 col-xs-12" name="currentResponsibility" id="currentResponsibility">${profile.currentResponsibility}</textarea>
		                        </div>
		                   	</div>
		                   	<div class="form-group">
		                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Posisi/Jabatan Atasan Langsung <span class="required red">*</span></label>
		                        <div class="col-md-6 col-sm-6 col-xs-12">
		                          	<input id="middle-name" class="form-control col-md-7 col-xs-12" type="text" readonly="readonly" name="currentDirectSupervisor" value="${profile.currentDirectSupervisor}">
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
		                          <a type="button" class="btn btn-primary" href="${pageContext.request.contextPath}/controller/pages/view/home"><fmt:message key="btn.cancel"></fmt:message></a>
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
