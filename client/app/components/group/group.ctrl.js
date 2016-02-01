(function () {
    'use strict';

    qrApp.controller('GroupCtrl', GroupCtrl);

    GroupCtrl.$inject = ['GroupFactory', "$state", "$stateParams"];

    function GroupCtrl(GroupFactory, $state, $stateParams) {
        var vm = this;
        vm.groups = [];
        vm.newGroup = {};
        
        vm.getGroups = function() {
            return GroupFactory.getGroups($stateParams.classid)
                .then(function(data) {
                    vm.groups = data;
                    return vm.groups;
                });
        }
        
        vm.createGroup = function() {
            //vm.newGroup.classe_id = $stateParams.classid;
            return GroupFactory.createGroup(vm.newGroup);
        }
    }
})();