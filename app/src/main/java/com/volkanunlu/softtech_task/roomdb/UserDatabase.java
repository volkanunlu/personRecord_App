package com.volkanunlu.softtech_task.roomdb;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.volkanunlu.softtech_task.model.UserEntityModel;
import com.volkanunlu.softtech_task.model.UserResponseModel;

@Database(entities = {UserEntityModel.class}, version = 1,exportSchema = false)

public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao userDao(); //abstract metot

}
