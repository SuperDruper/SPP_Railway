/**
 * Created by dzmitry.antonenka on 11.04.2016.
 */
app.factory('RaceStationService', ['Service', 'GetRaceStationService', function(Service, GetRaceStationService) {
    return {
        getRaceStations: function() {
            return GetRaceStationService.getRaceStations();
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