package Service;
import Dao.CustomerDAO;
import Dao.UserDAO;
import models.Customer;
import models.Encryptor;
import models.Users;

import java.security.NoSuchAlgorithmException;


public class LoginService {

    private final UserDAO user;
    private final CustomerDAO cus;
    private final Encryptor encryptor;

    public LoginService(){user = new UserDAO(); encryptor = new Encryptor(); cus = new CustomerDAO();}

    public boolean loginUser(String username, String password, String type) throws NoSuchAlgorithmException {

        if(type.equals("Customer")){
            Customer secureCustomer = cus.selectCustomerByUsername(username);

            if(secureCustomer != null){
                //generate encrypted version of customers password and compare
                String passCheck = encryptor.encoder(password);
                String securePassword = secureCustomer.getPass();

                //Check current hash against DB hash
                if(securePassword.equals(passCheck))
                    return true;
                else
                    return false;

            }else{
                System.out.println("Customer does not exist");
            }
        }else {
            Users secureUser = user.selectUserByUsername(username);

            if(secureUser != null){

                //Generate password hash
                String passCheck = encryptor.encoder(password);
                String securePassword = secureUser.getPassword();

                //Check current hash against DB hash
                if(securePassword.equals(passCheck))
                    return true;
                else
                    return false;

            }else{
                System.out.println("User does not exist");
            }
        }
        //If user exists
        return false;
    }
}
