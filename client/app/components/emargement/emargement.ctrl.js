(function () {
    'use strict';

    qrApp.controller('EmargementCtrl', EmargementCtrl);

    EmargementCtrl.$inject = ['EmargementFactory', 'ClassFactory', 'GroupFactory', 'SubjectFactory', '$state', '$scope', '$interval', '$uibModal', 'WEBAPPURL'];

    function EmargementCtrl(EmargementFactory, ClassFactory, GroupFactory, SubjectFactory, $state, $scope, $interval, $uibModal, WEBAPPURL) {
        var vm = this;
        vm.typesCours = ["CM", "TD", "TP"];
        vm.emargements = [];
        vm.subjects = [];
        vm.newEmargement = {};
        vm.newEmargement.date = new Date();
        vm.actualEmargement = [];
        vm.actualGroupsStudents = [];
        vm.qrCodeUrl = "";
        vm.classes = [];
        vm.classesGroups = [];
        vm.classesGroupsSelected = [];

        //----Fonctions liées à la liste de feuilles d'émargement

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


        //----Fonctions liées à une feuille d'émargement

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
         * Signature manuelle de la feuille d'émargement par un étudiant
         * @param studentId étudiant ayant signé
         */
        vm.studentSign = function(studentId){

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
         * Affiche une modal avec le QRCode plus grand pour un meilleur scan lorsqu'il
         * est projeté sur videoprojecteur
         */
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

        //----Fonctions liées à la création d'une feuille d'émargement

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

        vm.getClassGroupes = function(){
            //Récupération des promotions
            ClassFactory.getClasses()
                .then(function(dataclasses){
                    vm.classes = dataclasses;
                    console.log(vm.classes);

                    //Récupération des groupes pour chaque promotion
                    for(var cla in vm.classes){
                        var tmpclassid = vm.classes[cla].id;
                        console.log(tmpclassid);
                        GroupFactory.getGroups(vm.classes[cla].id)
                            .then(function(datagroupes){
                                var groupes = datagroupes;
                                for(var gr in groupes){
                                    var tmp = {};
                                    tmp.groupid = groupes[gr].id;
                                    tmp.grouplabel= groupes[gr].libelle;
                                    tmp.classid = tmpclassid;
                                    tmp.classlabel = vm.classes[cla].libelle;
                                    vm.classesGroups.push(tmp);
                                }
                            });
                    }
                });
        }

        vm.multiSelectSettings = {
            groupByTextProvider: function (groupValue) {
                return groupValue
            },
            displayProp: 'grouplabel',
            idProp: 'groupid',
            externalIdProp: 'myCustomPropertyForTheObject'
        };

        var ex = [
            {
                id: 1,
                label: "David",
                gender: 'M'
            }, {
                id: 2,
                label: "Jhon",
                gender: 'M'
            }
        ];



        //----Appel de fonction à la modification d'une variable
        //----Appel de fonction à intervalle régulier

        if($state.current.name == "emargement.actual") {
            /**
             * Vérification de la signature manuelle par le professeur
             */
            $scope.$watch(
                function watchStudents( scope ) {
                    return( vm.actualGroupsStudents );
                },
                function handleStudentsChange( newValue, oldValue ) {
                    //TODO Envoyer la requête de signature de l'étudiant pour la feuille
                    vm.updateSignaturesChart();
                },
                true
            );

            $interval(function () {
                vm.getSignaturesByEmargementId();

            }, 1000, 1);
        }

    }
})();