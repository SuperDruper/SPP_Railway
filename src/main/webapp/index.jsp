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
  <link rel="stylesheet" type="text/css" href="http://getbootstrap.com/dist/css/bootstrap.min.css">

  <link rel="stylesheet" type="text/css" media="screen" href="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/master/build/css/bootstrap-datetimepicker.min.css" />
  <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.1/css/font-awesome.css" rel="stylesheet">
  <link rel="stylesheet" href="bower_components/angular-modal-progress-bar/dist/angular-modal-progress-bar.css">

  <script type="text/javascript" src="//code.jquery.com/jquery-2.1.1.min.js"></script>

  <script type="text/javascript" src="bower_components/angular-modal-progress-bar/dist/angular-modal-progress-bar.js"></script>

  <script type="text/javascript" src="http://momentjs.com/downloads/moment.js"></script>
  <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"></script>
  <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/master/src/js/bootstrap-datetimepicker.js"></script>
</head>

<body>

<div>
  <a href="/user/list">Users</a> -
  <a href="/user/register">Registration</a> -
  <a href="/user/login">Login</a>
</div>

<div>
  <p>
    <a href="/train/crud">Train CRUD</a><br />
    <a href="/train_type/crud">TrainType CRUD</a>
  </p>
</div>

<div>
  <p>
    <a href="/role/crud">Role CRUD</a><br />
  </p>
</div>

<div>
  <p>
    <a href="/race/crud">Race CRUD</a><br />
  </p>
</div>

<div>
  <p>
    <a href="/race_station/crud">RaceStation CRUD</a><br />
  </p>
</div>

<div>
  <p>
    <a href="/station_distance/crud">station_distance CRUD</a><br />
  </p>
</div>

<div>
  <p>
    <a href="/station/crud">station CRUD</a><br />
  </p>
</div>

<div>
  <p>
    <a href="/route/crud">route CRUD</a><br />
  </p>
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

<script src="<s:url value="modules/train/crud/train.controller.js" />"></script>
<script src="<s:url value="modules/train/crud/train.service.js" />"></script>

<script src="<s:url value="modules/train_type/crud/trainType.controller.js" />"></script>
<script src="<s:url value="modules/train_type/crud/trainType.service.js" />"></script>

<script src="<s:url value="modules/role/crud/role.controller.js" />"></script>
<script src="<s:url value="modules/role/crud/role.service.js" />"></script>

<script src="<s:url value="modules/race/crud/race.controller.js" />"></script>
<script src="<s:url value="modules/race/crud/race.service.js" />"></script>

<script src="<s:url value="modules/route/crud/route.controller.js" />"></script>
<script src="<s:url value="modules/route/crud/route.service.js" />"></script>

<script src="<s:url value="modules/station/crud/station.controller.js" />"></script>
<script src="<s:url value="modules/station/crud/station.service.js" />"></script>

<script src="<s:url value="modules/station_distance/crud/station_distance.controller.js" />"></script>
<script src="<s:url value="modules/station_distance/crud/station_distance.service.js" />"></script>

<script src="<s:url value="modules/race_station/crud/race_station.controller.js" />"></script>
<script src="<s:url value="modules/race_station/crud/race_station.service.js" />"></script>

</body>
</html>