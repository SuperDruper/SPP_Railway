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
        }); //add new routes here
}]);

app.controller('boostapp', function ($scope) {
    //global app controller
});