/**
 * Created by dzmitry.antonenka on 11.04.2016.
 */
app.controller('StationDistanceController', function ($scope, $window, StationDistanceService) {
    $scope.errors = [];
    $scope.stationFrom = '';
    $scope.stationTo = '';
    $scope.distance = '';

    $scope.tryToRemoveRow = function(id) {
        var index = -1;
        var comArr = eval( $scope.stationDistances );
        for( var i = 0; i < comArr.length; i++ ) {
            if( comArr[i] == id ) {
                index = i;
                break;
            }
        }

        if( index === -1 ) {
            alert( "Something gone wrong" );
        } else {
            const Message = "Are you sure you want to delete station distance from station number \'" + comArr[index].stIdFrom + "\' to \'" +  comArr[index].stIdTo  + "\' ?";
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

            StationDistanceService.removeRow({ stationDistance: object, action: action })
                .then(function (data) {
                    $scope.errors.push.apply($scope.errors, data.errorList);

                    if($scope.errors.length == 0) {
                        $scope.stationDistances.splice(index, 1);
                    }
                });
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

        if(!validate($scope.stationFrom, $scope.stationTo, $scope.distance)) return;

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
            $scope.errors.push("Start station is not set !");
            isValid = false;
        }

        if(stationTo == '') {
            $scope.errors.push("End station is not set !");
            isValid = false;
        }

        if(distance == '' || isNaN(parseInt(distance))) {
            $scope.errors.push("Please enter valid distance(needs to be in meters. E.g \'12500\')!");
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