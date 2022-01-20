package Dao;

import models.Customer;

public interface ICustomerDAO {

    boolean insert(Customer a);

    boolean update(Customer a);

    boolean delete(Customer a);

    boolean updateMemberStatus(Customer a);

}
