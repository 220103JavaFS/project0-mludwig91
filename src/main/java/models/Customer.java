package models;

import java.util.Objects;

public class Customer{

    /*
    Customers of the bank should be able to register
    with a username and password, and apply to open an account.
    Stretch Goal: Customers should be able to apply for joint accounts
    Once the account is open, customers should be able to withdraw, deposit,
    and transfer funds between accounts
    All basic validation should be done, such as trying to input negative amounts,
    overdrawing from accounts etc.
    */

    private Integer customerID;
    private String firstName;
    private Integer creditScore;
    private Integer customerAge;
    private String lastName;
    private String userName;
    private String pass;
    private String email;
    private Boolean memberStatus;
    private Boolean identification;

    public Customer() {}

    public Customer(Integer customerID, String firstName, Integer creditScore, Integer customerAge, String lastName, String userName, String pass, String email, Boolean memberStatus, Boolean identification) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.creditScore = creditScore;
        this.customerAge = customerAge;
        this.lastName = lastName;
        this.userName = userName;
        this.pass = pass;
        this.email = email;
        this.memberStatus = memberStatus;
        this.identification = identification;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    public Integer getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(Integer customerAge) {
        this.customerAge = customerAge;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(Boolean memberStatus) {
        this.memberStatus = memberStatus;
    }

    public Boolean getIdentification() {
        return identification;
    }

    public void setIdentification(Boolean identification) {
        this.identification = identification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getCustomerID(), customer.getCustomerID()) && Objects.equals(getFirstName(), customer.getFirstName()) && Objects.equals(getCreditScore(), customer.getCreditScore()) && Objects.equals(getCustomerAge(), customer.getCustomerAge()) && Objects.equals(getLastName(), customer.getLastName()) && Objects.equals(getUserName(), customer.getUserName()) && Objects.equals(getPass(), customer.getPass()) && Objects.equals(getEmail(), customer.getEmail()) && Objects.equals(getMemberStatus(), customer.getMemberStatus()) && Objects.equals(getIdentification(), customer.getIdentification());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerID(), getFirstName(), getCreditScore(), getCustomerAge(), getLastName(), getUserName(), getPass(), getEmail(), getMemberStatus(), getIdentification());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", firstName='" + firstName + '\'' +
                ", creditScore=" + creditScore +
                ", customerAge=" + customerAge +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", pass='" + pass + '\'' +
                ", email='" + email + '\'' +
                ", memberStatus=" + memberStatus +
                ", identification=" + identification +
                '}';
    }
}