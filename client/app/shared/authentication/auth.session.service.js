(function () {

qrApp.service('AuthSessionService', function () {
    this.create = function (current) {
        this.current = current;
        return true;
    };

    this.destroy = function () {
        this.current = null;
        return false;
    };
})
    
})();