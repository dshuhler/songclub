package com.shuhler.negadelphia.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.shuhler.negadelphia.domain.model.TweetCohort;
import com.shuhler.negadelphia.domain.model.TweetData;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class TweetCohortRepo {

    public void saveTweetToFile(TweetCohort tweetCohort) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();

        try {
            mapper.writeValue(new File("src/test/resources/testoutput/tweet_test.yaml"), tweetCohort);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }






}
