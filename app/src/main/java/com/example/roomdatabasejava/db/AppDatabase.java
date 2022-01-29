package com.example.roomdatabasejava.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//asigning this as database and user as table of database
@Database(entities = {User.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    private static AppDatabase iNSTANCE;

    public  static AppDatabase getiNSTANCE(Context context){
        if(iNSTANCE==null){
            iNSTANCE= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"DB_NAME")
                    .allowMainThreadQueries()
                    .build();

        }
        return iNSTANCE;
    }

}
