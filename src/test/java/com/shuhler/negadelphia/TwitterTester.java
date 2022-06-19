package com.shuhler.negadelphia;

import com.shuhler.negadelphia.domain.TwitterManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.shuhler.negadelphia.domain.twitter.SearchConstants.EAGLES_NO_RETWEETS;


/**
 * Intended for expirimenting without bringing up the application, not actual testing
 */
@SpringBootTest
@ActiveProfiles("local")
public class TwitterTester {

    @Autowired
    private TwitterManager twitterManager;

    @Test
    void runTest() {
        twitterManager.pollingTwitterSearch(EAGLES_NO_RETWEETS);
    }


}
