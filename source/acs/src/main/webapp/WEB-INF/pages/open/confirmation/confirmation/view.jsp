<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages"/>

<div class="main_container" role="main" >
	<div class="right_col">
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<form method="POST" action="${pageContext.request.contextPath}/controller/pages/open/confirmation/confirmation/confirm" class="form-horizontal row-fluid">
					<input type="hidden" value="${id}" name="id" />
	                <div class="x_panel">
	               		<div class="x_title">
	                    	<h2>Confirmation <i class="fa fa-edit"></i></h2>
	                    	<div class="clearfix"></div>
	                  	</div>
	                  	<div class="x_content">
	                    	<p class="font-gray-dark"> 
	                    		Apakah Anda bisa mengikuti sesi yang sudah di jadwalkan ?
	                    	</p>
	                    	<div class="col-md-9 col-sm-8 col-xs-12">
	                        	<div class="radio">
		                            <label>
		                              <input type="radio"   value="true"  name="answer"> YA
		                            </label>
	                          	</div>
	                          	<div class="radio">
		                            <label>
		                              <input type="radio"   value="false"  name="answer"> TIDAK
		                            </label>
		                       	</div>
	                        </div>
	                        
	                        <p>
	                        	Mohon Mengisi Deskripsi jika Anda tidak bisa menghadiri sesi yang sudah dijadwalkan :
	                        </p>
                   			<textarea rows="5" class="form-control col-md-7 col-xs-12" name="notes" id="notes"></textarea>
	                    	<div class="ln_solid"></div>
	                    </div>
	              	</div>
	              	<div class="x_panel">
	              		<div class="x_title">
	              		</div>
	              		<div class="x_content">
	              			<div class="form-group">
	                        	<div class="col-md-6 col-sm-6 col-xs-12">
		                          <button type="submit" class="btn btn-success">Submit</button>
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
