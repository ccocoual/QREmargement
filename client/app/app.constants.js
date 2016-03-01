qrApp.constant('SERVURL', 'http://148.60.11.185:8080/');
//qrApp.constant('WEBAPPURL', 'http://localhost:63342/client/index.html#/');
/*
    Url de production
 */
qrApp.constant('WEBAPPURL', 'http://148.60.11.185/client/index.html#/');

qrApp.constant('AUTH_EVENTS', {
    loginSuccess: 'auth-login-success',
    loginFailed: 'auth-login-failed',
    logoutSuccess: 'auth-logout-success',
    sessionTimeout: 'auth-session-timeout',
    notAuthenticated: 'auth-not-authenticated',
    notAuthorized: 'auth-not-authorized'
})