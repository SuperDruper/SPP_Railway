/**
 * Created by dzmitry.antonenka on 03.05.2016.
 */
app.factory('TicketService', ['Service', function(Service) {
    return {
        getTickets: function() {
            return Service.request('/api/ticket/crud');
        },

        register: function(object) {
            return Service.request('/api/ticket/update', 'POST', object);
        },
        updateRow: function(object) {
            return Service.request('/api/ticket/update', 'POST', object);
        },
        removeRow: function(object) {
            return Service.request('/api/ticket/update', 'POST', object);
        }
    }
}]);