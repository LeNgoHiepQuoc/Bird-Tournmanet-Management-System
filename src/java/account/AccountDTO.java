package account;

import java.io.Serializable;

public class AccountDTO implements Serializable {

    private int accountID;
    private String email;
    private String password;
    private String name;
    private String profilePhoto;
    private int role;
    private int phone;
    private int accountStatus;

    public AccountDTO() {
    }

    public AccountDTO(String name) {
        this.name = name;
    }

    public AccountDTO(int accountID, int phone, String email, String name) {
        this.accountID = accountID;
        this.phone = phone;
        this.email = email;
        this.name = name;
    }

    public AccountDTO(int accountID, String email, String password, String name, String profilePhoto, int role, int phone, int accountStatus) {
        this.accountID = accountID;
        this.email = email;
        this.password = password;
        this.name = name;
        this.profilePhoto = profilePhoto;
        this.role = role;
        this.phone = phone;
        this.accountStatus = accountStatus;
    }

    public AccountDTO(int accountID, String email, String password, int phone) {
        this.accountID = accountID;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }
    
    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(int accountStatus) {
        this.accountStatus = accountStatus;
    }

    @Override
    public String toString() {
        return "AccountDTO{" + "accountID=" + accountID + ", email=" + email + ", password=" + password + ", name=" + name + ", profilePhoto=" + profilePhoto + ", role=" + role + ", phone=" + phone + ", accountStatus=" + accountStatus + '}';
    }

}
