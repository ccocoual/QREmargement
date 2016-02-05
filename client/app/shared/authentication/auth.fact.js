(function () {
    'use strict';

    qrApp.factory('AuthFactory', AuthFactory);

    function AuthFactory($http, RESTURL) {

        var factory = {
            isConnected: isConnected,
            authentication: authentication
        };
        return factory;

        function isConnected() {
            // regarder les cookies
        }
        
        function authentication(credentials) { console.log(credentials);
            return $http.post(RESTURL + 'qrcode/authentication_etudiant', credentials)
                .then(getComplete)
                .catch(getFailed);

            function getComplete(response) {
                console.log('POST authentication succeed');
            }

            function getFailed(error) {
                console.log('POST authentication failed.' + error);
            }
        }
    }
})();

