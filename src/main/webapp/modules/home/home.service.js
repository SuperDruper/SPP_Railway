app.factory('HomeService', ['Service', function(Service) {
    return {
        getProjects: function() {
            return Service.request('/api/projects');
        }
    }
}]);
