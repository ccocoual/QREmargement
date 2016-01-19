qrApp.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise("/");

    $stateProvider
        .state('home', {
            url: '/home',
            controller: 'HomeCtrl',
            templateUrl: "./app/components/home/home.tpl.html"
        });


    //Routes pour les vues relatives aux cours
    $stateProvider
        .state('cours', {
            url: '/cours'/**,
            abstract:true,
             controller: 'HomeCtrl',
             templateUrl: "./app/components/home/home.tpl.html"**/
        })
        .state('cours.list', {
            url: '/list'
        })
        .state('cours.create', {
            url: '/create'
        });

    //Routes pour les vues relatives aux étudiants
    $stateProvider
        .state('etudiant', {
            url: '/etudiant'/**,
             abstract:true,
             controller: 'HomeCtrl',
             templateUrl: "./app/components/home/home.tpl.html"**/
        })
        .state('etudiant.list', {
            url: '/list'
        })
        .state('etudiant.create', {
            url: '/create'
        });

    //Routes pour les vues relatives aux statistiques
    $stateProvider
        .state('statistiques', {
            url: '/statistiques'/**,
             controller: 'HomeCtrl',
             templateUrl: "./app/components/home/home.tpl.html"**/
        });
});
