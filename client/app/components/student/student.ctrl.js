(function () {
    'use strict';

    qrApp.controller('StudentCtrl', StudentCtrl);

    StudentCtrl.$inject = ['StudentFactory', '$state'];

    function StudentCtrl(StudentFactory, $state) {
        var vm = this;
        vm.students = [];
        vm.newStudent = {};

        vm.getStudents = function() {
            return StudentFactory.getStudents()
                .then(function(data) {
                    vm.students = data;
                    return vm.students;
                });
        }
        
        vm.createStudent = function() {
            StudentFactory.createStudent(vm.newStudent);
            $state.go('student.list');
        }
        
        vm.removeStudent = function(id) {
            StudentFactory.removeStudent(id);
            $state.reload();
        }
    }
})();