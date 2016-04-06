app.controller('RegisterController', function ($scope, $window, RegisterService) {

    $scope.register = function() {
        $scope.errors = [];

        if ($scope.password != $scope.repeatPassword) {
            $scope.errors = ['Repeated password is not equals!'];
            return;
        }

        const user = {
            name: $scope.name,
            surname: $scope.surname,
            login: $scope.login,
            email: $scope.email,
            password: $scope.password
        };

        $scope.asyncRequestComplited = false;

        var smth = RegisterService.register({user:user})
            .then(function(data) {
                $scope.errors.push.apply($scope.errors, data.errorList);
                $scope.asyncRequestComplited = true;
            });


        $scope.$watch('asyncRequestComplited',function(newValue, oldValue, scope){
            if(scope.asyncRequestComplited && $scope.errors.length == 0){
                $window.location.href = '/';
            }
        });

    return smth;
}
});
