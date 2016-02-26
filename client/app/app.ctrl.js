(function () {
    'use strict';

    qrApp.controller('AppCtrl', AppCtrl);

    AppCtrl.$inject = ['AuthSessionService'];

    function AppCtrl(AuthSessionService) {
        var vm = this;
        vm.connected = "false";
        
        vm.setCurrent = function(current) {
            vm.connected = AuthSessionService.create(current);
        }
        
        vm.removeCurrent = function() {
            vm.connected = AuthSessionService.destroy();
        }
    }
})();