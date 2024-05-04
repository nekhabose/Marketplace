/*package model;

/**
 * A utility class to manage the session data of the logged-in user.
 */
/*public class SessionManager {
    // Static variable to hold the current logged-in user's ID
    private static Integer currentUserId;

    /**
     * Gets the ID of the current logged-in user.
     * @return The ID of the current user or null if no user is logged in.
     */
    /*public static Integer getCurrentUserId() {
        return currentUserId;
    }

    /**
     * Sets the ID of the current user. This method should be called
     * when the user logs in to the application.
     * @param userId The ID of the user who has logged in.
     */
   /* public static void setCurrentUserId(Integer userId) {
        currentUserId = userId;
    }

    /**
     * Clears the current session. This method should be called
     * when the user logs out of the application.
     */
  /*  public static void clearCurrentSession() {
        currentUserId = null;
    }
}*/

package model;

public class SessionManager {
    private static Integer currentUserId;

    public static Integer getCurrentUserId() {
        return currentUserId;
    }

    public static void setCurrentUserId(Integer userId) {
        currentUserId = userId;
    }

    public static void clearCurrentSession() {
        currentUserId = null;
    }
}
