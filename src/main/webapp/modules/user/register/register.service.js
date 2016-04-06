app.factory('RegisterService', ['Service', function(Service) {
    return {
        register: function(user) {
            return Service.request('/api/user/register', 'POST', user);
        }
    }
}]);
