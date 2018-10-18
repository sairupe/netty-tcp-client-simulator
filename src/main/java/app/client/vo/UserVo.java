package app.client.vo;

/**
 * Created by zh on 2018/9/5.
 */
public class UserVo {

    private int accountId;

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
