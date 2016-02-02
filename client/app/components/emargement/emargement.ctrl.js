(function () {
    'use strict';

    qrApp.controller('EmargementCtrl', EmargementCtrl);

    EmargementCtrl.$inject = ['EmargementFactory', 'GroupFactory', 'SubjectFactory', '$state', '$stateParams'];

    function EmargementCtrl(EmargementFactory, GroupFactory, SubjectFactory, $state, $stateParams) {
        var vm = this;
        vm.emargements = [];
        vm.actualEmargement = [];
        vm.actualStudents = [];
        vm.qrCodeUrl = "";

        vm.getEmargements = function(){
            return EmargementFactory.getEmargements()
                .then(function(data) {
                    vm.emargements = data;
                    console.log(data);
                    return vm.emargements;
                });
        };

        vm.getEmargementActual = function() {
            EmargementFactory.getEmargement($state.params.emargementid)
                .then(function(data) {
                    vm.actualEmargement = data;

                    GroupFactory.getStudentsByGroup(data.groupes[0].id)
                        .then(function(datastudents) {
                            console.log(datastudents);
                            vm.actualStudents = datastudents;
                            console.log(datastudents);
                            return vm.actualStudents;
                        });
                });

        }

        /*vm.getStudentsByGroup = function() {
            console.log(vm.actualEmargement);
            return GroupFactory.getStudentsByGroup(vm.actualEmargement.)
                .then(function(data) {
                    vm.actualStudents = data;
                    return vm.actualStudents;
                });

        }*/

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

        vm.getMatiereLabel = function getMatiereLabel(matiereid){
            return SubjectFactory.getSubject(matiereid)
                .then(function(data){
                    console.log(data);
                    return data;
                });
        }

    }
})();