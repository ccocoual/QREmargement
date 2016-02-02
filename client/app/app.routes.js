qrApp.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise("/");

    $stateProvider
        .state('home', {
            url: '/',
            controller: 'HomeCtrl',
            templateUrl: './app/components/home/home.tpl.html'
        });

    $stateProvider
        .state('auth_student', {
            url: '/authentification/etudiant',
            controller: 'AuthCtrl',
            controllerAs: 'auth',
            templateUrl: './app/shared/authentication/auth.form.tpl.html'
        });
    
    //Routes pour les vues relatives aux cours
    $stateProvider
        .state('emargement', {
            name: "Emargement",
            url: '/emargement',
            //abstract:true,
            controller: 'EmargementCtrl',
            controllerAs: 'emargement',
            templateUrl: "./app/components/emargement/emargement.tpl.html"
        })
        .state('emargement.list', {
            url: '/list',
            templateUrl: "./app/components/emargement/emargement.list.tpl.html"
        })
        .state('emargement.create', {
            url: '/create',
            templateUrl: "./app/components/emargement/emargement.create.tpl.html"
        })
        .state('emargement.actual', {
            url: '/:emargementid/actual',
            templateUrl: "./app/components/emargement/emargement.actual.tpl.html"
        });
    
     //Routes pour les vues relatives aux promotions
    $stateProvider
        .state('class', {
            name: "Classes",
            url: '/class',
            //abstract:true,
            controller: 'ClassCtrl',
            controllerAs: 'class',
            templateUrl: "./app/components/class/class.tpl.html"
        })
        .state('class.list', {
            url: '/list',
            templateUrl: "./app/components/class/class.list.tpl.html"
        })
        .state('class.create', {
            url: '/create',
            templateUrl: "./app/components/class/class.create.tpl.html"
        })
        .state('class.groups', {
            url:'/:classid/groups',
            controller: 'ClassCtrl',
            controllerAs: 'class',
            templateUrl: "./app/components/group/group.tpl.html"
        })
        .state('class.groups.list', {
            url:'/list',
            templateUrl: "./app/components/group/group.list.tpl.html"
        })
        .state('class.groups.create', {
            url:'/create',
            templateUrl: "./app/components/group/group.create.tpl.html"
        });

    //Routes pour les vues relatives aux étudiants
    $stateProvider
        .state('student', {
            url: '/student',
            //abstract:true,
            controller: 'StudentCtrl',
            controllerAs: 'student',
            templateUrl: "./app/components/student/student.tpl.html"
        })
        .state('student.list', {
            url: '/list',
            templateUrl: "./app/components/student/student.list.tpl.html"
        })
        .state('student.create', {
            url: '/create',
            templateUrl: "./app/components/student/student.create.tpl.html"
        });

    //Routes pour les vues relatives aux statistiques
    $stateProvider
        .state('statistic', {
            url: '/statistic',
             controller: 'StatisticCtrl',
             templateUrl: "./app/components/statistic/statistic.tpl.html"
        });
});
