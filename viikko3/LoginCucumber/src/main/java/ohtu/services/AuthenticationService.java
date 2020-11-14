package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        Pattern userp = Pattern.compile("[a-z]{3,}");
        Pattern paswp1 = Pattern.compile("[a-z]{8,}");
        Pattern paswp2 = Pattern.compile(".*[0-9].*");
        Matcher umatcher = userp.matcher(username);
        Matcher pmatcher1 = paswp1.matcher(password);
        Matcher pmatcher2 = paswp2.matcher(password);
        if (umatcher.find() && pmatcher1.find() && pmatcher2.find()) {
            return false;
        }
        return true;
    }
}
