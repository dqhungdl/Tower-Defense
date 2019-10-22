package com.uet.towerdefense;

import com.uet.towerdefense.common.enums.Workers;
import com.uet.towerdefense.common.util.CompareUtil;
import com.uet.towerdefense.common.util.LoggingUtil;
import com.uet.towerdefense.worker.base.BaseWorker;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EntityScan("com.uet.towerdefense.common")
@EnableMongoRepositories("com.uet.towerdefense.worker.repository")
public class TowerDefenseApplication implements CommandLineRunner {
    private static Logger logger = LoggingUtil.createLogger(TowerDefenseApplication.class);

    @Autowired
    private BaseWorker worker;

    public static void main(String[] args) {
        new SpringApplicationBuilder(TowerDefenseApplication.class)
                .headless(false)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            worker.start(Workers.TOWER_DEFENSE);
        } catch (Exception e) {
            logger.error("ERROR:START:WORKER:" + e.getMessage());
            try {
                if (!CompareUtil.isEqualsNull(worker)) {
                    worker.stop();
                }
            } catch (Exception ignored) {

            }
        }
    }
}
