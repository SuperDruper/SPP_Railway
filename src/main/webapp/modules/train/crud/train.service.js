app.factory('TrainService', ['Service', function(Service) {
    return {
        register: function(train, action) {
            return Service.request('/api/train/register', 'POST', train);
        },
        update: function(train, action) {
            return Service.request('/api/train/register', 'POST', train, action);
        },
        deleteRequest: function(train, action) {
            return Service.request('/api/train/register', 'POST', train, action);
        }
    }
}]);
