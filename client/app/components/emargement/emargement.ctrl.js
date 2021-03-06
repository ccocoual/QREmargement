(function () {
    'use strict';

    qrApp.controller('EmargementCtrl', EmargementCtrl);

    EmargementCtrl.$inject = ['EmargementFactory', 'ClassFactory', 'GroupFactory', 'SubjectFactory', 'ProfessorFactory', 'NgTableParams', '$state', '$filter', '$scope', '$interval', '$uibModal', 'toastr', 'WEBAPPURL'];

    function EmargementCtrl(EmargementFactory, ClassFactory, GroupFactory, SubjectFactory, ProfessorFactory, NgTableParams, $state, $filter, $scope, $interval, $uibModal, toastr, WEBAPPURL) {
        var vm = this;
        vm.typesCours = ["CM", "TD", "TP"];
        vm.emargements = [];
        vm.subjects = [];
        vm.newEmargement = {};
        vm.newEmargement.date = new Date();
        vm.newEmargement.groupes = [];
        vm.updatedEmargement = {};

        vm.actualEmargement = [];
        vm.actualGroupsStudents = [];
        vm.qrCodeUrl = "";
        vm.classesGroups = [];
        vm.classesGroupsSelected = [];
        vm.emargementsTableParams = {};
        vm.actualEmargementTableParams = {};

        //----Fonctions liées à la liste de feuilles d'émargement

        /**
         * Récupère la liste des feuilles d'émargement
         * @returns Liste des feuilles d'émargement
         */
        vm.getEmargements = function(){
            EmargementFactory.getEmargements()
                .then(function(data) {
                    vm.emargements = data;
                    vm.emargementsTableParams = new NgTableParams({
                        page: 1,            // show first page
                        count: 15

                    }, {
                        dataset : vm.emargements,
                        counts: [],
                        total: vm.emargements.length, // length of data
                        getData: function($defer, params) {
                            var orderedData = params.sorting() ? $filter('orderBy')(vm.emargements, params.orderBy()) : vm.emargements;
                            $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
                            //$scope.reportBillingData = new Date();
                        }
                    });

                });
        };

        vm.removeEmargement = function(emargement){
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/shared/modalremoveEmargement/modalremoveEmargement.tpl.html',
                controller: 'ModalRemoveEmargementCtrl',
                controllerAs: 'modalremoveEmargement',
                size: 'md',
                resolve: {
                    emargement: function () {
                        return emargement;
                    }
                }
            });

            modalInstance.result.then(function (emar) {
                EmargementFactory.removeEmargement(emar.id)
                    .then(function(data){
                        vm.getEmargements();
                        toastr.success("Le " + emar.type_cours + " de " + emar.matiere.libelle + " du " + emar.date + " a bien été supprimé.", "Vous avez bien supprimé la feuille d'émargement !");
                    });
            }, function () {

            });
        }

        /**
         * Récupère l'émargement à mettre à jour dans les paramètres pour pré-remplir le formulaire
         */
        vm.getEmargementUpdate = function(){
            console.log($state.params.emargementToUpdate);
            $state.params.emargementToUpdate[0].date = new Date($state.params.emargementToUpdate[0].date);
            vm.updatedEmargement = $state.params.emargementToUpdate[0];
        }

        vm.updateEmargement = function(){
            EmargementFactory.updateEmargement(vm.updatedEmargement)
                .then(function(dataupdated){
                    toastr.success("La feuille d'émargement a bien été mise à jour");
                });
        }

        //----Fonctions liées à une feuille d'émargement

        /**
         * Récupère la feuille d'émargement actuelle grâce à l'id passé en paramètre de l'url
         */
        vm.getEmargementActual = function() {
            vm.actualEmargement = [];
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
            vm.actualGroupsStudents = [];
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
            vm.actualEmargementTableParams = new NgTableParams({
                page: 1,            // show first page
                count: 15

            }, {
                dataset : vm.actualGroupsStudents,
                counts: [],
                total: vm.actualGroupsStudents.length, // length of data
                getData: function($defer, params) {
                    var orderedData = params.sorting() ? $filter('orderBy')(vm.actualGroupsStudents, params.orderBy()) : vm.actualGroupsStudents;
                    $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
                    //$scope.reportBillingData = new Date();
                }
            });
            vm.actualEmargementTableParams.settings().$scope = $scope;
        }

        /**
         * PRIVATE
         * Trouve l'étudiant pour lequel la signature a été modifiée manuellement
         * @param oldStudList Liste des étudiants avant la modification
         * @param newStudList Liste des étudiants après la modification
         * @return Objet contenant l'identifiant de l'étudiant modifié et le nouvel état de sa signature
         */
        function getStudentSign(newStudList, oldStudList){
            if(newStudList.length > 0 && oldStudList.length > 0) {
                for (var gr in oldStudList) {
                    for (var stud in oldStudList[gr]) {
                        if (stud != "$$hashKey") {
                            if (oldStudList[gr][stud].id == newStudList[gr][stud].id && oldStudList[gr][stud].isPresent != newStudList[gr][stud].isPresent) {
                                return {
                                    "etudiant_id": newStudList[gr][stud].id,
                                    "signee": newStudList[gr][stud].isPresent
                                };
                            }
                        }
                    }
                }
            }
            return null;
        }

        /**
         * Signature manuelle de la feuille d'émargement
         * @param oldStudList
         * @param newStudList
         */
        vm.setStudentSign = function(oldStudList, newStudList){
            var tmpSignature = getStudentSign(oldStudList, newStudList);
            if(tmpSignature != null){
                console.log(tmpSignature);
                var studentSign = {
                    "emargement_id": $state.params.emargementid,
                    "etudiant_id":tmpSignature.etudiant_id,
                    "presence":tmpSignature.signee
                }
                EmargementFactory.signEmargement(studentSign).then(function(data){});
            }
        }

        /**
         * Récupère la liste des étudiants avec l'état de leur signature pour la feuille d'émargement actuelle
         */
        vm.getSignaturesByEmargementId = function(){
            EmargementFactory.getSignaturesByEmargementId($state.params.emargementid)
                .then(function(data){
                    if(data.signatures.length > 0) {
                        for (var signature in data.signatures) {
                            vm.setActualGroupsStudentsSign(data.signatures[signature].etudiant.id, data.signatures[signature].signee);
                        }
                    }
                });

        }


        /**
         * Met à jour la signature de l'étudiant passé en paramètre avec l'état de signature passé en paramètre
         * @param studentId Etudiant à mettre à jour
         * @param sign Etat de la signature
         */
        vm.setActualGroupsStudentsSign = function(studentId, sign){
            if(vm.actualGroupsStudents.length > 0) {
                for (var gr in vm.actualGroupsStudents) {
                    for (var stud in vm.actualGroupsStudents[gr]) {
                        if(stud != "$$hashKey") {
                            if (vm.actualGroupsStudents[gr][stud].id == studentId) {
                                vm.actualGroupsStudents[gr][stud].isPresent = sign;
                            }
                        }
                    }
                }
            }
        }

        /**
         * PRIVATE
         * Retourne l'état de la signature de l'étudiant passé en paramètre
         * @param studentId Etudiant dont on veut connaitre l'état de la signature
         * @returns {boolean|*} Etat de la signature
         */
        function getActualGroupsStudentsSign(studentId){
            if(vm.actualGroupsStudents.length > 0) {
                for (var gr in vm.actualGroupsStudents) {
                    for (var stud in vm.actualGroupsStudents[gr]) {
                        if(vm.actualGroupsStudents[gr][stud].id == studentId){
                            return vm.actualGroupsStudents[gr][stud].isPresent;
                        }
                    }
                }
            }
        }

        /**
         * PRIVATE
         * Met à jour le graphique des signatures en temps réel
         */
        function updateSignaturesChart(){
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
            ProfessorFactory.getProfessor()
                .then(function (dataprofessor) {
                    vm.newEmargement.professeur = {};
                    vm.newEmargement.professeur.id = dataprofessor.id;
                    EmargementFactory.createEmargement(vm.newEmargement).then(function(data){
                        toastr.success("Feuille d'émargement pour le " + vm.newEmargement.type_cours, "La feuille d'émargement a bien été créée !");
                        vm.newEmargement = {};
                        $state.go("emargement.list");
                    })
                });

            //var tmp = { type_cours: blabla, matiere: {id: X}, professeur: {id : X}, groupes: [ {id : X}, {id : X} ] };
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
                    var classes = dataclasses;
                    //Parcours des groupes pour chaque promotion
                    for(var cla in classes){
                        var groupes = classes[cla].groupes;
                        for(var gr in groupes){
                            var tmp = {};
                            tmp.id = groupes[gr].id;
                            tmp.grouplabel= groupes[gr].libelle;
                            tmp.label = classes[cla].libelle + " - " +groupes[gr].libelle;
                            tmp.classlabel = classes[cla].libelle;
                            vm.classesGroups.push(tmp);
                        }
                    }
                });
        }

        /**
         * Paramètres de la directive angular de multiselect
         */
        vm.multiSelectSettings = {
            externalIdProp: '',
            enableSearch: true,
            scrollable: true
        };



        //----Appel de fonction à la modification d'une variable
        //----Appel de fonction à intervalle régulier


        /**
         * Vérification de la signature manuelle par le professeur
         */
        $scope.$watch(
            function watchStudents( scope ) {
                return( vm.actualGroupsStudents );
            },
            function handleStudentsChange( newValue, oldValue ) {
                if($state.current.name == "emargement.actual") {
                    vm.setStudentSign(newValue, oldValue);
                    updateSignaturesChart();
                }
            },
            true
        );

        $interval(function () {
            if($state.current.name == "emargement.actual") {
                vm.getSignaturesByEmargementId();
            }
        }, 1000);

    }
})();