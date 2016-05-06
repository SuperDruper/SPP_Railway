/**
 * Created by dzmitry.antonenka on 11.04.2016.
 */
app.controller('RouteController', function ($scope, $window, RouteService) {
    $scope.errors = [];

    $scope.removeRow = function(id){
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
            const object = comArr[index];
            const action = {
                id : 2
            };

            RouteService.removeRow({ route: object, action: action });
            $scope.routes.splice(index, 1);
        }
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
                    $scope.errors.push.apply($scope.errors, data.errorList);
                    refreshData();
                });
        }
    };
    $scope.register = function() {
        $scope.errors = [];
        $scope.events = [];

        const route = {
            id : $scope.routeIdToCreate,
            name : $scope.routeNameToCreate
        };
        const action = {
            id : 0
        };

        if(!validate($scope.routeNameToCreate)) return;

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

                RouteService.getRoutes()
                    .then(function(data) {
                        $scope.routes = data.data.routes;

                        $scope.defaultSelectedRoute = $scope.routes[0].id;
                        $scope.defaultSelectedRace = $scope.races[0].id;
                    });
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
        if(routeName != null && routeName.trim().length != 0) {
            return true;
        } else {
            $scope.errors.push("Route name cannot be empty !");
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
