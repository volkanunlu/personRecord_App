package com.volkanunlu.softtech_task.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.volkanunlu.softtech_task.R;
import com.volkanunlu.softtech_task.adapter.UserAdapter;
import com.volkanunlu.softtech_task.databinding.ActivityListBinding;
import com.volkanunlu.softtech_task.model.UserEntityModel;
import com.volkanunlu.softtech_task.model.UserResponseModel;
import com.volkanunlu.softtech_task.roomdb.UserDao;
import com.volkanunlu.softtech_task.roomdb.UserDatabase;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ListActivity extends AppCompatActivity {

    private ActivityListBinding binding; //data binding ile xml tarafındaki öğeleri erişiyoruz.
    private Toolbar toolbarList;
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    UserDatabase db;
    UserDao userDao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityListBinding.inflate(getLayoutInflater()); //Elementlerime erişmek adına binding işlemi
        View view=binding.getRoot();
        setContentView(view);


        db= Room.databaseBuilder(getApplicationContext(),UserDatabase.class,"Users").build();
        userDao=db.userDao();

        compositeDisposable.add(userDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ListActivity.this::handleResponse)

        );

        toolbarList=binding.toolbarList;
        toolbarList.setTitle("Kayıtlar");
        toolbarList.setTitleTextColor(Color.WHITE);
        toolbarList.setLogo(R.drawable.ic_baseline_list_24);

    }
    public  void handleResponse(List<UserEntityModel> userList){

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        UserAdapter userAdapter=new UserAdapter(userList);
        binding.recyclerView.setAdapter(userAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();

    }
}