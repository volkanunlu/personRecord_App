package com.volkanunlu.softtech_task.model;


import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class UserResponseModel implements Serializable {

 //sadece retrofit kullanımı
    @SerializedName("TextInputName")  //retrofit ile çekilecek verinin adı.
    public String userName;

    @SerializedName("TextInputSurname")
    public String userSurname;

    @SerializedName("TextInputMail")
    public String email;

    @SerializedName("TextInputAge")
    public String age;

    @SerializedName("Combobox")
    public List<String> cities;

    @SerializedName("Header")
    public String header;



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
    public String getHeader(){
        return header;
    }
    public List<String> getCities(){
        return cities;

    }


}
