(function () {
    'use strict';

    qrApp.controller('StudentCtrl', StudentCtrl);

    StudentCtrl.$inject = ['StudentFactory', '$scope', '$state', '$filter', 'NgTableParams'];

    function StudentCtrl(StudentFactory, $scope, $state, $filter, NgTableParams) {
        var vm = this;
        vm.students = [];
        vm.newStudent = {};
        vm.studentsTableParams = {};

        vm.getStudents = function() {
            StudentFactory.getStudents()
                .then(function(data) {
                    vm.students = data;
                    vm.studentsTableParams = new NgTableParams({
                        page: 1,            // show first page
                        count: 15
                    }, {
                        dataset : vm.students,
                        counts: [],
                        total: vm.students.length, // length of data
                        getData: function($defer, params) {
                            var orderedData = params.sorting() ? $filter('orderBy')(vm.students, params.orderBy()) : vm.students;
                            $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
                            //$scope.reportBillingData = new Date();
                        }
                    });
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