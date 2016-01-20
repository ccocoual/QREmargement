(function () {
    'use strict';

    qrApp.controller('StudentCtrl', StudentCtrl);

    StudentCtrl.$inject = ['StudentFactory'];

    function StudentCtrl(StudentFactory) {
        var vm = this;
        vm.students = [];
        vm.test = "toto";

        vm.getStudents = function() {
            console.log('getstudents');
            return StudentFactory.getStudents()
                .then(function(data) {
                    vm.students = data;
                    return vm.students;
                });
        }
    }
})();