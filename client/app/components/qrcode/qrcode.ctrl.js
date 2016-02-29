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
                    "etudiant_id":$cookies.get('qre_cookie'),
                    "signee":true
                }
                EmargementFactory.signEmargement($state.params.qrcodeid, studentSign).
                    then(function(data){
                        getEmargementInfo();
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