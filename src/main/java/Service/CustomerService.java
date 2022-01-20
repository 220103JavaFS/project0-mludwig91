package Service;

import Dao.AccountDAO;
import Dao.CustomerDAO;
import Dao.CustomerDAO;
import models.Account;
import models.Customer;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Pattern;

public class CustomerService {


    private final CustomerDAO serviceCustomer;
    private final AccountDAO serviceAccount;
    private final EncryptService encryptService;
    private final String regex = "^(.+)@(.+)$";
    Pattern pattern = Pattern.compile(regex);

    private final Integer ZERO = 0;
    private final Integer MINIMUM_AGE = 18;
    private final Integer MAXIMUM_AGE = 150;
    private final Integer MINIMUM_NAME_LENGTH = 2;
    private final Integer MAXIMUM_NAME_LENGTH = 20;
    private final Integer MINIMUM_CREDIT_SCORE = 400;
    private final Integer MAXIMUM_CREDIT_SCORE = 800;
    private final Integer MINIMUM_PASSWORD_LENGTH = 8;
    private final Integer MAXIMUM_PASSWORD_LENGTH = 50;

    public CustomerService(){serviceCustomer = new CustomerDAO(); serviceAccount = new AccountDAO(); encryptService = new EncryptService();}

    public boolean insertCustomer(Customer customer){
        if(customer.getFirstName().length() >= MINIMUM_NAME_LENGTH &&
                customer.getLastName().length() >= MINIMUM_NAME_LENGTH &&
                customer.getUserName().length() >= MINIMUM_NAME_LENGTH &&
                customer.getPass().length() >= MINIMUM_PASSWORD_LENGTH &&
                customer.getPass().length() <= MAXIMUM_PASSWORD_LENGTH &&
                customer.getFirstName().length() <= MAXIMUM_NAME_LENGTH &&
                customer.getLastName().length() <= MAXIMUM_NAME_LENGTH &&
                customer.getUserName().length() <= MAXIMUM_NAME_LENGTH ) {
            return serviceCustomer.insert(customer);
        }else{
            return false;
        }
}

    public boolean updateCustomer(Customer customer) throws NoSuchAlgorithmException {
        if(customer.getFirstName().length() >= MINIMUM_NAME_LENGTH &&
                customer.getLastName().length() >= MINIMUM_NAME_LENGTH &&
                customer.getUserName().length() >= MINIMUM_NAME_LENGTH &&
                customer.getPass().length() >= MINIMUM_PASSWORD_LENGTH &&
                customer.getPass().length() <= MAXIMUM_PASSWORD_LENGTH &&
                customer.getFirstName().length() <= MAXIMUM_NAME_LENGTH &&
                customer.getLastName().length() <= MAXIMUM_NAME_LENGTH &&
                customer.getUserName().length() <= MAXIMUM_NAME_LENGTH &&
                customer.getCreditScore() >= ZERO &&
                customer.getCreditScore() <= MAXIMUM_CREDIT_SCORE &&
                customer.getCustomerAge() >= MINIMUM_AGE && customer.getCustomerAge() <= MAXIMUM_AGE) {
            customer.setPass(encryptService.encrypt(customer.getPass()));
            return serviceCustomer.update(customer);
        }else
            return false;
    }

    public boolean updateMemberStatus(Customer customer) throws NoSuchAlgorithmException {
        if(customer.getCustomerAge() >= MINIMUM_AGE && customer.getCustomerAge() <= MAXIMUM_AGE &&
                (customer.getCreditScore() >= MINIMUM_CREDIT_SCORE || customer.getCreditScore() == ZERO) &&
                customer.getCreditScore() <= MAXIMUM_CREDIT_SCORE && customer.getIdentification() && !customer.getMemberStatus()) {
            customer.setMemberStatus(true);
            System.out.println(customer.getLastName());
            Account account = new Account(0,"checking", 0 , customer.getCustomerID(), customer);
            serviceAccount.insert(account, customer);
            return serviceCustomer.updateMemberStatus(customer);
        }else{
            customer.setMemberStatus(false);
            return false;
        }
    }

    public boolean deleteCustomer(Customer customer) {
        return serviceCustomer.delete(customer);
    }

    public Customer selectSingleCustomer(Integer id) {return serviceCustomer.selectSingleCustomer(id);}

    public List<Customer> selectAllCustomers() {return serviceCustomer.selectAllCustomers();}


}
