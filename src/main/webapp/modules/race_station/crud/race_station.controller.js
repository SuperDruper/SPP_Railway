/**
 * Created by dzmitry.antonenka on 11.04.2016.
 */
app.controller('RaceStationController', function ($scope, RaceStationService) {
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

            RaceStationService.removeRow({ race: object, action: action });
            $scope.raceStations.splice(index, 1);
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
            alert( "Cannot update row with id" + id);
        } else {
            const object = comArr[index];
            const action = {
                id : 1
            };

            RaceStationService.updateRow({ race: object, action: action });
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

        $scope.asyncRequestComplited = false;

        var smth = RaceStationService.register({ raceStation:raceStation, action: action})
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

    return RaceStationService.getRaceStations()
        .then(function(data) {
            $scope.raceStations = data.data.raceStations;
            $scope.races = data.data.races;
            $scope.stations = data.data.stations;
        });
});