app.controller('TrainController', function ($scope, $window, TrainService) {

    $scope.register = function() {
        $scope.errors = [];
        $scope.events = [];

        if($scope.id <= 0 || $scope.carriageAmount <= 0) {
            $scope.errors = ['Input field are invalid. Please check it and try again!'];
            return;
        }

        const trainType = {
            id : $scope.trainType
        };

        const train = {
            id: $scope.id,
            carriageAmount: $scope.carriageAmount,
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
                $scope.id = "";
                $scope.carriageAmount = "";
                $scope.trainType = "";
            }
        });

        return smth;
    }

});
