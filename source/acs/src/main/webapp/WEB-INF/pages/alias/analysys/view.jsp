<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages" />
<script>

var flagArray = new Array();

var flag = {};
flag.variable = "flaginfo1";
flag.value = "false";

flagArray.push(flag);


flag = {};
flag.variable = "flaginfo2";
flag.value = "false";

flagArray.push(flag);

flag = {};
flag.variable = "flaginfo3";
flag.value = "false";

flagArray.push(flag);

flag = {};
flag.variable = "flaginfo4";
flag.value = "false";
flagArray.push(flag);

flag = {};
flag.variable = "flaginfo5";
flag.value = "false";
flagArray.push(flag);

flag = {};
flag.variable = "flaginfo6";
flag.value = "false";
flagArray.push(flag);

flag = {};
flag.variable = "flaginfo7";
flag.value = "false";
flagArray.push(flag);

flag = {};
flag.variable = "flaginfo8";
flag.value = "false";
flagArray.push(flag);

flag = {};
flag.variable = "flaginfo9";
flag.value = "false";
flagArray.push(flag);

flag = {};
flag.variable = "flaginfo10";
flag.value = "false";
flagArray.push(flag);


function getFlagArray()
{
	return flagArray;
}

function setFlagArray(updatedFlagArray)
{
	flagArray = updatedFlagArray;
}

function isAllCheckedFlagArray()
{
	var result = true;
	
	for(var i in flagArray)
	{
		if(flagArray[i].value == "false" || flagArray[i].value == false)
		{
			result = false;
			break;
		}
	}
	
	return result;
}


</script>
<div class="main_container" role="main">
	<div class="right_col">
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
           		<div class="x_panel">
           			<div class="x_title">
           				<h2>Simulasi Rencana Bisnis <span id="timer" style="display:none"></span>
							</h2>
							
	                    <div class="clearfix"></div>
           			</div>
           			<div class="x_content">
           				<p>Anda dapat menggunakan informasi dibawah ini, serta informasi latar belakang perusahaan sebelumnya, untuk menjawab pertanyaan-pertanyaan ini.</p>
		           		<iframe frameborder="0" src="${initParam.staticfiles}/static/articulate/gsi/20170330/story_html5.html" width="950px" height="600px"></iframe>
	           		</div>
	           	</div>
	      	</div>
	      	<div class="col-md-12 col-sm-12 col-xs-12">
	           	<div class="x_panel">
	           		<div class="x_title">
           				<h2>Berikut adalah pertanyaan yang harus Anda jawab</h2>
	                    <div class="clearfix"></div>
           			</div>
	           		<div class="x_content">          	
		           		<ul class="list-unstyled">
		           			<c:forEach items="${list}" var="analysys" varStatus="count">
		                      	<li><i class="fa fa-chevron-circle-right"></i>  <a href="javascript:gotoDetail(${analysys.id})">
				                      Pertanyaan ${count.index +1 }
				                    </a>
				                    
				                    <c:forEach items="${map}" var="m" varStatus="count">
					                		<c:if test="${m.key == analysys.id && m.value == true }">
					                		<i class="fa fa-check"></i>
					                	</c:if>
					                	</c:forEach>
			                    </li>
							</c:forEach>
						</ul>
					</div>
				</div>
				
				
				<div class="x_panel">
	              		
	              		<div class="x_content">
	              			<div class="form-group">
	                        	<div class="col-md-6 col-sm-6 col-xs-12">
		                          
		                        <a class="btn btn-success" href="javascript:gotoDetail(${list[0].id})"><fmt:message key="btn.next"></fmt:message></a>
		                          	
	                        	</div>
	                      	</div>
	              		</div>
	              	</div>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
</div>