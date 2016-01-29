(function () {
    'use strict';

    qrApp.factory('StudentFactory', StudentFactory);

    function StudentFactory($http, RESTURL) {

        var factory = {
            getStudents: getStudents,
            getStudent: getStudent,
            createStudent: createStudent,
            updateStudent: updateStudent,
            removeStudent: removeStudent
        };
        return factory;

        function getStudents() {
            return $http.get(RESTURL + 'students')
                .then(getComplete)
                .catch(getFailed);

            function getComplete(response) {
                console.log("GETS students succeed");
                return response.data;
            }

            function getFailed(error) {
                console.log('GETS students failed' + error.data);
            }
        }  
        
        function getStudent(id) {
            return $http.get(RESTURL + 'students/' + id)
                .then(getComplete)
                .catch(getFailed);

            function getComplete(response) {
                console.log('GET Student succeed');
                return response.data;
            }

            function getFailed(error) {
                console.log('GET Student failed .' + error.data);
            }
        }

        function createStudent(student) {
            return $http.post(RESTURL + 'students', student)
                .then(getComplete)
                .catch(getFailed);

            function getComplete(response) {
                console.log("POST student succeed");
            }

            function getFailed(error) {
                console.log('POST student for create.' + error.data);
            }
        }
        
        function updateStudent(student) {
            return $http.put(RESTURL + 'students', student)
                .then(getComplete)
                .catch(getFailed);
            
            function getComplete(response) {
                console.log('PUT student succeed');
            }
            
            function getFailed(error) {
                console.log('PUT student failed' + error.data);
            }
        }
        
        function removeStudent(id) {
            return $http.delete(RESTURL + 'students/' + id)
                .then(getComplete)
                .catch(getFailed);
            
            function getComplete(response) {
                console.log('DELETE student succeed');
            }
            
            function getFailed(error) {
                console.log('DELETE student failed' + error.data);
            }
        }
    }
})();

