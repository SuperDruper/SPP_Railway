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

    $scope.updateStationToForStationDistance = function(stationDistance, defaultSelectedStationTo) {
        var index = -1;
        var comArr = eval( $scope.stationDistances );
        for( var i = 0; i < comArr.length; i++ ) {
            if( comArr[i] === stationDistance ) {
                index = i;
                break;
            }
        }

        var stationDistance = comArr[index];
        stationDistance.stIdTo = defaultSelectedStationTo;
    };

    $scope.updateStationFromForStationDistance = function(stationDistance, defaultSelectedStationFrom) {
        var index = -1;
        var comArr = eval( $scope.stationDistances );
        for( var i = 0; i < comArr.length; i++ ) {
            if( comArr[i] === stationDistance ) {
                index = i;
                break;
            }
        }

        var stationDistance = comArr[index];
        stationDistance.stIdTo = defaultSelectedStationFrom;
    };

    $scope.register = function() {
        $scope.errors = [];
        $scope.events = [];

        const object = {
            stIdFrom: $scope.stationFrom,
            stIdTo: $scope.stationTo,
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
                $scope.stationFrom = '';
                $scope.stationTo = '';

                StationDistanceService.getStationDistances()
                    .then(function(data) {
                        $scope.stationDistances = data.data.stationDistances;
                        $scope.stations = data.data.stations;
                    });
            }
        });

        return smth;
    }

    return StationDistanceService.getStationDistances()
        .then(function(data) {
            $scope.stationDistances = data.data.stationDistances;
            $scope.stations = data.data.stations;
        });

});