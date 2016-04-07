//package controllers;
//
//import models.Authenticate;
//import models.User;
//import play.libs.Crypto;
//import play.mvc.Before;
//import play.mvc.Controller;
//
///**
// * @author Michael Ruf
// * @since 2016-03-10
// */
//public class SecurityOld extends Controller {
//
//    public static boolean login(User user, String password) {
//        if (authenticate(user, getHash(password))) {
//            // Set the session variables
//            session.put("user_id", user.id);
//            session.put("password", user.password);
//
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * Method to check whether the user is allowed to access a controller action
//     * whenever an Authenticate annotation is given
//     */
//    @Before
//    public static void checkAccess() {
//        // Get and check for the annotation
//        Authenticate auth = getActionAnnotation(Authenticate.class);
//        if (auth != null) {
//            checkToken(auth);
//            checkLogin(auth);
//        }
//    }
//
//    private static void checkToken(Authenticate auth) {
//        // TODO Check for the signature
//        // TODO: token shell be disableable in the config
//        // if (!true)
//        // {
//        // new Message(Message.TYPE_ERROR, "no signature given", 1).throwNow();
//        // }
//
//        String token = Application.request.params.get("token");
//
//        if (token != null) {
//            User user = User.find("byToken", token).first();
//
//            if (auth != null) {
//                if (user == null) {
//                    new Message(Message.TYPE_ERROR, "user does not exist or unexpected token", 403).throwNow();
//                }
//
//                if (!user.prooveToken(token)) {
//                    new Message(Message.TYPE_ERROR, "token not valid", 403).throwNow();
//                }
//
//                if (!checkPermissions(auth, user)) {
//                    new Message(Message.TYPE_ERROR, "insufficient permissions", 403).throwNow();
//                }
//            }
//
//            if (user != null) {
//                user.refreshToken();
//                session.put("user_id", user.id);
//                session.put("password", user.password);
//            }
//        }
//    }
//
//    private static void checkLogin(Authenticate auth) {
//        if (!session.contains("password") || !authenticate(connectedUser(), session.get("password"))) {
//            Application.displayMessage("error", "Login to access this page.");
//            // new Message(...).display();
//        }
//
//        if (auth != null && !checkPermissions(auth, connectedUser())) {
//            Application.displayMessage("error", "Insufficient permission.");
//            // new Message(...).display();
//        }
//    }
//
//    private static boolean checkPermissions(Authenticate auth, User user) {
//        if (auth.adminLevel() != 0 && user.adminLevel < auth.adminLevel()) {
//            return false;
//        }
//
//        return true;
//    }
//
//    /**
//     * This method is called during the authentication process. This is where
//     * you check if the user is allowed to log in into the system. This is the
//     * actual authentication process against a third party system (most of the
//     * time a DB).
//     *
//     * @param user
//     * @param password
//     * @return boolean success
//     */
//    public static boolean authenticate(User user, String password) {
//        // Check whether a user exists and the password is correct
//        if (user != null && user.password.equals(password)) {
//            return true;
//        }
//
//        return false;
//    }
//
//    /**
//     * Get the current user that is logged in.
//     *
//     * @return User
//     */
//    public static User connectedUser() {
//        String id = session.get("user_id");
//
//        User user = null;
//
//        // Check whether the id is set
//        if (id != null) {
//            user = User.findById(Long.parseLong(id));
//        }
//
//        String username = Application.request.user;
//
//        if (username != null) {
//            user = User.find("byUsername", username).first();
//        }
//
//        return user;
//    }
//
//    /**
//     * Generates the hash value for a password.
//     *
//     * @param password
//     * @return hash
//     */
//    public static String getHash(String password) {
//        return Crypto.passwordHash(password, Crypto.HashType.SHA256);
//    }
//
//    public static String getSigned(String token) {
//        return Crypto.sign(token);
//    }
//
//}