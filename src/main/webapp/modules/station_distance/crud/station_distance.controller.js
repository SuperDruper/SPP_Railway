/**
 * Created by dzmitry.antonenka on 11.04.2016.
 */
app.controller('StationDistanceController', function ($scope, $window, StationDistanceService) {

    $scope.removeRow = function(stationDistance){
        var index = -1;
        var comArr = eval( $scope.stationDistances );
        for( var i = 0; i < comArr.length; i++ ) {
            if( comArr[i] == stationDistance ) {
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

            StationDistanceService.removeRow({ stationDistance: object, action: action });
            $scope.stationDistances.splice(index, 1);
        }

    };
    $scope.updateRow = function(stationDistance){
        var index = -1;
        var comArr = eval( $scope.stationDistances );
        for( var i = 0; i < comArr.length; i++ ) {
            if( comArr[i] === stationDistance ) {
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

            StationDistanceService.updateRow({ stationDistance: object, action: action });
        }
    };
    $scope.register = function() {
        $scope.errors = [];
        $scope.events = [];

        const object = {
            stIdFrom: $scope.stationIdFrom,
            stIdTo: $scope.stationIdTo,
            distance : $scope.distance
        };

        const action = {
            id : 0
        };

        $scope.asyncRequestComplited = false;

        var smth = StationDistanceService.register({stationDistance:object, action: action})
            .then(function(data) {
                $scope.errors.push.apply($scope.errors, data.errorList);
                $scope.events.push.apply($scope.events, data.eventList);

                $scope.asyncRequestComplited = true;
            });
        $scope.$watch('asyncRequestComplited',function(newValue, oldValue, scope){
            if(scope.asyncRequestComplited && $scope.errors.length == 0){
                // $window.location.href = '/';
                $scope.stationIdToCreate = "";
                $scope.stationNameToCreate = "";

                StationDistanceService.getStationDistances()
                    .then(function(data) {
                        $scope.stationDistances = data.data.stationDistances;
                    });
            }
        });

        return smth;
    }

    return StationDistanceService.getStationDistances()
        .then(function(data) {
            $scope.stationDistances = data.data.stationDistances;
        });

});