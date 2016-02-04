(function () {
    'use strict';

    qrApp.controller('EmargementCtrl', EmargementCtrl);

    EmargementCtrl.$inject = ['EmargementFactory', 'GroupFactory', 'SubjectFactory', '$state', '$scope', 'RESTURL'];

    function EmargementCtrl(EmargementFactory, GroupFactory, SubjectFactory, $state, $scope, RESTURL) {
        var vm = this;
        vm.emargements = [];
        vm.actualEmargement = [];
        vm.actualGroupsStudents = [];
        vm.qrCodeUrl = RESTURL + "qrcode/flash/";

        vm.getEmargements = function(){
            return EmargementFactory.getEmargements()
                .then(function(data) {
                    vm.emargements = data;
                    return vm.emargements;
                });
        };

        vm.getEmargementActual = function() {
            EmargementFactory.getEmargement($state.params.emargementid)
                .then(function(data) {
                    vm.actualEmargement = data;
                });
        }

        vm.getStudentsByGroup = function() {
            var gr = $state.params.groupes;
            for(var i=0 ; i<gr.length ; i++){
                GroupFactory.getStudentsByGroup(gr[i].id)
                    .then(function(datastudents){
                        for(var j=0 ; j<datastudents.length ; j++){
                            datastudents[j].isPresent = false;
                        }
                        vm.actualGroupsStudents.push(datastudents);
                    });
            }
        }

        vm.getQRCodeURL = function(id) {
            return EmargementFactory.getUrlGenerated(id)
                .then(function(data) {
                    vm.qrCodeUrl = data;
                    return vm.qrCodeUrl;
                });
        }

        vm.updateSignatures = function updateSignatures(){
            var nbSignTrue = 0;
            var nbSignFalse = 0;
            var nbTotal = 0;
            if(vm.actualGroupsStudents.length > 0) {
                for (var gr in vm.actualGroupsStudents) {
                    console.log(vm.actualGroupsStudents[gr]);
                    for (var stud in vm.actualGroupsStudents[gr]) {
                        if (vm.actualGroupsStudents[gr][stud].isPresent && stud != "$$hashKey") {
                            nbSignTrue++;
                        } else if(!vm.actualGroupsStudents[gr][stud].isPresent  && stud != "$$hashKey") {
                            nbSignFalse++;
                        }
                    }
                }
            }
            vm.nbSignatures = [nbSignTrue,nbSignFalse];
            vm.nbSignaturesLabels = ["Présents", "Absents"];
        }

        vm.getMatiereLabel = function getMatiereLabel(matiereid){
            SubjectFactory.getSubject(matiereid)
                .then(function(data){
                    return data;
                });
        }


        $scope.$watch(
            function watchStudents( scope ) {
                return( vm.actualGroupsStudents );
            },
            function handleStudentsChange( newValue, oldValue ) {
                //TODO Envoyer la requête de signature de l'étudiant pour la feuille
                //TODO Mettre à jour le comptage des signatures
                vm.updateSignatures();
                //console.log( newValue );
            },
            true
        );
    }
})();