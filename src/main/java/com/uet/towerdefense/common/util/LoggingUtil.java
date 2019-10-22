package com.uet.towerdefense.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingUtil {

    public static Logger createLogger(String classname) {
        Logger logger = LoggerFactory.getLogger(classname);
        return logger;
    }

    public static Logger createLogger(Class<?> clazz) {
        Logger logger = createLogger(clazz.getName());
        return logger;
    }

    private static String removeByPaterrn(String input, char patternChar) {
        StringBuilder strBuilder = new StringBuilder();
        if (!CompareUtil.isEqualsNullOrEmpty(input)) {
            for (int i = 0; i < input.length(); i++) {
                strBuilder.append(patternChar);
            }
        }
        return strBuilder.toString();
    }
}
