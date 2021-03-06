/**
 * Created by dzmitry.antonenka on 11.04.2016.
 */
app.controller('RoleController', function ($scope, $window, RoleService, ModalViewAnimatorService) {
    $scope.errors = [];

    $scope.tryToRemoveRow = function(id) {
        var index = -1;
        var comArr = eval( $scope.roles );
        for( var i = 0; i < comArr.length; i++ ) {
            if( comArr[i].id === id ) {
                index = i;
                break;
            }
        }

        if( index === -1 ) {
            alert( "Something gone wrong" );
        } else {
            const Message = "Are you sure you want to delete role \'" + comArr[index].name + "\' ?";
            $scope.objectForDeleteOpearion = comArr[index];
            $scope.indexOFObjectForDeleteOpearion = index;

            bootbox.confirm({
                    message: Message ,
                    callback: function(result) {
                        if(result == true) {
                            removeRow($scope.objectForDeleteOpearion, $scope.indexOFObjectForDeleteOpearion);
                        }
                    },
                    title: "Delete confirmation"}
            );
        }
    };

    $(".blackout .close").click(function(){
        $('.blackout').fadeOut(600);
        $('.popup').fadeOut(600);
        $('.error_block').fadeOut(600);
    });

    function removeRow(object, index) {
            $scope.errors = [];

            const action = {
                id : 2
            };

            RoleService.removeRow({ role: object, action: action })
                .then(function(data) {
                    $scope.errors.push.apply($scope.errors, data.errorList);
                    ModalViewAnimatorService.showModelViewAnimated($scope);

                    if($scope.errors.length == 0) {
                        $scope.roles.splice(index, 1);
                    }
                });
    };
    $scope.updateRow = function(id){
        $scope.errors = [];

        var index = -1;
        var comArr = eval( $scope.roles );
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

            if(!validate(object.name)) {
                ModalViewAnimatorService.showModelViewAnimated($scope);
                return;
            }

            RoleService.updateRow({ role: object, action: action })
                .then(function(data) {
                    refreshData();

                    $scope.errors.push.apply($scope.errors, data.errorList);
                    ModalViewAnimatorService.showModelViewAnimated($scope);
                });
        }
    };
    $scope.register = function() {
        $scope.errors = [];
        $scope.events = [];

        const object = {
            name: $scope.roleName
        };

        const action = {
            id : 0
        };

        $scope.asyncRequestComplited = false;

        if(!validate($scope.roleName))  {
            ModalViewAnimatorService.showModelViewAnimated($scope);
            return;
        }

        var smth = RoleService.register({role:object, action: action})
            .then(function(data) {
                if(data.errorList.length )
                $scope.errors.push.apply($scope.errors, data.errorList);
                ModalViewAnimatorService.showModelViewAnimated($scope);
                $scope.asyncRequestComplited = true;
            });
        $scope.$watch('asyncRequestComplited',function(newValue, oldValue, scope){
            if(scope.asyncRequestComplited && $scope.errors.length == 0){
                // $window.location.href = '/';
                $scope.roleId = "";
                $scope.roleName = "";

                refreshData();
            }
        });

        return smth;
    }

    function validate(roleName)
    {
        $scope.errors = [];

        if(roleName != null && roleName.trim().length != 0) {
            return true;
        } else {
            $scope.errors.push("Please enter correct role name!");
            return false;
        }
    }

    function refreshData()
    {
       return RoleService.getRoles()
            .then(function(data) {
               $scope.roles = data.data.roles;
            });
    }

    return refreshData();
});