
app.controller('ExampleController', function ($scope, ExampleService) {

    $scope.send = function() {
        const message = {
            text: $scope.text,
            amount: $scope.amount
        };

        return ExampleService.send(message);
    }
});