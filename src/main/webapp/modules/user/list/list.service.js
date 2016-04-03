app.factory('UserListService', ['Service', function(Service) {
    return {
        getUsers: function() {
            return Service.request('/api/user/list');
        }
    }
}]);
