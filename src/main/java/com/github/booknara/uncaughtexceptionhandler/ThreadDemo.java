package com.github.booknara.uncaughtexceptionhandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Created by Daehee Han(@daniel_booknara) on 3/11/16.
 */
public class ThreadDemo {
    final static Logger logger = LogManager.getLogger(ThreadDemo.class);

    public static void main(String[] args) {
        reportCrash();

        hookShutdown();
        logger.trace("Trying to shut down...");
        System.exit(0);
    }

    private static void hookShutdown() {
        attachShutDownHook();
    }

    private static void reportCrash() {
        // Global thread handler
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());

        Thread t = new Thread(new adminThread());
        t.start();
    }

    private static class adminThread implements Runnable {

        public void run() {
            throw new RuntimeException();
        }
    }

    public static void attachShutDownHook(){
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                logger.fatal("Shutdown hooking code");
            }
        });

        logger.trace("Finished attaching shutdown hooking.");
    }
}

