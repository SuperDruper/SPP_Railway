/**
 * Created by dzmitry.antonenka on 10.04.2016.
 */
app.factory('TrainTypeListService', ['Service', function(Service) {
    return {
        getTrainTypes: function() {
            return Service.request('/api/train_type/list');
        },

        updateRow: function(object) {
            return Service.request('/api/train_type/update', 'POST', object);
        },
        removeRow: function(object) {
            return Service.request('/api/train_type/update', 'POST', object);
        }
    }
}]);
