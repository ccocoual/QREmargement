(function () {
    'use strict';

    qrApp.controller('ModalRemoveEmargementCtrl', ModalRemoveEmargementCtrl);

    ModalRemoveEmargementCtrl.$inject = ["$scope", "$uibModalInstance", "emargement"];

    function ModalRemoveEmargementCtrl($scope, $uibModalInstance, emargement) {
        var vm = this;
        $scope.emargement = emargement;

        $scope.ok = function () {
            $uibModalInstance.close($scope.emargement);
        };

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
    }
})();