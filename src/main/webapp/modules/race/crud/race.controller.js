/**
 * Created by dzmitry.antonenka on 11.04.2016.
 */
app.controller('RaceListController', function ($scope, RaceListService) {
    $scope.removeRow = function(id){
        var index = -1;
        var comArr = eval( $scope.races );
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

            RaceListService.removeRow({ race: object, action: action });
            $scope.race.splice(index, 1);
        }

    };
    $scope.updateRow = function(id){
        var index = -1;
        var comArr = eval( $scope.races );
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

            RaceListService.updateRow({ race: object, action: action });
        }
    };

    $scope.register = function() {
        $scope.errors = [];
        $scope.events = [];

        const race = {

        };
        const action = {
            id : 0
        };

        $scope.asyncRequestComplited = false;

        var smth = RaceListService.register({race:race, action: action})
            .then(function(data) {
                $scope.errors.push.apply($scope.errors, data.errorList);
                $scope.events.push.apply($scope.events, data.eventList);

                $scope.asyncRequestComplited = true;
            });
        $scope.$watch('asyncRequestComplited',function(newValue, oldValue, scope){
            if(scope.asyncRequestComplited && $scope.errors.length == 0){
                // $window.location.href = '/';
                //$scope.trainId = "";
                //$scope.trainCarriageAmount = "";
                //$scope.trainType = "";

                RaceListService.getRaces()
                    .then(function(data) {
                        $scope.races = data.data.races;
                    });
            }
        });

        return smth;
    }

    $scope.refreshData = function() {
        RaceListService.getRaces()
            .then(function(data) {
                $scope.races = data.data.races;
            });
    };

    return RaceListService.getRaces()
        .then(function(data) {
            $scope.races = data.data.races;
            $scope.routes = data.data.routes;
        });
});