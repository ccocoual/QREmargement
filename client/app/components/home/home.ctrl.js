(function () {
    'use strict';

    qrApp.controller('HomeCtrl', HomeCtrl);

    HomeCtrl.$inject = ['RESTURL'];

    function HomeCtrl(RESTURL) {
        var vm = this;
        vm.authenticationUrl = "http://localhost:8888/client/#/authentification/etudiant";
    }
})();