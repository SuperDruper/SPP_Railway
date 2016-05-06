app.controller('TrainController', function ($scope, $window, TrainService) {
    $scope.errors = [];

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

            if(!validate(object.id, object.carriageAmount, object.trainType)) return;
            TrainService.updateRow({ train: object, action: action });
        }
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

        if(!validate($scope.trainId, $scope.trainCarriageAmount, trainType)) return;

        var smth = TrainService.register({train:train, action: action})
            .then(function(data) {
                $scope.errors.push.apply($scope.errors, data.errorList);
                $scope.events.push.apply($scope.events, data.eventList);

                $scope.asyncRequestComplited = true;
            });
        $scope.$watch('asyncRequestComplited',function(newValue, oldValue, scope){
            if(scope.asyncRequestComplited && $scope.errors.length == 0){
                $scope.trainTypeToCreate = -1;
                TrainService.getTrains()
                    .then(function(data) {
                        $scope.trainId = '';
                        $scope.trainCarriageAmount = '';
                        $scope.trainTypeToCreate = '';

                        $scope.trains = data.data.trains;
                        $scope.trainTypes = data.data.trainTypes;
                    });
            }
        });

        return smth;
    }

    function validate(trainIdentifier, trainCarriageAmount, trainType)
    {
        var isValid = true;

        if(isNaN(parseInt(trainIdentifier)))
        {
            $scope.errors.push("Train identifier must be an integer value.");
            isValid = false;
        }

        if(isNaN(parseInt(trainCarriageAmount)))
        {
            $scope.errors.push("Train carriage amount must be an integer value.");
            isValid = false;
        }

        if(trainType == null || trainType.id <=0 )
        {
            $scope.errors.push("Train type must be selected");
            isValid = false;
        }

        return isValid;
    }

    return TrainService.getTrains()
        .then(function(data) {
            $scope.trains = data.data.trains;
            $scope.trainTypes = data.data.trainTypes;

            $scope.defaultSelectedTrainType = $scope.trainTypes[0].id;
        });

});
