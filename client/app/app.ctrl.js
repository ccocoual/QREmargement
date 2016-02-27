(function () {
    'use strict';

    qrApp.controller('AppCtrl', AppCtrl);

    AppCtrl.$inject = ['AuthSessionService', 'AUTH_EVENTS', '$rootScope'];

    function AppCtrl(AuthSessionService, AUTH_EVENTS, $rootScope) {
        var vm = this;
        vm.connected = AuthSessionService.isConnected();
        
        $rootScope.$on(AUTH_EVENTS.loginSuccess, function(){
            vm.connected = true;
        });
        $rootScope.$on(AUTH_EVENTS.logoutSuccess, function(){
            vm.connected = false;
        });
    }
})();