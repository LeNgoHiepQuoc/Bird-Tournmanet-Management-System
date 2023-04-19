package account;

public class AccountError {

    private String duplicate;
    private String password;
    private String repass;
    private String name;
    private String phone;
    private String status;

    public AccountError() {
        this.duplicate = "";
        this.password = "";
        this.repass = "";
        this.name = "";
        this.phone = "";
        this.status = "";
    }

    public AccountError(String duplicate, String password, String repass, String name, String phone, String status) {
        this.duplicate = duplicate;
        this.password = password;
        this.repass = repass;
        this.name = name;
        this.phone = phone;
        this.status = status;
    }

    public String getDuplicate() {
        return duplicate;
    }

    public void setDuplicate(String duplicate) {
        this.duplicate = duplicate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepass() {
        return repass;
    }

    public void setRepass(String repass) {
        this.repass = repass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
