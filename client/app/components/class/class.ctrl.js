(function () {
    'use strict';

    qrApp.controller('ClassCtrl', ClassCtrl);

    ClassCtrl.$inject = ['ClassFactory', "$state", "$stateParams"];

    function ClassCtrl(ClassFactory, $state, $stateParams) {
        var vm = this;
        vm.classes = [];
        vm.newClass = {};
        vm.groups = [];
        
        vm.getClasses = function() {
            return ClassFactory.getClasses()
                .then(function(data) {
                    vm.classes = data;
                    return vm.classes;
                });
        }
        
        vm.createClass = function() {
            return ClassFactory.createClass(vm.newClass);
            $state.go('class.list');
        }
        
        vm.getClassGroups = function() {
            console.log($stateParams.classid);
            return ClassFactory.getClassGroups($stateParams.class_id)
                .then(function(data) {
                    vm.groups = data;
                    return vm.groups;
                });
        }
    }
})();