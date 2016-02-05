(function () {
    'use strict';

    qrApp.controller('EmargementCtrl', EmargementCtrl);

    EmargementCtrl.$inject = ['EmargementFactory', 'GroupFactory', 'SubjectFactory', '$state', '$scope', '$interval', '$uibModal', 'WEBAPPURL'];

    function EmargementCtrl(EmargementFactory, GroupFactory, SubjectFactory, $state, $scope, $interval, $uibModal, WEBAPPURL) {
        var vm = this;
        vm.typesCours = ["CM", "TD", "TP"];
        vm.emargements = [];
        vm.subjects = [];
        vm.newEmargement = {};
        vm.newEmargement.date = new Date();
        vm.actualEmargement = [];
        vm.actualGroupsStudents = [];
        vm.qrCodeUrl = "";

        /**
         * Récupère la liste des feuilles d'émargement
         * @returns Liste des feuilles d'émargement
         */
        vm.getEmargements = function(){
            return EmargementFactory.getEmargements()
                .then(function(data) {
                    vm.emargements = data;
                    return vm.emargements;
                });
        };

        /**
         * Récupère la feuille d'émargement actuelle grâce à l'id passé en paramètre de l'url
         */
        vm.getEmargementActual = function() {
            EmargementFactory.getEmargement($state.params.emargementid)
                .then(function(data) {
                    vm.actualEmargement = data;
                    vm.qrCodeUrl = WEBAPPURL + "qrcode/flash/" + $state.params.emargementid;
                });
        }

        /**
         * Récupère la liste des étudiants par groupe
         */
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

        /**
         * Met à jour le graphique des signatures en temps réel
         */
        vm.updateSignaturesChart = function updateSignaturesChart(){
            var nbSignTrue = 0;
            var nbSignFalse = 0;
            if(vm.actualGroupsStudents.length > 0) {
                for (var gr in vm.actualGroupsStudents) {
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


        /**
         * Crée une feuille d'émargement
         */
        vm.createEmargement = function createEmargement(){

        }

        /**
         * Récupère les matières qui existent avec leur id et leur libellé
         * @returns Liste des matières
         */
        vm.getSubjects = function() {
            return SubjectFactory.getSubjects()
                .then(function(data) {
                    vm.subjects = data;
                    return vm.subjects;
                });
        }

        /**
         * Récupère la liste des étudiants avec l'état de leur signature pour la feuille d'émargement actuelle
         */
        vm.getSignaturesByEmargementId = function(){
            EmargementFactory.getSignaturesByEmargementId($state.params.emargementid)
                .then(function(data){
                    if(data.length > 0) {
                        for (var signature in data) {
                            vm.actualGroupsStudentsSign(data[signature].etudiant_id, data[signature].signee);
                        }
                    }
                });
        }

        /**
         * Signature manuelle de la feuille d'émargement par un étudiant
         * @param studentId étudiant ayant signé
         */
        vm.studentSign = function(studentId){

        }


        /**
         * Met à jour la signature de l'étudiant passé en paramètre avec l'état de signature passé en paramètre
         * @param studentId Etudiant à mettre à jour
         * @param sign Etat de la signature
         */

        vm.actualGroupsStudentsSign = function(studentId, sign){
            if(vm.actualGroupsStudents.length > 0) {
                for (var gr in vm.actualGroupsStudents) {
                    for (var stud in vm.actualGroupsStudents[gr]) {
                        if(vm.actualGroupsStudents[gr][stud].id == studentId){
                            vm.actualGroupsStudents[gr][stud].isPresent = sign;
                        }
                    }
                }
            }
        }


        vm.extendQrCode = function(){
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/shared/modalqrcode/modalqrcode.tpl.html',
                controller: 'ModalQrCodeCtrl',
                controllerAs: 'modalqrcode',
                size: 'lg',
                resolve: {
                    qrcodeurl: function () {
                        return vm.qrCodeUrl;
                    }
                }
            });
        }

        /**
         * Vérification de la signature manuelle par le professeur
         */
        $scope.$watch(
            function watchStudents( scope ) {
                return( vm.actualGroupsStudents );
            },
            function handleStudentsChange( newValue, oldValue ) {
                //TODO Envoyer la requête de signature de l'étudiant pour la feuille
                //TODO Mettre à jour le comptage des signatures
                vm.updateSignaturesChart();
                //console.log( newValue );
            },
            true
        );

        $interval(function(){
            vm.getSignaturesByEmargementId();

        },1000, 1);

    }
})();