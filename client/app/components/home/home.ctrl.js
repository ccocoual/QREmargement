(function () {
    'use strict';

    qrApp.controller('HomeCtrl', HomeCtrl);

    HomeCtrl.$inject = ['WEBAPPURL'];

    function HomeCtrl(WEBAPPURL) {
        var vm = this;
        vm.authenticationUrl = WEBAPPURL+"authentification/etudiant";
    }
})();