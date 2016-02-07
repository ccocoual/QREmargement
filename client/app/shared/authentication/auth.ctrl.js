(function () {
    'use strict';

    qrApp.controller('AuthCtrl', AuthCtrl);

    AuthCtrl.$inject = ['AuthFactory', '$state', '$cookies'];

    function AuthCtrl(AuthFactory, $state, $cookies) {
        var vm = this;
        vm.credentials = {};

        vm.authentication = function() { console.log(vm.credentials);
            return AuthFactory.authentication(vm.credentials);
        }
    }
})();