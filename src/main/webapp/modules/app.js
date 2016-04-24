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
        });
}]);

app.controller('boostapp', function ($scope) {
    //global app controller
});