/**
 * Created by dzmitry.antonenka on 03.05.2016.
 */
app.controller('TicketController', function ($scope, $window, TicketService) {
    $scope.errors = [];
    $scope.events = [];

    $scope.carriageNumber = '';
    $scope.placeNumber = '';
    $scope.orderDate = '';

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

            if(!validate(ticket.carriageNum, ticket.num, ticket.orderDate.toString())) return;
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

    function validate(carriageNumber, placeNumber, orderDate) {
        var isValid = true;

        if(carriageNumber == "" || isNaN(parseInt(carriageNumber))) {
            $scope.errors.push("Entered Carriage number number is NAN !");
            isValid = false;
        }
        if(placeNumber == "" || isNaN(parseInt(placeNumber))) {
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

        return isValid;
    }

    $scope.register = function() {
        $scope.errors = [];

        if(!validate($scope.carriageNumber, $scope.placeNumber, $scope.orderDate)) {
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
            race : race
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
        });
});
