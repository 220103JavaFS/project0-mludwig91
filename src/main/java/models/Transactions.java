package models;

import java.util.Objects;

public class Transactions {

    private Integer transactionsID;
    private Integer custID;
    private Integer custID2;
    private Integer acctID;
    private Integer acctID2;
    private float amount;
    private String transType;

    public Transactions() {}

    public Transactions(Integer transactionsID, Integer custID, Integer custID2, Integer acctID, Integer acctID2, float amount, String transType) {
        this.transactionsID = transactionsID;
        this.custID = custID;
        this.custID2 = custID2;
        this.acctID = acctID;
        this.acctID2 = acctID2;
        this.amount = amount;
        this.transType = transType;
    }

    public Transactions(Integer transactionsID, Integer custID,Integer acctID,  float amount, String transType){
        this.transactionsID = transactionsID;
        this.custID = custID;
        this.acctID = acctID;
        this.amount = amount;
        this.transType = transType;
    }

    public Integer getTransactionsID() {
        return transactionsID;
    }

    public void setTransactionsID(Integer transactionsID) {
        this.transactionsID = transactionsID;
    }

    public Integer getCustID() {
        return custID;
    }

    public void setCustID(Integer custID) {
        this.custID = custID;
    }

    public Integer getCustID2() {
        return custID2;
    }

    public void setCustID2(Integer custID2) {
        this.custID2 = custID2;
    }

    public Integer getAcctID() {
        return acctID;
    }

    public void setAcctID(Integer acctID) {
        this.acctID = acctID;
    }

    public Integer getAcctID2() {
        return acctID2;
    }

    public void setAcctID2(Integer acctID2) {
        this.acctID2 = acctID2;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transactions)) return false;
        Transactions that = (Transactions) o;
        return Objects.equals(getTransactionsID(), that.getTransactionsID()) && Objects.equals(getCustID(), that.getCustID()) && Objects.equals(getCustID2(), that.getCustID2()) && Objects.equals(getAcctID(), that.getAcctID()) && Objects.equals(getAcctID2(), that.getAcctID2()) && Objects.equals(getAmount(), that.getAmount()) && Objects.equals(getTransType(), that.getTransType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTransactionsID(), getCustID(), getCustID2(), getAcctID(), getAcctID2(), getAmount(), getTransType());
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "transactionsID=" + transactionsID +
                ", custID=" + custID +
                ", custID2=" + custID2 +
                ", acctID=" + acctID +
                ", acctID2=" + acctID2 +
                ", amount=" + amount +
                ", transType='" + transType + '\'' +
                '}';
    }
}
