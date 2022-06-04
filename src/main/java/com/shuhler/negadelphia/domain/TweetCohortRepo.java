package com.shuhler.negadelphia.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.shuhler.negadelphia.domain.model.TweetCohort;
import com.twitter.clientlib.TwitterCredentialsOAuth2;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

import static com.fasterxml.jackson.dataformat.yaml.YAMLGenerator.Feature.*;

@Component
public class TweetCohortRepo {

    private ObjectMapper mapper;

    @PostConstruct
    private void init() {
        YAMLFactory yamlFactory = YAMLFactory.builder()
                .disable(SPLIT_LINES)
                .enable(INDENT_ARRAYS)
                .build();


        mapper = new ObjectMapper(yamlFactory);
        mapper.findAndRegisterModules();
    }


    public void saveToYaml(TweetCohort tweetCohort) {
        String fileName = tweetCohort.getId() + "_" + tweetCohort.getTimeStamp();
        String path = "src/test/resources/testoutput/" + fileName;

        try {
            mapper.writeValue(new File(path), tweetCohort);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }






}
