(function () {
    'use strict';

    qrApp.controller('EmargementCtrl', EmargementCtrl);

    EmargementCtrl.$inject = ['EmargementFactory', 'GroupFactory', 'SubjectFactory', '$state', '$scope'];

    function EmargementCtrl(EmargementFactory, GroupFactory, SubjectFactory, $state, $scope) {
        var vm = this;
        vm.emargements = [];
        vm.actualEmargement = [];
        vm.actualGroupsStudents = [];
        vm.qrCodeUrl = "";

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
                    console.log(data);
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

        vm.getNbSignatures = function getNbSignatures(){
            vm.nbSignatures = [12,1];
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
                console.log( newValue );
            },
            true
        );
    }
})();