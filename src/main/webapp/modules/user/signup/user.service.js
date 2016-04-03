app.factory('UserService', ['Service', function(Service) {
    return {
        signup: function(user) {
            return Service.request('/api/users', 'POST', user);
        }
    }
}]);
