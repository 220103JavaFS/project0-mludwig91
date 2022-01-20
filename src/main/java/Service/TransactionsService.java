package Service;

import Dao.TransactionsDAO;
import models.Transactions;
import Dao.AccountDAO;
import models.Account;

public class TransactionsService {

    private final TransactionsDAO transactionsDAO;
    private final AccountDAO accountDAO;
    public TransactionsService(){transactionsDAO = new TransactionsDAO(); accountDAO = new AccountDAO();}

    public boolean insertWithdrawDepositTransactions(Transactions a){
        if(a.getAmount() > 0) {
            Account check = accountDAO.selectSingleAccount(a.getAcctID());
            //if withdraw make sure funds available otherwise just deposit
            if(a.getTransType().equals("withdraw")){
                    if(check.getAcctTotal() - a.getAmount() >= 0){
                        check.setAcctTotal(check.getAcctTotal() - a.getAmount());
                        boolean b = accountDAO.updateAccountTotal(check);
                        return transactionsDAO.insertWithdrawDeposit(a);
                    }else{
                        return false;
                    }
            }else{
                check.setAcctTotal(check.getAcctTotal() + a.getAmount());
                boolean b = accountDAO.updateAccountTotal(check);
                return transactionsDAO.insertWithdrawDeposit(a);
            }
        }else{
            System.out.println("Witdraw service layer failed");
            return false;
        }
    }
    public boolean insertTransferTransactions(Transactions a){
        if(a.getAmount() > 0) {

            //find transfer accounts
            Account fromcheck = accountDAO.selectSingleAccount(a.getAcctID());
            Account tocheck = accountDAO.selectSingleAccount(a.getAcctID2());

            //make sure funds available for withdraw otherwise just deposit
            if (a.getTransType().equals("transfer") && fromcheck.getAcctTotal() - a.getAmount() >= 0) {
                fromcheck.setAcctTotal(fromcheck.getAcctTotal() - a.getAmount());
                boolean update = accountDAO.updateAccountTotal(fromcheck);
                tocheck.setAcctTotal(tocheck.getAcctTotal() + a.getAmount());
                boolean update2 = accountDAO.updateAccountTotal(tocheck);
                if(update && update2)
                    return transactionsDAO.insertTransfer(a);
                else
                    return false;
            }
        }
        return false;
    }

    public boolean updateTransactions(Transactions a){return transactionsDAO.update(a);}

    public boolean deleteTransactions(Transactions a){return transactionsDAO.delete(a);}
}
