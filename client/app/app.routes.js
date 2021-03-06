qrApp.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise("/");

    $stateProvider
        .state('home', {
            url: '/',
            controller: 'HomeCtrl',
            controllerAs: 'home',
            templateUrl: './app/components/home/home.tpl.html'
        });

    $stateProvider
        .state('auth_teacher', {
            url: '/auth/enseignant',
            controller: 'AuthCtrl',
            controllerAs: 'auth',
            templateUrl: './app/shared/authentication/auth.teacher.form.tpl.html'
        });
    
    // Student authentication routes
    $stateProvider
        .state('auth_student', {
            url: '/authentification/etudiant',
            controller: 'AuthCtrl',
            controllerAs: 'auth',
            templateUrl: './app/shared/authentication/auth.form.tpl.html',
            params:{
                qrcodeid: null
            }
        })
        .state('auth_success', {
            url: '/authentification/succes',
            controller: 'AuthCtrl',
            controllerAs: 'auth',
            templateUrl: './app/shared/authentication/auth.ok.tpl.html',
            params:{
                student: null
            }
        });
    
    // Routes pour les vues relatives aux cours.
    $stateProvider
        .state('emargement', {
            name: "Emargement",
            url: '/emargement',
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
        .state('emargement.update', {
            url: '/update',
            templateUrl: "./app/components/emargement/emargement.update.tpl.html",
            params:{
                emargementToUpdate : {array:true}
            }
        })
        .state('emargement.actual', {
            url: '/:emargementid/actual?{groupes:json}',
            templateUrl: "./app/components/emargement/emargement.actual.tpl.html",
            params:{
                groupes: {array:true}
            }
        });
    
     // Routes pour les vues relatives aux promotions.
    $stateProvider
        .state('class', {
            name: "Classes",
            url: '/class',
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
            controller: 'GroupCtrl',
            controllerAs: 'group',
            templateUrl: "./app/components/group/group.tpl.html"
        })
        .state('class.groups.list', {
            url:'/list',
            controller: 'ClassCtrl',
            controllerAs: 'class',
            templateUrl: "./app/components/group/group.list.tpl.html"
        })
        .state('class.groups.create', {
            url:'/create',
            templateUrl: "./app/components/group/group.create.tpl.html"
        });

    // Routes pour les vues relatives aux étudiants
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

    // Routes pour la gestion des scans de QRCode
    $stateProvider
        .state('qrcode', {
            url: '/qrcode',
            controller: 'QRCodeCtrl',
            controllerAs: 'qrcode',
            templateUrl: "./app/components/qrcode/qrcode.tpl.html"
        })
        .state('qrcode.flash', {
            url: '/flash/:qrcodeid',
            templateUrl: "./app/components/qrcode/qrcode.flash.tpl.html",

        });

    // Routes pour les vues relatives aux statistiques
    $stateProvider
        .state('statistic', {
            url: '/statistic',
             controller: 'StatisticCtrl',
             templateUrl: "./app/components/statistic/statistic.tpl.html"
        });
});
