
'use strict';

qrApp.controller('HomeCtrl', function($scope, $http) {
  console.log("Welcome Home ! ");
    $http({
        method: 'GET',
        url: 'http://localhost:3000/posts'
    }).then(function successCallback(response) {
        // this callback will be called asynchronously
        // when the response is available
        console.log(response);
        $scope.posts = response;
    }, function errorCallback(response) {
        console.log("REST error");
  });
});
