angular.module('app').controller('RaceChoiceController', function ($scope, $location, RaceChoiceService,
                                                 StationService, Service, TicketShare) {
    $scope.dateTimeNow = function() {
        $scope.date = new Date();
    };
    $scope.dateTimeNow();

    $scope.toggleMinDate = function() {
        var minDate = new Date();
        // set to yesterday
        minDate.setDate(minDate.getDate() - 1);
        $scope.dateOptions.minDate = $scope.dateOptions.minDate ? null : minDate;
    };

    $scope.dateOptions = {
        showWeeks: false,
        startingDay: 0
    };

    $scope.toggleMinDate();


    $scope.open = function($event,opened) {
        $event.preventDefault();
        $event.stopPropagation();
        $scope.dateOpened = true;
    };

    $scope.dateOpened = false;
    $scope.format = "yyyy-MMM-dd ";

    $scope.timeOptions = {
        minuteStep: [1, 5, 10, 15, 25, 30]
    };

    $scope.showMeridian = true;
    $scope.timeToggleMode = function() {
        $scope.showMeridian = !$scope.showMeridian;
    };

    $scope.$watch("date", function(date) {
        // read date value
    }, true);

    $scope.resetHours = function() {
        $scope.date.setHours(1);
    };






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
        if (typeof $scope.raceDate == 'undefined' || $scope.raceDate == null) {
            $scope.errors.push.apply($scope.errors, ['Your race date is incorrect!']);
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


        $scope.options = {
            year: 'numeric',
            month: 'long',
            day: 'numeric',
            weekday: 'long',
            timezone: 'UTC',
            hour: 'numeric',
            minute: 'numeric'
        };

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

                const RACE_DATA_TO_SHARE = {
                    raceId: id,
                    departureStationName: departureStationName,
                    arriveStationName: arriveStationName,
                    name: raceInfo.name,
                    cost: raceInfo.cost,
                    departure: raceInfo.departure,
                    arriving: raceInfo.arriving,
                    carriages: data.raceDetails.carriages
                };
                TicketShare.set(RACE_DATA_TO_SHARE);
                $location.path('/ticketorder/racedetails');
            }
        });
    };

    return StationService.getStation().then(function(data) {
        $scope.stations = data.data.stations;
    });

});
