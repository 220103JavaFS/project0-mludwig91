package Dao;

import models.Users;

public interface IUserDAO {

    boolean insert(Users a);

    boolean update(Users a);

    boolean delete(Users a);

}
