(function () {
    'use strict';

    qrApp.controller('AppCtrl', AppCtrl);

    AppCtrl.$inject = ['AuthSessionService'];

    function AppCtrl(AuthSessionService) {
        var vm = this;
        vm.connected = AuthSessionService.isConnected();
        //vm.connected = true;
    }
})();