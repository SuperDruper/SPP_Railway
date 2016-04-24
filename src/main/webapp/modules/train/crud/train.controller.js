app.controller('TrainController', function ($scope, $window, TrainService) {

    $scope.removeRow = function(id){
        var index = -1;
        var comArr = eval( $scope.trains );
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

            TrainService.removeRow({ train: object, action: action });
            $scope.trains.splice(index, 1);
        }

    };

    $scope.updateTrainTypeForTrain = function(train, trainTypeId) {
        var comTrainTypesArr = eval( $scope.trainTypes );
        var indexTrainType = -1;
        for( var i = 0; i < comTrainTypesArr.length; i++ ) {
            if( comTrainTypesArr[i].id == trainTypeId) {
                indexTrainType = i;
                break;
            }
        }

        var comTrainsArr = eval( $scope.trains );
        var indexTrain = -1;
        for( var i = 0; i < comTrainsArr.length; i++ ) {
            if( comTrainsArr[i].id === train.id) {
                indexTrain = i;
                break;
            }
        }

        var train = $scope.trains[indexTrain];
        var trainType = $scope.trainTypes[indexTrainType];
        train.trainType = trainType;

        //$scope.trains.splice(indexTrain, 1);
        //$scope.trains.splice(indexTrain, 0, train);
        //$scope.trains[indexTrain] = train;
    };

    $scope.updateRow = function(id){
        var index = -1;
        var comArr = eval( $scope.trains );
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

            TrainService.updateRow({ train: object, action: action });
        }v
    };
    $scope.register = function() {
        $scope.errors = [];
        $scope.events = [];

        const trainType = {
            id : $scope.trainTypeToCreate
        };
        const train = {
            id: $scope.trainId,
            carriageAmount: $scope.trainCarriageAmount,
            trainType: trainType
        };
        const action = {
            id : 0
        };

        $scope.asyncRequestComplited = false;

        var smth = TrainService.register({train:train, action: action})
            .then(function(data) {
                $scope.errors.push.apply($scope.errors, data.errorList);
                $scope.events.push.apply($scope.events, data.eventList);

                $scope.asyncRequestComplited = true;
            });
        $scope.$watch('asyncRequestComplited',function(newValue, oldValue, scope){
            if(scope.asyncRequestComplited && $scope.errors.length == 0){
                // $window.location.href = '/';
                //private int id;
                //private String name;
                //private double coefficient;
                //private int placesAmount;
                //$scope.trainyTypeId = "";
                //$scope.trainTypeName = "";
                //$scope.trainTypeCoefficient = "";
                $scope.trainTypeToCreate = -1;
                TrainService.getTrains()
                    .then(function(data) {
                        $scope.trains = data.data.trains;
                        $scope.trainTypes = data.data.trainTypes;

                        $score.defaultSelectedTrainType = $scope.trainTypes[0].id;
                    });
            }
        });

        return smth;
    }

    return TrainService.getTrains()
        .then(function(data) {
            $scope.trains = data.data.trains;
            $scope.trainTypes = data.data.trainTypes;

            $scope.defaultSelectedTrainType = $scope.trainTypes[0].id;
        });

});
