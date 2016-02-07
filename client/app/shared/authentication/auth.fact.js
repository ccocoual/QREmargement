(function () {
    'use strict';

    qrApp.factory('AuthFactory', AuthFactory);

    function AuthFactory($http, RESTURL, $cookies) {

        var factory = {
            isConnected: isConnected,
            authentication: authentication
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
            return $http.post('http://148.60.11.185:8080/' + 'qrcode/authentication_etudiant', credentials)
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
    }
})();

