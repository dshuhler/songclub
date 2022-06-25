package com.shuhler.negadelphia.domain.ingest.api;

import com.shuhler.negadelphia.domain.ingest.TweetData;
import com.twitter.clientlib.model.TweetSearchResponse;
import com.twitter.clientlib.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class ResponseExtractor {

    private Logger logger = LoggerFactory.getLogger(ResponseExtractor.class);


    private TweetSearchResponse tsr;

    public ResponseExtractor(TweetSearchResponse tsr) {
        this.tsr = tsr;
    }

    public List<TweetData> asTweetData() {

        List<TweetData> extractedTweetDatas = new ArrayList<>();

        if (tsr.getData() == null) {
            return extractedTweetDatas;
        }

        for (int n = 0; n < tsr.getData().size(); n++) {
            TweetData tweetData = new TweetData(tsr.getData().get(n));

            User authorUser = getUser(tweetData.getAuthorId());

            tweetData.setUsername(authorUser.getUsername());

            extractedTweetDatas.add(tweetData);
        }

        return extractedTweetDatas;
    }

    private User getUser(String userId) {

        if (tsr.getIncludes() == null || CollectionUtils.isEmpty(tsr.getIncludes().getUsers())) {
            logger.warn("Could not find user expansion information in twitter response");
            return null;
        }

        List<User> users = tsr.getIncludes().getUsers();

        return users.stream().filter(u -> u.getId().equals(userId)).findFirst().orElse(null);
    }

}
