package com.slapapp.slambook;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class WriteForm extends AppCompatActivity {
    SQLClass db;
    SQLiteDatabase slamfriends;
    long setvalues;

    int nowyear, nowmonth, nowdate;             //CURRENT DATE VARIABLES
    int getyear, getmonth, getdate;
    DatePickerDialog datepickerdialog;

    boolean cancel = false;
    boolean delete = false;

    EditText name, nick_name, mobile, email, hobbies, color, movies, sports, place, about_our_friendship, opinion_on_me;
    EditText quote, best_friend, lucky_number, goal;
    TextView  birthday;
    Button submit;

    private  String emailFor;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collect_details);
        emailFor = getIntent().getStringExtra("emailFor");

        db = new SQLClass(this, "slamfriends.db", null, 1);
        slamfriends = db.getReadableDatabase();

        //DECLARING VARIBALES AND SETTING HINTS
        (name = (EditText) findViewById(R.id.name1)).setHint("your name !");
        (nick_name = (EditText) findViewById(R.id.nick_name)).setHint("your nick name !");
        (mobile = (EditText) findViewById(R.id.mobile)).setHint("mobile number !");
        (email = (EditText) findViewById(R.id.email)).setHint("your email id !");
        (birthday = (TextView) findViewById(R.id.birthday)).setHint("when did u born !");
        final Calendar calendar = Calendar.getInstance();
        nowyear = calendar.get(Calendar.YEAR);
        nowmonth = calendar.get(Calendar.MONTH);
        nowdate = calendar.get(Calendar.DAY_OF_MONTH);

        //DATE SELECTION FUNCTION:-
        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepickerdialog = new DatePickerDialog(WriteForm.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                getyear = year;
                                getmonth = month;
                                getdate = dayOfMonth;
                                birthday.setText(getmonth + "/" + getdate + "/" + getyear);
                            }
                        }, nowyear, nowmonth, nowdate);
                datepickerdialog.show();
            }
        });
        (hobbies = (EditText) findViewById(R.id.hobbies)).setHint("and your hobbies !");
        (color = (EditText) findViewById(R.id.color)).setHint("favorite color !");
        (movies = (EditText) findViewById(R.id.movies)).setHint("movies !");
        (sports = (EditText) findViewById(R.id.sports)).setHint("about sports !");
        (place = (EditText) findViewById(R.id.place)).setHint("places u like !");
        (about_our_friendship = (EditText) findViewById(R.id.about_our_friendship)).setHint("about our relationship !");
        (opinion_on_me = (EditText) findViewById(R.id.opinion_on_me)).setHint("opinion on meee !");
        (quote = (EditText) findViewById(R.id.quote)).setHint("your fav quote !");
        (best_friend = (EditText) findViewById(R.id.best_friend)).setHint("who is ur bestee !");
        (lucky_number = (EditText) findViewById(R.id.lucky_number)).setHint("lucky number !");
        (goal = (EditText) findViewById(R.id.goal)).setHint("u r goal !");

        View focusView = null;

        String getname = name.getText().toString();
        String getnick_name = nick_name.getText().toString();

        //ERROE SETTING:-
        if (TextUtils.isEmpty(getname)) {
            name.setError("All my frineds have names!");
            focusView = name;
            cancel = true;
        } else if (TextUtils.isEmpty(getnick_name)) {
            nick_name.setError("dont be shy, say ur nickname!");
            focusView = nick_name;
            cancel = true;
        } else
            cancel = false;


        submit = (Button) findViewById(R.id.submit);
        //ENTERING VALUES INTO DATABASE TABLE
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues values = new ContentValues();
                values.put("name", String.valueOf(name.getText()));
                values.put("nick_name", String.valueOf(nick_name.getText()));
                values.put("mobile", String.valueOf(mobile.getText()));
                values.put("email", String.valueOf(email.getText()));
                values.put("birthday", String.valueOf(birthday.getText()));
                values.put("hobbies", String.valueOf(hobbies.getText()));
                values.put("color", String.valueOf(color.getText()));
                values.put("movies", String.valueOf(movies.getText()));
                values.put("sports", String.valueOf(sports.getText()));
                values.put("place", String.valueOf(place.getText()));
                values.put("about_our_friendship", String.valueOf(about_our_friendship.getText()));
                values.put("opinion_on_me", String.valueOf(opinion_on_me.getText()));
                values.put("quote", String.valueOf(quote.getText()));
                values.put("best_friend", String.valueOf(best_friend.getText()));
                values.put("lucky_number", String.valueOf(lucky_number.getText()));
                values.put("goal", String.valueOf(goal.getText()));

                setvalues = slamfriends.insert("slamfriends", null, values);
                if (setvalues > 0)
                    Toast.makeText(WriteForm.this, String.valueOf(setvalues), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(WriteForm.this, "data not inserted", Toast.LENGTH_SHORT).show();

                //GOING BACK TO HOME
                Intent intent = new Intent(WriteForm.this, UserHome.class);
                startActivity(intent);


            }
        });



    }

}


