package com.jmorenov.tweetscweb;

public class TweetModel {
    private long id;
    private String content;
    private String correctedContent;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCorrectedContent() {
        return correctedContent;
    }

    public void setCorrectedContent(String correctedContent) {
        this.correctedContent = correctedContent;
    }
}
