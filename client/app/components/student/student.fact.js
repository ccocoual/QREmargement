'use strict';

qrApp.factory('StudentFactory', StudentFactory);

function StudentFactory($http) {
    
    var factory = {
        getStudents: getStudents
    };
    return factory;
    
    function getStudents() {
        return $http.get('http://localhost:3000/students')
            .then(getComplete)
            .catch(getFailed);

        function getComplete(response) {
            return response.data;
        }

        function getFailed(error) {
            console.log('GET Failed for gets.' + error.data);
        }
    }   
}

