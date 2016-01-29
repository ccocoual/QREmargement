(function () {
    'use strict';

    qrApp.controller('ClassCtrl', ClassCtrl);

    ClassCtrl.$inject = ['ClassFactory', "$state"];

    function ClassCtrl(ClassFactory) {
        var vm = this;
        vm.classes = [];
        vm.newClass = {};
        
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
    }
})();