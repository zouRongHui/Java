package org.rone.study.java.third.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * logback的服务组件
 */
public class LogBackService {
    private static Logger logger = LoggerFactory.getLogger(LogBackService.class);

    public void test() {
        logger.debug("logback的debug级别日志");
        logger.info("logback的info级别日志");
        logger.warn("logback的warn级别日志");
        logger.error("logback的error级别日志");
    }
}
