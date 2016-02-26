(function () {

qrApp.service('AuthSessionService', function () {
    this.create = function (current) {
        this.current = current
    };

    this.destroy = function () {
        this.current = null;
    };
})
    
})();