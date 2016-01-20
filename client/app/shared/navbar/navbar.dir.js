'use strict';

angular
    .module('QRApp')
    .directive('navbar', navbar);


function navbar() {
    var directive = {
        restrict: 'E',
        replace:true,
        templateUrl: 'app/shared/navbar/navbar.tpl.html',
        controller:'NavbarCtrl'
    };
    return directive;
}

