(function () {
    'use strict';

    qrApp.controller('GroupCtrl', GroupCtrl);

    ClassCtrl.$inject = ['ClassFactory', 'GroupFactory', '$state', '$stateParams', 'toastr'];

    function ClassCtrl(ClassFactory, GroupFactory, $state, $stateParams, toastr) {
        var vm = this;
        vm.groups = [];
        vm.newGroup = {};
        
        vm.getGroups = function() {
            return GroupFactory.getGroups()
                .then(function(data) {
                    vm.groups = data;
                    return vm.groups;
                });
        }
        
        vm.createGroup = function() {
            return GroupFactory.createGroup(vm.newGroup)
                .then(function(data){
                    toastr.success("Le groupe a été créé !");
                });
            $state.go('group.list');
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