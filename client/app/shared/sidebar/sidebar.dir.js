'use strict';

angular
    .module('QRApp')
    .directive('sidebar', sidebar);


function sidebar() {
    var directive = {
        restrict: 'E',
        replace:true,
        templateUrl: 'app/shared/sidebar/sidebar.tpl.html',
        controller:'SidebarCtrl'
    };
    return directive;
}

