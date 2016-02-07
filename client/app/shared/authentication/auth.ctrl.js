(function () {
    'use strict';

    qrApp.controller('AuthCtrl', AuthCtrl);

    AuthCtrl.$inject = ['AuthFactory', '$state', '$cookies', 'toastr'];

    function AuthCtrl(AuthFactory, $state, $cookies, toastr) {
        var vm = this;
        vm.credentials = {};
        vm.student = {};

        vm.authentication = function() {
            return AuthFactory.authentication(vm.credentials)
                .then(function(data) {
                    console.log(data);
                    if (data != undefined) {
                        $cookies.remove('qre_cookie');
                        $cookies.put('qre_cookie', data.id);
                        if($state.params.qrcodeid != null){
                            toastr.success("Vous êtes bien authentifié",data.prenom+" "+data.nom);
                            $state.go('qrcode.flash',{"qrcodeid":$state.params.qrcodeid, "studentid":data.id});
                        }else{
                            $state.go('auth_success',{"student":data});
                        }
                    }else{
                        toastr.error("Une erreur est survenue, veuillez rééssayer ultérieurement");
                    }
                });
        }

        vm.authenticationSuccess = function(){
            console.log($state.params.student);
            vm.student = $state.params.student;
        }
    }
})();