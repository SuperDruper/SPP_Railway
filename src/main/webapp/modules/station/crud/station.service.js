/**
 * Created by dzmitry.antonenka on 11.04.2016.
 */
app.factory('StationService', ['Service', function(Service) {
    return {
        getStation: function() {
            return Service.request('/api/station/crud');
        },

        register: function(object) {
            return Service.request('/api/station/update', 'POST', object);
        },
        updateRow: function(object) {
            return Service.request('/api/station/update', 'POST', object);
        },
        removeRow: function(object) {
            return Service.request('/api/station/update', 'POST', object);
        }
    }
}]);