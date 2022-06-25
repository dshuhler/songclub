package com.shuhler.negadelphia.domain.ingest;



public class NegaQuery {

    public static final String EAGLES_CONTEXT = "context:12.689566314990436352";
    public static final String EXCLUDE_RETWEETS = "-is:retweet";
    public static final String EAGLES_NO_RETWEETS = EAGLES_CONTEXT + " " + EXCLUDE_RETWEETS;


    private String outputFolderName;

    private String query;

    public NegaQuery(String outputFolderName) {
        this.outputFolderName = outputFolderName;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getOutputFolderName() {
        return outputFolderName;
    }

    public String getQuery() {
        return query;
    }
}
