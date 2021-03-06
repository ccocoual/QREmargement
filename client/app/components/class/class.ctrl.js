(function () {
    'use strict';

    qrApp.controller('ClassCtrl', ClassCtrl);

    ClassCtrl.$inject = ['ClassFactory', 'GroupFactory', '$state', '$stateParams', 'toastr'];

    function ClassCtrl(ClassFactory, GroupFactory, $state, $stateParams, toastr) {
        var vm = this;
        vm.classes = [];
        vm.groups = [];
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
        
        vm.getGroups = function(class_id) {
            if (angular.isUndefined(class_id)) {
                class_id = $stateParams.classid;
            }
            return GroupFactory.getGroups(class_id)
                .then(function(data) {
                    vm.groups = data;
                    return vm.groups;
            });
        }

        vm.removeClass = function(classid){
            ClassFactory.removeClass(classid)
                .then(function(data){
                   toastr.success("Vous avez bien supprimé la promotion");
                    vm.getClasses();
                });
        }
    }
})();