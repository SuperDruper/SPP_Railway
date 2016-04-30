/**
 * Created by PC-Alyaksei on 29.04.2016.
 */
app.factory('UserRoleNameService', ['ProfileService', function(ProfileService) {
    return {
        roleName: '',
        uploadRoleName: function() {
            var self = this;

            return ProfileService.getProfile().then(function (data) {
                try {
                    self.roleName = data.data.user.role.name;
                } catch (err) {}
            });
        }
    }
}]);
