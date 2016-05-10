
app.controller('RaceStationController', function ($scope, RaceStationService) {
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

        // Disable weekend selection
        $scope.disabled = function(calendarDate, mode) {
            return mode === 'day' && ( calendarDate.getDay() === 0 || calendarDate.getDay() === 6 );
        };

        $scope.open = function($event,opened) {
            $event.preventDefault();
            $event.stopPropagation();
            $scope.dateOpened = true;
        };

        $scope.dateOpened = false;
        $scope.hourStep = 1;
        $scope.format = "yyyy-MMM-dd";
        $scope.minuteStep = 1;

        $scope.timeOptions = {
            hourStep: [1, 2, 3],
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


    $scope.errors = [];

    $scope.removeRow = function(id){
        var index = -1;
        var comArr = eval( $scope.raceStations );
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

            RaceStationService.removeRow({ raceStationContainer : object, action: action })
                .then(function(data) {
                    $scope.errors.push.apply($scope.errors, data.errorList);
                    if($scope.errors.length == 0) {
                        $scope.raceStations.splice(index, 1);
                    }
                });
        }
    };
    $scope.updateRow = function(id){
        var index = -1;
        var comArr = eval( $scope.raceStations );
        for( var i = 0; i < comArr.length; i++ ) {
            if( comArr[i].id === id ) {
                index = i;
                break;
            }
        }
        if( index === -1 ) {
            alert( "Cannot update row with id " + id);
        } else {
            const object = comArr[index];
            const action = {
                id : 1
            };

            var raceStationCopy = angular.copy(object);
            raceStationCopy.depature = RaceStationService.convertUTCDateToLocalDate(object.depature);
            raceStationCopy.arriving = RaceStationService.convertUTCDateToLocalDate(object.arriving);

            if(!validate(raceStationCopy.id, raceStationCopy.depature, raceStationCopy.arriving, raceStationCopy.race.id, raceStationCopy.station.id)) return;

            RaceStationService.updateRow({ raceStationContainer: raceStationCopy, action: action })
                .then(function(data) {
                    $scope.errors.push.apply($scope.errors, data.errorList);
                    refreshData();
                });
        }
    };

    $scope.register = function() {
        $scope.errors = [];
        $scope.events = [];

        const raceStation = {
            id : $scope.raceStationIdToCreate,
            depature : RaceStationService.convertUTCDateToLocalDate($scope.departure),
            arriving : RaceStationService.convertUTCDateToLocalDate($scope.arriving),
            race : { id : $scope.raceIdToCreate },
            station : { id : $scope.stationIdToCreate }
        };
        const action = {
            id : 0
        };

        if(!validate($scope.raceStationIdToCreate, $scope.departure, $scope.arriving, $scope.raceIdToCreate, $scope.stationIdToCreate)) return;

        $scope.asyncRequestComplited = false;

        var smth = RaceStationService.register({ raceStationContainer :raceStation, action: action})
            .then(function(data) {
                refreshData();

                $scope.errors.push.apply($scope.errors, data.errorList);
                $scope.asyncRequestComplited = true;
            });
        $scope.$watch('asyncRequestComplited',function(newValue, oldValue, scope){
            if(scope.asyncRequestComplited && $scope.errors.length == 0){
               $scope.raceStationIdToCreate = "";
                $scope.departureToCreate = ""
                $scope.arrivingToCreate = ""

                RaceStationService.getRaceStations()
                    .then(function(data) {
                        $scope.races = data.data.races;
                    });
            }
        });

        return smth;
    };

    $scope.updateRaceForRaceStation = function(raceStation, raceId) {
        var comTrainTypesArr = eval( $scope.races );
        var indexRace = -1;
        for( var i = 0; i < comTrainTypesArr.length; i++ ) {
            if( comTrainTypesArr[i].id == raceId) {
                indexRace = i;
                break;
            }
        }

        var comTrainsArr = eval( $scope.raceStations );
        var indexRaceStation = -1;
        for( var i = 0; i < comTrainsArr.length; i++ ) {
            if( comTrainsArr[i].id === raceStation.id) {
                indexRaceStation = i;
                break;
            }
        }

        var race = $scope.raceStations[indexRace];
        var raceStations = $scope.trains[indexRaceStation];
        raceStations.race = race;

    };
    $scope.updateStationForRaceStation = function(raceStation, stationId) {
        var comTrainTypesArr = eval( $scope.stations );
        var indexStation = -1;
        for( var i = 0; i < comTrainTypesArr.length; i++ ) {
            if( comTrainTypesArr[i].id == stationId) {
                indexStation = i;
                break;
            }
        }

        var comTrainsArr = eval( $scope.raceStations );
        var indexRaceStation = -1;
        for( var i = 0; i < comTrainsArr.length; i++ ) {
            if( comTrainsArr[i].id === raceStation.id) {
                indexRaceStation = i;
                break;
            }
        }

        var raceStation = $scope.races[indexRace];
        var station = $scope.routes[indexStation];
        raceStation.station = station;
    };

    $scope.refreshData = function() {
        RaceStationService.getRaceStations()
            .then(function(data) {
                $scope.raceStations = data.data.raceStations;
                $scope.races = data.data.races;
                $scope.stations = data.data.stations;
            });
    };

    function validate(raceStationId, departureDate, arrivingDate, raceId, stationId) {
        var isValid = true;
        $scope.errors = [];

        if(raceStationId == "" || isNaN(parseInt(raceStationId)) || parseInt(raceStationId) <=0 ) {
            $scope.errors.push("RaceStation identifier is incorrect(must be greater then 0)");
            isValid = false;
        }

        var timestampForDeparture=Date.parse(departureDate)
        if (isNaN(timestampForDeparture))
        {
            $scope.errors.push("Departure date is incorrect !");
            isValid = false;
        }

        var timestampForArriving=Date.parse(arrivingDate)
        if (isNaN(timestampForArriving))
        {
            $scope.errors.push("Arriving date is incorrect !");
            isValid = false;
        }

        if(raceId <= 0) {
            $scope.errors.push("Station \'From\' NOT selected");
            isValid = false;
        }
        if(stationId <= 0) {
            $scope.errors.push("Station \'To\' NOT selected");
            isValid = false;
        }

        if(timestampForArriving <= timestampForDeparture)
        {
            $scope.errors.push("Arriving and Departure date overlap each other !");
            isValid = false;
        }

        return isValid;
    }

    function refreshData() {
        return RaceStationService.getRaceStations()
            .then(function(data) {
                $scope.raceStations = data.data.raceStationContainers;

                for(var i = 0; i <  $scope.raceStations.length; i++) {
                    $scope.raceStations[i].depature = new Date($scope.raceStations[i].depature);
                    $scope.raceStations[i].arriving = new Date($scope.raceStations[i].arriving);
                }

                $scope.races = data.data.races;
                $scope.stations = data.data.stations;
            });
    }

    return refreshData();
});