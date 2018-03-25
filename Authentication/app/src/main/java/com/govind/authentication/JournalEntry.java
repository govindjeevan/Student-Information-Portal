package com.govind.authentication;

/**
 * Created by govind on 23/3/18.
 */

public class JournalEntry {
    private String journalId;
    private String title;
    private String content;
    private long dateCreated;
    private long dateModified;
    private String tagId;
    private String tagName;

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDateModified(long dateModified) {
        this.dateModified = dateModified;
    }

    public void setJournalId(String journalId) {
        this.journalId = journalId;
    }
}