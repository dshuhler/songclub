package com.shuhler.negadelphia.domain;

import com.shuhler.negadelphia.domain.ingest.TweetCohort;
import com.shuhler.negadelphia.domain.ingest.TweetCohortRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("local")
class TweetCohortRepoIT {

    @Autowired
    private TweetCohortRepo tweetCohortRepo;

    @Test
    public void canLoadFromFile() {
        TweetCohort result = tweetCohortRepo.loadFromYaml("src/test/resources/simple-test.yml");

        assertEquals("myid", result.getId());
    }

}