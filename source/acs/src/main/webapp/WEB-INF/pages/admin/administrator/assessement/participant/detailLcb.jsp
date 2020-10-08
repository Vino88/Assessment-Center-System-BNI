<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages" />

<div class="main_container" role="main">
	<div class="right_col">
		<form class="form-horizontal form-label-left" >
            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                	<div class="x_title">
                    	<h2>Inventory <i class="fa fa-edit"></i></h2>
                    	<div class="clearfix"></div>
                  	</div>
                 <!-- Smart Wizard -->
                    <div id="wizard_lcb" class="form_wizard wizard_horizontal">
                   		<ul class="wizard_steps">
	                        <li>
	                        	<a href="#step-1">
		                            <span class="step_no">1</span>
		                            <span class="step_descr">Bagian 1<br />
										
									</span>
	                          	</a>
	                        </li>
                        	<li>
	                          	<a href="#step-2">
	                            	<span class="step_no">2</span>
	                            	<span class="step_descr">Bagian 2<br />
	                            		
									</span>
	                          	</a>
                        	</li>
                        	<li>
	                        	<a href="#step-3">
		                            <span class="step_no">3</span>
		                            <span class="step_descr">Bagian 3<br />
										
									</span>
	                          	</a>
	                        </li>
	                        
                      	</ul>
                      
                      	<div id="step-1">
                      		<c:set var="lcbIndex" value="0" scope="request" />
                      		<h2 class="StepTitle">Bagian 1</h2>
   <br/>
                      		<p><b>Untuk setiap pertanyaan dalam bagian ini, pilihlah jawaban yang paling sesuai mengenai tindakan yang akan Anda lakukan dalam situasi tersebut.</b></p>
<br/>
                        	<c:forEach items="${listParticipantLcb}" var="participantLcb" varStatus="count">
								<c:if test='${participantLcb.lcb.lbCategory == "LCB1"}'>
								
								<div class="form-group">
			                   		<label class="col-md-12 col-sm-8 col-xs-12">${participantLcb.lcb.question} </label>
			                        <div class="col-md-9 col-sm-8 col-xs-12">
			                        	<div class="radio">
				                            <label>
				                              <input type="radio" readonly="readonly" required="required" <c:if test='${participantLcb.lcbAnswer == "A" }'> checked="checked" </c:if>  value="A" > ${participantLcb.lcb.choiceA }
				                            </label>
			                          	</div>
			                          	<div class="radio">
				                            <label>
				                              <input type="radio" readonly="readonly" required="required" <c:if test='${participantLcb.lcbAnswer == "B" }'> checked="checked" </c:if>  value="B" > ${participantLcb.lcb.choiceB }
				                            </label>
			                          	</div>
			                          	<div class="radio">
				                            <label>
				                              <input type="radio" readonly="readonly" required="required" <c:if test='${participantLcb.lcbAnswer == "C" }'> checked="checked" </c:if>  value="C" > ${participantLcb.lcb.choiceC }
				                            </label>
			                          	</div>
			                          	<c:if test="${not empty participantLcb.lcb.choiceD }">
			                          		<div class="radio">
				                            <label>
				                              <input type="radio" readonly="readonly" required="required" <c:if test='${participantLcb.lcbAnswer == "D" }'> checked="checked" </c:if>  value="D" > ${participantLcb.lcb.choiceD }
				                            </label>
			                          	</div>
			                          	</c:if>
			                         	<c:if test="${not empty participantLcb.lcb.choiceE }">
				                        	<div class="radio">
				                            <label>
				                              <input type="radio" readonly="readonly" required="required" <c:if test='${participantLcb.lcbAnswer == "E" }'> checked="checked" </c:if>  value="E" > ${participantLcb.lcb.choiceE }
				                            </label>
			                          	</div>
				                     	</c:if>
			                        </div>
			               		</div>
			               		<br/>
		                      	</c:if>
							</c:forEach>
                      	</div>
                      	<div id="step-2">
                       		<h2 class="StepTitle">Bagian 2</h2>
                       		<br/>
                       		<p><b>Nyatakan sejauh mana Anda menyetujui atau tidak menyetujui setiap pernyataan di bawah ini.</b>
                       		</p>
