package com.govind.authentication;

/**
 * Created by govind on 23/3/18.
 */

public class Tag {
    private String tagId;
    private String tagName;
    private int journalCount;


    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public void setJournalCount(int journalCount) {
        this.journalCount = journalCount;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagId() {
        return tagId;
    }
}
