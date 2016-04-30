app.factory('TrainService', ['Service', function(Service) {
    return {
        getTrains: function() {
            return Service.request('/api/train/crud');
        },

        register: function(object) {
            return Service.request('/api/train/update', 'POST', object);
        },
        updateRow: function(object) {
            return Service.request('/api/train/update', 'POST', object);
        },
        removeRow: function(object) {
            return Service.request('/api/train/update', 'POST', object);
        }
    }
}]);
