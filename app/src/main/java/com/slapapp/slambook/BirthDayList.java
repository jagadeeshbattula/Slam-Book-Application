package com.slapapp.slambook;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class BirthDayList extends AppCompatActivity {

    SQLClass db;
    SQLiteDatabase slamfriends;
    Cursor cursor;
    ListView birthdayslist;

    TextView birthdayheading;

    ArrayList<String> birthdays, names;

    String[] birthdaysarray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.birthdays_list);
        birthdayslist = (ListView) findViewById(R.id.birthdaylist);
        birthdayheading = (TextView) findViewById(R.id.birthdayheading);

        db = new SQLClass(this, "slamfriends.db", null, 1);
        slamfriends = db.getWritableDatabase();

        //GETTING CURSOR FROM TABLE:-
        cursor = slamfriends.rawQuery("select name, birthday from slamfriends", null);

        if (cursor.getCount() != 0)
        {
            cursor.moveToFirst();


        birthdays = new ArrayList<>();

        do {
            birthdays.add(cursor.getString(0) + " :- " + cursor.getString(1));
        } while (cursor.moveToNext());
            //SETTING DATA TO LISTVIEW:-
        birthdaysarray = new String[birthdays.size()];
        birthdays.toArray(birthdaysarray);

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, birthdaysarray);
        birthdayslist.setAdapter(itemsAdapter);
    }
    else
            birthdayheading.setText("no one here to wish!");

    }
}
