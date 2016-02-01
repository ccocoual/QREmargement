(function () {
    'use strict';

    qrApp.controller('EmargementCtrl', EmargementCtrl);

    EmargementCtrl.$inject = ['EmargementFactory', '$state', '$stateParams'];

    function EmargementCtrl(EmargementFactory, $state, $stateParams) {
        var vm = this;
        vm.emargements = [];
        vm.actualEmargement = [];
        vm.qrCodeUrl = "";

        vm.getEmargements = function(){
            return EmargementFactory.getEmargements()
                .then(function(data) {
                    vm.emargements = data;
                    return vm.emargements;
                });
        };

        vm.getEmargementActual = function() {
            console.log($state.current);
            return EmargementFactory.getEmargement($state.params.emargementid)
                .then(function(data) {
                    vm.actualEmargement = data;
                    console.log(data);
                    return vm.actualEmargement;
                });
        }

        vm.getQRCodeURL = function(id) {
            return EmargementFactory.getUrlGenerated(id)
                .then(function(data) {
                    vm.qrCodeUrl = data;
                    return vm.qrCodeUrl;
                });
        }

        vm.getNbSignatures = function getNbSignatures(){
            vm.nbSignatures = [12,1];
            vm.nbSignaturesLabels = ["Présents", "Absents"];
            //console.log(vm.nbSignatures);
        }

    }
})();