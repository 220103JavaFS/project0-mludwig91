package Dao;

import models.Account;
import models.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO extends ConnectorClass implements IAccountDAO{

    public List<Account> selectAllAccount() {

        List<Account> allAccounts = new ArrayList<>();

        try {
            //Create a connection to DB and prepare select statement
            Connection myConnect = super.getConnection();

            PreparedStatement ps = myConnect.prepareStatement(
                    "select * from Account inner join Customer on Customer.customerID = Account.custID;");

            //Store query result to build list of Accounts
            ResultSet queryResult = ps.executeQuery();

            //while data in query result create Account list
            while(queryResult.next()){
                Integer customerID = queryResult.getInt("customerID");
                String firstname = queryResult.getString("firstName");
                String lastname = queryResult.getString("lastName");
                String email = queryResult.getString("email");
                String userName = queryResult.getString("userName");
                String password = queryResult.getString("pass");
                boolean memberStatus = queryResult.getBoolean("memberStatus");
                boolean idProof = queryResult.getBoolean("identification");
                Integer creditScore = queryResult.getInt("creditScore");
                Integer age = queryResult.getInt("customerAge");

                Customer cus = new Customer(customerID, firstname, creditScore, age, lastname, userName, password,email, memberStatus, idProof);

                String acctType = queryResult.getString("acctType");
                Integer acctID = queryResult.getInt("accountID");
                Integer custID = queryResult.getInt("custID");
                float acctTotal = queryResult.getFloat("acctTotal");

                Account acct = new Account(acctID, acctType, acctTotal, custID, cus);
                allAccounts.add(acct);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Select all Accounts failed");

        }

        return allAccounts;
    }

    public Account selectCustomersAccounts(Integer id) {
        Account acct = null;

        try {
            //Create a connection to DB and prepare select statement
            Connection myConnect = super.getConnection();

            PreparedStatement ps = myConnect.prepareStatement(
                    "select acctType, acctTotal, custID from Account inner join Customer on Customer.customerID = Account.custID;;");
            ps.setInt(1,id);

            //Store query result to build list of Accounts
            ResultSet queryResult = ps.executeQuery();

            //while data in query result create Account list
            while(queryResult.next()){

                Integer customerID = queryResult.getInt("customerID");
                String firstname = queryResult.getString("firstName");
                String lastname = queryResult.getString("lastName");
                String email = queryResult.getString("email");
                String userName = queryResult.getString("userName");
                String password = queryResult.getString("pass");
                boolean memberStatus = queryResult.getBoolean("memberStatus");
                boolean idProof = queryResult.getBoolean("identification");
                Integer creditScore = queryResult.getInt("creditScore");
                Integer age = queryResult.getInt("customerAge");

                Customer cus = new Customer(customerID, firstname, creditScore, age, password, lastname, userName, password, memberStatus, idProof);


                String acctType = queryResult.getString("acctType");
                Integer acctID = queryResult.getInt("acctID");
                float acctTotal = queryResult.getFloat("acctTotal");
                Integer custID = queryResult.getInt("custID");


                acct = new Account(acctID, acctType, acctTotal, custID, cus);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Select customers Accounts failed");
        }

        return acct;
    }

    public Account selectSingleAccount(Integer id) {
        Account acct = null;

        try {
            //Create a connection to DB and prepare select statement
            Connection myConnect = super.getConnection();

            PreparedStatement ps = myConnect.prepareStatement(
                    "select * from Account inner join Customer on Customer.customerID = Account.custID where accountID = ?;");
            ps.setInt(1,id);

            //Store query result to build list of Accounts
            ResultSet queryResult = ps.executeQuery();

            //while data in query result create Account list
            while(queryResult.next()){

                Integer customerID = queryResult.getInt("customerID");
                String firstname = queryResult.getString("firstName");
                String lastname = queryResult.getString("lastName");
                String email = queryResult.getString("email");
                String userName = queryResult.getString("userName");
                String password = queryResult.getString("pass");
                boolean memberStatus = queryResult.getBoolean("memberStatus");
                boolean idProof = queryResult.getBoolean("identification");
                Integer creditScore = queryResult.getInt("creditScore");
                Integer age = queryResult.getInt("customerAge");

                Customer cus = new Customer(customerID, firstname, creditScore, age, lastname, userName, password, email, memberStatus, idProof);

                String acctType = queryResult.getString("acctType");
                Integer acctID = queryResult.getInt("accountID");
                float acctTotal = queryResult.getFloat("acctTotal");
                Integer custID = queryResult.getInt("custID");

                acct = new Account(acctID, acctType, acctTotal, custID, cus);
            }

        } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Select Single Account failed");
        }

        return acct;
    }


        @Override
    public boolean insert(Account a, Customer b) {


        try{
            Connection myConnect = super.getConnection();
            PreparedStatement ps = myConnect.prepareStatement(
                    "insert into Account (acctType, acctTotal, custID) VALUES (?,?,?)");
            ps.setString(1,a.getAcctType());
            ps.setFloat(2, a.getAcctTotal());
            ps.setInt(3, b.getCustomerID());
            ps.execute();
            return true;
        }catch(SQLException e){
            e.printStackTrace();}
            System.out.println("Account insert failed");

        return false;
    }

    @Override
    public boolean update(Account a) {
        boolean updated = false;

        try{
            Connection myConnect = super.getConnection();
            PreparedStatement ps = myConnect.prepareStatement(
                        "UPDATE Account set acctType = ?, acctTotal = ?, where accountID = ?");
                ps.setString(1, a.getAcctType());
                ps.setFloat(2, a.getAcctTotal());
                ps.setInt(3,a.getAcctID());
                ps.execute();

            }catch(SQLException e){
                e.printStackTrace();}
            System.out.println("Account update failed");

            return updated;
    }

    @Override
    public boolean updateAccountTotal(Account a) {
        try{
            Connection myConnect = super.getConnection();
            PreparedStatement ps = myConnect.prepareStatement(
                    "UPDATE Account set acctTotal = ? where accountID = ?");
            ps.setFloat(1, a.getAcctTotal());
            ps.setInt(2, a.getAcctID());
            ps.execute();
            return true;

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Customer update failed");
        }
        return false;
    }

    @Override
    public boolean delete(Account a) {

        boolean updated = false;

        try{
            Connection myConnect = super.getConnection();
            PreparedStatement ps = myConnect.prepareStatement(
                    "DELETE from Account where accountID = ?");
            ps.setInt(1,a.getAcctID());
            ps.execute();
            updated = true;

        }catch(SQLException e){
            e.printStackTrace();}
        System.out.println("Account update failed");

        return updated;


    }
}
