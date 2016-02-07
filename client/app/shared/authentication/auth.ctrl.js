(function () {
    'use strict';

    qrApp.controller('AuthCtrl', AuthCtrl);

    AuthCtrl.$inject = ['AuthFactory', '$state', '$cookies'];

    function AuthCtrl(AuthFactory, $state, $cookies) {
        var vm = this;
        vm.credentials = {};

        vm.authentication = function() {
            return AuthFactory.authentication(vm.credentials)
                .then(function(data) {
                    if (data.id != undefined) {
                        console.log($cookies.get('qre_cookie'));
                        $cookies.remove('qre_cookie');
                        console.log($cookies.get('qre_cookie'));
                        $state.go('auth_success');
                    }
                });
        }
    }
})();