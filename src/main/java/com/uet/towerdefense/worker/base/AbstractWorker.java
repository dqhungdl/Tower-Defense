package com.uet.towerdefense.worker.base;

import com.uet.towerdefense.common.util.LoggingUtil;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

public abstract class AbstractWorker implements BaseWorker {
    private static Logger logger = LoggingUtil.createLogger(AbstractWorker.class);

    private String workerName;

    @Override
    public void start(String workerName) throws Exception {
        this.workerName = workerName;
        logger.info("START:WORKER:" + workerName);
        onStarted();
    }

    @Override
    public void stop() throws Exception {
        logger.info("STOP:WORKER:" + workerName);
        onStopped();
    }

    @Override
    public abstract void onStarted();

    @Override
    public abstract void onStopped();
}
