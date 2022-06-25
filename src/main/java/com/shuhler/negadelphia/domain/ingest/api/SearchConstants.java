package com.shuhler.negadelphia.domain.ingest.api;

public class SearchConstants {

    public static final String EAGLES_CONTEXT = "context:12.689566314990436352";
    public static final String EXCLUDE_RETWEETS = "-is:retweet";
    public static final String EAGLES_NO_RETWEETS = EAGLES_CONTEXT + " " + EXCLUDE_RETWEETS;
}
