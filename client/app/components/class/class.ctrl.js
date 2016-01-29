(function () {
    'use strict';

    qrApp.controller('ClassCtrl', ClassCtrl);

    ClassCtrl.$inject = ['ClassFactory'];

    function ClassCtrl(ClassFactory) {
        var vm = this;
        vm.classes = [];
        
        vm.getClasses = function() {
            return ClassFactory.getClasses()
                .then(function(data) {
                    vm.classes = data;
                    return vm.classes;
                });
        }
    }
})();