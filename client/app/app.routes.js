qrApp.config(function($stateProvider, $urlRouterProvider) {

  $urlRouterProvider.otherwise("/");

  $stateProvider
    .state('home', {
      url: '/home',
      controller: 'HomeCtrl',
      templateUrl: "./app/components/home/home.tpl.html"
    });
});
