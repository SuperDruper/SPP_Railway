app.controller('RegisterController', function ($scope, $window, RegisterService) {

    $scope.register = function() {
        $scope.errors = [];

        if ($scope.password != $scope.repeatPassword) {
            $scope.errors = ['Repeated password is not equal to password!'];
            return;
        }

        const user = {
            name: $scope.name,
            surname: $scope.surname,
            login: $scope.login,
            email: $scope.email,
            password: $scope.password
        };

        return RegisterService.register({user:user})
            .then(function(data) {
                $scope.errors.push.apply($scope.errors, data.errorList);

                if($scope.errors.length == 0){
                    $window.location.href = '/';
                }
            });
    }
});
