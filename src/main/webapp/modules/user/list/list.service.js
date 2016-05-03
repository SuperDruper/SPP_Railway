app.factory('UserListService', ['Service', function(Service) {
    return {
        getUsers: function() {
            return Service.request('/api/user/list');
        },

        updateRow: function(object) {
            return Service.request('/api/user/update', 'POST', object);
        },
        removeRow: function(object) {
            return Service.request('/api/user/update', 'POST', object);
        }
    }
}]);
