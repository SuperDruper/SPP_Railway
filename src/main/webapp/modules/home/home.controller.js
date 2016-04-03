app.controller('HomeController', function ($scope, HomeService) {
    return HomeService.getProjects()
        .then(function(data) {
            $scope.projects = data.data.projectNames;
        });
});
