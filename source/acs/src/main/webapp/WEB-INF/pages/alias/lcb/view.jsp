<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages" />

<div class="main_container" role="main">
	<div class="right_col">
		<form id="lcbForm" method="POST" action="${pageContext.request.contextPath}/controller/pages/view/lcb" class="form-horizontal form-label-left" >
            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                	<div class="x_title">
                    	<h2>Leadership Inventory<span id="timer" style="display:none"></span>
							</h2>
							
                    	<div class="clearfix"></div>
                  	</div>
                 <!-- Smart Wizard -->
                 
                 
                    <div id="wizard_lcb" class="form_wizard wizard_horizontal">
                   		<ul class="wizard_steps">
	                        <li>
	                        	<a href="#step-1">
		                            <span class="step_no">1</span>
		                            <span class="step_descr">Bagian 1<br/>
									</span>
	                          	</a>
	                        </li>
                        	<li>
	                          	<a href="#step-2">
	                            	<span class="step_no">2</span>
	                            	<span class="step_descr">Bagian 2<br/>
									</span>
	                          	</a>
                        	</li>
                        	<li>
	                        	<a href="#step-3">
		                            <span class="step_no">3</span>
		                            <span class="step_descr">Bagian 3<br/>
									</span>
	                          	</a>
	                        </li>
                      	</ul>
                      
                      	<div id="step-1" style="min-height:900px;height:900px">
                      		<c:set var="lcbIndex" value="0" scope="request" />
                      		<c:set var="countIndex" value="0" scope="request" />
<br/>
                      		<p><b>Untuk setiap pertanyaan dalam bagian ini, pilihlah jawaban yang paling sesuai mengenai tindakan yang akan Anda lakukan dalam situasi tersebut.</b></p>
<br/>

<div style="text-align: center">
	<a id="prevSlideSection1" value="" class="btn btn-app" ><i class="fa fa-arrow-circle-left"></i>Sebelumnya</a>
&nbsp;&nbsp;
	<a id="nextSlideSection1" value="" class="btn btn-app" ><i class="fa fa-arrow-circle-right"></i>Selanjutnya</a>

	
</div>
<div id="questionSection1Slick">
                        	<c:forEach items="${listParticipantLcb}" var="participantLcb" varStatus="count">
                        	
                        	<c:set var="lcb" value="${participantLcb.lcb}" />
                        	
                        	<c:if test='${lcb.lbCategory == "LCB1"}'>
                        	
                        	<c:set var="countIndex" value="${countIndex + 1 }" scope="request" />
                        	<div>
								<input type="hidden" value="${lcb.number}" name="listParticipantLcbDto[${lcbIndex}].questionNumber" />
								<div class="form-group answerContainer" id="answerContainer${lcb.number}" data-section="1" data-index="${countIndex }" data-number="${lcb.number}" >
			                   		<label class="col-md-12 col-sm-8 col-xs-12 qlabel" > ${lcb.question }</label>
			                        <div class="col-md-9 col-sm-8 col-xs-12">
			                        	<div class="radio">
				                            <label>
				                            
				                              <input type="radio" <c:if test='${participantLcb.lcbAnswer == "A" }'> checked="checked" </c:if>   value="A"  name="listParticipantLcbDto[${lcbIndex}].lcbAnswer"> ${lcb.choiceA }
				                            </label>
			                          	</div>
			                          	<div class="radio">
				                            <label>
				                              <input type="radio" <c:if test='${participantLcb.lcbAnswer == "B" }'> checked="checked" </c:if>   value="B"  name="listParticipantLcbDto[${lcbIndex}].lcbAnswer"> ${lcb.choiceB }
				                            </label>
				                       	</div>
			                          	<div class="radio">
				                            <label>
				                        		<input type="radio" <c:if test='${participantLcb.lcbAnswer == "C" }'> checked="checked" </c:if>   value="C"  name="listParticipantLcbDto[${lcbIndex}].lcbAnswer"> ${lcb.choiceC }
				                            </label>
				                      	</div>
			                          	<c:if test="${not empty lcb.choiceD }">
			                          		<div class="radio">
					                            <label>
					                              <input type="radio" <c:if test='${participantLcb.lcbAnswer == "D" }'> checked="checked" </c:if>   value="D"  name="listParticipantLcbDto[${lcbIndex}].lcbAnswer"> ${lcb.choiceD }
					                            </label>
					                          </div>
			                          	</c:if>
			                         	<c:if test="${not empty lcb.choiceE }">
				                        	<div class="radio">
				                            	<label>
				                             		<input type="radio" <c:if test='${participantLcb.lcbAnswer == "E" }'> checked="checked" </c:if>   value="E"  name="listParticipantLcbDto[${lcbIndex}].lcbAnswer"> ${lcb.choiceE }
				                            	</label>
				                          	</div>
				                     	</c:if>
			                        </div>
			               		</div>
			               		<br/>
		                      	<c:set var="lcbIndex" value="${lcbIndex+1 }" />
		                      	
		                     </div>
		                     </c:if>
							</c:forEach>
