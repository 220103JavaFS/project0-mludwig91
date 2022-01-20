package Service;

import Dao.UserDAO;
import models.Encryptor;

import java.security.NoSuchAlgorithmException;

public class EncryptService {

    private final Encryptor encryptor;

    public EncryptService(){encryptor = new Encryptor();}

    //Encrypt user password
    public String encrypt(String password) throws NoSuchAlgorithmException {return encryptor.encoder(password);}
}
