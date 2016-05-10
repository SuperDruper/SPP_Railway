/**
 * Created by PC-Alyaksei on 29.04.2016.
 */
app.factory('UserRoleNameService', ['ProfileService', function(ProfileService) {
    return {
        roleId: -1,
        uploadRoleName: function() {
            var self = this;

            return ProfileService.getProfile().then(function (data) {
                try {
                    self.roleId = data.data.user.role.id;
                } catch (err) {}
            });
        }
    }
}]);
