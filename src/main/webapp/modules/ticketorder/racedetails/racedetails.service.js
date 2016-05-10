angular.module('app').factory('RaceDetailsService', ['Service', function(Service) {
    return {
        orderTicket: function(ticketObject) {
            return Service.request('/api/ticket/ticketorder', 'POST', ticketObject);
        }
    }
}]);
