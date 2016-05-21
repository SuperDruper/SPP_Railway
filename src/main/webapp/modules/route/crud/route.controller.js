/**
 * Created by dzmitry.antonenka on 11.04.2016.
 */
app.controller('RouteController', function ($scope, $window, RouteService) {
    $scope.errors = [];

    $scope.tryToRemoveRow = function(id) {
        var index = -1;
        var comArr = eval( $scope.routes );
        for( var i = 0; i < comArr.length; i++ ) {
            if( comArr[i].id === id ) {
                index = i;
                break;
            }
        }

        if( index === -1 ) {
            alert( "Something gone wrong" );
        } else {
            const Message = "Are you sure you want to delete route \'" + comArr[index].name + "\' ?";
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

    function removeRow(object, index){
            const action = {
                id : 2
            };

            RouteService.removeRow({ route: object, action: action })
                .then(function(data) {
                    $scope.errors.push.apply($scope.errors, data.errorList);

                    if($scope.errors.length == 0) {
                        $scope.routes.splice(index, 1);
                    }
                });
    };

    $scope.updateRow = function(id){
        var index = -1;
        var comArr = eval( $scope.routes );
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

            if(!validate(object.name)) return;

            RouteService.updateRow({ route: object, action: action })
                .then(function(data) {
                    refreshData();
                    $scope.errors.push.apply($scope.errors, data.errorList);
                });
        }
    };
    $scope.register = function() {
        $scope.errors = [];
        $scope.events = [];

        if(!validate($scope.routeNameToCreate)) return;

        const route = {
            name : $scope.routeNameToCreate
        };
        const action = {
            id : 0
        };

        $scope.asyncRequestComplited = false;

        var smth = RouteService.register({route:route, action: action})
            .then(function(data) {
                $scope.errors.push.apply($scope.errors, data.errorList);
                $scope.asyncRequestComplited = true;
            });
        $scope.$watch('asyncRequestComplited',function(newValue, oldValue, scope){
            if(scope.asyncRequestComplited && $scope.errors.length == 0){
                $scope.routeIdToCreate = "";
                $scope.routeNameToCreate = "";
                refreshData();
            }
        });

        return smth;
    }

    function refreshData() {
        return RouteService.getRoutes()
            .then(function(data) {
                $scope.routes = data.data.routes;
            })
    }

    function validate(routeName)
    {
        $scope.errors = [];

        if(routeName != null && routeName.trim().length != 0) {
            return true;
        } else {
            $scope.errors.push("Please enter valid route name!");
            return false;
        }
    }

    return RouteService.getRoutes()
        .then(function(data) {
            $scope.routes = data.data.routes;
            $scope.races = data.data.routes;

            $scope.defaultSelectedRoute = $scope.routes[0].id;
            $scope.defaultSelectedRace = $scope.races[0].id;
        });

});
