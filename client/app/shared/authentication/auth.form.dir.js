(function () {
    'use strict';
    
    qrApp.directive('teacherLoginDialog', function (AUTH_EVENTS) {
        return {
            restrict: 'A',
            template: '<div ng-if="visible" ng-include="\'app/shared/authentication/auth.teacher.form.tpl.html\'">',
            link: function (scope) {
                var showDialog = function () {
                    console.log("showDialog");
                    scope.visible = true;
                };

                scope.visible = false;
                scope.$on(AUTH_EVENTS.notAuthenticated, showDialog);
                scope.$on(AUTH_EVENTS.sessionTimeout, showDialog)
            }
        };
    })
    
})();