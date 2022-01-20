package Service;

import Dao.UserDAO;
import models.Users;

import java.util.List;

//create an instance of the doa here and perform actions
public class UserService {

    private final UserDAO serviceUser;

    public UserService(){serviceUser = new UserDAO();}

    public boolean insertUser(Users user){
        return serviceUser.insert(user);
    }

    public boolean updateUser(Users user){return serviceUser.update(user);}

    public boolean deleteUser(Users user) {
        return serviceUser.delete(user);
    }

    public Users selectSingleUser(int id) {return serviceUser.selectSingleUser(id);}

    public List<Users> selectAllUsers() {return serviceUser.selectAllUser();}

    public Users selectByUsername(String username){return serviceUser.selectUserByUsername(username);}

}
