/**
 * Created by dzmitry.antonenka on 03.05.2016.
 */
app.controller('TicketController', function ($scope, $window, TicketService, ModalViewAnimatorService) {
    $scope.errors = [];
    $scope.events = [];

    $scope.stations = [];
    $scope.carriageNumber = '';
    $scope.placeNumber = '';
    $scope.orderDate = '';
    $scope.stationFrom = '';
    $scope.stationTo = '';

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

    $scope.tryToRemoveRow = function(id) {
        var index = -1;
        var comArr = eval( $scope.tickets );
        for( var i = 0; i < comArr.length; i++ ) {
            if( comArr[i] == id ) {
                index = i;
                break;
            }
        }

        if( index === -1 ) {
            alert( "Something gone wrong" );
        } else {
            const Message = "Are you sure you want to delete this ticket ?";
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

            TicketService.removeRow({ ticket: object, action: action })
                .then(function(data) {
                    $scope.errors.push.apply($scope.errors, data.errorList);
                    ModalViewAnimatorService.showModelViewAnimated($scope);
                    if($scope.errors.length == 0) {
                        $scope.tickets.splice(index, 1);
                    }
                });
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
                $scope.stations = [];
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

            if(!validate(ticket.carriageNum, ticket.num, ticket.orderDate.toString(), ticket.stationFrom.id, ticket.stationTo.id)) {
                ModalViewAnimatorService.showModelViewAnimated($scope);
                return;
            }
            ticket.orderDate = TicketService.convertUTCDateToLocalDate(ticket.orderDate);

            TicketService.updateRow({ ticketContainer: ticket, action: action })
                .then(function(data) {
                    refreshData();
                    $scope.errors.push.apply($scope.errors, data.errorList);
                    ModalViewAnimatorService.showModelViewAnimated($scope);
                });
        }
    };

    function refreshData() {
        return TicketService.getTickets()
            .then(function(data) {
                $scope.tickets = data.data.tickets;
                $scope.errors.push.apply($scope.errors, data.data.errorList);
            });
    };

    function validate(carriageNumber, placeNumber, orderDate, stationFrom, stationTo) {
        var isValid = true;
        $scope.errors = [];

        if(carriageNumber == null || carriageNumber == "" || isNaN(parseInt(carriageNumber)) || carriageNumber <= 0) {
            $scope.errors.push("Please enter correct carriage number!");
            isValid = false;
        }
        if(placeNumber == null || placeNumber == "" || isNaN(parseInt(placeNumber)) || carriageNumber <= 0) {
            $scope.errors.push("Please enter correct place number!");
            isValid = false;
        }

        var timestamp=Date.parse(orderDate)
        if (isNaN(timestamp))
        {
            $scope.errors.push("Please enter correct order date!");
            isValid = false;
        }

        if(stationFrom <= 0) {
            $scope.errors.push("Please select \'start\' station");
            isValid = false;
        }
        if(stationTo <= 0) {
            $scope.errors.push("Please select \'end\' station");
            isValid = false;
        }

        if(stationFrom == stationTo && $scope.errors.length == 0)
        {
            $scope.errors.push("Station \'start\' equal with Station \'end\'! Please use different stations!");
            isValid = false;
        }

        return isValid;
    }

    $scope.register = function() {
        $scope.errors = [];

        if(!validate($scope.carriageNumber, $scope.placeNumber, $scope.orderDate, $scope.stationFrom, $scope.stationTo)) {
            ModalViewAnimatorService.showModelViewAnimated($scope);
            return;
        }

        const race =
        {
            id : $scope.race
        }
        const ticket = {
            orderDate : TicketService.convertUTCDateToLocalDate($scope.orderDate),
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
        var smth = TicketService.register({ticketContainer: ticket, action: action})
            .then(function(data) {
                $scope.errors.push.apply($scope.errors, data.errorList);
                ModalViewAnimatorService.showModelViewAnimated($scope);

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

                refreshData();
            }
        });

        return smth;
    }

    function refreshData() {
        return TicketService.getTickets()
            .then(function(data) {
                $scope.races = data.data.races;
                $scope.tickets = data.data.ticketContainers;

                for(var i = 0; i <  $scope.tickets.length; i++) {
                    $scope.tickets[i].orderDate = new Date($scope.tickets[i].orderDate);
                }
                $scope.errors.push.apply($scope.errors, data.errorList);

                return TicketService.getStationsForRace({races : data.data.races, action : { id : 10 }})
                    .then(function(data) {
                        $scope.stationHashMap = data.stationHashMap;
                    });
            });
    }

    return refreshData();
});
