<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="en" ng-app="app">
<head>
  <meta charset="utf-8">
  <title>My AngularJS Struts2 App</title>

  <s:url var="ctxUrl" forceAddSchemeHostAndPort="true" includeContext="true" value="/" namespace="/" ></s:url>
  <base href="<s:property value="ctxUrl"/>">
  <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<body>

<div>
  <a href="/user/list">Users</a> -
  <a href="/user/register">Registration</a> -
  <a href="/user/login">Login</a>
</div>

<div ng-controller="boostapp">
  <div ng-view></div>
</div>

<script src="<s:url value="js/lib/angular/angular.min.js" />"></script>
<script src="<s:url value="js/lib/angular/angular-route.min.js" />"></script>

<script src="<s:url value="modules/app.js" />"></script>
<script src="<s:url value="shared/service.js" />"></script>

<script src="<s:url value="modules/example/example.controller.js" />"></script>
<script src="<s:url value="modules/example/example.service.js" />"></script>

<script src="<s:url value="modules/user/login/login.controller.js" />"></script>

<script src="<s:url value="modules/user/register/register.controller.js" />"></script>
<script src="<s:url value="modules/user/register/register.service.js" />"></script>

<script src="<s:url value="modules/user/list/list.controller.js" />"></script>
<script src="<s:url value="modules/user/list/list.service.js" />"></script>

<script src="<s:url value="modules/home/home.controller.js" />"></script>
<script src="<s:url value="modules/home/home.service.js" />"></script>

</body>
</html>