(function () {
    'use strict';

    qrApp.factory('EmargementFactory', EmargementFactory);

    function EmargementFactory($http, RESTURL) {

        var factory = {
            getEmargements: getEmargements,
            getEmargement: getEmargement,
            createEmargement: createEmargement,
            updateEmargement: updateEmargement,
            removeEmargement: removeEmargement,
            getUrlGenerated: getUrlGenerated,
            getSignaturesByEmargementId: getSignaturesByEmargementId
        };
        return factory;
        
        function getEmargements() {
            return $http.get(RESTURL + 'emargements')
                .then(getComplete)
                .catch(getFailed);

            function getComplete(response) {
                console.log('GETS emargements succeed');
                return response.data;
            }

            function getFailed(error) {
                console.log('GETS emargements failed' + error.data);
            }
        }
        
        function getEmargement(id) {
            return $http.get(RESTURL + 'emargements/' + id)
                .then(getComplete)
                .catch(getFailed);

            function getComplete(response) {
                console.log('GET emargement succeed');
                return response.data;
            }

            function getFailed(error) {
                console.log('GET emargement failed .' + error.data);
            }
        }  

        function createEmargement(emargement) {
            return $http.post(RESTURL + 'emargement', emargement)
                .then(getComplete)
                .catch(getFailed);

            function getComplete(response) {
                console.log('POST emargement succeed');
            }

            function getFailed(error) {
                console.log('POST emargement failed' + error.data);
            }
        }
        
        function updateEmargement(emargement) {
            return $http.put(RESTURL + 'emargements', emargement)
                .then(getComplete)
                .catch(getFailed);
            
            function getComplete(response) {
                console.log('PUT emargement succeed');
            }
            
            function getFailed(error) {
                console.log('PUT emargement failed' + error.data);
            }
        }
        
        function removeEmargement(id) {
            return $http.delete(RESTURL + 'emargements/' + id)
                .then(getComplete)
                .catch(getFailed);
            
            function getComplete(response) {
                console.log('DELETE emargement succeed');
            }
            
            function getFailed(error) {
                console.log('DELETE emargement failed' + error.data);
            }
        }
        
        function getUrlGenerated(id) {
            return $http.get(RESTURL + 'emargements/' + id)
                .then(getComplete)
                .catch(getFailed);

            function getComplete(response) {
                console.log('GET UrlGenerated succeed');
                return response.data.url_generated;
            }

            function getFailed(error) {
                console.log('GET UrlGenerated failed' + error.data);
            }
        }

        function getSignaturesByEmargementId(id){
            return $http.get(RESTURL + 'emargements/' + id + "/signatures")
                .then(getComplete)
                .catch(getFailed);

            function getComplete(response) {
                console.log('GET Signatures by emargement id succeed');
                return response.data;
            }

            function getFailed(error) {
                console.log('GET Signatures by emargement id failed' + error.data);
            }
        }
    }
})();

