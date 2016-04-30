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

            StationService.updateRow({ station: object, action: action });
        }
    };
    $scope.register = function() {
        $scope.errors = [];
        $scope.events = [];

        const object = {
            id: $scope.stationIdToCreate,
            name: $scope.stationNameToCreate
        };

        const action = {
            id : 0
        };

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

                StationService.getStation()
                    .then(function(data) {
                        $scope.stations = data.data.stations;
                    });
            }
        });

        return smth;
    }

    return StationService.getStation()
        .then(function(data) {
            $scope.stations = data.data.stations;
        });

});