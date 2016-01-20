(function () {
    'use strict';

    qrApp.controller('StudentCtrl', StudentCtrl);

    StudentCtrl.$inject = ['StudentFactory'];

    function StudentCtrl(StudentFactory) {
        var vm = this;
        vm.students = [];

        function getStudents() {
            return StudentFactory.getStudents()
                .then(function(data) {
                    vm.students = data;
                    return vm.students;
                });
        }
    }
})();