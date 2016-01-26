(function () {
    'use strict';

    qrApp.controller('LessonCtrl', LessonCtrl);

    LessonCtrl.$inject = ['LessonFactory'];

    function LessonCtrl(LessonFactory) {
        var vm = this;
        vm.qrCodeUrl = "";
        
        vm.getQRCodeURL = function(id) {
            return LessonFactory.getUrlGenerated(id)
                .then(function(data) {
                    vm.qrCodeUrl = data;
                    return vm.qrCodeUrl;
                });
        }

        vm.getNbSignatures = function getNbSignatures(){
            vm.nbSignatures = [12,1];
            vm.nbSignaturesLabels = ["Présents", "Absents"];
            console.log(vm.nbSignatures);
        }
    }
})();