<br/>
                       		<c:forEach items="${listParticipantLcb}" var="participantLcb" varStatus="count">
								<c:if test='${participantLcb.lcb.lbCategory == "LCB2"}'>
								
								<div class="form-group">
			                   		<label class="col-md-12 col-sm-8 col-xs-12">${participantLcb.lcb.question }</label>
			                        <div class="col-md-9 col-sm-8 col-xs-12">
			                        	<div class="radio">
				                            <label>
				                              <input type="radio" readonly="readonly" required="required" <c:if test='${participantLcb.lcbAnswer == "A" }'> checked="checked" </c:if>  value="A" > ${participantLcb.lcb.choiceA }
				                            </label>
			                          	</div>
			                          	<div class="radio">
				                            <label>
				                              <input type="radio" readonly="readonly" required="required" <c:if test='${participantLcb.lcbAnswer == "B" }'> checked="checked" </c:if>  value="B" > ${participantLcb.lcb.choiceB }
				                            </label>
			                          	</div>
			                          	<div class="radio">
				                            <label>
				                              <input type="radio" readonly="readonly" required="required" <c:if test='${participantLcb.lcbAnswer == "C" }'> checked="checked" </c:if>  value="C" > ${participantLcb.lcb.choiceC }
				                            </label>
			                          	</div>
			                          	<c:if test="${not empty participantLcb.lcb.choiceD }">
			                          		<div class="radio">
				                            <label>
				                              <input type="radio" readonly="readonly" required="required" <c:if test='${participantLcb.lcbAnswer == "D" }'> checked="checked" </c:if>  value="D" > ${participantLcb.lcb.choiceD }
				                            </label>
			                          	</div>
			                          	</c:if>
			                         	<c:if test="${not empty participantLcb.lcb.choiceE }">
				                        	<div class="radio">
				                            <label>
				                              <input type="radio" readonly="readonly" required="required" <c:if test='${participantLcb.lcbAnswer == "E" }'> checked="checked" </c:if>  value="E" > ${participantLcb.lcb.choiceE }
				                            </label>
			                          	</div>
				                     	</c:if>
			                        </div>
			               		</div>
			               		<br/>
		                      	</c:if>
							</c:forEach>
                      </div>
                      <div id="step-3">
                        <h2 class="StepTitle">Bagian 3</h2>
                        <br/>
                        <p><b>Pilih tindakan yang paling tepat menggambarkan pengalaman Anda sebelumnya.</b></p>
<br/>
                        <c:forEach items="${listParticipantLcb}" var="participantLcb" varStatus="count">
								<c:if test='${participantLcb.lcb.lbCategory == "LCB3"}'>
								
								<div class="form-group">
			                   		<label class="col-md-12 col-sm-8 col-xs-12">${participantLcb.lcb.question }</label>
			                        <div class="col-md-9 col-sm-8 col-xs-12">
			                        	<div class="radio">
				                            <label>
				                              <input type="radio" readonly="readonly" required="required" <c:if test='${participantLcb.lcbAnswer == "A" }'> checked="checked" </c:if>  value="A" > ${participantLcb.lcb.choiceA }
				                            </label>
			                          	</div>
			                          	<div class="radio">
				                            <label>
				                              <input type="radio" readonly="readonly" required="required" <c:if test='${participantLcb.lcbAnswer == "B" }'> checked="checked" </c:if>  value="B" > ${participantLcb.lcb.choiceB }
				                            </label>
			                          	</div>
			                          	<div class="radio">
				                            <label>
				                              <input type="radio" readonly="readonly" required="required" <c:if test='${participantLcb.lcbAnswer == "C" }'> checked="checked" </c:if>  value="C" > ${participantLcb.lcb.choiceC }
				                            </label>
			                          	</div>
			                          	<c:if test="${not empty participantLcb.lcb.choiceD }">
			                          		<div class="radio">
				                            <label>
				                              <input type="radio" readonly="readonly" required="required" <c:if test='${participantLcb.lcbAnswer == "D" }'> checked="checked" </c:if>  value="D" > ${participantLcb.lcb.choiceD }
				                            </label>
			                          	</div>
			                          	</c:if>
			                         	<c:if test="${not empty participantLcb.lcb.choiceE }">
				                        	<div class="radio">
				                            <label>
				                              <input type="radio" readonly="readonly" required="required" <c:if test='${participantLcb.lcbAnswer == "E" }'> checked="checked" </c:if>  value="E" > ${participantLcb.lcb.choiceE }
				                            </label>
			                          	</div>
				                     	</c:if>
			                        </div>
			               		</div>
			               		<br/>
		                      	</c:if>
							</c:forEach>
                      </div>
                      
                      </div>
                    <!-- End SmartWizard Content -->
                  </div>
                </div>
              </div>
       </form>
	</div>
</div>