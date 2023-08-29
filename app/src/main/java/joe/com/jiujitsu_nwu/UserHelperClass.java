package joe.com.jiujitsu_nwu;

public class UserHelperClass {
    String STUDENT_NUMBER, DATE, TIME;

    public UserHelperClass() {
    }

    public UserHelperClass(String STUDENT_NUMBER, String DATE, String TIME) {
        this.STUDENT_NUMBER = STUDENT_NUMBER;
        this.DATE = DATE;
        this.TIME = TIME;
    }

    public String getSTUDENT_NUMBER() {
        return STUDENT_NUMBER;
    }

    public void setSTUDENT_NUMBER(String STUDENT_NUMBER) {
        this.STUDENT_NUMBER = STUDENT_NUMBER;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }
}
