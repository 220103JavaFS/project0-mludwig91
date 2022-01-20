package Dao;

import models.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends ConnectorClass implements IUserDAO {

    public List<Users> selectAllUser() {

        List<Users> allUsers = new ArrayList<>();

        try {
            //Create a connection to DB and prepare select statement
            Connection myConnect = super.getConnection();
            PreparedStatement ps = myConnect.prepareStatement("SELECT * from users");

            //Store query result to build list of Users
            ResultSet queryResult = ps.executeQuery();

            //while data in query result create user list
            while(queryResult.next()){

                int id = queryResult.getInt("userID");
                String firstname = queryResult.getString("firstname");
                String lastname = queryResult.getString("lastname");
                String userName = queryResult.getString("userName");
                String password = queryResult.getString("pass");
                String email = queryResult.getString("email");
                String type = queryResult.getString("usertype");


                Users User = new Users(id, firstname, lastname, userName, password, email, type);
                allUsers.add(User);
                }

                return allUsers;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Select all Users failed");

        }

        return null;
    }

    public Users selectSingleUser(int id) {

        Users user = null;

        try {

            //Create a connection to DB and prepare select statement
            Connection myConnect = super.getConnection();
            PreparedStatement ps = myConnect.prepareStatement(
                    "SELECT firstname, lastname, userName, pass, email, usertype from users where userID = ?");
            ps.setInt(1,id);

            //Store query result to build list of Users
            ResultSet queryResult = ps.executeQuery();

            //while data in query result create user
            while(queryResult.next()) {
                String firstname = queryResult.getString("firstname");
                String lastname = queryResult.getString("lastname");
                String email = queryResult.getString("email");
                String userName = queryResult.getString("userName");
                String password = queryResult.getString("pass");
                String type = queryResult.getString("usertype");
                user = new Users(id, firstname, lastname, userName, password, email, type);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Select single User failed");
        }

        return user;
    }

    public Users selectUserByUsername(String username){

        Users user = null;

        try {

            //Create a connection to DB and prepare select statement
            Connection myConnect = super.getConnection();
            PreparedStatement ps = myConnect.prepareStatement(
                    "SELECT userID, firstname, lastname, userName, pass, email, usertype from users where username = ?");
            ps.setString(1, username);

            //Store query result to build list of Users
            ResultSet queryResult = ps.executeQuery();

            //while data in query result create user
            while(queryResult.next()) {
                Integer id = queryResult.getInt("userID");
                String firstname = queryResult.getString("firstname");
                String lastname = queryResult.getString("lastname");
                String email = queryResult.getString("email");
                String userName = queryResult.getString("userName");
                String password = queryResult.getString("pass");
                String type = queryResult.getString("usertype");
                user = new Users(id, firstname, lastname, userName, password, email, type);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Select single User failed");
        }
        return user;
    }

    @Override
    public boolean insert(Users t) {

        boolean updated = false;

        try {
            Connection myConnect = super.getConnection();
            PreparedStatement ps = myConnect.prepareStatement(
                    "INSERT into users (firstname, lastname, username, pass, email, usertype) values (?,?,?,?,?,?)" );
            ps.setString(1, t.getFirstname());
            ps.setString(2, t.getLastname());
            ps.setString(3, t.getUsername());
            ps.setString(4, t.getPassword());
            ps.setString(5, t.getEmail());
            ps.setString(6, t.getUsertype());
            ps.execute();
            updated = true;

        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("User insert failed");
        }
        return updated;
    }

    @Override
    public boolean update(Users a) {

        boolean updated = false;

        try{
            Connection myConnect = super.getConnection();
            PreparedStatement ps = myConnect.prepareStatement(
                    "UPDATE users set firstname = ?, lastname = ?, email = ?, userName = ?, pass = ?, usertype = ?");
            ps.setString(1, a.getFirstname());
            ps.setString(2, a.getLastname());
            ps.setString(3, a.getUsername());
            ps.setString(4, a.getPassword());
            ps.setString(5, a.getEmail());
            ps.setString(6, a.getUsertype());
            ps.execute();
            updated = true;

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("User update failed");
        }

        return updated;
    }


    @Override
    public boolean delete(Users a) {

        boolean updated = false;

        try{
            Connection myConnect = super.getConnection();
            PreparedStatement ps = myConnect.prepareStatement("DELETE from User where userID =?");
            ps.setInt(1,a.getUserID());
            ps.execute();
            updated = true;

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("User delete failed");
        }

        return updated;
    }


}
