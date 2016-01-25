(function () {
    'use strict';

    qrApp.factory('QRCodeFactory', QRCodeFactory);

    function QRCodeFactory($http, RESTURL) {

        var factory = {
            getUrl: getUrl
        };
        return factory;

        function getUrl() {
            return $http.get(RESTURL + 'emargements/1')
                .then(getComplete)
                .catch(getFailed);

            function getComplete(response) {
                return response.data.url_generated;
            }

            function getFailed(error) {
                console.log('GET failed for QRCodeFactory.getUrl().' + error.data);
            }
        }  
    }
})();

