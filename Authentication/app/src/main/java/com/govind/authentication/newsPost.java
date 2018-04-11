package com.govind.authentication;

/**
 * Created by govind on 30/3/18.
 */

public class newsPost {
    public String body;
    public String title;
    public newsPost()
    {

    }

    public newsPost(String body, String title) {
        this.body = body;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String body() {
        return body;
    }
}
