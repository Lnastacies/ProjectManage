package com.adtec.tools;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * File: TestLog4j2
 *
 * @Author 王林柱
 * @Since 2017/12/29 9:46
 * @Ver 1.0
 */
public class TestLog4j2 {

    static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);


    public static void main(String[] args) {
        logger.trace("tarce message");
        logger.debug("debug message");
        logger.info("info message");
        logger.warn("warn message");
        logger.error("error message");
        logger.fatal("fatal message");
        System.out.println("Hello World");
    }
}
