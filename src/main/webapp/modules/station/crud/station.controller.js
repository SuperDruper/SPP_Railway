/**
 * Created by dzmitry.antonenka on 11.04.2016.
 */
app.controller('StationController', function ($scope, $window, StationService, ModalViewAnimatorService) {
    $scope.errors = [];

    $scope.tryToRemoveRow = function(id) {
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
            const Message = "Are you sure you want to delete station \'" + comArr[index].name + "\' ?";
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

            StationService.removeRow({ station: object, action: action })
                .then(function(data) {
                    $scope.errors.push.apply($scope.errors, data.errorList);
                    ModalViewAnimatorService.showModelViewAnimated($scope);
                    if($scope.errors.length == 0) {
                        $scope.stations.splice(index, 1);
                    }
                });
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

            if(!validate(object.name)) {
                ModalViewAnimatorService.showModelViewAnimated($scope);
                return;
            }
            StationService.updateRow({ station: object, action: action })
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

        const object = {
            name: $scope.stationNameToCreate
        };

        const action = {
            id : 0
        };

        if(!validate($scope.stationNameToCreate)) {
            ModalViewAnimatorService.showModelViewAnimated($scope);
            return;
        }

        $scope.asyncRequestComplited = false;

        var smth = StationService.register({station:object, action: action})
            .then(function(data) {
                $scope.errors.push.apply($scope.errors, data.errorList);
                ModalViewAnimatorService.showModelViewAnimated($scope);

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
        $scope.errors = [];

        if(stationName != null && stationName.trim().length != 0) {
            return true;
        } else {
            $scope.errors.push("Please enter valid station name!");
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