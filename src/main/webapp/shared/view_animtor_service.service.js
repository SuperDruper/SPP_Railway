/**
 * Created by dzmitry.antonenka on 21.05.2016.
 */

app.factory('ModalViewAnimatorService', function() {
    return {
        showModelViewAnimated: function($scope) {
            var self = this;
            if($scope.errors.length == 0)
                $('.popup').fadeIn(600);
            else {
                $("html, body").animate({
                    scrollTop: 0
                }, 600);
                $('.blackout').fadeIn(600);
                $('.error_block').fadeIn(600);
            }
        }
    }
});