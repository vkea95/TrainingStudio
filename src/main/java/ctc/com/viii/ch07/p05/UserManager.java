package ctc.com.viii.ch07.p05;

import java.util.HashMap;

public class UserManager {
    private HashMap<Integer, User> users;

    public User addUser(int userId, String details, int accountType) {
        users.putIfAbsent(userId, new User(userId, details, accountType));
        return users.get(userId);
    }

    public boolean removeUser(int id) {
        return users.remove(id) != null;
    }

    public User find(int id) {
        return users.get(id);
    }
}
