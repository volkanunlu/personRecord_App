package com.volkanunlu.softtech_task.roomdb;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.volkanunlu.softtech_task.model.UserEntityModel;
import com.volkanunlu.softtech_task.model.UserResponseModel;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface UserDao {

    @Query("SELECT * FROM UserEntityModel")
    Flowable<List<UserEntityModel>> getAll(); //Bana bir list döndürecek

    @Insert //Ekleme işlemi
    Completable insert(UserEntityModel user);

    @Delete //Silme işlemi daha kullanmadım kullanılacak sonrasında
    Completable delete (UserEntityModel user);





}
