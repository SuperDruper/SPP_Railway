<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="en" ng-app="app">
<head>
  <meta charset="utf-8">
  <title>BelTrain Inc.</title>

  <s:url var="ctxUrl" forceAddSchemeHostAndPort="true" includeContext="true" value="/" namespace="/" />
  <base href="<s:property value="ctxUrl"/>">
  <link rel="stylesheet" type="text/css" href="/css/style.css">
  <link rel="stylesheet" type="text/css" href="http://getbootstrap.com/dist/css/bootstrap.min.css">


  <link rel="stylesheet" type="text/css" media="screen" href="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/master/build/css/bootstrap-datetimepicker.min.css" />
  <link href="https://netdna.bootstrapcdn.com/font-awesome/4.0.1/css/font-awesome.css" rel="stylesheet">
  <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>


  <script src="/js/lib/jquery/jquery-1.12.3.min.js"></script>
  <script type="text/javascript" src="http://momentjs.com/downloads/moment.js"></script>
  <%--<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"></script>--%>
 <!-- <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>-->
  <script type="text/javascript" src="https://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/master/src/js/bootstrap-datetimepicker.js"></script>



  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.0/angular.min.js"></script>
  <script src="http://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-1.2.4.js"></script>

  <script src="./bower_components/angular-ui-bootstrap-datetimepicker/datetimepicker.js"></script>
  <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">

  <link href="https://cdn.rawgit.com/zhaber/datetimepicker/master/datetimepicker.css" type="text/css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="/css/main_style.css">
</head>

<body>

<div class="to_white">
  <div class="not-footer">
    <div class="container-fluid mrg">
      <div class="pdg">
        <div class="pdg">
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
                <a class="navbar-brand on-top" href="/"><img src="images/logo.png" style="margin-left: 10px;" alt="logo"></a>
              </div>

              <!-- Collect the nav links, forms, and other content for toggling -->
              <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <%--<a class="navbar-brand" href="/home"><img src="images/logo.png" alt="logo"></a>--%>
                <ul class="nav navbar-nav navbar-right">
                  <li><a href="/team">Our team</a></li>
                  <li><a href="/ticketorder/racechoice">Races</a></li>
                  <li>
                    <a href="#"
                       id="myTicketsTable"
                       class="btn-pop"
                       ng-click="uploadData()"
                       role="button"
                       ng-show="roleId > 0">
                      My tickets
                    </a>
                  </li>
                  <li><a href="/user/register" ng-hide="roleId > 0">Registration</a><a href="/user/profile" ng-show="roleId > 0">Profile</a></li>
                  <li> <a href="/user/login" ng-hide="roleId > 0">Login</a><a href="#" ng-click="logout()" ng-show="roleId > 0">Logout</a> </li>
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
          <div ng-show="roleId == 1">
            <ul class="admin-link">
              <li><a class="btn-primary btn-lg btn-block" href="/user/list">Users CRUD</a></li>
              <li><a class="btn-primary btn-lg btn-block" href="/ticket/crud">Tickets CRUD</a></li>
              <li><a class="btn-primary btn-lg btn-block" href="/train/crud">Train CRUD</a></li>
              <li><a class="btn-primary btn-lg btn-block" href="/role/crud">Role CRUD</a></li>
              <li><a class="btn-primary btn-lg btn-block" href="/race/crud">Race CRUD</a></li>
              <li><a class="btn-primary btn-lg btn-block" href="/race_station/crud">RaceStation CRUD</a></li>
              <li><a class="btn-primary btn-lg btn-block" href="/station_distance/crud">Station Distance CRUD</a></li>
              <li><a class="btn-primary btn-lg btn-block" href="/station/crud">Station CRUD</a></li>
              <li><a class="btn-primary btn-lg btn-block" href="/route/crud">Route CRUD</a></li>
              <li><a class="btn-primary btn-lg btn-block" href="/train_type/crud">Train Type CRUD</a></li>

            </ul>
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


    <div class="error_block">
      <ul ng-repeat="error in ticketErrors">
        <li class="validationError">
          {{error}}
        </li>
      </ul>
    </div>

    <div class="blackout">
      <div class="close">X</div>
    </div>

    <div id="modalViewWithTickets" class="tickets_block">
      <table class="table table-bordered table-striped">
        <thead>
        <tr>
          <th>Ticket</th>
          <th>Race</th>
          <th>Route</th>
          <th>Station from</th>
          <th>Station to</th>
          <th>Date from</th>
          <th>Date to</th>
          <th>Carriage</th>
          <th>Place</th>
          <th>Remove Action</th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="ticketDetails in ticketDetailsList">
          <td>{{ticketDetails.ticketNum}}</td>
          <td>{{ticketDetails.raceId}}</td>
          <td>{{ticketDetails.routeName}}</td>
          <td>{{ticketDetails.departureStationName}}</td>
          <td>{{ticketDetails.arriveStationName}}</td>
          <td>{{ticketDetails.departureDate.replace('T', ' ')}}</td>
          <td>{{ticketDetails.arriveDate.replace('T', ' ')}}</td>
          <td>{{ticketDetails.carriageNum}}</td>
          <td>{{ticketDetails.placeNum}}</td>
          <td><input type="button" value="Remove" class="btn btn-danger" ng-click="tryToRemoveRow(ticketDetails.ticketNum)"/></td>
        </tr>
        </tbody>

      </table>
    </div>

  </div>

  <footer id="foot" class="foot_blue">
  <div class="container">
    <div class="row">
      <div class="col-xs-12">
        <p class="text-center">&copy Aleksey Varfolomeey, Dzmitry Antonenka, Nikita Pushnov - 2016</p>
      </div>
    </div>
  </div>
  </footer>

