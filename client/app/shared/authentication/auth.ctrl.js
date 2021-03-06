(function () {
    'use strict';

    qrApp.controller('AuthCtrl', AuthCtrl);

    AuthCtrl.$inject = ['AuthFactory', '$state', '$cookies', 'toastr', '$rootScope', 'AUTH_EVENTS', 'AuthSessionService'];

    function AuthCtrl(AuthFactory, $state, $cookies, toastr, $rootScope, AUTH_EVENTS, AuthSessionService) {
        var vm = this;
        vm.credentials = {};
        vm.student = {};
        vm.teacher_credentials = {};
        vm.teacher_logged = {};

        vm.authentication = function() {
            return AuthFactory.authentication(vm.credentials)
                .then(function(data) {
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
        
        // Teacher authentication method
        // vm.teacher_credentials are filled in auth.teacher.form.tpl.html
        vm.teacherAuth = function() {
            AuthFactory.teacherAuth(vm.teacher_credentials)
                .then(function(user) {

                    $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
                    AuthSessionService.create(user);
                    $state.go('home');
                }, function() {
                    // Not implemented
                    $rootScope.$broadcast(AUTH_EVENTS.loginFailed);
                });

        }
    
        vm.teacherLogout = function() {
            // Destroy the user storage in the AuthSessionService
            AuthSessionService.destroy();
            AuthFactory.teacherLogout();
            $rootScope.$broadcast(AUTH_EVENTS.logoutSuccess);
            $state.go("auth_teacher");
        }
    }
})();