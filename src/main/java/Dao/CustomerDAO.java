package Dao;

import models.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends ConnectorClass implements ICustomerDAO{

    public List<Customer> selectAllCustomers(){
        List<Customer> cusList = new ArrayList<>();


        try {
            //Create a connection to DB and prepare select statement
            Connection myConnect = super.getConnection();
            PreparedStatement ps = myConnect.prepareStatement("SELECT * from Customer");

            //Store query result to build list of Users
            ResultSet queryResult = ps.executeQuery();

            //while data in query result create user list
            while(queryResult.next()){

                Integer customerID = queryResult.getInt("customerID");
                String firstname = queryResult.getString("firstname");
                String lastname = queryResult.getString("lastname");
                String email = queryResult.getString("email");
                String userName = queryResult.getString("userName");
                String password = queryResult.getString("pass");
                boolean memberStatus = queryResult.getBoolean("memberStatus");
                boolean idProof = queryResult.getBoolean("identification");
                Integer creditScore = queryResult.getInt("creditScore");
                Integer age = queryResult.getInt("customerAge");

                Customer cus = new Customer(customerID, firstname, creditScore, age, lastname, userName, password, email, memberStatus, idProof);

                cusList.add(cus);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Select all Customers failed");

        }

        return cusList;
    }

    public Customer selectCustomerByUsername(String username) {
        Customer cus = null;

        try {

            //Create a connection to DB and prepare select statement
            Connection myConnect = super.getConnection();
            PreparedStatement ps = myConnect.prepareStatement(
                    "SELECT customerID, firstName, creditScore, customerAge, lastName, userName, pass, email, memberStatus, identification from Customer where userName = ?");
            ps.setString(1, username);

            //Store query result to build list of Users
            ResultSet queryResult = ps.executeQuery();
            while(queryResult.next()) {
                String email = queryResult.getString("email");
                Integer customerID = queryResult.getInt("customerID");
                String firstname = queryResult.getString("firstName");
                String lastname = queryResult.getString("lastName");
                String userName = queryResult.getString("userName");
                String password = queryResult.getString("pass");
                boolean memberStatus = queryResult.getBoolean("memberStatus");
                boolean idProof = queryResult.getBoolean("identification");
                Integer creditScore = queryResult.getInt("creditScore");
                Integer age = queryResult.getInt("customerAge");
                cus = new Customer(customerID, firstname, creditScore, age, lastname, userName, password, email, memberStatus, idProof);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Select Customer by username failed");
        }

        return cus;
        }

    public Customer selectSingleCustomer(Integer id){
        Customer cus = null;

        try {

            //Create a connection to DB and prepare select statement
            Connection myConnect = super.getConnection();
            PreparedStatement ps = myConnect.prepareStatement(
                    "SELECT firstName, lastName, userName, pass, email, customerID, memberStatus, identification, creditScore, customerAge from Customer where customerID = ?");
            ps.setInt(1,id);

            //Store query result to build list of Users
            ResultSet queryResult = ps.executeQuery();

            while(queryResult.next()) {
                //while data in query result create user
                String email = queryResult.getString("email");
                Integer customerID = queryResult.getInt("customerID");
                String firstname = queryResult.getString("firstName");
                String lastname = queryResult.getString("lastName");
                String userName = queryResult.getString("userName");
                String password = queryResult.getString("pass");
                boolean memberStatus = queryResult.getBoolean("memberStatus");
                boolean idProof = queryResult.getBoolean("identification");
                Integer creditScore = queryResult.getInt("creditScore");
                Integer age = queryResult.getInt("customerAge");

                cus = new Customer(customerID, firstname, creditScore, age, lastname, userName, password, email, memberStatus, idProof);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Select single Customer failed");
        }

        return cus;

    }

    @Override
    public boolean insert(Customer t) {

        boolean updated = false;

        try {
            Connection myConnect = super.getConnection();
            PreparedStatement ps = myConnect.prepareStatement(
                    "INSERT into Customer (firstName, lastName, userName, pass, email) values (?,?,?,?,?)");
            ps.setString(1, t.getFirstName());
            ps.setString(2, t.getLastName());
            ps.setString(3, t.getUserName());
            ps.setString(4, t.getPass());
            ps.setString(5, t.getEmail());
            ps.execute();

            updated = true;

        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Customer insert failed");
        }
        return updated;
    }

    @Override
    public boolean updateMemberStatus(Customer t) {

        try{
            Connection myConnect = super.getConnection();
            PreparedStatement ps = myConnect.prepareStatement(
                    "UPDATE Customer set memberStatus = ? where customerID = ?");
            ps.setBoolean(1, t.getMemberStatus());
            ps.setInt(2, t.getCustomerID());
            ps.execute();
            return true;

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Customer update failed");
        }
        return false;
    }




    @Override
    public boolean update(Customer t) {

        try{
            Connection myConnect = super.getConnection();
            PreparedStatement ps = myConnect.prepareStatement(
                    "UPDATE Customer set firstName = ?, lastName = ?, email = ?," +
                            " userName = ?, pass = ?, identification = ?, creditScore = ?, customerAge = ?" +
                            "where customerID = ?");

            ps.setString(1, t.getFirstName());
            ps.setString(2, t.getLastName());
            ps.setString(3, t.getEmail());
            ps.setString(4, t.getUserName());
            ps.setString(5, t.getPass());
            ps.setBoolean(6, t.getIdentification());
            ps.setInt(7,t.getCreditScore());
            ps.setInt(8, t.getCustomerAge());
            ps.setInt(9,t.getCustomerID());
            ps.execute();

            return true;

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Customer update failed");
        }

        return false;
    }


    @Override
    public boolean delete(Customer a) {

        boolean updated = false;

        try{
            Connection myConnect = super.getConnection();
            PreparedStatement ps = myConnect.prepareStatement("DELETE from Customer where customerID =?");
            ps.setInt(1,a.getCustomerID());
            ps.execute();
            updated = true;

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Customer delete failed");
        }

        return updated;

    }
}
