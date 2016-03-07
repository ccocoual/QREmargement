(function () {
    'use strict';

    qrApp.factory('GroupFactory', GroupFactory);

    function GroupFactory($http, AuthSessionService) {

        var factory = {
            getGroups: getGroups,
            getGroup: getGroup,
            getStudentsByGroup: getStudentsByGroup,
            createGroup: createGroup,
            updateGroup: updateGroup,
            removeGroup: removeGroup
        };
        return factory;
        
        function getGroups(id) {
            return $http.get(AuthSessionService.getTeacherURL() + 'classes/' + id + '/groupes')
                .then(getComplete)
                .catch(getFailed);

            function getComplete(response) {
                console.log('GETS groups succeed');
                return response.data;
            }

            function getFailed(error) {
                console.log('GETS groups failed' + error.data);
            }
        }
        
        function getGroup(id) {
            return $http.get(AuthSessionService.getTeacherURL() + 'groupes/' + id)
                .then(getComplete)
                .catch(getFailed);

            function getComplete(response) {
                console.log('GET group succeed');
                return response.data;
            }

            function getFailed(error) {
                console.log('GET group failed .' + error.data);
            }
        }  

        function createGroup(group) {
            return $http.post(AuthSessionService.getTeacherURL() + 'groupes', group)
                .then(getComplete)
                .catch(getFailed);

            function getComplete(response) {
                console.log('POST group succeed');
            }

            function getFailed(error) {
                console.log('POST group failed' + error.data);
            }
        }
        
        function updateGroup(group) {
            return $http.put(AuthSessionService.getTeacherURL() + 'groupes', group)
                .then(getComplete)
                .catch(getFailed);
            
            function getComplete(response) {
                console.log('PUT group succeed');
            }
            
            function getFailed(error) {
                console.log('PUT group failed' + error.data);
            }
        }
        
        function removeGroup(id) {
            return $http.delete(AuthSessionService.getTeacherURL() + 'groupes/' + id)
                .then(getComplete)
                .catch(getFailed);
            
            function getComplete(response) {
                console.log('DELETE group succeed');
            }
            
            function getFailed(error) {
                console.log('DELETE group failed' + error.data);
            }
        }

        function getStudentsByGroup(id) {
            return $http.get(AuthSessionService.getTeacherURL() + 'groupes/' + id + '/etudiants')
                .then(getComplete)
                .catch(getFailed);

            function getComplete(response) {
                console.log('GET students by group succeed');
                return response.data;
            }

            function getFailed(error) {
                console.log('GET students by group  failed' + error.data);
            }
        }
    }
})();

