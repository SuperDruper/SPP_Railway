app.controller('ProfileController', function ($scope, $window, ProfileService) {

    $scope.update = function() {
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

        return ProfileService.updateProfile({user: user})
            .then(function (data) {
                $scope.errors.push.apply($scope.errors, data.errorList);

                if ($scope.errors.length == 0) {
                    $window.location.href = '/';
                }
            });
    };

    return ProfileService.getProfile()
        .then(function(data) {
            var user = data.data.user;
            $scope.name = user.name;
            $scope.surname = user.surname;
            $scope.login = user.login;
            $scope.email = user.email;
            $scope.password = user.password;
            $scope.repeatPassword = user.password;
            $scope.roleName = user.role.name;
        });
});
