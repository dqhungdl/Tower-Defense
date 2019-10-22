package com.uet.towerdefense;

import com.uet.towerdefense.common.util.LoggingUtil;
import com.uet.towerdefense.worker.base.AbstractWorker;
import com.uet.towerdefense.worker.repository.GameFieldRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class TowerDefenseWorker extends AbstractWorker {
    private static Logger logger = LoggingUtil.createLogger(TowerDefenseWorker.class);

    @Autowired
    GameFieldRepository gameFieldRepository;

    @Override
    public void onStarted() {
        logger.info("START:WORKER:SUCCESSFUL");
        logger.info(gameFieldRepository.count() + "");
        JFrame frame = new JFrame("Monkeys vs Balloons");
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    @Override
    public void onStopped() {
        logger.info("STOP:WORKER:SUCCESSFUL");
    }
}
