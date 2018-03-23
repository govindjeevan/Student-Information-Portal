package com.govind.authentication;

/**
 * Created by govind on 20/3/18.
 */

public class Question {

    public String question;
    public String opt1;
    public String opt2;
    public int count1;
    public int count2;
    public Question() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Question(String question, String opt1, String opt2) {
        this.question = question;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.count1=0;
        this.count2=0;

    }

}