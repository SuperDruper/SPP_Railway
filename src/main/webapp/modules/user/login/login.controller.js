app.controller('LoginController', function ($scope, $window, $rootScope, Service, UserRoleNameService) {

    $scope.doLogin = function() {
        $scope.errors = [];

        const user = {
            login: $scope.login,
            password: $scope.password
        };

        return Service.request('/api/user/login', 'POST', user)
            .then(function(data) {
                $scope.errors.push.apply($scope.errors, data.errorList);

                if($scope.errors.length == 0) {
                    $rootScope.userRole = data.userRoleName;
                    $window.location.href = '/';
                }
            });
    }
});
