app.controller('SignupController', function ($scope, UserService) {

    $scope.signup = function() {
        const user = {
            email: $scope.email,
            password: $scope.password,
            repeatPassword: $scope.repeatPassword
        };

        return UserService.signup(user);
    }
});
