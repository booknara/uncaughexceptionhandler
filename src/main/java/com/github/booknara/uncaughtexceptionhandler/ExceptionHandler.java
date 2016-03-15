package com.github.booknara.uncaughtexceptionhandler;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by Daehee Han(@daniel_booknara) on 3/11/16.
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    private final static Logger logger = LogManager.getLogger(ExceptionHandler.class);

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        logger.fatal("This is fatal : ", e);
    }

    private String getStackTrace(Throwable throwable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        throwable.printStackTrace(printWriter);
        return result.toString();
    }
}
