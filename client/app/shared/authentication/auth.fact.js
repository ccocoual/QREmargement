(function () {
    'use strict';

    qrApp.factory('AuthFactory', AuthFactory);

    AuthFactory.$inject = ['$http', 'SERVURL', '$cookies', 'localStorageService', 'AUTH_EVENTS', '$rootScope'];
    
    function AuthFactory($http, SERVURL, $cookies, localStorageService, AUTH_EVENTS, $rootScope) {
        var factory = {
            isConnected: isConnected,
            authentication: authentication,
            teacherAuth: teacherAuth,
            isAuthenticated: isAuthenticated,
            teacherLogout: teacherLogout
        };
        return factory;

        function isConnected() {
            if ($cookies.get('qre_cookie') != undefined) {
                $state.go('cnx_student');
            } else {
                $state.go('auth_student');
            }
        }
        
        function authentication(credentials) {
            return $http.post(SERVURL + 'qrcode/authentication_etudiant', credentials)
                .then(getComplete)
                .catch(getFailed);

            function getComplete(response) {
                console.log('POST authentication succeed');
                if (response.data.id != undefined) {     
                    $cookies.put('qre_cookie', response.data.id);
                    return response.data;
                } else  {
                    console.log(response);
                }
            }

            function getFailed(error) {
                console.log('POST authentication failed.' + error);
            }
        }
        
        function teacherAuth(credentials) {
            return $http
                .post(SERVURL + 'authentication', credentials)
                .then(function (res) {
                    // Store the token
                    localStorageService.set("teacher_token", res.data[0]);
                    return res.data.professeur;
                });
        }
        
        function isAuthenticated() {
            return !!localStorageService.get("teacher_token");
        }
        
        function teacherLogout() {
            localStorageService.remove("teacher_token");
        }
    }
})();

