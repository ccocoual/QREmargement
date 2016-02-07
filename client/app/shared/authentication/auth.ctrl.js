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
                    if (data.id != undefined) {
                        $cookies.remove('qre_cookie');
                        if($state.params.qrcodeid != null){
                            $state.go('qrcode.flash',{"qrcodeid":$state.params.qrcodeid});
                            toastr.success("Vous êtes bien authentifié",data.prenom+" "+data.nom)
                        }else{
                            $state.go('auth_success',{"student":data});
                        }
                    }
                });
        }

        vm.authenticationSuccess = function(){
            console.log($state.params.student);
            vm.student = $state.params.student;
        }
    }
})();