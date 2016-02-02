(function () {
    'use strict';

    qrApp.controller('AuthCtrl', AuthCtrl);

    AuthCtrl.$inject = ['AuthFactory', '$state'];

    function AuthCtrl(AuthFactory, $state) {
        var vm = this;
        vm.credentials = {};

        vm.authentication = function() { console.log(vm.credentials);
            return AuthFactory.authentication(vm.credentials)
                .then(function(data) {
                    console.log(data);
                });
        }
    }
})();