(function () {
    'use strict';

    qrApp.controller('HomeCtrl', HomeCtrl);

    HomeCtrl.$inject = ['RESTURL'];

    function HomeCtrl(RESTURL) {
        var vm = this;
        vm.authenticationUrl = RESTURL + "authentification/etudiant";
    }
})();