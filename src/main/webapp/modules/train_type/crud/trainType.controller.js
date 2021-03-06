/**
 * Created by dzmitry.antonenka on 10.04.2016.
 */
app.controller('TrainListController', function ($scope, TrainTypeListService, ModalViewAnimatorService) {
    $scope.errors = [];
    $scope.trainTypeNameToCreate = '';
    $scope.trainTypeCoefficientToCreate = '';
    $scope.trainTypePlacesAmountToCreate = '';

    $scope.objectForDeleteOpearion = '';

    $scope.tryToRemoveRow = function(id) {
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
            const Message = "Are you sure you want to delete train type " + comArr[index].name + "?";
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

            TrainTypeListService.removeRow({ trainType: object, action: action })
                .then(function(data) {
                    $scope.errors.push.apply($scope.errors, data.errorList);
                    ModalViewAnimatorService.showModelViewAnimated($scope);

                    if($scope.errors.length == 0) {
                        $scope.trainTypes.splice(index, 1);
                    }
                });
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
            const trainType = comArr[index];
            const action = {
                id : 1
            };

            if(!validate(trainType.name, trainType.coefficient, trainType.placesAmount)) {
                ModalViewAnimatorService.showModelViewAnimated($scope);
                return;
            }

            TrainTypeListService.updateRow({ trainType: trainType, action: action })
                .then(function(data) {
                    refreshData();
                    $scope.errors.push.apply($scope.errors, data.errorList);
                    ModalViewAnimatorService.showModelViewAnimated($scope);
                });
        }
    };


    function validate(trainTypeNameToCreate, trainTypeCoefficientToCreate, trainTypePlacesAmountToCreate) {
        $scope.errors = [];
        var isValid = true;

        if(trainTypeNameToCreate == null || trainTypeNameToCreate.trim().length == 0) {
            $scope.errors.push("Please enter valid name for train type!");
            isValid = false;
        }

        if(trainTypeCoefficientToCreate == null || isNaN(parseFloat(trainTypeCoefficientToCreate)) || parseFloat(trainTypeCoefficientToCreate) <= 0)
        {
            $scope.errors.push("Please enter valid multiply coefficient. E.g \'0.55\'");
            isValid = false;
        }

        if(trainTypePlacesAmountToCreate == null || isNaN(parseInt(trainTypePlacesAmountToCreate)) || parseInt(trainTypePlacesAmountToCreate) <= 0)
        {
            $scope.errors.push("Please enter valid value for amount of places. E.g \'60\'");
            isValid = false;
        }

        return isValid;
    }

    $scope.register = function() {
        $scope.errors = [];

        if(!validate($scope.trainTypeNameToCreate, $scope.trainTypeCoefficientToCreate, $scope.trainTypePlacesAmountToCreate)) {
            ModalViewAnimatorService.showModelViewAnimated($scope);
            return;
        }
        const trainType = {
            name : $scope.trainTypeNameToCreate,
            coefficient : $scope.trainTypeCoefficientToCreate,
            placesAmount : $scope.trainTypePlacesAmountToCreate
        };
        const action = {
            id : 0
        };

        $scope.asyncRequestComplited = false;

        var smth = TrainTypeListService.register({trainType : trainType, action: action})
            .then(function(data) {
                $scope.errors.push.apply($scope.errors, data.errorList);
                ModalViewAnimatorService.showModelViewAnimated($scope);
                $scope.asyncRequestComplited = true;
            });
        $scope.$watch('asyncRequestComplited',function(newValue, oldValue, scope){
            if(scope.asyncRequestComplited && $scope.errors.length == 0){
                 $scope.trainTypeNameToCreate = '';
                 $scope.trainTypeCoefficientToCreate = '';
                 $scope.trainTypePlacesAmountToCreate = '';

                 refreshData();
            }
        });

        return smth;
    }

    function refreshData() {
       return TrainTypeListService.getTrainTypes()
            .then(function(data) {
                $scope.trainTypes = data.data.trainTypes;
            });
    };

        return TrainTypeListService.getTrainTypes()
        .then(function(data) {
            $scope.trainTypes = data.data.trainTypes;
        });
});