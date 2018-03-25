package com.govind.authentication;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by govind on 23/3/18.
 */

class SampleData {
    public static List<JournalEntry> getSampleJournalEntries() {

        List<JournalEntry> journalEntries = new ArrayList<>();
        //create the dummy journal
        JournalEntry journalEntry1 = new JournalEntry();
        journalEntry1.setTitle("DisneyLand Trip");
        journalEntry1.setContent
                ("We went to Disneyland today and the kids had lots of fun!");
        Calendar calendar1 = GregorianCalendar.getInstance();
        journalEntry1.setDateModified(calendar1.getTimeInMillis());
        journalEntries.add(journalEntry1);

        return journalEntries;
    }


}
