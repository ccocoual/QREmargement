(function () {
    'use strict';

    qrApp.controller('ModalQrCodeCtrl', ModalQrCodeCtrl);

    ModalQrCodeCtrl.$inject = ["$scope", "$uibModalInstance", "qrcodeurl"];

    function ModalQrCodeCtrl($scope, $uibModalInstance, qrcodeurl) {
        var vm = this;
        $scope.qrCodeUrl = qrcodeurl;

        $scope.close = function () {
            $uibModalInstance.close();
        };
    }
})();