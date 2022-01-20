package Controller;

import Service.*;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import models.*;

import java.util.List;

public class UserController extends Controller{
    LoginService loginService = new LoginService();
    private final UserService userService = new UserService();
    private final EncryptService encryptor = new EncryptService();
    private final CustomerService customerService = new CustomerService();
    private final AccountService accountService = new AccountService();
    private final TransactionsService transactionsService = new TransactionsService();


    private final Handler userLogin = (ctx) ->{
        Users user = ctx.bodyAsClass(Users.class);
        if(loginService.loginUser(user.getUsername(), user.getPassword(), "Users")){
            //Creates stable http session object for user if none exists
            ctx.req.getSession();
            ctx.json("Login Successful!!!");
            ctx.status(200);
        }else{
            //invalidates an open session that is attatched to the client
            ctx.req.getSession().invalidate();
            ctx.status(401);
        }
    };

    private final Handler registerUser = (ctx) -> {
        Users newUser = ctx.bodyAsClass(Users.class);
        newUser.setPassword(encryptor.encrypt(newUser.getPassword()));
        if(userService.insertUser(newUser)){
            ctx.json("User registered successfully!!!!");
            ctx.json(newUser);
            ctx.status(201);
        }else{
            ctx.status(400);
        }
    };



    //Path for employees and admins to view all customer accounts
    private final Handler viewAllAccounts = (ctx) -> {
        if(ctx.req.getSession(false)!= null) {
            List<Account> acctList = accountService.selectAllAccounts();
                ctx.json(acctList);
                ctx.status(200);
        }else{
            ctx.json("UNAUTHORIZED ERROR");
            ctx.status(401);
        }
    };

    //Path for employees and admins to view customer accounts
    private final Handler viewAccount = (ctx) -> {
        if(ctx.req.getSession(false)!= null) {
            String id = ctx.pathParam("acctID");
            int acctID = Integer.parseInt(id);
            Account acct = accountService.selectSingleAccount(acctID);
            Customer cus = acct.getOwner();
            ctx.json(cus);
            ctx.json(acct);
            ctx.status(201);
        }else{
            ctx.json("UNAUTHORIZED ERROR");
            ctx.status(401);
        }
    };

    //Path for Admins only to approve customer accounts
    private final Handler approveCustomer = (ctx) -> {
        if (ctx.req.getSession(false) != null) {
            String id = ctx.pathParam("custID");
            int custID = Integer.parseInt(id);
            Customer cus = customerService.selectSingleCustomer(custID);
            System.out.println(cus.getLastName());
            System.out.println(cus.getPass());
            System.out.println(cus.getEmail());
            if(!cus.getMemberStatus()) {
                if (customerService.updateMemberStatus(cus)) {
                    ctx.json(cus);
                    ctx.status(201);
                } else {
                    ctx.json("Customer does not meet account requirements.");
                }
            }else {
                ctx.json("This customer is already a member!!");
            }
            } else {
            ctx.json("UNAUTHORIZED ERROR");
            ctx.status(401);
            }

    };

    private final Handler withdrawAccount = (ctx) -> {
        if(ctx.req.getSession(false)!= null) {
            Transactions trans = ctx.bodyAsClass(Transactions.class);
            if(transactionsService.insertWithdrawDepositTransactions(trans)){
                Account acct = accountService.selectSingleAccount(trans.getAcctID());
                ctx.status(200);
                ctx.json("Withdraw successful!!");
                ctx.json(trans);
                ctx.json(acct);
            }else{
                ctx.status(400);
                ctx.json("Withdraw failed!!");
            }
        }else{
            ctx.json("Withdraw failure!!");
            ctx.status(400);
        }
    };

    private final Handler depositAccount = (ctx) -> {
        if(ctx.req.getSession(false)!= null) {
            Transactions trans = ctx.bodyAsClass(Transactions.class);
            if (transactionsService.insertWithdrawDepositTransactions(trans)) {
                Account acct = accountService.selectSingleAccount(trans.getAcctID());
                ctx.json("Deposit successful!!");
                ctx.json(trans);
                ctx.json(acct);
                ctx.status(200);
            } else {
                ctx.status(400);
                ctx.json("Deposit failed!!");
            }
        }
    };

    private final Handler transferAccount = (ctx) -> {
        if(ctx.req.getSession(false)!= null) {
            Transactions trans = ctx.bodyAsClass(Transactions.class);
            if (transactionsService.insertTransferTransactions(trans)) {
                Account acct1 = accountService.selectSingleAccount(trans.getAcctID());
                Account acct2 = accountService.selectSingleAccount(trans.getAcctID2());
                ctx.json("Transfer successful!!");
                ctx.json(trans);
                ctx.json(acct1);
                ctx.json(acct2);
                ctx.status(200);
            }else{
                ctx.json("Transfer failure!");
            }
        }else {
            ctx.status(401);
            ctx.json("UNAUTHORIZED!!");
        }

    };

    @Override
    public void addRoutes(Javalin app) {
        app.post("/users/login", userLogin);
        app.post("/users/register", registerUser);
        app.get("/users/accounts",viewAllAccounts);
        app.get("/users/accounts/{acctID}",viewAccount);
        app.post("/users/accounts/pending/{custID}", approveCustomer);
        app.post("/users/accounts/deposit/{custID}", depositAccount);
        app.post("/users/accounts/withdraw/{custID}", withdrawAccount);
        app.post("/users/accounts/transfer", transferAccount);
    }
}
