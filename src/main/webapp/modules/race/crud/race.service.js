/**
 * Created by dzmitry.antonenka on 11.04.2016.
 */
app.factory('RaceListService', ['Service', function(Service) {
    return {
        getRaces: function() {
            return Service.request('/api/race/crud');
        },

        register: function(object) {
            return Service.request('/api/race/update', 'POST', object);
        },
        updateRow: function(object) {
            return Service.request('/api/race/update', 'POST', object);
        },
        removeRow: function(object) {
            return Service.request('/api/race/update', 'POST', object);
        }
    }
}]);