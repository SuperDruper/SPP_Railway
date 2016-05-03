app.factory('RaceDetailsService', ['Service', function(Service) {
    return {
        getRaceInfos: function(raceSearchData) {
            return Service.request('/api/race/listraceinfo', 'POST', raceSearchData);
        },
        getRaceDetails: function(raceIdObject) {
            return Service.request('/api/race/racedetails', 'POST', raceIdObject);
        },
        convertUTCDateToLocalDate: function (date) {
            var localOffset = date.getTimezoneOffset() * 60000;
            var localTime = date.getTime();
            date = localTime - localOffset;
            date = new Date(date);
            return date;
        }
    }
}]);
