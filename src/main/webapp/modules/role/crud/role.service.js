/**
 * Created by dzmitry.antonenka on 11.04.2016.
 */
app.factory('RoleService', ['Service', function(Service) {
    return {
        getRoles: function() {
            return Service.request('/api/role/crud');
        },

        register: function(object) {
            return Service.request('/api/role/update', 'POST', object);
        },
        updateRow: function(object) {
            return Service.request('/api/role/update', 'POST', object);
        },
        removeRow: function(object) {
            return Service.request('/api/role/update', 'POST', object);
        }
    }
}]);