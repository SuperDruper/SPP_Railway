app.controller('UserListController', function ($scope, UserListService) {
    return UserListService.getUsers()
        .then(function(data) {
            $scope.users = data.data.users;
        });
});
