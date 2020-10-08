<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages"/>

<script>

var flagArray = new Array();

var flag = {};
flag.variable = "flagread1";
flag.value = "false";

flagArray.push(flag);


flag = {};
flag.variable = "flagread2";
flag.value = "false";

flagArray.push(flag);

flag = {};
flag.variable = "flagread3";
flag.value = "false";

flagArray.push(flag);

flag = {};
flag.variable = "flagread4";
flag.value = "false";
flagArray.push(flag);

flag = {};
flag.variable = "flagread5";
flag.value = "false";
flagArray.push(flag);

flag = {};
flag.variable = "flagread6";
flag.value = "false";
flagArray.push(flag);

flag = {};
flag.variable = "flagread7";
flag.value = "false";
flagArray.push(flag);

flag = {};
flag.variable = "flagread8";
flag.value = "false";
flagArray.push(flag);

flag = {};
flag.variable = "flagread9";
flag.value = "false";
flagArray.push(flag);


flag = {};
flag.variable = "flagread10";
flag.value = "false";
flagArray.push(flag);

flag = {};
flag.variable = "flagread11";
flag.value = "false";
flagArray.push(flag);


flag = {};
flag.variable = "flagread11";
flag.value = "false";
flagArray.push(flag);


flag = {};
flag.variable = "flagread12";
flag.value = "false";
flagArray.push(flag);


flag = {};
flag.variable = "flagread13";
flag.value = "false";
flagArray.push(flag);

flag = {};
flag.variable = "flagread14";
flag.value = "false";
flagArray.push(flag);


flag = {};
flag.variable = "flagread15";
flag.value = "false";
flagArray.push(flag);

/* flag = {};
flag.variable = "flagread16";
flag.value = "false";
flagArray.push(flag);
 */
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

	if(flagArray[15].value == "true" || flagArray[15].value == true)
	{
		result = true;
	}
	else
	{
		result = true;
	}
	
	return result;
}




var flagMemoArray = new Array();

var flagMemo = {};
flagMemo.variable = "flag1";
flagMemo.value = "false";

//flagMemoArray.push(flagMemo);


flagMemo = {};
flagMemo.variable = "flag2";
flagMemo.value = "false";

flagMemoArray.push(flagMemo);

flagMemo = {};
flagMemo.variable = "flag3";
flagMemo.value = "false";

flagMemoArray.push(flagMemo);

flagMemo = {};
flagMemo.variable = "flag4";
flagMemo.value = "false";
flagMemoArray.push(flagMemo);

flagMemo = {};
flagMemo.variable = "flag5";
flagMemo.value = "false";
flagMemoArray.push(flagMemo);

flagMemo = {};
flagMemo.variable = "flag6";
flagMemo.value = "false";
flagMemoArray.push(flagMemo);

flagMemo = {};
flagMemo.variable = "flag7";
flagMemo.value = "false";
flagMemoArray.push(flagMemo);

flagMemo = {};
flagMemo.variable = "flag8";
flagMemo.value = "false";
flagMemoArray.push(flagMemo);

flagMemo = {};
flagMemo.variable = "flag9";
flagMemo.value = "false";
flagMemoArray.push(flagMemo);

flagMemo = {};
flagMemo.variable = "flag10";
flagMemo.value = "false";
flagMemoArray.push(flagMemo);


function getFlagMemoArray()
{
	return flagMemoArray;
}

function setFlagMemoArray(updatedFlagArray)
{
	flagMemoArray = updatedFlagArray;
}

function isAllCheckedFlagMemoArray()
{
	
	var result = true;
	if(flagMemoArray[8].value == "true" || flagMemoArray[8].value == true)
	{
		result = true;
	}
	else
	{
		result = true;
	}
	
	
	
	return result;
}

</script>


<div><span id="timer" style="display:none"></span></div>
<div class="x_panel">
<div class="x_content">
<div style="width:100%">
	<div id="step-1" style="text-align: center">
		<iframe frameborder="0" src="${staticfiles}/static/articulate/articulate3/20170309/story.html" width="1200px" height="550px"></iframe>
  	</div>
				
	<div id="step-2" style="text-align: center;display:none">
		<iframe frameborder="0" src="${staticfiles}/static/articulate/gsi/20170330/story.html" width="1200px" height="550px"></iframe>
	</div>
    
    <div id="step-3" style="text-align: center;display:none">
    	<iframe frameborder="0" src="${staticfiles}/static/memo/articulate/20170329/flag/story.html" width="1200px" height="550px"></iframe>
  	</div>
  	<br>
  	<div class="col-md-8 col-sm-8 col-xs-8 col-md-offset-1"> 
	    		<input type="button" id="btnPrevSlide" class="buttonNext btn btn-primary" value="Kembali">
	    		<input style="text-align:right" type="button" id="btnNextSlide" class="buttonNext btn btn-success" value="Lanjut">
	    	</div>
	    	</div>
	</div>
</div>
<%@include file="/WEB-INF/tiles/template/themes/foot.jsp" %>

<%@include file="background_script.jsp" %>


