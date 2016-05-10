<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="en" ng-app="app">
<head>
  <meta charset="utf-8">
  <title>My AngularJS Struts2 App</title>

  <s:url var="ctxUrl" forceAddSchemeHostAndPort="true" includeContext="true" value="/" namespace="/" />
  <base href="<s:property value="ctxUrl"/>">
  <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<body>

<div class="container-fluid mrg">
  <div class="row pdg">
    <div class="col-xs-12 pdg">
      <nav class="navbar navbar-default navbar-static-top" role="navigation">
        <div class="container">
          <!-- Brand and toggle get grouped for better mobile display -->
          <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/home"><img src="images/logo.png" alt="logo"></a>
          </div>

          <!-- Collect the nav links, forms, and other content for toggling -->
          <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
              <li><a href="/ticketorder/racechoice">Races</a></li>
              <li><a href="#" data-toggle="modal" data-target="#myTickets" ng-show="userRole">My tickets</a></li>
              <li><a href="/user/register" ng-hide="userRole">Registration</a><a href="/user/profile" ng-show="userRole">Profile</a></li>
              <li> <a href="/user/login" ng-hide="userRole">Login</a><a href="#" ng-click="logout()" ng-show="userRole">Logout</a> </li>
            </ul>
          </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
      </nav>
    </div>
  </div>

</div>

<div class="container">
  <div class="row">
    <div class="col-xs-12">
      <div ng-show="userRole == 'admin'">
        <ul class="admin-link">
          <li><a class="btn" href="/user/list">Users</a></li>
          <li><a class="btn" href="/train/crud">Train CRUD</a></li>
          <li><a class="btn" href="/role/crud">Role CRUD</a></li>
          <li><a class="btn" href="/race/crud">Race CRUD</a></li>
          <li><a class="btn" href="/race_station/crud">RaceStation CRUD</a></li>
          <li><a class="btn" href="/station_distance/crud">station_distance CRUD</a></li>
          <li><a class="btn" href="/station/crud">station CRUD</a></li>
          <li><a class="btn" href="/route/crud">route CRUD</a></li>
          <li><a class="btn" href="/train_type/crud">TrainType CRUD</a></li>
        </ul>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="myTickets" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">Название модали</h4>
      </div>
      <div class="modal-body">
        <div class="table-responsive">
          <table class="table table-striped table-bordered text-center">
            <thead>
            <tr>
              <th>Ticets number</th>
              <th>Route</th>
              <th>Dispatch st.</th>
              <th>Destination st.</th>
              <th>Dispatch date</th>
              <th>Destination date</th>
              <th>Car number</th>
              <th>Place number</th>
              <th>&nbsp;</th>
            </tr>
            </thead>
            <tbody>
            <tr>
              <td>5</td>
              <td>Витебск-Брест</td>
              <td>Витебск</td>
              <td>Брест</td>
              <td>29.06.2016Т09:00:00</td>
              <td>29.06.2016Т14:00:00</td>
              <td>7</td>
              <td>14</td>
              <td><button type="button" class="close">&times;</button></td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
      </div>
    </div>
  </div>
</div>


<div ng-controller="boostapp">
  <div class="container">
    <div class="row">
      <div class="col-xs-12 content">
        <div ng-view></div>
      </div>
    </div>
  </div>
</div>

<footer id="foot">
<div class="container">
  <div class="row">
    <div class="col-xs-12">
      <p class="text-center">&copy Name Surname, Name Surname, 2016</p>
    </div>
  </div>
</div>
</footer>


<script src="<s:url value="js/jquery.js" />"></script>
<script src="<s:url value="js/bootstrap.min.js" />"></script>
<script src="<s:url value="js/lib/angular/angular.min.js" />"></script>
<script src="<s:url value="js/lib/angular/angular-route.min.js" />"></script>

<script src="<s:url value="modules/app.js" />"></script>
<script src="<s:url value="shared/service.js" />"></script>
<script src="<s:url value="shared/user_role_name.service.js" />"></script>
<script src="<s:url value="shared/get_race_stations.service.js" />"></script>

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

<script src="<s:url value="modules/ticketorder/racechoice/racechoice.controller.js" />"></script>
<script src="<s:url value="modules/ticketorder/racechoice/racechoice.service.js" />"></script>

<script src="<s:url value="modules/ticketorder/racedetails/racedetails.controller.js" />"></script>
<script src="<s:url value="modules/ticketorder/racedetails/racedetails.service.js" />"></script>


</body>
</html>