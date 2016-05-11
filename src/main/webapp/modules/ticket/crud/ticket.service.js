/**
 * Created by dzmitry.antonenka on 03.05.2016.
 */
app.factory('TicketService', ['Service', function(Service) {
    return {
        getTickets: function() {
            return Service.request('/api/ticket/crud');
        },

        getStationsForRace: function(object) {
            return Service.request('/api/ticket/update', 'POST', object)
        },

        register: function(object) {
            return Service.request('/api/ticket/update', 'POST', object);
        },
        updateRow: function(object) {
            return Service.request('/api/ticket/update', 'POST', object);
        },
        removeRow: function(object) {
            return Service.request('/api/ticket/update', 'POST', object);
        },
        convertUTCDateToLocalDate: function (date) {
            var localOffset = date.getTimezoneOffset() * 60000;
            var localTime = date.getTime();
            date = localTime - localOffset;
            date = new Date(date);
            return date;
        }
    }
}]);