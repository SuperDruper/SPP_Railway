/**
 * Created by dzmitry.antonenka on 10.04.2016.
 */
app.controller('TrainListController', function ($scope, TrainTypeListService) {
    $scope.removeRow = function(id){
        var index = -1;
        var comArr = eval( $scope.trainTypes );
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

            TrainTypeListService.removeRow({ trainType: object, action: action });
            $scope.trainTypes.splice(index, 1);
        }

    };
    $scope.updateRow = function(id){
        var index = -1;
        var comArr = eval( $scope.trainTypes );
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

            TrainTypeListService.updateRow({ trainType: object, action: action });
        }
    };

    $scope.register = function() {
        $scope.errors = [];
        $scope.events = [];

        const trainType = {

        };
        const action = {
            id : 0
        };

        $scope.asyncRequestComplited = false;

        var smth = TrainTypeListService.register({train:train, action: action})
            .then(function(data) {
                $scope.errors.push.apply($scope.errors, data.errorList);
                $scope.events.push.apply($scope.events, data.eventList);

                $scope.asyncRequestComplited = true;
            });
        $scope.$watch('asyncRequestComplited',function(newValue, oldValue, scope){
            if(scope.asyncRequestComplited && $scope.errors.length == 0){
                // $window.location.href = '/';
                $scope.trainId = "";
                $scope.trainCarriageAmount = "";
                $scope.trainType = "";

                TrainService.getTrains()
                    .then(function(data) {
                        $scope.trains = data.data.trains;
                    });
            }
        });

        return smth;
    }

    $scope.refreshData = function() {
        TrainTypeListService.getTrainTypes()
            .then(function(data) {
                $scope.trainTypes = data.data.trainTypes;
            });
    };

        return TrainTypeListService.getTrainTypes()
        .then(function(data) {
            $scope.trainTypes = data.data.trainTypes;
        });
});