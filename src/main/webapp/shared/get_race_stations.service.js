/**
 * Created by PC-Alyaksei on 01.05.2016.
 */
app.factory('GetRaceStationService', ['Service', function(Service) {
    return {
        getRaceStations: function() {
            return Service.request('/api/race_station/crud');
        }
    }
}]);