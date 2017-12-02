package com.slapapp.slambook;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by jagad on 5/5/2017.
 */

public class FriendList extends AppCompatActivity {

    ListView myfriendslist;
    SQLClass db;
    SQLiteDatabase slamfriends;
    Cursor cursor;
    ArrayList<String> frindslist;
    String[] frindlistarray;
    TextView listheading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_list);

        myfriendslist = (ListView) findViewById(R.id.friendlist);
        listheading = (TextView)findViewById(R.id.listheading);

        db = new SQLClass(this, "slamfriends.db", null, 1);
        slamfriends = db.getWritableDatabase();

        //CURSOR TO GET FRIEND DETAILS:-
        cursor = slamfriends.rawQuery("select name from slamfriends", null);
        if (cursor.getCount() != 0)
        {
            cursor.moveToFirst();
        frindslist = new ArrayList<>();
        do {
            frindslist.add(cursor.getString(0));
        } while (cursor.moveToNext());
        frindlistarray = new String[frindslist.size()];
        frindslist.toArray(frindlistarray);

        //SENDING FRIEND DETAILS TO LISTVIEW:-
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, frindlistarray);
        myfriendslist.setAdapter(itemsAdapter);
        myfriendslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(FriendList.this, ReadData.class);
                intent.putExtra("friendname", (frindlistarray[position]));
                startActivity(intent);
            }
        });

    }
    else
        {
            listheading.setText("opps!, you still do not have any friends");
        }

    }
}
