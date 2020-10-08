<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages" />

<div class="main_container" role="main">
	<div class="right_col">
		<form id="capForm" method="POST" action="${pageContext.request.contextPath}/controller/pages/alias/cap" class="form-horizontal form-label-left">
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="x_panel">
						<div class="x_title">
							<h2>
								Prestasi Karir (<a style="color: #1ABB9C" href="javascript:showExample()" >lihat contoh</a>)<span id="timer" style="display:none"></span>
							</h2>
							<div class="nav navbar-right ">

		                    </div>
							<div class="clearfix"></div>
						</div>
						<!-- Smart Wizard -->
						
						<input type="hidden" value="${currentIndex}" id="fromStep" name="fromStep"/>
						<input type="hidden" value="${currentIndex}" id="toStep" name="toStep"/>
						<input type="hidden" value="" id="lastTimer" name="lastTimer"/>
						
						<div id="wizard_cap" class="form_wizard wizard_horizontal">
							<ul class="wizard_steps">
								<c:forEach items="${listParticipantCap}" var="cap"
									varStatus="count">
									<li><a id="headerStep${count.index + 1}" href="#step-${count.index + 1}"> <span
											class="step_no">${count.index + 1}</span>
									</a></li>
								</c:forEach>
							</ul>
							<c:forEach items="${listParticipantCap}" var="participantCap" varStatus="count">
								<div id="step-${count.index + 1}">
									
									
									<input type="hidden" value="${participantCap.cap.id}" name="listParticipantCapDto[${count.index}].capId" /> 
									<input type="hidden" value="${participantCap.id}" name="listParticipantCapDto[${count.index}].participantCapId" />
									<div class="x_content">
										<h2 class="font-gray-dark">${participantCap.cap.question}</h2>
										
										<br/>
										<div >
										<p>(ST) ${participantCap.cap.qSituation } </p>
										<textarea rows="14"
											class="form-control col-md-7 col-xs-12 resizable_textarea"
											name="listParticipantCapDto[${count.index}].ansSituation">${participantCap.ansSituation}</textarea>
										</div>
										
										<br/>
										<div >
										<p>(A) ${participantCap.cap.qAction } </p>
										<textarea rows="14"
											class="form-control col-md-7 col-xs-12 resizable_textarea"
											name="listParticipantCapDto[${count.index}].ansAction">${participantCap.ansAction}</textarea>
										</div>
										
										<br/>
										<div >
										<p>(R) ${participantCap.cap.qResult } </p>
										<textarea rows="14"
											class="form-control col-md-7 col-xs-12 resizable_textarea"
											name="listParticipantCapDto[${count.index}].ansResult">${participantCap.ansResult}</textarea>
										</div>
										
										<div class="ln_solid"></div>
									</div>
								</div>
							</c:forEach>
						</div>
						<!-- End SmartWizard Content -->
					</div>
				</div>
			</div>
		</form>
	</div>
</div>