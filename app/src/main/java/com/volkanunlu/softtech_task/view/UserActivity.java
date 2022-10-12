package com.volkanunlu.softtech_task.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;
import com.volkanunlu.softtech_task.R;
import com.volkanunlu.softtech_task.databinding.ActivityUserBinding;
import com.volkanunlu.softtech_task.model.UserEntityModel;
import com.volkanunlu.softtech_task.model.UserResponseModel;
import com.volkanunlu.softtech_task.roomdb.UserDao;
import com.volkanunlu.softtech_task.roomdb.UserDatabase;
import com.volkanunlu.softtech_task.service.UserAPI;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserActivity extends AppCompatActivity {
    private ActivityUserBinding binding;  //data binding ile xml tarafındaki öğeleri erişiyoruz.
    private TextInputEditText userTextName,userTextSurname,userTextEmail,userTextAge;
    private Toolbar toolbarUser;
    private Spinner spinner;
    ArrayList<UserResponseModel> userResponseModels;
    private String selectedCity;
    UserDatabase db;
    UserDao userDao;
    private CompositeDisposable compositeDisposable=new CompositeDisposable(); //Kullan at öğesi yarattım
    private String BASE_URL="https://raw.githubusercontent.com/"; //verileri çekeceğim baseurl
    UserAPI userAPI;  //retrofitimi tanımladım.



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUserBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        //database build ettim,bulunduğum alan,çalışacak alan sınıfı , database ismi
        db=Room.databaseBuilder(getApplicationContext(),UserDatabase.class,"Users").build();
        userDao=db.userDao(); //dao'ya erişim sağladım.

        spinner=binding.spinnerCity;
        //Edittext Area
        userTextName=binding.textInputName;
        userTextSurname=binding.textInputSurname;
        userTextEmail=binding.textInputEmail;
        userTextAge=binding.textInputAge;






        userAPI=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //Rxjava kullanacağımı belirttim
                .addConverterFactory(GsonConverterFactory.create()) //json verdim
                .build().create(UserAPI.class); //inşa ettim.


        userAPI.getData().enqueue(new Callback<UserResponseModel>() {
            @Override
            public void onResponse(Call<UserResponseModel> call, Response<UserResponseModel> response) {
                if(response.isSuccessful()){
                    System.out.println(response.body().userName);
                    binding.tilName.setHint(response.body().userName);
                    binding.tilSurname.setHint(response.body().userSurname);
                    binding.tilEmail.setHint(response.body().email);
                    binding.tilAge.setHint(response.body().age);
                    binding.toolbarUser.setTitle(response.body().header);
                    List<String> cities=response.body().cities;
                    ArrayAdapter<String> adapter=new ArrayAdapter(UserActivity.this, android.R.layout.simple_spinner_item,cities);
                    binding.spinnerCity.setAdapter(adapter);
                    binding.spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            selectedCity=adapterView.getSelectedItem().toString();

                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });


                    //Toolbar düzenlemeleri
                    binding.toolbarUser.setLogo(R.drawable.ic_baseline_person_pin_24);
                    binding.toolbarUser.setTitleTextColor(Color.WHITE);
                    setSupportActionBar(binding.toolbarUser);
                }
            }

            @Override
            public void onFailure(Call<UserResponseModel>call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public void saveData(View view){

        //Kayıt işlemi ve sayfa geçişi yapılacak.

        UserEntityModel user= new UserEntityModel(
                userTextName.getText().toString(),
                userTextSurname.getText().toString(),
                userTextEmail.getText().toString(),
                userTextAge.getText().toString(),
                selectedCity
        );


        compositeDisposable.add(userDao.insert(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(UserActivity.this::handleResponse)
        );

        //Kayıt sonrası inputarea düzenlemesi

        userTextName.setText("");
        userTextSurname.setText("");
        userTextEmail.setText("");
        userTextAge.setText("");

    }
    public void goUserList(View view){ //Sadece mevcut kullanıcıları görüntülemek için var ettik.

        Intent intent=new Intent(UserActivity.this,ListActivity.class);
        startActivity(intent);
    }

    private void handleResponse(){ //save işlemi bittikten sonra ne yapılacağını tanımlıyoruz.

        Intent intent=new Intent(UserActivity.this,ListActivity.class); //olduğum alan , gideceğim yer
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //her şeyi temizlemek adına yapıyorum
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear(); //Kullan at objemi temizledim.onDestroy Lifecycle içerisinde
    }
}

