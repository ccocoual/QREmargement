(function () {
    'use strict';

    qrApp.factory('GroupFactory', GroupFactory);

    function GroupFactory($http, RESTURL) {

        var factory = {
            getGroups: getGroups
        };
        return factory;
        
        function getGroups(id) {
            return $http.get(RESTURL + 'groups?classe_id=' + id)
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
    }
})();

