package models;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {

    private Integer acctID;
    private String acctType;
    private float acctTotal;
    private Integer custID;
    private Customer owner;

    public Account() {}

    public Account(Integer acctID, String acctType, float acctTotal, Integer custID, Customer owner) {
        this.acctID = acctID;
        this.acctType = acctType;
        this.acctTotal = acctTotal;
        this.custID = custID;
        this.owner = owner;
    }

    public Integer getAcctID() {
        return acctID;
    }

    public void setAcctID(Integer acctID) {
        this.acctID = acctID;
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

    public float getAcctTotal() {
        return acctTotal;
    }

    public void setAcctTotal(float acctTotal) {
        this.acctTotal = acctTotal;
    }

    public Integer getCustID() {
        return custID;
    }

    public void setCustID(Integer custID) {
        this.custID = custID;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Objects.equals(getAcctID(), account.getAcctID()) && Objects.equals(getAcctType(), account.getAcctType()) && Objects.equals(getAcctTotal(), account.getAcctTotal()) && Objects.equals(getCustID(), account.getCustID()) && Objects.equals(getOwner(), account.getOwner());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAcctID(), getAcctType(), getAcctTotal(), getCustID(), getOwner());
    }

    @Override
    public String toString() {
        return "Account{" +
                "acctID=" + acctID +
                ", acctType='" + acctType + '\'' +
                ", acctTotal=" + acctTotal +
                ", custID=" + custID +
                ", owner=" + owner +
                '}';
    }
}