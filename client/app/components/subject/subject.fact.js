(function () {
    'use strict';

    qrApp.factory('SubjectFactory', SubjectFactory);

    function SubjectFactory($http, AuthSessionService) {

        var factory = {
            getSubjects: getSubjects,
            getSubject: getSubject,
            createSubject: createSubject,
            updateSubject: updateSubject,
            removeSubject: removeSubject
        };
        return factory;
        
        function getSubjects() {
            return $http.get(AuthSessionService.getTeacherURL() + 'matieres')
                .then(getComplete)
                .catch(getFailed);

            function getComplete(response) {
                console.log('GETS subject succeed');
                return response.data;
            }

            function getFailed(error) {
                console.log(error);
                console.log('GETS subject failed' + error.data);
            }
        }
        
        function getSubject(id) {
            return $http.get(AuthSessionService.getTeacherURL() + 'matieres/' + id)
                .then(getComplete)
                .catch(getFailed);

            function getComplete(response) {
                console.log('GET subject succeed');
                return response.data;
            }

            function getFailed(error) {
                console.log('GET subject failed .' + error.data);
            }
        }  

        function createSubject(subject) {
            return $http.post(AuthSessionService.getTeacherURL() + 'matieres', subject)
                .then(getComplete)
                .catch(getFailed);

            function getComplete(response) {
                console.log('POST subject succeed');
            }

            function getFailed(error) {
                console.log('POST subject failed' + error.data);
            }
        }
        
        function updateSubject(subject) {
            return $http.put(AuthSessionService.getTeacherURL() + 'matieres', subject)
                .then(getComplete)
                .catch(getFailed);
            
            function getComplete(response) {
                console.log('PUT subject succeed');
            }
            
            function getFailed(error) {
                console.log('PUT subject failed' + error.data);
            }
        }
        
        function removeSubject(id) {
            return $http.delete(AuthSessionService.getTeacherURL() + 'matieres/' + id)
                .then(getComplete)
                .catch(getFailed);
            
            function getComplete(response) {
                console.log('DELETE subject succeed');
            }
            
            function getFailed(error) {
                console.log('DELETE subject failed' + error.data);
            }
        }
    }
})();

