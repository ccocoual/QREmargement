qrApp.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise("/");

    $stateProvider
        .state('home', {
            url: '/',
            controller: 'HomeCtrl',
            templateUrl: "./app/components/home/home.tpl.html"
        });


    //Routes pour les vues relatives aux cours
    $stateProvider
        .state('lesson', {
            url: '/lesson',
            //abstract:true,
            controller: 'LessonCtrl',
            templateUrl: "./app/components/lesson/lesson.tpl.html"
        })
        .state('lesson.list', {
            url: '/list'
        })
        .state('lesson.create', {
            url: '/create'
        });

    //Routes pour les vues relatives aux étudiants
    $stateProvider
        .state('student', {
            url: '/student',
            //abstract:true,
            controller: 'StudentCtrl',
            controllerAs: 'studentCtrl',
            templateUrl: "./app/components/student/student.tpl.html"
        })
        .state('student.list', {
            url: '/list',
            templateUrl: "./app/components/student/student.list.tpl.html"
        })
        .state('student.create', {
            url: '/create'
        });

    //Routes pour les vues relatives aux statistiques
    $stateProvider
        .state('statistic', {
            url: '/statistic',
             controller: 'StatisticCtrl',
             templateUrl: "./app/components/statistic/statistic.tpl.html"
        });
});
