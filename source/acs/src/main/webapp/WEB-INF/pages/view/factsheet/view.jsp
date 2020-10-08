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
				<form id="factsheetform" name="factsheetform" method="POST" action="${pageContext.request.contextPath}/controller/pages/view/factsheet" class="form-horizontal row-fluid">
					<div class="x_panel">
						<div class="x_title">
	                    	<h2>Data Pribadi <i class="fa fa-edit"></i></h2>
	                    	<div class="clearfix"></div>
	                  	</div>
	                  	<div class="x_content">
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Nama Lengkap <span class="required red">*</span></label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input type="text" readonly="readonly" name="participantName" value="${employee.fullName}" id="first-name"  class="form-control col-md-7 col-xs-12" maxlength="255">
		                        </div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" >Tempat Lahir <span class="required red">*</span></label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input type="text" readonly="readonly" name="placeOfBirth" value="${employee.placeOfBirth}" id="first-name"  class="form-control col-md-7 col-xs-12"  maxlength="255">
		                        </div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Tanggal Lahir <span class="required red">*</span></label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input type="text" readonly="readonly" name="dateOfBirth" value='<fmt:formatDate value="${employee.dateOfBirth}"  pattern="dd-MM-yyyy" />' id="first-name"  class="form-control col-md-7 col-xs-12"  maxlength="255">
		                        </div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Alamat E-mail <span class="required red">*</span></label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input type="text" name="participantEmail" value="${employee.email}" id="participantEmail"  class="form-control col-md-7 col-xs-12"  maxlength="255">
		                        </div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" >Wilayah/Unit <span class="required red">*</span></label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input type="text" name="workingArea" value="${profile.workingArea}" id="workingArea"  class="form-control col-md-7 col-xs-12"  maxlength="255">
		                        </div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" >No. Telephone Genggam <span class="required red">*</span></label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input type="text" name="participantPhone" value="${employee.phone}" id="participantPhone" class="form-control col-md-7 col-xs-12"  maxlength="255">
		                        </div>
							</div>
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
