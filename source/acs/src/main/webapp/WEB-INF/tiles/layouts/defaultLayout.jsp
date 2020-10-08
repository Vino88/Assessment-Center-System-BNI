<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<c:set value="${initParam.staticfiles}" var="staticfiles" scope="request"></c:set>
<%-- <c:set value="http://10.212.101.185/acsstatic" var="staticfiles" scope="request"></c:set> --%>

<!DOCTYPE >
<html>

<head>
	<title><tiles:getAsString name="title" /></title>
	<tiles:insertAttribute name="head" />
</head>
 
<body>
		<header id="header">
			<tiles:insertAttribute name="header" />
		</header>
			
		<section id="site-content">
			<tiles:insertAttribute name="body" />
		</section>
		
		<div class="footer">
		    <div>
        		<span class="copyright">&copy; 2017 All rights reserved <img class="pull-right" src="${initParam.staticfiles}/static/images/bnv-logo-footer.png" alt="BNV"/></span>
      		</div>
		</div>
		
		<tiles:insertAttribute name="foot" />
		<tiles:insertAttribute name="footscript" />
</body>
</html>