package com.volkanunlu.softtech_task.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.volkanunlu.softtech_task.R;
import com.volkanunlu.softtech_task.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    ImageView bgapp, softtechnim;
    LinearLayout textSplash, textHome, routerArea;
    Animation frombottom;
    private TextView userText;
    private TextView passwordText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        //kullanıcı adı ve şifre tanımlaması

        userText=binding.userNameText;
        passwordText=binding.passwordText;


        //Animasyon düzenlemeleri--> homepage kısmı -- binding erişimler , animate set değerleri

        frombottom= AnimationUtils.loadAnimation(this, R.anim.frombottom);

        textSplash=(LinearLayout)binding.textSplash;  //Casting yaptım
        textHome=(LinearLayout)binding.textHome;
        routerArea=(LinearLayout)binding.routerArea;

        bgapp=(ImageView) binding.homeImageView;

        textSplash.animate().translationY(140).setDuration(800).setStartDelay(600);
        textHome.startAnimation(frombottom);
        routerArea.startAnimation(frombottom);

        //farklı ekranlardaki boyut sorunu çözümü - lightblue ekran
        DisplayMetrics dimension = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(dimension);

        int width = dimension.widthPixels;
        int height = dimension.heightPixels;

        if(height>2550) {
            bgapp.animate().translationY(-2000).setDuration(800).setStartDelay(1000);
        }
        if(height>2500 && height <=2550 ) {
            bgapp.animate().translationY(-1950).setDuration(800).setStartDelay(1000);
        }
        if(height>2450 && height <=2500 ) {
            bgapp.animate().translationY(-1900).setDuration(800).setStartDelay(1000);
        }
        if(height>2400 && height <=2450 ) {
            bgapp.animate().translationY(-1850).setDuration(800).setStartDelay(1000);
        }
        if(height>2350 && height <=2400 ) {
            bgapp.animate().translationY(-1800).setDuration(800).setStartDelay(1000);
        }
        if(height>2300 && height <=2350 ) {
            bgapp.animate().translationY(-1750).setDuration(800).setStartDelay(1000);
        }
        if(height>2250 && height <=2300 ) {
            bgapp.animate().translationY(-1700).setDuration(800).setStartDelay(1000);
        }
        if(height>2200 && height <=2250 ) {
            bgapp.animate().translationY(-1650).setDuration(800).setStartDelay(1000);
        }
        if(height>2150 && height <=2200 ) {
            bgapp.animate().translationY(-1600).setDuration(800).setStartDelay(1000);
        }
        if(height>2100 && height <=2150 ) {
            bgapp.animate().translationY(-1550).setDuration(800).setStartDelay(1000);
        }
        if(height>2050 && height <=2100 ) {
            bgapp.animate().translationY(-1500).setDuration(800).setStartDelay(1000);
        }
        if(height>2000 && height <=2050 ) {
            bgapp.animate().translationY(-1450).setDuration(800).setStartDelay(1000);
        }
        if(height>1950 && height <=2000 ) {
            bgapp.animate().translationY(-1400).setDuration(800).setStartDelay(1000);
        }
        if(height>1900 && height <=1950 ) {
            bgapp.animate().translationY(-1350).setDuration(800).setStartDelay(1000);
        }
        if(height>1850 && height <=1900 ) {
            bgapp.animate().translationY(-1300).setDuration(800).setStartDelay(1000);
        }
        if(height>1800 && height <=1850 ) {
            bgapp.animate().translationY(-1250).setDuration(800).setStartDelay(1000);
        }
        if(height>1750 && height <=1800 ) {
            bgapp.animate().translationY(-1200).setDuration(800).setStartDelay(1000);
        }
        if(height>1700 && height <=1750 ) {
            bgapp.animate().translationY(-1150).setDuration(800).setStartDelay(1000);
        }
        if(height>1650 && height <=1700 ) {
            bgapp.animate().translationY(-1100).setDuration(800).setStartDelay(1000);
        }
        if(height <=1650 ) {
            bgapp.animate().translationY(-1050).setDuration(800).setStartDelay(1000);
        }

    }


    public void goToUserPage(View view){ //Intent ile kullanıcı şifre ve parola kontrolü ile User ekranına yönlendirme

        String userName=userText.getText().toString();
        String password=passwordText.getText().toString();

        if(userName.equals("Softtech") && password.equals("1234567")) //Deneme adına bir kontrol şartı verdim.
        {
            Intent intent=new Intent(MainActivity.this,UserActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Kullanıcı Adı - Şifre Yanlış!", Toast.LENGTH_SHORT).show(); //Hatalı giriş sonrası bir toast mesajı
        }
    }

}