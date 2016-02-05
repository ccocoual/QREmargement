(function () {
    'use strict';

    qrApp.controller('SubjectCtrl', SubjectCtrl);

    SubjectCtrl.$inject = ['SubjectFactory'];

    function SubjectCtrl(SubjectFactory) {
        var vm = this;
        vm.subjects = [];

        console.log("ME VLA");

        vm.getSubjects = function() {
            return SubjectFactory.getSubjects()
                .then(function(data) {
                    vm.subjects = data;
                    console.log(data);q
                    return vm.subjects;
                });
        }
        
    }
})();