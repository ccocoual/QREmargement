(function () {
    'use strict';

    qrApp.factory('StudentFactory', StudentFactory);

    function StudentFactory($http, RESTURL) {

        var factory = {
            getStudents: getStudents,
            createStudent: createStudent
        };
        return factory;

        function getStudents() {
            return $http.get(RESTURL + 'students')
                .then(getComplete)
                .catch(getFailed);

            function getComplete(response) {
                console.log("GETS students succeed" + response.data);
                return response.data;
            }

            function getFailed(error) {
                console.log('GETS students failed' + error.data);
            }
        }  

        function createStudent(student) {
            console.log(student);
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
    }
})();

