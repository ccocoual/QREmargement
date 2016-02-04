'use strict';

var qrApp = angular.module("QRApp", [
    "ui.router",
    "monospaced.qrcode",
    "chart.js",
    "frapontillo.bootstrap-switch"

]);

qrApp.config(['ChartJsProvider', function (ChartJsProvider) {
    ChartJsProvider.setOptions({
        colours: ['#4abebc', '#f54b50'],
        responsive: true
    })
}]);