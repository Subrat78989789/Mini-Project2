package employee;

public class LoginService {
    private final String USERNAME = "admin";
    private final String PASSWORD = "admin123";

    public boolean login(String user, String pass) {
        return USERNAME.equals(user) && PASSWORD.equals(pass);
    }
}

