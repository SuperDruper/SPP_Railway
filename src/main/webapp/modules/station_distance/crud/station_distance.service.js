/**
 * Created by dzmitry.antonenka on 11.04.2016.
 */
app.factory('StationDistanceService', ['Service', function(Service) {
    return {
        getStationDistances: function() {
            return Service.request('/api/station_distance/crud');
        },

        register: function(object) {
            return Service.request('/api/station_distance/update', 'POST', object);
        },
        updateRow: function(object) {
            return Service.request('/api/station_distance/update', 'POST', object);
        },
        removeRow: function(object) {
            return Service.request('/api/station_distance/update', 'POST', object);
        }
    }
}]);