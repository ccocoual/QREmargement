qrApp.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise("/");

    $stateProvider
        .state('home', {
            url: '/home',
            controller: 'HomeCtrl',
            templateUrl: "./app/components/home/home.tpl.html"
        })
        .state('student', {
            url: '/students',
            controller: 'StudentCtrl',
            controllerAs: 'studentCtrl',
            templateUrl: "./app/components/student/student.tpl.html"
        });
});
