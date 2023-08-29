package joe.com.jiujitsu_nwu;

import java.util.Date;

public class User {
    private Date date;
    private String displayName, email, userId;

    public User() {
    }

    public User(Date date, String displayName, String email, String userId) {
        this.date = date;
        this.displayName = displayName;
        this.email = email;
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
