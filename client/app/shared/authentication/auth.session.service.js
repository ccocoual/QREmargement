(function () {

qrApp.service('AuthSessionService', AuthSessionService);
    
function AuthSessionService() {
    
    var service = {
            create: create,
            destroy: destroy,
            isConnected: isConnected
        };
    return service;
    
    var current = null;

    function create(current) {
        this.current = current;
    };

    function destroy() {
        this.current = null;
    };

    function isConnected() {
        if (this.current == null) {
            return false;
        } else {
            return true;
        }
    }
    
}
    
})();