app.controller('TrainController', function ($scope, $window, TrainService, ModalViewAnimatorService) {
    $scope.errors = [];

    $scope.tryToRemoveRow = function(id) {
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
            const Message = "Are you sure you want to delete train with number \'" + comArr[index].id + "\' ?";
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
            $scope.errors = [];
            const action = {
                id : 2
            };

            TrainService.removeRow({ train: object, action: action }).then(function(data) {
                $scope.errors.push.apply($scope.errors, data.errorList);
                ModalViewAnimatorService.showModelViewAnimated($scope);

                if($scope.errors.length == 0) {
                    $scope.trains.splice(index, 1);
                }
            });
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

            if(!validate(object.train_number, object.carriageAmount, object.trainType)) {
                ModalViewAnimatorService.showModelViewAnimated($scope);
                return;
            }
            TrainService.updateRow({ train: object, action: action })
                .then(function(data) {
                    refreshData();

                    $scope.errors.push.apply($scope.errors, data.errorList);
                    ModalViewAnimatorService.showModelViewAnimated($scope);
            });
        }
    };
    $scope.register = function() {
        $scope.errors = [];
        $scope.events = [];

        if(!validate($scope.trainId, $scope.trainCarriageAmount, $scope.trainTypeToCreate)) {
            ModalViewAnimatorService.showModelViewAnimated($scope);
            return;
        }

        const trainType = {
            id : $scope.trainTypeToCreate
        };
        const train = {
            train_number: $scope.trainId,
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
                ModalViewAnimatorService.showModelViewAnimated($scope);

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
        $scope.errors = [];
        var isValid = true;

        if(trainIdentifier == null || isNaN(parseInt(trainIdentifier)))
        {
            $scope.errors.push("Please enter valid train number(must be greater then 0)!");
            isValid = false;
        }

        if(trainCarriageAmount == null || isNaN(parseInt(trainCarriageAmount)))
        {
            $scope.errors.push("Please enter valid train carriage number(must be greater then 0)!");
            isValid = false;
        }

        if(trainType == null || (trainType.id <=0 || trainType.id > $scope.trainTypes.length))
        {
            $scope.errors.push("Please select train type!");
            isValid = false;
        }

        return isValid;
    }


    function refreshData()
    {
        return TrainService.getTrains()
            .then(function(data) {
                $scope.trains = data.data.trains;
                $scope.trainTypes = data.data.trainTypes;

                $scope.defaultSelectedTrainType = $scope.trainTypes[0].id;
            });
    }

    return TrainService.getTrains()
        .then(function(data) {
            $scope.trains = data.data.trains;
            $scope.trainTypes = data.data.trainTypes;

            $scope.defaultSelectedTrainType = $scope.trainTypes[0].id;
        });

});
