app.controller('UserListController', function ($scope, UserListService, Service) {
    $scope.errors = [];

    $scope.removeRow = function(id){
        var index = -1;
        $scope.errors = [];
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

            UserListService.removeRow({ user: object, action: action })
                .then(function (data) {
                    $scope.errors.push.apply($scope.errors, data.errorList);
                    if($scope.errors.length == 0) {
                        $scope.users.splice(index, 1);
                    }
                });
        }

    }
        ;

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

            if(!validate(object.name, object.surname, object.login, object.email, object.password, object.role)) return;

            UserListService.updateRow({ user: object, action: action })
                .then(function (data) {
                    refreshData();
                    $scope.errors.push.apply($scope.errors, data.errorList);
                });
        }
    };

    $scope.register = function() {
        $scope.errors = [];
        $scope.events = [];

        const role = {
            id : $scope.roleToCreate
        };

        const user = {
            name: $scope.name,
            surname: $scope.surname,
            login: $scope.login,
            email: $scope.email,
            password: $scope.password,
            role: role
        };

        if(!validate($scope.name, $scope.surname, $scope.login, $scope.email, $scope.password, role)) return;

        return Service.request('/api/user/register', 'POST', {user: user})
            .then(function (data) {
                $scope.errors.push.apply($scope.errors, data.errorList);
                refreshData();

                if ($scope.errors.length == 0) {
                    $scope.name = '';
                    $scope.surname = '';
                    $scope.login = '';
                    $scope.email = '';
                    $scope.password = '';
                    $scope.roleToCreate = '';
                }
            });
    };

    function validate(name, surname, login, email, password, roleToCreate)
    {
        $scope.errors = [];

        var isValid = true;
        if(name == null || name == '') {
            $scope.errors.push("Name cannot be empty !");
            isValid = false;
        }
        if(surname == null || surname == '') {
            $scope.errors.push("Surname cannot be empty !");
            isValid = false;
        }
        if(login == null || login == '') {
            $scope.errors.push("Login cannot be empty !");
            isValid = false;
        }
        if(email == null || email == '') {
            $scope.errors.push("Email cannot be empty !");
            isValid = false;
        }
        if(password == null || password == '') {
            $scope.errors.push("Password cannot be empty !");
            isValid = false;
        }
        if(roleToCreate == null || roleToCreate == '') {
            $scope.errors.push("Role not SELECTED !");
            isValid = false;
        }

        return isValid;
    }

    function refreshData() {
        return UserListService.getUsers()
            .then(function(data) {
                $scope.users = data.data.users;
                $scope.roles = data.data.roles;
            });
    }

    return UserListService.getUsers()
        .then(function(data) {
            $scope.users = data.data.users;
            $scope.roles = data.data.roles;
        });
});
