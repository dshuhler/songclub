package com.shuhler.negadelphia.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.shuhler.negadelphia.domain.twitter.TweetCohort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static com.fasterxml.jackson.dataformat.yaml.YAMLGenerator.Feature.INDENT_ARRAYS;
import static com.fasterxml.jackson.dataformat.yaml.YAMLGenerator.Feature.SPLIT_LINES;

@Component
public class TweetCohortRepo {

    private Logger logger = LoggerFactory.getLogger(TweetCohortRepo.class);
    private ObjectMapper mapper;
    private DateTimeFormatter sortableMinuteFormatter;

    private final String outputPath = "src/test/resources/testoutput/";

    @PostConstruct
    private void init() {
        YAMLFactory yamlFactory = YAMLFactory.builder()
                .disable(SPLIT_LINES)
                .enable(INDENT_ARRAYS)
                .build();

        mapper = new ObjectMapper(yamlFactory);
        mapper.findAndRegisterModules();

        sortableMinuteFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss")
                .withZone(ZoneId.systemDefault());
    }


    public void saveToYaml(TweetCohort tweetCohort) {

        String fileName = sortableMinuteFormatter.format(tweetCohort.getTimeStamp()) + ".yaml";
        String path = "src/test/resources/testoutput/" + fileName;

        try {
            mapper.writeValue(new File(path), tweetCohort);
            logger.info("Wrote {} tweets to {}", tweetCohort.numTweets(), path);
        } catch (IOException e) {
            logger.error("Error writing to YAML file");
            throw new RuntimeException(e);
        }

    }

    public TweetCohort loadFromYaml(String path) {

        File yamlFile = new File(path);

        try {
            TweetCohort readCohort = mapper.readValue(yamlFile, TweetCohort.class);
            return readCohort;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }




}
