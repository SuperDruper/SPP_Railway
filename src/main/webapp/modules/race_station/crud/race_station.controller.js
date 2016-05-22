
app.controller('RaceStationController', function ($scope, RaceStationService, ModalViewAnimatorService) {
        $scope.date = new Date();
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

        $scope.resetHours = function() {
            $scope.date.setHours(1);
        };


    $scope.errors = [];

    $scope.tryToRemoveRow = function(id) {
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
            const Message = "Are you sure you want to delete race station \'" + comArr[index].race_station_numbr + "\' ?";
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

            RaceStationService.removeRow({ raceStationContainer : object, action: action })
                .then(function(data) {
                    $scope.errors.push.apply($scope.errors, data.errorList);
                    ModalViewAnimatorService.showModelViewAnimated($scope);
                    if($scope.errors.length == 0) {
                        $scope.raceStations.splice(index, 1);
                    }
                });
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

            if(!validate(raceStationCopy.race_station_numbr, raceStationCopy.depature, raceStationCopy.arriving, raceStationCopy.race.id, raceStationCopy.station.id)) {
                ModalViewAnimatorService.showModelViewAnimated($scope);
                return;
            }

            RaceStationService.updateRow({ raceStationContainer: raceStationCopy, action: action })
                .then(function(data) {
                    $scope.errors.push.apply($scope.errors, data.errorList);
                    ModalViewAnimatorService.showModelViewAnimated($scope);
                    refreshData();
                });
        }
    };

    $scope.register = function() {
        $scope.errors = [];
        $scope.events = [];


        if(!validate($scope.raceStationIdToCreate, $scope.departure, $scope.arriving, $scope.raceIdToCreate, $scope.stationIdToCreate)) {
            ModalViewAnimatorService.showModelViewAnimated($scope);
            return;
        }

        const raceStation = {
            race_station_numbr : $scope.raceStationIdToCreate,
            depature : RaceStationService.convertUTCDateToLocalDate($scope.departure),
            arriving : RaceStationService.convertUTCDateToLocalDate($scope.arriving),
            race : { id : $scope.raceIdToCreate },
            station : { id : $scope.stationIdToCreate }
        };
        const action = {
            id : 0
        };

        $scope.asyncRequestComplited = false;

        var smth = RaceStationService.register({ raceStationContainer :raceStation, action: action})
            .then(function(data) {
                refreshData();

                $scope.errors.push.apply($scope.errors, data.errorList);
                ModalViewAnimatorService.showModelViewAnimated($scope);
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
            $scope.errors.push("Please enter race station number(must be greater then 0)");
            isValid = false;
        }

        var timestampForDeparture=Date.parse(departureDate)
        if (isNaN(timestampForDeparture))
        {
            $scope.errors.push("Please enter correct departure date!");
            isValid = false;
        }

        var timestampForArriving=Date.parse(arrivingDate)
        if (isNaN(timestampForArriving))
        {
            $scope.errors.push("Please enter correct arriving date!");
            isValid = false;
        }

        if(raceId <= 0) {
            $scope.errors.push("Please select race from drop-down list!");
            isValid = false;
        }
        if(stationId <= 0) {
            $scope.errors.push("Please select station from drop-down list!");
            isValid = false;
        }

        if(timestampForArriving <= timestampForDeparture)
        {
            $scope.errors.push("Please enter correct date arriving and departure dates. Currently they arriving earlier then departure!");
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
                $scope.stationHashMap = data.data.stationHashMap;
            });
    }

    return refreshData();
});