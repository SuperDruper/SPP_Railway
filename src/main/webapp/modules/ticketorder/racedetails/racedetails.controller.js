angular.module('app').controller('RaceDetailsController',
        function ($scope, $location, TicketShare, RaceDetailsService, ModalViewAnimatorService) {

    $scope.buy = function() {
        $scope.errors = [];

        var raceData = $scope.race;
        const ticketDataToOrder = {
            raceId: raceData.raceId,
            departureStationName: raceData.departureStationName,
            arriveStationName: raceData.arriveStationName,
            carriageNum: $scope.carriageNum,
            placeNum: $scope.placeNum
        };


         RaceDetailsService.orderTicket( {ticketDataToOrder:ticketDataToOrder} )
            .then(function(data) {
                $scope.errors.push.apply($scope.errors, data.errorList);
                ModalViewAnimatorService.showModelViewAnimated($scope);

                if($scope.errors.length == 0) {
                    TicketShare.get().carriageNum = $scope.carriageNum;
                    TicketShare.get().placeNum = $scope.placeNum;
                    TicketShare.get().ticketNum = data.ticketNum;
                    $location.path('/ticketorder/ticketshow');
                }
            });
    };

    $scope.race = TicketShare.get();
});
