package com.govind.authentication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by govind on 28/3/18.
 */

public class CustomListAdapter extends ArrayAdapter {
    //to reference the Activity
    private final Activity context;

    private final String[] newsArray;

    public CustomListAdapter(Activity context, String[] newsArrayParam){

        super(context,R.layout.listview_row , newsArrayParam);

        this.context=context;
        this.newsArray = newsArrayParam;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview_row, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView nameTextField = (TextView) rowView.findViewById(R.id.nametextViewID);

        //this code sets the values of the objects to values from the arrays
        nameTextField.setText(newsArray[position]);
        return rowView;

    };
}
