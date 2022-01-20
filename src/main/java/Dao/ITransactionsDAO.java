package Dao;

import models.Customer;
import models.Transactions;

public interface ITransactionsDAO {

    boolean insertWithdrawDeposit(Transactions a);

    boolean insertTransfer(Transactions a);

    boolean update(Transactions a);

    boolean delete(Transactions a);

}
