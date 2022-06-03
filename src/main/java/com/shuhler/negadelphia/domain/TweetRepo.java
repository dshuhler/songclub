package com.shuhler.negadelphia.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class TweetRepo {

    public void saveTweetToFile(TweetData tweetData) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();

        try {
            mapper.writeValue(new File("src/test/resources/testoutput/tweet_test.yaml"), tweetData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }






}
