
app.controller('RaceStationController', function ($scope, RaceStationService) {
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

            if(!validate(object.id, object.depature, object.arriving, object.race.id, object.station.id)) return;

            RaceStationService.updateRow({ raceStationContainer: object, action: action })
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
            depature : $scope.departureToCreate,
            arriving : $scope.arrivingToCreate,
            race : { id : $scope.raceIdToCreate },
            station : { id : $scope.stationIdToCreate }
        };
        const action = {
            id : 0
        };

        if(!validate($scope.raceStationIdToCreate, $scope.departureToCreate, $scope.arrivingToCreate, $scope.raceIdToCreate, $scope.stationIdToCreate)) return;

        $scope.asyncRequestComplited = false;

        var smth = RaceStationService.register({ raceStationContainer :raceStation, action: action})
            .then(function(data) {
                $scope.errors.push.apply($scope.errors, data.errorList);
                $scope.events.push.apply($scope.events, data.eventList);

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

        var dateComponentsForDeparture = departureDate.split(' ');

        var timestampForDeparture=Date.parse(dateComponentsForDeparture[0] + "T" + dateComponentsForDeparture[1])
        if (isNaN(timestampForDeparture))
        {
            $scope.errors.push("Departure date is incorrect !");
            isValid = false;
        }

        var dateComponentsForArriving = arrivingDate.split(' ');

        var timestampForArriving=Date.parse(dateComponentsForArriving[0] + "T" + dateComponentsForArriving[1])
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
                $scope.raceStations = data.data.raceStations;
                $scope.races = data.data.races;
                $scope.stations = data.data.stations;
            });
    }

    return refreshData();
});