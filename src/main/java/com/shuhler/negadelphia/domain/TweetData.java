package com.shuhler.negadelphia.domain;

import com.twitter.clientlib.model.Tweet;

public class TweetData {

    private String id;
    private String text;


    public TweetData(Tweet tweet) {

        id = tweet.getId();
        text = tweet.getText();
    }

    public TweetData() {

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
