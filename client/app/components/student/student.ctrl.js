'use strict';

qrApp.controller('StudentCtrl', StudentCtrl);

StudentCtrl.$inject = ['StudentFactory'];

function StudentCtrl(StudentFactory) {
    var vm = this;
    vm.test = "toto";
    vm.students = [];

    activate();

    function activate() {
        return getStudents().then(function() {
            console.log("log");
        });
    }
    
    function getStudents() {
        return StudentFactory.getStudents()
            .then(function(data) {
                console.log(data);
                vm.students = data;
                return vm.students;
            });
    }
}