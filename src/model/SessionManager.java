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
