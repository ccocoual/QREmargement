(function () {
    'use strict';

    qrApp.factory('ProfessorFactory', ProfessorFactory);

    function ProfessorFactory($http, AuthSessionService) {

        var factory = {
            getProfessor: getProfessor
        };
        return factory;

        /**
         * Retourne les informations du professeur actuellement connecté
         */
        function getProfessor() {
            return $http.get(AuthSessionService.getTeacherURL() + 'professeur')
                .then(getComplete)
                .catch(getFailed);

            function getComplete(response) {
                console.log('GET professor succeed');
                return response.data;
            }

            function getFailed(error) {
                console.log(error);
                console.log('GET professor failed' + error.data);
            }
        }
    }
})();