</div>

<script src="<s:url value="js/lib/angular/angular-route.min.js" />"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="<s:url value="js/lib/bootstrap/bootbox.min.js" />"></script>

<script src="<s:url value="modules/app.js" />"></script>
<script src="<s:url value="shared/service.js" />"></script>
<script src="<s:url value="shared/user_role_name.service.js" />"></script>
<script src="<s:url value="shared/view_animtor_service.service.js" />"></script>
<script src="<s:url value="shared/datasharing/ticket_share.service.js" />"></script>

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

<script src="<s:url value="modules/ticket/crud/ticket.controller.js" />"></script>
<script src="<s:url value="modules/ticket/crud/ticket.service.js" />"></script>

<script src="<s:url value="modules/ticketorder/racechoice/racechoice.controller.js" />"></script>
<script src="<s:url value="modules/ticketorder/racechoice/racechoice.service.js" />"></script>

<script src="<s:url value="modules/ticketorder/racedetails/racedetails.controller.js" />"></script>
<script src="<s:url value="modules/ticketorder/racedetails/racedetails.service.js" />"></script>

<script src="<s:url value="modules/ticketorder/ticketshow/ticketshow.controller.js" />"></script>


<script src="<s:url value="modules/ticketorder/racechoice/racechoice.controller.js" />"></script>
<script src="<s:url value="modules/ticketorder/racechoice/racechoice.service.js" />"></script>

<script src="<s:url value="modules/ticketorder/racedetails/racedetails.controller.js" />"></script>
<script src="<s:url value="modules/ticketorder/racedetails/racedetails.service.js" />"></script>

<script src="<s:url value="modules/team/team.controller.js" />"></script>

<script type="text/javascript" src="/js/lib/bootstrap/bootstrap.min.js"></script>

<script>
  $(".btn-pop").click(function(){
    $('.blackout').fadeIn(600);
    var errors = document.querySelectorAll(".error_block ul");
    var tickets =  document.querySelectorAll(".tickets_block tr");
    if(errors.length == 0)
      $('.popup').fadeIn(600);
    else {
      $('.blackout').fadeIn(600);
      $('.error_block').fadeIn(600);
    }

    if(tickets.length > 0)
    {
      $('.blackout').fadeIn(600);
      $('.tickets_block').fadeIn(600);
    }
  });
  $(".blackout .close").click(function(){
    $('.blackout').fadeOut(600);
    $('.popup').fadeOut(600);
    $('.error_block').fadeOut(600);
    $('.tickets_block').fadeOut(600);
  });
</script>



</body>
</html>