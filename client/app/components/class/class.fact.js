(function () {
    'use strict';

    qrApp.factory('ClassFactory', ClassFactory);

    function ClassFactory($http, RESTURL) {

        var factory = {
            getClasses: getClasses,
            getClass: getClass,
            createClass: createClass,
            removeClass: removeClass
        };
        return factory;
        
        function getClasses() {
            return $http.get(RESTURL + 'classes')
                .then(getComplete)
                .catch(getFailed);

            function getComplete(response) {
                console.log('GETS classes succeed');
                return response.data;
            }

            function getFailed(error) {
                console.log('GETS classes failed' + error.data);
            }
        }
        
        function getClass(id) {
            return $http.get(RESTURL + 'classes/' + id)
                .then(getComplete)
                .catch(getFailed);

            function getComplete(response) {
                console.log('GET class succeed');
                return response.data;
            }

            function getFailed(error) {
                console.log('GET class failed .' + error.data);
            }
        }  

        function createClass(class_) {
            return $http.post(RESTURL + 'classes', class_)
                .then(getComplete)
                .catch(getFailed);

            function getComplete(response) {
                console.log('POST class succeed');
            }

            function getFailed(error) {
                console.log('POST class failed' + error.data);
            }
        }
        
        function removeClass(id) {
            return $http.delete(RESTURL + 'classes/' + id)
                .then(getComplete)
                .catch(getFailed);
            
            function getComplete(response) {
                console.log('DELETE class succeed');
            }
            
            function getFailed(error) {
                console.log('DELETE class failed' + error.data);
            }
        }
    }
})();

