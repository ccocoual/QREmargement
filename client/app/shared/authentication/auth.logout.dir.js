(function () {
    'use strict';
    
    qrApp.directive('teacherLogoutMenu', function (AUTH_EVENTS) {
        return {
            restrict: 'A',
            template: '<div ng-if="visible" ng-include="\'app/shared/authentication/auth.teacher.logout.tpl.html\'">',
            link: function (scope) {
                var showDialog = function () {
                    scope.visible = true;
                };

                scope.visible = false;
                scope.$on(AUTH_EVENTS.loginSuccess, showDialog);
            }
        };
    })
    
})();