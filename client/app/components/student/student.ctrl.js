(function () {
    'use strict';

    qrApp.controller('StudentCtrl', StudentCtrl);

    StudentCtrl.$inject = ['StudentFactory'];

    function StudentCtrl(StudentFactory) {
        var vm = this;
        vm.students = [];
        vm.newStudent = {
            group_id: "",
            classe_id: ""
        };

        vm.getStudents = function() {
            return StudentFactory.getStudents()
                .then(function(data) {
                    vm.students = data;
                    return vm.students;
                });
        }
        
        vm.createStudent = function() {
            return StudentFactory.createStudent(vm.newStudent);
        }
    }
})();