</div>

                      	</div>
                      	<div id="step-2">
<br/>
                       		<p><b>Nyatakan sejauh mana Anda menyetujui atau tidak menyetujui setiap pernyataan di bawah ini.</b>
                       		</p>
<br/>

<table id="entityTable" class="table table-striped table-bordered">
                      <thead>
                        <tr>
                        						  <th>Pertanyaan</th>
                                                  <th>Sangat Tidak Setuju</th>
                                                  <th>Tidak Setuju</th>
                                                  <th>Netral</th>
                                                  <th>Setuju</th>
                                                  <th>Sangat Setuju</th>
                                                  
                        </tr>
                      </thead>

<c:set var="countIndex" value="0" scope="request" />
                      <tbody>
                       		<c:forEach items="${listParticipantLcb}" var="participantLcb" varStatus="count2">
                        	
                        	<c:set var="lcb" value="${participantLcb.lcb}" />
                        	
                        	<c:if test='${lcb.lbCategory == "LCB2"}'>
                        	<c:set var="countIndex" value="${countIndex + 1 }" scope="request" />
								<input type="hidden" value="${lcb.number}" name="listParticipantLcbDto[${lcbIndex}].questionNumber" />
								
								
                        <tr class="answerContainer" id="answerContainer${lcb.number}" data-section="2" data-index="${countIndex}" data-number="${lcb.number}">
                          <td>${lcb.question }</td>
                          
                          <td><div class="radio">
			                            	<label>
			                              		<input type="radio" value="A" <c:if test='${participantLcb.lcbAnswer == "A" }'> checked="checked" </c:if>  name="listParticipantLcbDto[${lcbIndex}].lcbAnswer"> ${lcb.choiceA }
			                            	</label>
			                          	</div>
			              </td>
			              
			              <td>
			              <div class="radio">
			                            	<label>
			                              		<input type="radio" value="B" <c:if test='${participantLcb.lcbAnswer == "B" }'> checked="checked" </c:if>  name="listParticipantLcbDto[${lcbIndex}].lcbAnswer"> ${lcb.choiceB }
			                            	</label>
			                          	</div>
			              </td>
			              
			              <td>
			              <div class="radio">
			                            	<label>
			                              		<input type="radio"   value="C" <c:if test='${participantLcb.lcbAnswer == "C" }'> checked="checked" </c:if>  name="listParticipantLcbDto[${lcbIndex}].lcbAnswer"> ${lcb.choiceC }
			                            	</label>
			                          	</div>
			              </td>
			              
			              <td>
			               <c:if test="${not empty lcb.choiceD }">
			                          <div class="radio">
			                            <label>
			                              <input type="radio"   value="D" <c:if test='${participantLcb.lcbAnswer == "D" }'> checked="checked" </c:if> name="listParticipantLcbDto[${lcbIndex}].lcbAnswer"> ${lcb.choiceD }
			                            </label>
			                          </div>
			                          </c:if>
			              </td>
			              
			              <td>
			               <c:if test="${not empty lcb.choiceE }">
			                          <div class="radio">
			                            <label>
			                              <input type="radio"   value="E" <c:if test='${participantLcb.lcbAnswer == "E" }'> checked="checked" </c:if>  name="listParticipantLcbDto[${lcbIndex}].lcbAnswer"> ${lcb.choiceE }
			                            </label>
			                          </div>
			                          </c:if>
			              </td>
                        </tr>
                      
                    
								
		                      <c:set var="lcbIndex" value="${lcbIndex+1 }" />
		                      </c:if>
							</c:forEach>
							
							</tbody>
                    </table>
                      </div>
                      <div id="step-3">
