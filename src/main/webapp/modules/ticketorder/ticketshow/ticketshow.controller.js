app.controller('TicketShowController', function ($scope, $location, TicketShare) {
        $scope.race = TicketShare.get();
});
