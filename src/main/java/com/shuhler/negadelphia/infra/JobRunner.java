package com.shuhler.negadelphia.infra;

import com.shuhler.negadelphia.domain.TwitterManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class JobRunner {

    private Logger logger = LoggerFactory.getLogger(JobRunner.class);

    private boolean allStop;

    private TwitterManager twitterManager;

    @Autowired
    public JobRunner(TwitterManager twitterManager) {
        this.twitterManager = twitterManager;
    }

    @Async
    public void doTestRun() {
        allStop = false;

        while (!allStop) {
            logger.warn("Doing a test run!" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopAll() {
        twitterManager.pollForEaglesTweets();
        allStop = true;
    }
}
