<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="en" ng-app="app">
<head>
  <meta charset="utf-8">
  <title>My AngularJS Struts2 App</title>

  <s:url var="ctxUrl" forceAddSchemeHostAndPort="true" includeContext="true" value="/" namespace="/" />
  <base href="<s:property value="ctxUrl"/>">
  <link rel="stylesheet" type="text/css" href="/css/style.css">
  <link rel="stylesheet" type="text/css" href="http://getbootstrap.com/dist/css/bootstrap.min.css">
</head>

<body>

<div>
  <a href="/user/register" ng-hide="userRole">Registration</a><a href="/user/profile" ng-show="userRole">Profile</a> -
  <a href="/user/login" ng-hide="userRole">Login</a><a href="#" ng-click="logout()" ng-show="userRole">Logout</a>
</div>

<br>

<div ng-show="userRole == 'admin'">
  <p>
    <a href="/user/list">Users</a><br>
    <a href="/train/crud">Train CRUD</a><br />
    <a href="/role/crud">Role CRUD</a><br />
    <a href="/race/crud">Race CRUD</a><br />
    <a href="/race_station/crud">RaceStation CRUD</a><br />
    <a href="/station_distance/crud">station_distance CRUD</a><br />
    <a href="/station/crud">station CRUD</a><br />
    <a href="/route/crud">route CRUD</a><br />
    <a href="/train_type/crud">TrainType CRUD</a>
  </p>
</div>


<div ng-controller="boostapp">
  <div ng-view></div>
</div>


<script src="<s:url value="js/lib/angular/angular.min.js" />"></script>
<script src="<s:url value="js/lib/angular/angular-route.min.js" />"></script>

<script src="<s:url value="modules/app.js" />"></script>
<script src="<s:url value="shared/service.js" />"></script>
<script src="<s:url value="shared/user_role_name.service.js" />"></script>

<script src="<s:url value="modules/example/example.controller.js" />"></script>
<script src="<s:url value="modules/example/example.service.js" />"></script>

<script src="<s:url value="modules/user/login/login.controller.js" />"></script>

<script src="<s:url value="modules/user/register/register.controller.js" />"></script>
<script src="<s:url value="modules/user/register/register.service.js" />"></script>

<script src="<s:url value="modules/user/list/list.controller.js" />"></script>
<script src="<s:url value="modules/user/list/list.service.js" />"></script>

<script src="<s:url value="modules/user/profile/profile.controller.js" />"></script>
<script src="<s:url value="modules/user/profile/profile.service.js" />"></script>

<script src="<s:url value="modules/home/home.controller.js" />"></script>
<script src="<s:url value="modules/home/home.service.js" />"></script>

<script src="<s:url value="modules/train/register/train.controller.js" />"></script>
<script src="<s:url value="modules/train/register/train.service.js" />"></script>

<script src="<s:url value="modules/train/register/trainList.controller.js" />"></script>
<script src="<s:url value="modules/train/register/trainList.service.js" />"></script>

</body>
</html>