app.controller('RaceChoiceController', function ($scope, $rootScope, $location, RaceChoiceService, GetRaceStationService, Service) {

    var departureStationId = 0;
    var arriveStationId = 0;

    $scope.find = function() {
            $scope.errors = [];

        var errorOccurs = false;
        if ($scope.departureStationId === $scope.arriveStationId) {
            $scope.errors = ['You don\'t need the race, ' +
                    'where departure and arrive stations are the same!'];
            errorOccurs = true;
        }
        if ($scope.departureStationId === 0) {
            $scope.errors.push.apply($scope.errors, ['Departure station can\'t be empty!']);
            errorOccurs = true;
        }
        if ($scope.arriveStationId === 0) {
            $scope.errors.push.apply($scope.errors, ['Arriving station can\'t be empty!']);
            errorOccurs = true;
        }
        if (errorOccurs) {
            return;
        }

        const raceSearchData = {
            departureStationId : $scope.departureStationId,
            arriveStationId : $scope.arriveStationId,
            raceDate : RaceChoiceService.convertUTCDateToLocalDate($scope.raceDate)
        };

        departureStationId = $scope.departureStationId;
        arriveStationId = $scope.arriveStationId;


        return RaceChoiceService.getRaceInfos( {raceSearchData:raceSearchData} )
            .then(function(data) {
                if(data.errorList.length > 0) {
                    $scope.errors.push.apply($scope.errors, data.errorList);
                }

                if($scope.errors.length == 0) {
                    $scope.raceInfos = data.raceInfos;
                    $scope.errors = null;
                }
            });
    };

    $scope.showRaceDetails = function(id) {
        RaceChoiceService.getRaceDetails({raceId: id}).then(function (data) {
            if (data.errorList.length != 0) {
                alert("Something was wrong with server! It says that:" + data.errorList.join(".\n"));
            }
            else {
                var departureStationName = Service.getObjectByIdFromList($scope.stations, departureStationId).name;
                var arriveStationName = Service.getObjectByIdFromList($scope.stations, arriveStationId).name;
                var raceInfo = Service.getObjectByIdFromList($scope.raceInfos, id);

                const RACE_DATA_TO_SHOW = {
                    departureStationName: departureStationName,
                    arriveStationName: arriveStationName,
                    name: raceInfo.name,
                    cost: raceInfo.cost,
                    departure: raceInfo.departure,
                    arriving: raceInfo.arriving,
                    carriages: data.raceDetails.carriages
                };
                $rootScope.raceDataToShow = RACE_DATA_TO_SHOW;
                $location.path('/ticketorder/racedetails');
            }
        });

    };

    return GetRaceStationService.getRaceStations().then(function(data) {
        $scope.stations = data.data.stations;
    });

});
