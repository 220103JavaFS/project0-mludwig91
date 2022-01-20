package com.revature;

import Controller.Controller;
import Controller.UserController;
import Controller.CustomerController;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {

    //Import logger
    private static Logger mylog = LoggerFactory.getLogger(Server.class);
    private static Javalin app;


    public static void main(String[] args) {

        //Create Http server
        app = Javalin.create();
        configure(new UserController(),new CustomerController());
        app.start(6000);
    }

    private static void configure(Controller... controllers){
        for(Controller c: controllers){
            c.addRoutes(app);
        }
    }
}

