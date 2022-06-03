package com.shuhler.negadelphia;

import com.shuhler.negadelphia.domain.TweetData;
import com.shuhler.negadelphia.domain.TweetRepo;
import com.shuhler.negadelphia.domain.TwitterManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


/**
 * Intended for expirimenting without bringing up the application, not actual testing
 */
@SpringBootTest
@ActiveProfiles("local")
public class TwitterTester {

    @Autowired
    private TwitterManager twitterManager;

    @Autowired
    private TweetRepo tweetRepo;

    @Test
    void runTest() {
        twitterManager.test();
    }

    @Test
    void testYaml() {

        TweetData tweetData = new TweetData();
        tweetData.setId("ABC");
        tweetData.setText("Hello twitter world!");


        tweetRepo.saveTweetToFile(tweetData);
    }

}
