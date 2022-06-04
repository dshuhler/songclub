package com.shuhler.negadelphia.domain.model;

import com.twitter.clientlib.model.Tweet;

public class TweetData {

    private String id;
    private String text;
    private String username;

    public TweetData(Tweet tweet) {

        id = tweet.getId();
        text = tweet.getText();
    }

    public TweetData() {

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
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
