package ctc.com.viii.ch07.p05;

public class User {
    private int userId;
    private String details;
    private int accountType;

    public void renewMembershipi(){

    }

    public User(int userId, String details, int accountType) {
        this.userId =userId;
        this.details =details;
        this.accountType = accountType;
    }

    public int getUserId() {
        return userId;
    }

    public String getDetails() {
        return details;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }
}
