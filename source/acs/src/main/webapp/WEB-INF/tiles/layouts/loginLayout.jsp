<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<c:set value="${initParam.staticfiles}" var="staticfiles" scope="request"></c:set>
<%-- <c:set value="http://10.212.101.185/acsstatic" var="staticfiles" scope="request"></c:set> --%>

<!DOCTYPE >
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title><tiles:getAsString name="title" /></title>

<link href="${initParam.staticfiles}/static/images/favicon.ico" rel="shortcut icon"/>
    <!-- Bootstrap -->
    <link href="${initParam.staticfiles}/static/themes/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="${initParam.staticfiles}/static/themes/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="${initParam.staticfiles}/static/themes/vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="${initParam.staticfiles}/static/themes/vendors/animate.css/animate.min.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="${initParam.staticfiles}/static/themes/build/css/custom.min.css" rel="stylesheet">
    <link href="${initParam.staticfiles}/static/js/jquery-toast/dist/jquery.toast.min.css" rel="stylesheet">
    <link href="${initParam.staticfiles}/static/js/alertify.js-0.3.11/themes/alertify.core.css" rel="stylesheet">
    <link href="${initParam.staticfiles}/static/js/alertify.js-0.3.11/themes/alertify.default.css" rel="stylesheet">
    <link href="${initParam.staticfiles}/static/js/jquery-loading/dist/jquery.loading.min.css" rel="stylesheet">
</head>
<body class="login">
    <tiles:insertAttribute name="body" />
</body>
</html>