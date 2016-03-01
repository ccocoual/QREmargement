'use strict';

var qrApp = angular.module("QRApp", [
    "ui.router",
    "monospaced.qrcode",
    "chart.js",
    "frapontillo.bootstrap-switch",
    "ngCookies",
    "ui.bootstrap",
    "angularjs-dropdown-multiselect",
    "ngAnimate",
    "toastr",
    "LocalStorageModule",
    "ngTable"
]);

// Broadcast notAuthenticated event everytime the state changes and the teacher isn't connected.
qrApp.run(function ($rootScope, AUTH_EVENTS, AuthFactory, $state) {
    $rootScope.$on('$stateChangeStart', function (event, next, params, current) {
        if (!AuthFactory.isAuthenticated()) {
            if ((next.name != "auth_teacher") && (next.name != "auth_student") && (next.name != "auth_success") && (next.name != "qrcode") && (next.name != "qrcode.flash")) {
                event.preventDefault();
                $state.go("auth_teacher");
            }
        }
    });
})

qrApp.config(['ChartJsProvider', function (ChartJsProvider) {
    ChartJsProvider.setOptions({
        colours: ['#4abebc', '#f54b50'],
        responsive: true
    })
}]);

qrApp.config(function(toastrConfig) {
    angular.extend(toastrConfig, {
        allowHtml: false,
        closeButton: true,
        closeHtml: '<button>&times;</button>',
        extendedTimeOut: 700,
        iconClasses: {
            error: 'toast-error',
            info: 'toast-info',
            success: 'toast-success',
            warning: 'toast-warning'
        },
        messageClass: 'toast-message',
        onHidden: null,
        onShown: null,
        onTap: null,
        progressBar: true,
        tapToDismiss: true,
        templates: {
            toast: 'directives/toast/toast.html',
            progressbar: 'directives/progressbar/progressbar.html'
        },
        timeOut: 5000,
        titleClass: 'toast-title',
        toastClass: 'toast'
    });
});

// Add HTTP and Authentication interceptor.
qrApp.config(function ($httpProvider) {
  $httpProvider.interceptors.push([
    '$injector',
    function ($injector) {
      return $injector.get('AuthInterceptor');
    }
  ]);
});