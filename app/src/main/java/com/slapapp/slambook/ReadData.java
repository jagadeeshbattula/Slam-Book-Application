package com.slapapp.slambook;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;


public class ReadData extends AppCompatActivity {

    TextView name, nick_name, mobile, email, birthday, hobbies, color, movies, sports, place;
    TextView about_our_friendship, opinion_on_me, quote, best_friend, lucky_number, goal;

    String clickedname;

    SQLClass db;
    SQLiteDatabase slamfriends;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_slam);
        Toast.makeText(this, "hello read data", Toast.LENGTH_SHORT).show();

        db = new SQLClass(this, "slamfriends.db", null, 1);
        slamfriends  = db.getWritableDatabase();

        Bundle extras = getIntent().getExtras();
        if(extras!=null)
        {
            clickedname = extras.getString("friendname");
        }

        //SETTING TEXTVIEWS FROM LAYOUT:-
        name = (TextView)findViewById(R.id.name2);
        nick_name = (TextView)findViewById(R.id.nick_name2);
        mobile = (TextView)findViewById(R.id.mobile2);
        email = (TextView)findViewById(R.id.email2);
        birthday = (TextView)findViewById(R.id.birthday2);
        hobbies = (TextView)findViewById(R.id.hobbies2);
        color = (TextView)findViewById(R.id.color2);
        movies = (TextView)findViewById(R.id.movies2);
        sports = (TextView)findViewById(R.id.sports2);
        place = (TextView)findViewById(R.id.place2);
        about_our_friendship = (TextView)findViewById(R.id.about_our_friendship2);
        opinion_on_me = (TextView)findViewById(R.id.opinion_on_me2);
        quote = (TextView)findViewById(R.id.quote2);
        best_friend = (TextView)findViewById(R.id.best_friend2);
        lucky_number = (TextView)findViewById(R.id.lucky_number2);
        goal = (TextView)findViewById(R.id.goal2);

        //WRITING QUERY TO GET DETAILS:-
        cursor = slamfriends.query("slamfriends", null, "name=?", new  String[]{clickedname}, null, null, null);

        cursor.moveToFirst();

        name.setText(String.valueOf(cursor.getString(0)));
        nick_name.setText(String.valueOf(cursor.getString(1)));
        mobile.setText(String.valueOf(cursor.getString(2)));
        email.setText(String.valueOf(cursor.getString(3)));
        birthday.setText(String.valueOf(cursor.getString(4)));
        hobbies.setText(String.valueOf(cursor.getString(5)));
        color.setText(String.valueOf(cursor.getString(6)));
        movies.setText(String.valueOf(cursor.getString(7)));
        sports.setText(String.valueOf(cursor.getString(8)));
        place.setText(String.valueOf(cursor.getString(9)));
        about_our_friendship.setText(String.valueOf(cursor.getString(10)));
        opinion_on_me.setText(String.valueOf(cursor.getString(11)));
        quote.setText(String.valueOf(cursor.getString(12)));
        best_friend.setText(String.valueOf(cursor.getString(13)));
        lucky_number.setText(String.valueOf(cursor.getString(14)));
        goal.setText(String.valueOf(cursor.getString(15)));

    }
}
