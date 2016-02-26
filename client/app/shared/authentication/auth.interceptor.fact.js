(function () {
    'use strict';

    qrApp.factory('AuthInterceptor', AuthInterceptor);

    function AuthInterceptor($rootScope, $q, AUTH_EVENTS, $state) {
        return {
            responseError: responseError,
            redirectLogin: redirectLogin
        }; 
        
        function responseError(response) {
            $rootScope.$broadcast({
                401: AUTH_EVENTS.notAuthenticated,
                403: AUTH_EVENTS.notAuthorized
            }[response.status], response);
            return $q.reject(response);
        }
        
        function redirectLogin() {
            $rootScope.$on(AUTH_EVENTS.notAuthenticated, function() {
                $state.go("auth_teacher");
            })
        }
    }
})();