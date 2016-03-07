(function () {
    'use strict';

    qrApp.controller('GroupCtrl', GroupCtrl);

    GroupCtrl.$inject = ['ClassFactory', 'GroupFactory', '$state', '$stateParams', 'toastr'];

    function GroupCtrl(ClassFactory, GroupFactory, $state, $stateParams, toastr) {
        var vm = this;
        vm.groups = [];
        vm.newGroup = {};
        vm.actualClass = {};

        vm.getGroups = function() {
            return GroupFactory.getGroups()
                .then(function(data) {
                    vm.groups = data;
                    return vm.groups;
                });
        }
        
        vm.createGroup = function() {
            console.log(vm.newGroup);
            GroupFactory.createGroup(vm.newGroup)
                .then(function(data){
                    toastr.success("Le groupe a été créé !");
                    $state.go('class.groups.list');
                });

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

        vm.getClassInfo = function(){
            console.log($state.params);
            ClassFactory.getClass($state.params.classid)
                .then(function(dataclass){
                    vm.actualClass = dataclass;
                    vm.newGroup.classe = {};
                    vm.newGroup.classe.id = $state.params.classid;
                });
        }
    }
})();