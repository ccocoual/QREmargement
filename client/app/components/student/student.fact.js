(function () {
    'use strict';

    qrApp.factory('StudentFactory', StudentFactory);

    function StudentFactory($http, RESTURL) {

        console.log(RESTURL);

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
                return response.data;
                console.log("GETS OK" + reponse.data);
            }

            function getFailed(error) {
                console.log('GET failed for gets.' + error.data);
            }
        }  

        function createStudent(student) {
            console.log(student);
            return $http.post(RESTURL + 'students', student)
                .then(getComplete)
                .catch(getFailed);

            function getComplete(response) {
                console.log("post ok");
            }

            function getFailed(error) {
                console.log('POST failed for create.' + error.data);
            }
        }
    }
})();

