/**
 * Created by dzmitry.antonenka on 03.05.2016.
 */
app.controller('TicketController', function ($scope, $window, TicketService) {
    $scope.errors = [];
    $scope.events = [];

    $scope.carriageNumber = '';
    $scope.placeNumber = '';
    $scope.orderDate = '';
    $scope.stationFrom = '';
    $scope.stationTo = '';


    $scope.removeRow = function(id){
        var index = -1;
        var comArr = eval( $scope.tickets );
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

            TicketService.removeRow({ ticket: object, action: action })
                .then(function(data) {
                    $scope.errors.push.apply($scope.errors, data.errorList);

                    if($scope.errors.length == 0) {
                        $scope.tickets.splice(index, 1);
                    }
                });
        }

    };
    $scope.updateRaceForTicket = function(ticket, raceId) {
        var comTrainTypesArr = eval( $scope.races );
        var indexRace = -1;
        for( var i = 0; i < comTrainTypesArr.length; i++ ) {
            if( comTrainTypesArr[i].id == raceId) {
                indexRace = i;
                break;
            }
        }

        var comTrainsArr = eval( $scope.tickets );
        var indexTicket = -1;
        for( var i = 0; i < comTrainsArr.length; i++ ) {
            if( comTrainsArr[i].id === ticket.id) {
                indexTicket = i;
                break;
            }
        }

        var ticket = $scope.tickets[indexTicket];
        var race = $scope.races[indexRace];
        ticket.race = race;
    };

    $scope.updateStationListForRace = function(raceId) {
        const race =
        {
            id : raceId
        }

        const action = {
            id : 10
        };

        TicketService.getStationsForRace({race : race, action : action})
            .then(function(data) {
                $scope.stations = data.stations;
            });
    }
    $scope.updateStationToForTicket = function(ticket, stationTo) {
        var comTrainsArr = eval( $scope.tickets );
        var indexTicket = -1;
        for( var i = 0; i < comTrainsArr.length; i++ ) {
            if( comTrainsArr[i].id === ticket.id) {
                indexTicket = i;
                break;
            }
        }

        var ticket = $scope.tickets[indexTicket];
        ticket.stationTo = { id : stationTo };
    }
    $scope.updateStationFromForTicket = function(ticket, stationFrom) {
        var comTrainsArr = eval( $scope.tickets );
        var indexTicket = -1;
        for( var i = 0; i < comTrainsArr.length; i++ ) {
            if( comTrainsArr[i].id === ticket.id) {
                indexTicket = i;
                break;
            }
        }

        var ticket = $scope.tickets[indexTicket];
        ticket.stationFrom = { id : stationFrom };
    }

    $scope.updateRow = function(id){
        var index = -1;
        var comArr = eval( $scope.tickets );
        for( var i = 0; i < comArr.length; i++ ) {
            if( comArr[i].id === id ) {
                index = i;
                break;
            }
        }
        if( index === -1 ) {
            alert( "Cannot update row with id" + id);
        } else {
            var ticket = jQuery.extend(true, {}, comArr[index]);
            const action = {
                id : 1
            };

            if(!validate(ticket.carriageNum, ticket.num, ticket.orderDate.toString(), ticket.stationFrom.id, ticket.stationTo.id)) return;
            ticket.dOrderDate = ticket.orderDate.toString();
            ticket.orderDate = null;

            TicketService.updateRow({ ticket: ticket, action: action })
                .then(function() {
                    refreshData();
                });
        }
    };

    function refreshData() {
        TrainTypeListService.getTickets()
            .then(function(data) {
                $scope.tickets = data.data.tickets;
                $scope.errors.push.apply($scope.errors, data.errorList);
            });
    };

    function validate(carriageNumber, placeNumber, orderDate, stationFrom, stationTo) {
        var isValid = true;

        if(carriageNumber == null || carriageNumber == "" || isNaN(parseInt(carriageNumber)) || carriageNumber <= 0) {
            $scope.errors.push("Entered Carriage number number is NAN !");
            isValid = false;
        }
        if(placeNumber == null || placeNumber == "" || isNaN(parseInt(placeNumber)) || carriageNumber <= 0) {
            $scope.errors.push("Entered place number is NAN !");
            isValid = false;
        }

        var dateComponents = orderDate.split(' ');

        var timestamp=Date.parse(dateComponents[0] + "T" + dateComponents[1])
        if (isNaN(timestamp))
        {
            $scope.errors.push("Entered order date is NAN !");
            isValid = false;
        }

        if(stationFrom <= 0) {
            $scope.errors.push("Station \'From\' NOT selected");
            isValid = false;
        }
        if(stationTo <= 0) {
            $scope.errors.push("Station \'To\' NOT selected");
            isValid = false;
        }

        if(stationFrom == stationTo && $scope.errors.length == 0)
        {
            $scope.errors.push("Station \'From\' equal with Station \'To\' !");
            isValid = false;
        }

        return isValid;
    }

    $scope.register = function() {
        $scope.errors = [];

        if(!validate($scope.carriageNumber, $scope.placeNumber, $scope.orderDate, $scope.stationFrom, $scope.stationTo)) {
            return;
        }

        const race =
        {
            id : $scope.race
        }
        const ticket = {
            dOrderDate : $scope.orderDate,
            num : $scope.placeNumber,
            carriageNum : $scope.carriageNumber,
            race : race,
            stationFrom : { id : $scope.stationFrom },
            stationTo : { id : $scope.stationTo }
        };
        const action = {
            id : 0
        };

        $scope.asyncRequestComplited = false;
        var smth = TicketService.register({ticket:ticket, action: action})
            .then(function(data) {
                $scope.errors.push.apply($scope.errors, data.errorList);
                $scope.events.push.apply($scope.events, data.eventList);

                $scope.asyncRequestComplited = true;
            });
        $scope.$watch('asyncRequestComplited',function(newValue, oldValue, scope){
            if(scope.asyncRequestComplited && $scope.errors.length == 0){
                $scope.orderDate = '';
                $scope.placeNumber = '';
                $scope.carriageNumber = '';
                $scope.race = '';
                $scope.stationFrom = '';
                $scope.stationTo = '';

                return TicketService.getTickets()
                    .then(function(data) {
                        $scope.races = data.data.races;
                        $scope.tickets = data.data.tickets;

                        $scope.errors = data.data.errors;
                    });
            }
        });

        return smth;
    }

    return TicketService.getTickets()
        .then(function(data) {
            $scope.races = data.data.races;
            $scope.tickets = data.data.tickets;

            $scope.errors.push.apply($scope.errors, data.errorList);
            $scope.events.push.apply($scope.events, data.eventList);

            return TicketService.getStationsForRace({races : data.data.races, action : { id : 10 }})
                .then(function(data) {
                    $scope.stationsHashMap = data.stationHashMap;
                });
        });
});