<br/>
                        <p><b>Pilih tindakan yang paling tepat menggambarkan pengalaman Anda sebelumnya.</b></p>
<br/>
<c:set var="countIndex" value="0" scope="request" />
                        <c:forEach items="${listParticipantLcb}" var="participantLcb" varStatus="count3">
                        	
                        	<c:set var="lcb" value="${participantLcb.lcb}" />
                        	
                        	<c:if test='${lcb.lbCategory == "LCB3"}'>
                        	
                        	<c:set var="countIndex" value="${countIndex + 1 }" scope="request" />
                        	
							<input type="hidden" value="${lcb.number}" name="listParticipantLcbDto[${lcbIndex}].questionNumber" />
							<div class="form-group answerContainer" id="answerContainer${lcb.number}" data-section="3" data-index="${countIndex}" data-number="${lcb.number}" >
		                        <label class="col-md-12 col-sm-8 col-xs-12">${lcb.question }
		                        </label>
		                        <div class="col-md-9 col-sm-8 col-xs-12">
		                          <div class="radio">
		                            <label>
		                              <input type="radio"   value="A" <c:if test='${participantLcb.lcbAnswer == "A" }'> checked="checked" </c:if>  name="listParticipantLcbDto[${lcbIndex}].lcbAnswer"> ${lcb.choiceA }
		                            </label>
		                          </div>
		                          <div class="radio">
		                            <label>
		                              <input type="radio"   value="B" <c:if test='${participantLcb.lcbAnswer == "B" }'> checked="checked" </c:if>  name="listParticipantLcbDto[${lcbIndex}].lcbAnswer"> ${lcb.choiceB }
		                            </label>
		                          </div>
		                          <div class="radio">
		                            <label>
		                              <input type="radio"   value="C" <c:if test='${participantLcb.lcbAnswer == "C" }'> checked="checked" </c:if>  name="listParticipantLcbDto[${lcbIndex}].lcbAnswer"> ${lcb.choiceC }
		                            </label>
		                          </div>
		                          <c:if test="${not empty lcb.choiceD }">
		                          <div class="radio">
		                            <label>
		                              <input type="radio"   value="D" <c:if test='${participantLcb.lcbAnswer == "D" }'> checked="checked" </c:if>  name="listParticipantLcbDto[${lcbIndex}].lcbAnswer"> ${lcb.choiceD }
		                            </label>
		                          </div>
		                          </c:if>
		                          <c:if test="${not empty lcb.choiceE }">
		                          <div class="radio">
		                            <label>
		                              <input type="radio"   value="E" <c:if test='${participantLcb.lcbAnswer == "E" }'> checked="checked" </c:if>  name="listParticipantLcbDto[${lcbIndex}].lcbAnswer"> ${lcb.choiceE }
		                            </label>
		                          </div>
		                          </c:if>
		                        </div>
		                      </div>
		                      <br/>
		                      <c:set var="lcbIndex" value="${lcbIndex+1 }" />
		                      </c:if>
							</c:forEach>
                      </div></div>
                    <!-- End SmartWizard Content -->
                  </div>
                </div>
              </div>
       </form>
	</div>
</div>