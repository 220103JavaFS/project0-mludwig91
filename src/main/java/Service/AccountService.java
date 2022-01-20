package Service;

import Dao.AccountDAO;
import models.Account;
import models.Customer;

import java.math.BigDecimal;
import java.util.List;

public class AccountService {

    private final AccountDAO accountDAO;
    private final Integer MINIMUM_ACCT_BALANCE = 0;

    public AccountService(){accountDAO = new AccountDAO();}

    public boolean insertAccount(Account a, Customer b){return accountDAO.insert(a,b);}

    public boolean updateAccount(Account a){return accountDAO.update(a);}

    public boolean deleteAccount(Account a){return accountDAO.delete(a);}

    public boolean updateAccountTotal(Account a, String transaction, float amount){
        if(amount > 0) {
            if (transaction.equals("withdraw") && a.getAcctTotal() - amount >= MINIMUM_ACCT_BALANCE) {
                a.setAcctTotal(a.getAcctTotal() - amount);
                return accountDAO.updateAccountTotal(a);
            }
            if (transaction.equals("deposit") && a.getAcctTotal() + amount >= MINIMUM_ACCT_BALANCE) {
                a.setAcctTotal(a.getAcctTotal() + amount);
                return accountDAO.updateAccountTotal(a);
            }
        }
        return false;
    }

    public Account selectSingleAccount(Integer id){return accountDAO.selectSingleAccount(id);}

    public List<Account> selectAllAccounts(){return accountDAO.selectAllAccount();}

    public Account selectCustomerAccounts(Integer id){return accountDAO.selectCustomersAccounts(id);}

}
