package Dao;

import models.Account;
import models.Customer;
import models.Transactions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionsDAO extends ConnectorClass implements ITransactionsDAO {

    public List<Transactions> selectAllTransactions() {

        List<Transactions> allTrans = new ArrayList<>();

        try {
            //Create a connection to DB and prepare select statement
            Connection myConnect = super.getConnection();

            PreparedStatement ps = myConnect.prepareStatement(
                    "select * from Transactions");
            //Store query result to build list of Accounts
            ResultSet queryResult = ps.executeQuery();

            //while data in query result create Account list
            while (queryResult.next()) {
                String acctType = queryResult.getString("acctType");
                Integer transID = queryResult.getInt("transactionsID");
                Integer custID = queryResult.getInt("custID");
                Integer custID2 = queryResult.getInt("custID2");
                Integer acctID = queryResult.getInt("acctID");
                Integer acctID2 = queryResult.getInt("acctID2");
                float amount = queryResult.getFloat("amount");
                String transType = queryResult.getString("transType");

                Transactions trans = new Transactions(transID, custID, custID2, acctID, acctID2, amount, transType);
                allTrans.add(trans);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Select all Transactions failed");
        }
        return allTrans;
    }


    @Override
    public boolean insertWithdrawDeposit(Transactions a) {
        try {
            //Create a connection to DB and prepare select statement
            Connection myConnect = super.getConnection();
            PreparedStatement ps = myConnect.prepareStatement(
                    "INSERT into Transactions (custID, acctID, amount, transType) VALUES (?,?,?,?)");
            ps.setInt(1, a.getCustID());
            ps.setInt(2, a.getAcctID());
            ps.setDouble(3, a.getAmount());
            ps.setString(4, a.getTransType());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Insert Withdraw Deposit Failed!!!");
        }

        return false;
    }

    @Override
    public boolean insertTransfer(Transactions a) {

        try {
            //Create a connection to DB and prepare select statement
            Connection myConnect = super.getConnection();
            PreparedStatement ps = myConnect.prepareStatement(
                    "INSERT into Transactions (custID, custID2, acctID, acctID2, amount, transType) VALUES (?,?,?,?,?,?)");
            ps.setInt(1, a.getCustID());
            ps.setInt(2, a.getCustID2());
            ps.setInt(3, a.getAcctID());
            ps.setInt(4, a.getAcctID2());
            ps.setFloat(5, a.getAmount());
            ps.setString(6, a.getTransType());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Transaction Transfer Failed!!!");
        }

        return false;

    }

    @Override
    public boolean update(Transactions a) {

        try {
            Connection myConnect = super.getConnection();
            PreparedStatement ps = myConnect.prepareStatement(
                    "UPDATE Transactions set custID = ?, acctID = ?, amount = ?, transType = ?, where transactionsID = ?");
            ps.setInt(1, a.getCustID());
            ps.setInt(2, a.getAcctID());
            ps.setFloat(3, a.getAmount());
            ps.setString(4, a.getTransType());
            ps.setInt(5, a.getTransactionsID());
            ps.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Transaction update failed");

        return false;
    }

    @Override
    public boolean delete(Transactions a) {
        try {
            Connection myConnect = super.getConnection();
            PreparedStatement ps = myConnect.prepareStatement(
                    "DELETE from Transactions where transacttionsID = ?");
            ps.setInt(1, a.getTransactionsID());
            ps.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Transaction update failed");
        }
        return false;
    }
}
