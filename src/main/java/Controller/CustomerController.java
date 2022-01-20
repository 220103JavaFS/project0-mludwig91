package Controller;

import Service.*;
import Service.TransactionsService;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import models.*;

public class CustomerController extends Controller{

    private final EncryptService encryptor = new EncryptService();
    private final CustomerService customerService = new CustomerService();
    private final AccountService accountService = new AccountService();
    private final TransactionsService transactionsService = new TransactionsService();
    LoginService loginService = new LoginService();


    private Handler customerLogin = (ctx) ->{
        Customer customer = ctx.bodyAsClass(Customer.class);
        if(loginService.loginUser(customer.getUserName(), customer.getPass(), "Customer")){
            //Creates stable http session object for user if none exists
            ctx.req.getSession();
            ctx.status(200);
            ctx.json("Customer Login Successful!!!");
        }else{
            //invalidates an open session that is attatched to the client
            ctx.req.getSession().invalidate();
            ctx.status(401);
        }
    };

    //Customer Registration path
    private final Handler registerCustomer = (ctx) -> {
        Customer newCustomer = ctx.bodyAsClass(Customer.class);
        newCustomer.setPass(encryptor.encrypt(newCustomer.getPass()));
        System.out.println(newCustomer.getPass());
        if(customerService.insertCustomer(newCustomer)) {
            ctx.status(201);
            ctx.json("Customer registration successful!!! Account is now awaiting approval!!");
        }else {
            ctx.json("Customer registration FAILED!!");

            ctx.status(400);
        }
    };

    private final Handler accountApply = (ctx) -> {
        if(ctx.req.getSession(false)!= null) {
            String acctID = ctx.pathParam("acctID");
            int id = Integer.parseInt(acctID);
            Customer updateCus = ctx.bodyAsClass(Customer.class);
            updateCus.setCustomerID(id);
            if(customerService.updateCustomer(updateCus))
                ctx.json("Application successfully submitted!!");
                ctx.json(updateCus);
                ctx.status(200);
        }else{
                ctx.json("Application submission failed!!!");
                ctx.status(400);
        }
    };

    private final Handler myAccount = (ctx) -> {
        if(ctx.req.getSession(false)!= null) {
            String acctID = ctx.pathParam("acctID");
            int id = Integer.parseInt(acctID);
            Customer thiscus = ctx.bodyAsClass(Customer.class);
            Account acct = accountService.selectSingleAccount(id);
            if(acct.getOwner().equals(thiscus)){
                ctx.json(acct);
                ctx.status(200);
            }else{
                ctx.json("You do not own this account!!");
                ctx.status(400);
            }
        }else{
            ctx.json("Account Does not exist for this customer!!");
            ctx.status(400);
        }
    };

    //Customer withdraw path
    private final Handler withdraw = (ctx) -> {
        if(ctx.req.getSession(false)!= null) {
            Transactions trans = ctx.bodyAsClass(Transactions.class);
            if(transactionsService.insertWithdrawDepositTransactions(trans)){
                Account acct = accountService.selectSingleAccount(trans.getAcctID());
                ctx.status(200);
                ctx.json("Withdraw successful!!");
                ctx.json(acct);
            }else{
                ctx.status(400);
                ctx.json("Withdraw failed!!");
            }
        }else{
            ctx.json("UNAUTHORIZED!!!");
            ctx.status(401);
        }
    };


    //Customer deposit path
    private final Handler deposit = (ctx) -> {
        if(ctx.req.getSession(false)!= null) {
            Transactions trans = ctx.bodyAsClass(Transactions.class);
            if(transactionsService.insertWithdrawDepositTransactions(trans)){
                Account acct = accountService.selectSingleAccount(trans.getAcctID());
                ctx.status(200);
                ctx.json("Deposit successful!!");
                ctx.json(trans);
                ctx.json(acct);
            }else{
                ctx.status(400);
                ctx.json("Deposit failed!!");
            }
        }else{
            ctx.json("UNAUTHORIZED!!!");
            ctx.status(401);
        }
    };

    //Customer transfer path
    private final Handler transfer = (ctx) -> {
        if(ctx.req.getSession(false)!= null){
            Transactions trans = ctx.bodyAsClass(Transactions.class);
            if(transactionsService.insertTransferTransactions(trans)){
                ctx.status(200);
                ctx.json("Transfer successful!!");
                ctx.json(trans);
            }else{
                ctx.status(400);
                ctx.json("Transfer failed!!");
            }
        }else{
            ctx.json("Unauthorized!!!");
            ctx.status(400);
        }
    };

    @Override
    public void addRoutes(Javalin app) {
        app.post("/customers/apply/{acctID}",accountApply);
        app.get("/customers/{acctID}", myAccount);
        app.post("/customers/login", customerLogin);
        app.post("/customers/deposit", deposit);
        app.post("/customers/withdraw", withdraw);
        app.post("/customers/register", registerCustomer);
        app.post("/customers/transfer", transfer);
    }
}
