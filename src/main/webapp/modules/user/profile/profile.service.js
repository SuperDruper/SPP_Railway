app.factory('ProfileService', ['Service', function(Service) {
    return {
        getProfile: function() {
            return Service.request('/api/user/getprofile');
        },
        updateProfile: function(user) {
            return Service.request('/api/user/editprofile', 'POST', user);
        }
    }
}]);
