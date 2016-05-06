/**
 * Created by dzmitry.antonenka on 11.04.2016.
 */
app.controller('StationController', function ($scope, $window, StationService) {

    $scope.removeRow = function(id){
        var index = -1;
        var comArr = eval( $scope.stations );
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

            StationService.removeRow({ station: object, action: action });
            $scope.stations.splice(index, 1);
        }

    };
    $scope.updateRow = function(id){
        var index = -1;
        var comArr = eval( $scope.stations );
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
            StationService.updateRow({ station: object, action: action })
                .then(function(data) {
                    refreshData();
                    $scope.errors.push.apply($scope.errors, data.errorList);
                });
        }
    };
    $scope.register = function() {
        $scope.errors = [];
        $scope.events = [];

        const object = {
            name: $scope.stationNameToCreate
        };

        const action = {
            id : 0
        };

        if(!validate($scope.stationNameToCreate)) return;

        $scope.asyncRequestComplited = false;

        var smth = StationService.register({station:object, action: action})
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

                refreshData();
            }
        });

        return smth;
    }

    function validate(stationName)
    {
        if(stationName != null && stationName.trim().length != 0) {
            return true;
        } else {
            $scope.errors.push("Station name cannot be empty !");
            return false;
        }
    }

    function refreshData() {
        return StationService.getStation()
            .then(function(data) {
                $scope.stations = data.data.stations;
            });
    }

    return StationService.getStation()
        .then(function(data) {
            $scope.stations = data.data.stations;
        });

});