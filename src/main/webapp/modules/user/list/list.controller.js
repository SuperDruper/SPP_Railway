app.controller('UserListController', function ($scope, UserListService, Service) {

    $scope.removeRow = function(id){
        var index = -1;
        var comArr = eval( $scope.users );
        for( var i = 0; i < comArr.length; i++ ) {
            if( comArr[i].id === id ) {
                index = i;
                break;
            }
        }
        if( index === -1 ) {
            alert( "Cannot find element in list" );
        } else {
            const object = comArr[index];
            const action = {
                id : 2
            };

            UserListService.removeRow({ user: object, action: action });
            $scope.users.splice(index, 1);
        }

    };

    $scope.updateRoleForUser = function(user, roleId) {
        var comTrainTypesArr = eval( $scope.roles );
        var indexRole = -1;
        for( var i = 0; i < comTrainTypesArr.length; i++ ) {
            if( comTrainTypesArr[i].id == roleId) {
                indexRole = i;
                break;
            }
        }

        var comTrainsArr = eval( $scope.users );
        var indexUser = -1;
        for( var i = 0; i < comTrainsArr.length; i++ ) {
            if( comTrainsArr[i].id === user.id) {
                indexUser = i;
                break;
            }
        }

        var user = $scope.users[indexUser];
        var role = $scope.roles[indexRole];
        user.role = role;

    };

    $scope.updateRow = function(id){
        var index = -1;
        var comArr = eval( $scope.users );
        for( var i = 0; i < comArr.length; i++ ) {
            if( comArr[i].id === id ) {
                index = i;
                break;
            }
        }
        if( index === -1 ) {
            alert( "Cannot update row with id" + id);
        } else {
            const object = comArr[index];
            const action = {
                id : 1
            };

            UserListService.updateRow({ user: object, action: action });
        }
    };

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
                UserListService.getUsers();
            });
    };

    return UserListService.getUsers()
        .then(function(data) {
            $scope.users = data.data.users;
            $scope.roles = data.data.roles;
        });
});
