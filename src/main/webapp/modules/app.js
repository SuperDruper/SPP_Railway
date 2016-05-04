var app = angular.module('app', ['ngRoute']);

app.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
    $locationProvider.html5Mode(true).hashPrefix('!');

    $routeProvider
        .when('/', {
            templateUrl: 'modules/home/home.view.html',
            controller: 'HomeController'
        })
        .when('/user/register', {
            templateUrl: 'modules/user/register/register.view.html',
            controller: 'RegisterController'
        }).when('/user/login', {
            templateUrl: 'modules/user/login/login.view.html',
            controller: 'LoginController'
        })
        .when('/user/list', {
            templateUrl: 'modules/user/list/list.view.html',
            controller: 'UserListController'
        })
        .when('/user/profile', {
            templateUrl: 'modules/user/profile/profile.view.html',
            controller: 'ProfileController'
        })
        .when('/train/register', {
            templateUrl: 'modules/train/register/train.view.html',
        }) //add new routes here
        .when('/train/crud', {
            templateUrl: 'modules/train/crud/train.view.html',
            controller: 'TrainController'
        })
        .when('/train_type/crud', {
            templateUrl: 'modules/train_type/crud/trainType.view.html',
            controller: 'TrainListController'
        })
        .when('/role/crud', {
            templateUrl: 'modules/role/crud/role.view.html',
            controller: 'RoleController'
        })
        .when('/race/crud', {
        templateUrl: 'modules/race/crud/race.view.html',
        controller: 'RaceListController'
        })
        .when('/route/crud', {
         templateUrl: 'modules/route/crud/route.view.html',
         controller: 'RouteController'
        })
        .when('/station/crud', {
            templateUrl: 'modules/station/crud/station.view.html',
            controller: 'StationController'
        })
        .when('/station_distance/crud', {
            templateUrl: 'modules/station_distance/crud/station_distance.view.html',
            controller: 'StationDistanceController'
        })
        .when('/race_station/crud', {
            templateUrl: 'modules/race_station/crud/race_station.view.html',
            controller: 'RaceStationController'
        })
        .when('/ticket/crud', {
            templateUrl: 'modules/ticket/crud/ticket.view.html',
            controller: 'TicketController'
        })
        .when('/ticketorder/racechoice', {
            templateUrl: 'modules/ticketorder/racechoice/racechoice.view.html',
            controller: 'RaceChoiceController'
        })
        .when('/ticketorder/racedetails', {
            templateUrl: 'modules/ticketorder/racedetails/racedetails.view.html',
            controller: 'RaceDetailsController'
        });
}]);

app.controller('boostapp', function ($rootScope, $window, UserRoleNameService, Service) {

    UserRoleNameService.uploadRoleName().then(function (data) {
        $rootScope.userRole = UserRoleNameService.roleName;
    });

    $rootScope.logout = function() {
        $rootScope.userRole = '';
        Service.request('/api/user/logout', 'GET');
        $window.location.href = '/';
    };

});