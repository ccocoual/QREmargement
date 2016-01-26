(function () {
    'use strict';

    qrApp.factory('LessonFactory', LessonFactory);

    function LessonFactory($http, RESTURL) {

        var factory = {
            getLessons: getLessons,
            getLesson: getLesson,
            createLesson: createLesson,
            updateLesson: updateLesson,
            removeLesson: removeLesson,
            getUrlGenerated: getUrlGenerated
        };
        return factory;
        
        function getLessons() {
            return $http.get(RESTURL + 'lessons')
                .then(getComplete)
                .catch(getFailed);

            function getComplete(response) {
                console.log('GETS lessons succeed');
                return response.data;
            }

            function getFailed(error) {
                console.log('GETS lessons failed' + error.data);
            }
        }
        
        function getLesson(id) {
            return $http.get(RESTURL + 'lessons/' + id)
                .then(getComplete)
                .catch(getFailed);

            function getComplete(response) {
                console.log('GET lesson succeed');
                return response.data;
            }

            function getFailed(error) {
                console.log('GET lesson failed .' + error.data);
            }
        }  

        function createLesson(lesson) {
            return $http.post(RESTURL + 'lessons', lesson)
                .then(getComplete)
                .catch(getFailed);

            function getComplete(response) {
                console.log('POST lesson succeed');
            }

            function getFailed(error) {
                console.log('POST lesson failed' + error.data);
            }
        }
        
        function updateLesson(lesson) {
            return $http.put(RESTURL + 'lessons', lesson)
                .then(getComplete)
                .catch(getFailed);
            
            function getComplete(response) {
                console.log('PUT lesson succeed');
            }
            
            function getFailed(error) {
                console.log('PUT lesson failed' + error.data);
            }
        }
        
        function removeLesson(id) {
            return $http.delete(RESTURL + 'lessons/' + id)
                .then(getComplete)
                .catch(getFailed);
            
            function getComplete(response) {
                console.log('DELETE lesson succeed');
            }
            
            function getFailed(error) {
                console.log('DELETE lesson failed' + error.data);
            }
        }
        
        function getUrlGenerated(id) {
            return $http.get(RESTURL + 'lessons/' + id)
                .then(getComplete)
                .catch(getFailed);

            function getComplete(response) {
                console.log('GET UrlGenerated succeed');
                return response.data.url_generated;
            }

            function getFailed(error) {
                console.log('GET UrlGenerated failed' + error.data);
            }
        }
    }
})();

