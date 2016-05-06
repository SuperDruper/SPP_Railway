/**
 * Created by dzmitry.antonenka on 10.04.2016.
 */
app.controller('TrainListController', function ($scope, TrainTypeListService) {
    $scope.errors = [];
    $scope.trainTypeNameToCreate = '';
    $scope.trainTypeCoefficientToCreate = '';
    $scope.trainTypePlacesAmountToCreate = '';

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
            const trainType = comArr[index];
            const action = {
                id : 1
            };

            if(!validate(trainType.name, trainType.coefficient, trainType.placesAmount)) return;

            TrainTypeListService.updateRow({ trainType: trainType, action: action })
                .then(function() {
                    refreshData()
                });
        }
    };


    function validate(trainTypeNameToCreate, trainTypeCoefficientToCreate, trainTypePlacesAmountToCreate) {
        var isValid = true;

        if(trainTypeNameToCreate.trim().length == 0) {
            $scope.errors.push("Train type name cannot be empty !");
            isValid = false;
        }

        if(isNaN(parseFloat(trainTypeCoefficientToCreate)))
        {
            $scope.errors.push("Train coefficient coefficient must be FLOAT number.");
            isValid = false;
        }

        if(isNaN(parseInt(trainTypePlacesAmountToCreate)))
        {
            $scope.errors.push("Train places amount must be INT number.");
            isValid = false;
        }

        return isValid;
    }

    $scope.register = function() {
        $scope.errors = [];

        if(!validate($scope.trainTypeNameToCreate, $scope.trainTypeCoefficientToCreate, $scope.trainTypePlacesAmountToCreate)) return;
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
        TrainTypeListService.getTrainTypes()
            .then(function(data) {
                $scope.trainTypes = data.data.trainTypes;
                $scope.errors.push.apply($scope.errors, data.errorList);
            });
    };

        return TrainTypeListService.getTrainTypes()
        .then(function(data) {
            $scope.trainTypes = data.data.trainTypes;
                $scope.errors.push.apply($scope.errors, data.data.errorList);
        });
});