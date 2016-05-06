angular.module('app').controller('RaceDetailsController',
        function ($scope, $location, TicketShare, RaceDetailsService) {

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


        return RaceDetailsService.orderTicket( {ticketDataToOrder:ticketDataToOrder} )
            .then(function(data) {
                if(data.errorList.length > 0) {
                    $scope.errors.push.apply($scope.errors, data.errorList);
                }

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
