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
            controller: 'TrainController'
        })
        .when('/trainType/list', {
            templateUrl: 'modules/train/register/trainList.view.html',
            controller: 'TrainListController'
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