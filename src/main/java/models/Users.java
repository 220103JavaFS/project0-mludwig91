package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

 /*
 * Employees of the bank should be able to view all of their customers information. This includes:
 * Account information
 * Account balances
 * Personal information
 *	Employees should be able to approve/deny open applications for accounts
 *	Bank admins should be able to view and edit all accounts. This includes:
 * Approving/denying accounts
 * withdrawing, depositing, transferring from all accounts
 * canceling accounts
 */


public class Users {

    private Integer userID;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private String usertype;


    public Users(){
    }

    public Users(Integer id, String firstname, String lastname, String userName, String password, String email, String type) {

        this.userID = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = userName;
        this.password = password;
        this.email = email;
        this.usertype = type;
    }


    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getUsertype() {return usertype;}

    public void setUsertype(String usertype) {this.usertype = usertype;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users users = (Users) o;
        return Objects.equals(getUserID(), users.getUserID()) && Objects.equals(getFirstname(), users.getFirstname()) && Objects.equals(getLastname(), users.getLastname()) && Objects.equals(username, users.username) && Objects.equals(getPassword(), users.getPassword()) && Objects.equals(getEmail(), users.getEmail()) && Objects.equals(usertype, users.usertype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserID(), getFirstname(), getLastname(), username, getPassword(), getEmail(), usertype);
    }

    @Override
    public String toString() {
        return "Users{" +
                "userID=" + userID +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", usertype='" + usertype + '\'' +
                '}';
    }
}