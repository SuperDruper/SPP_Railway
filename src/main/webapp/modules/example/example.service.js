/**
 * Created by PC-Alyaksei on 02.04.2016.
 */

app.factory('ExampleService', ['Service', function(Service) {
    return {
        send: function(message) {
            return Service.request('/api/example', 'POST', {model: message});
        }
    }
}]);