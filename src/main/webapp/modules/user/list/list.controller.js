app.controller('UserListController', function ($scope, UserListService, Service) {

    $scope.register = function() {
        $scope.errors = [];
        $scope.events = [];

        const role = {
            name : $scope.roleName
        };

        const user = {
            name: $scope.name,
            surname: $scope.surname,
            login: $scope.login,
            email: $scope.email,
            password: $scope.password,
            role: role
        };

        return Service.request('/api/user/register', 'POST', {user: user})
            .then(function (data) {
                $scope.errors.push.apply($scope.errors, data.errorList);

                if ($scope.errors.length == 0) {
                    $scope.name = '';
                    $scope.surname = '';
                    $scope.login = '';
                    $scope.email = '';
                    $scope.password = '';
                    $scope.roleName = '';
                }
            });
    };

    return UserListService.getUsers()
        .then(function(data) {
            $scope.users = data.data.users;
        });
});
