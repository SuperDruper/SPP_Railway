/**
 * Created by dzmitry.antonenka on 11.04.2016.
 */
app.factory('RouteService', ['Service', function(Service) {
    return {
        getRoutes: function() {
            return Service.request('/api/route/crud');
        },

        register: function(object) {
            return Service.request('/api/route/update', 'POST', object);
        },
        updateRow: function(object) {
            return Service.request('/api/route/update', 'POST', object);
        },
        removeRow: function(object) {
            return Service.request('/api/route/update', 'POST', object);
        }
    }
}]);
