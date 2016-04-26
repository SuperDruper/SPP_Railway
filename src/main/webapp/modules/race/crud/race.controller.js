/**
 * Created by dzmitry.antonenka on 11.04.2016.
 */
app.controller('RaceListController', function ($scope, RaceListService) {
    $scope.removeRow = function(id){
        var index = -1;
        var comArr = eval( $scope.races );
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

            RaceListService.removeRow({ race: object, action: action });
            $scope.races.splice(index, 1);
        }

    };
    $scope.updateRow = function(id){
        var index = -1;
        var comArr = eval( $scope.races );
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

            RaceListService.updateRow({ race: object, action: action });
        }
    };

    $scope.register = function() {
        $scope.errors = [];
        $scope.events = [];

        const race = {
            id : $scope.raceIdToCreate,
            route : { id : $scope.routeToCreate },
            train : { id : $scope.trainToCreate }
        };
        const action = {
            id : 0
        };

        $scope.asyncRequestComplited = false;

        var smth = RaceListService.register({race:race, action: action})
            .then(function(data) {
                $scope.errors.push.apply($scope.errors, data.errorList);
                $scope.events.push.apply($scope.events, data.eventList);

                $scope.asyncRequestComplited = true;
            });
        $scope.$watch('asyncRequestComplited',function(newValue, oldValue, scope){
            if(scope.asyncRequestComplited && $scope.errors.length == 0){
                    $scope.raceIdToCreate = ""
                    $scope.routeToCreate = ""
                    $scope.trainToCreate = ""

                RaceListService.getRaces()
                    .then(function(data) {
                        $scope.races = data.data.races;
                    });
            }
        });

        return smth;
    }

    $scope.updateTrainForRace = function(race, trainId) {
        var comTrainTypesArr = eval( $scope.trains );
        var indexTrain = -1;
        for( var i = 0; i < comTrainTypesArr.length; i++ ) {
            if( comTrainTypesArr[i].id == trainId) {
                indexTrain = i;
                break;
            }
        }

        var comTrainsArr = eval( $scope.races );
        var indexRace = -1;
        for( var i = 0; i < comTrainsArr.length; i++ ) {
            if( comTrainsArr[i].id === race.id) {
                indexRace = i;
                break;
            }
        }

        var race = $scope.races[indexRace];
        var train = $scope.trains[indexTrain];
        race.train = train;

        //$scope.trains.splice(indexTrain, 1);
        //$scope.trains.splice(indexTrain, 0, train);
        //$scope.trains[indexTrain] = train;
    };
    $scope.updateRouteForRace = function(race, routeId) {
        var comTrainTypesArr = eval( $scope.routes );
        var indexRoute = -1;
        for( var i = 0; i < comTrainTypesArr.length; i++ ) {
            if( comTrainTypesArr[i].id == routeId) {
                indexRoute = i;
                break;
            }
        }

        var comTrainsArr = eval( $scope.races );
        var indexRace = -1;
        for( var i = 0; i < comTrainsArr.length; i++ ) {
            if( comTrainsArr[i].id === race.id) {
                indexRace = i;
                break;
            }
        }

        var race = $scope.races[indexRace];
        var route = $scope.routes[indexRoute];
        race.route = route;
    };

    $scope.refreshData = function() {
        RaceListService.getRaces()
            .then(function(data) {
                $scope.races = data.data.races;
                $scope.routes = data.data.routes;
                $scope.trains = data.data.trains;
            });
    };

    return RaceListService.getRaces()
        .then(function(data) {
            $scope.races = data.data.races;
            $scope.routes = data.data.routes;
            $scope.trains = data.data.trains;
        });
});