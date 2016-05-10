/**
 * Created by dzmitry.antonenka on 11.04.2016.
 */
app.controller('StationDistanceController', function ($scope, $window, StationDistanceService) {
    $scope.errors = [];
    $scope.stationFrom = '';
    $scope.stationTo = '';
    $scope.distance = '';

    $scope.removeRow = function(stationDistance){
        var index = -1;
        $scope.errors = [];
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

            StationDistanceService.removeRow({ stationDistance: object, action: action })
                .then(function (data) {
                    $scope.errors.push.apply($scope.errors, data.errorList);

                    if($scope.errors.length == 0) {
                        $scope.stationDistances.splice(index, 1);
                    }
                });
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

            if(!validate(object.stIdFrom, object.stIdTo, object.distance)) return;

            StationDistanceService.updateRow({ stationDistance: object, action: action })
                .then(function(data) {
                    refreshData();
                    $scope.errors.push.apply($scope.errors, data.errorList);
                });
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


        if(!validate($scope.stationFrom, $scope.stationTo, $scope.distance)) return;

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
                $scope.distance = '';

                StationDistanceService.getStationDistances()
                    .then(function(data) {
                        $scope.stationDistances = data.data.stationDistances;
                        $scope.stations = data.data.stations;
                    });
            }
        });

        return smth;
    }

    function validate(stationFrom, stationTo, distance)
    {
        $scope.errors = [];
        var isValid = true;

        if(stationFrom == '') {
            $scope.errors.push("Station \'From\' not set !");
            isValid = false;
        }

        if(stationTo == '') {
            $scope.errors.push("Station \'To\' not set !");
            isValid = false;
        }

        if(distance == '' || isNaN(parseInt(distance))) {
            $scope.errors.push("Eneterd invalid distance(needs to be in meters) !");
            isValid = false;
        }

        return isValid;
    }

    function refreshData()
    {
        return StationDistanceService.getStationDistances()
            .then(function(data) {
                $scope.stationDistances = data.data.stationDistances;
                $scope.stations = data.data.stations;
            });
    }

    return StationDistanceService.getStationDistances()
        .then(function(data) {
            $scope.stationDistances = data.data.stationDistances;
            $scope.stations = data.data.stations;
        });

});