'use strict';

angular
    .module('QRApp')
    .directive('sidebar', function () {
        return {
            restrict: 'E',
            replace:true,
            templateUrl: 'app/shared/sidebar/sidebar.tpl.html',
            controller:'SidebarCtrl'
        };
    })
    .controller('SidebarCtrl',  function($scope){
        $scope.test = 'HELLO';
    });