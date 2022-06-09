package com.shuhler.negadelphia.domain.twitter;

import com.twitter.clientlib.model.Tweet;

public class TweetData {

    private String id;
    private String text;
    private String authorId;
    private String username;

    public TweetData(Tweet tweet) {

        id = tweet.getId();
        text = tweet.getText();
        authorId = tweet.getAuthorId();
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

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
