(function () {
    'use strict';

    qrApp.controller('QRCodeCtrl', QRCodeCtrl);

    QRCodeCtrl.$inject = ['$cookies', '$state', 'EmargementFactory'];

    function QRCodeCtrl($cookies, $state, EmargementFactory) {
        var vm = this;

        vm.QrCodeSigning = function(){
            if($cookies.get('qre_cookie') == undefined){
                console.log($state.params.qrcodeid);
                $state.go("auth_student", {"qrcodeid":$state.params.qrcodeid});
            }else{
                console.log("Cookie : " + $cookies.get('qre_cookie'));
                var studentSign = {
                    "etudiant_id":$cookies.get('qre_cookie'),
                    "signee":true
                }
                EmargementFactory.signEmargement($state.params.qrcodeid, studentSign).
                    then(function(data){});
            }
        }
    }
})();