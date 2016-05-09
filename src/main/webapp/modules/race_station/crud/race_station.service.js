
app.factory('RaceStationService', ['Service', function(Service) {
    return {
        getRaceStations: function() {
            return Service.request('/api/race_station/crud');
        },
        register: function(object) {
            return Service.request('/api/race_station/update', 'POST', object);
        },
        updateRow: function(object) {
            return Service.request('/api/race_station/update', 'POST', object);
        },
        removeRow: function(object) {
            return Service.request('/api/race_station/update', 'POST', object);
        }
    }
}]);