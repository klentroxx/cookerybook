package hu.cookerybook.desktop.session;

public final class UserSession {

    private static UserSession instance;

    private String userName;
    private int userRole;

    public UserSession(String userName, int userRole) {
        this.userName = userName;
        this.userRole = userRole;
    }

    public static UserSession getInstance(String userName, int userRole) {
        if (instance == null) {
            instance = new UserSession(userName, userRole);
        }
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserRole() {
        return userRole;
    }

    public void cleanUserSession() {
        userName = "";
        userRole = 0;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + userName + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
