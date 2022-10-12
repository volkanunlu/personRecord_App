package com.volkanunlu.softtech_task.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity //Room database kullanmak adına gerekli.
public class UserEntityModel implements Serializable {

    @PrimaryKey(autoGenerate = true)  //roomdb primary key
    public int id;

    @SerializedName("TextInputName")  //retrofit ile çekilecek verinin adı.
    @ColumnInfo(name = "userName")  //roomdb column adı.
    public String userName;

    @SerializedName("TextInputSurname")
    @ColumnInfo(name = "userSurname")
    public String userSurname;

    @SerializedName("TextInputMail")
    @ColumnInfo(name = "email")
    public String email;

    @SerializedName("TextInputAge")
    @ColumnInfo(name = "age")
    public String age;

    @ColumnInfo(name = "city")
    public String city;


    //Constructor yapımız
    public UserEntityModel(String userName, String userSurname, String email, String age, String city) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.email = email;
        this.age=age;
        this.city=city;
    }

    //Getter method area//

    public String getUserName(){
        return  userName;
    }
    public String getUserSurname(){
        return  userSurname;
    }
    public String getEmail(){
        return email;
    }
    public String getAge(){
        return age;
    }


}
