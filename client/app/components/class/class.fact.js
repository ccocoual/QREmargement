(function () {
    'use strict';

    qrApp.factory('ClassFactory', ClassFactory);

    function ClassFactory($http, RESTURL) {

        var factory = {
            getClasses: getClasses,
            getClass: getClass,
            createClass: createClass,
            updateClass: updateClass,
            removeClass: removeClass
        };
        return factory;
        
        function getClasses() {
            return $http.get(RESTURL + 'Classes')
                .then(getComplete)
                .catch(getFailed);

            function getComplete(response) {
                console.log('GETS Classes succeed');
                return response.data;
            }

            function getFailed(error) {
                console.log('GETS Classes failed' + error.data);
            }
        }
        
        function getClass(id) {
            return $http.get(RESTURL + 'Classes/' + id)
                .then(getComplete)
                .catch(getFailed);

            function getComplete(response) {
                console.log('GET Class succeed');
                return response.data;
            }

            function getFailed(error) {
                console.log('GET Class failed .' + error.data);
            }
        }  

        function createClass(Class) {
            return $http.post(RESTURL + 'Classes', Class)
                .then(getComplete)
                .catch(getFailed);

            function getComplete(response) {
                console.log('POST Class succeed');
            }

            function getFailed(error) {
                console.log('POST Class failed' + error.data);
            }
        }
        
        function updateClass(Class) {
            return $http.put(RESTURL + 'Classs', Class)
                .then(getComplete)
                .catch(getFailed);
            
            function getComplete(response) {
                console.log('PUT Class succeed');
            }
            
            function getFailed(error) {
                console.log('PUT Class failed' + error.data);
            }
        }
        
        function removeClass(id) {
            return $http.delete(RESTURL + 'Classs/' + id)
                .then(getComplete)
                .catch(getFailed);
            
            function getComplete(response) {
                console.log('DELETE Class succeed');
            }
            
            function getFailed(error) {
                console.log('DELETE Class failed' + error.data);
            }
        }
    }
})();

