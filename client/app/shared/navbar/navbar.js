'use strict';

angular
    .module('QRApp')
    .directive('navbar', function () {
        return {
            restrict: 'E',
            replace:true,
            templateUrl: 'app/shared/navbar/navbar.tpl.html',
            controller:'NavbarCtrl'
        };
    })
    .controller('NavbarCtrl',  function($scope){
        $scope.test = 'HELLO';
    });