package com.uet.towerdefense.worker.base;

public interface BaseWorker {

    void start(String workerName) throws Exception;

    void stop() throws Exception;

    void onStarted() throws Exception;

    void onStopped() throws Exception;
}
