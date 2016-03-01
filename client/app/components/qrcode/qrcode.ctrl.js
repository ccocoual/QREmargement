(function () {
    'use strict';

    qrApp.controller('QRCodeCtrl', QRCodeCtrl);

    QRCodeCtrl.$inject = ['$cookies', '$state', 'EmargementFactory'];

    function QRCodeCtrl($cookies, $state, EmargementFactory) {
        var vm = this;
        vm.emargementInfo = {};

        vm.QrCodeSigning = function(){
            if($cookies.get('qre_cookie') == undefined){
                $state.go("auth_student", {"qrcodeid":$state.params.qrcodeid});
            }else{
                var studentSign = {
                    "emargement_id": $state.params.qrcodeid, 
                    "etudiant_id":$cookies.get('qre_cookie'),
                    "presence":true
                }
                EmargementFactory.signEmargement(studentSign).
                    then(function(data){
                    
                        vm.emargementInfo = studentSign;
                    });
            }
        }

        function getEmargementInfo(){
            EmargementFactory.getEmargement($state.params.qrcodeid)
                .then(function(data){
                    vm.emargementInfo = data;
                }
            );
        }
    }
})();