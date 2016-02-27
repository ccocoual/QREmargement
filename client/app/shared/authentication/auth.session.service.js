(function () {

qrApp.service('AuthSessionService', AuthSessionService);
    
AuthSessionService.$inject = ['localStorageService', 'SERVURL'];
    
function AuthSessionService(localStorageService, SERVURL) {
    
    var service = {
            create: create,
            destroy: destroy,
            isConnected: isConnected,
            getTeacherURL: getTeacherURL
        };
    return service;
    
    

    function create(current) {
        this.current = current;
    };

    function destroy() {
        this.current = null;
    };

    function isConnected() {
        
        if(localStorageService.get("teacher_token") != null) { console.log(localStorageService.get("teacher_token"));
            return true;
        } else {
            return false;
        }
    }
    
    function getTeacherURL() {
        return SERVURL + localStorageService.get("teacher_token") + "/";
    }
    
}
    
})();