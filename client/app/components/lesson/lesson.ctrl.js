(function () {
    'use strict';

    qrApp.controller('LessonCtrl', LessonCtrl);


    function LessonCtrl() {
        var vm = this;
        vm.getLessonQrCode = getLessonQrCode;
        vm.getNbSignatures = getNbSignatures;


        /**
         * Return the string to transform into QRCode
         */
        function getLessonQrCode(lessonId){

            return 'Test';
        }

        function getNbSignatures(){
            vm.nbSignatures = [12,1];
            vm.nbSignaturesLabels = ["Présents", "Absents"];
            console.log(vm.nbSignatures);
        }
    }
})();