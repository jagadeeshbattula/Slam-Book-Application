package com.slapapp.slambook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jagad on 5/4/2017.
 */

public class SQLClass extends SQLiteOpenHelper {
    int DB_VERSION = 1;
    String DB_NAME = "slamfriends.db";
    Context context;

    public SQLClass(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
        this.DB_NAME = name;
        this.DB_VERSION = version;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //TABLE slamfriends WITH 16 COLOMNS:-
        db.execSQL("CREATE TABLE slamfriends ("
                + "name TEXT NOT NULL, "
                + "nick_name TEXT NOT NULL, "
                + "mobile INTEGER NOT NULL, "
                + "email TEXT NOT NULL, "
                + "birthday INTEGER NOT NULL, "
                + "hobbies TEXT, "
                + "color TEXT, "
                + "movies TEXT, "
                + "sports TEXT, "
                + "place TEXT, "
                + "about_our_friendship TEXT, "
                + "opinion_on_me TEXT, "
                + "quote TEXT, "
                + "best_friend TEXT, "
                + "lucky_number INTEGER, "
                + "goal TEXT)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
