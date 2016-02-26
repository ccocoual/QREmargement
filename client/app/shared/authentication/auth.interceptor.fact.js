(function () {
    'use strict';

    qrApp.factory('AuthInterceptor', AuthInterceptor);

    AuthInterceptor.$inject = ['$rootScope', '$q', '$injector', 'AUTH_EVENTS'];
    
    function AuthInterceptor($rootScope, $q, AUTH_EVENTS) {
        return {
            responseError: responseError
        }; 
        
        function responseError(response) {
            $rootScope.$broadcast({
                401: AUTH_EVENTS.notAuthenticated,
                403: AUTH_EVENTS.notAuthorized
            }[response.status], response);
            return $q.reject(response);
        }
        
//        function redirectLogin() {
//            $rootScope.$on(AUTH_EVENTS.notAuthenticated, function() {
//                $injector.get('$state').go("auth_teacher");
//            })
//        }
    }
})();