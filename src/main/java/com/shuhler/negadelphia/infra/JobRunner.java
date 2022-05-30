package com.shuhler.negadelphia.infra;

import com.shuhler.negadelphia.web.MainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class JobRunner {

    Logger logger = LoggerFactory.getLogger(JobRunner.class);


    @Async
    public void doTestRun() {

        while (true) {
            logger.warn("Doing a test run!" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
