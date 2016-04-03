app.factory('Service', ['$http', '$q', function($http, $q) {
    return {
        request: function (url, method, model) {
            if (!method) {
                method = 'GET';
            }
            var def = $q.defer();
            if (method === 'GET') {
                return $http.get(url).success(function (data) {
                    def.resolve(data);
                }).error(function () {
                    def.reject("Failed to get data");
                });
            } else if (method === 'POST') {
                $http.post(url, model).success(function (data) {
                    def.resolve(data);
                }).error(function () {
                    def.reject("Failed to post data");
                });
            }
            return def.promise;
        }
    }
}]);
