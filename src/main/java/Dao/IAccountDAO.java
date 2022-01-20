package Dao;

import models.Account;
import models.Customer;

public interface IAccountDAO {

    boolean insert(Account a, Customer b);

    boolean update(Account a);

    boolean updateAccountTotal(Account a);

    boolean delete(Account a);

}
