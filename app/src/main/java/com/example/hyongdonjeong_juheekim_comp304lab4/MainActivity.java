package com.example.hyongdonjeong_juheekim_comp304lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }

        public void register (View v){
            Intent intent = new Intent(MainActivity.this, Main2Activity.class );
            startActivity(intent);
        }

        public void logIn (View v){
            Intent intent = new Intent(MainActivity.this, WelcomeActivity.class );
            startActivity(intent);
        }
    }
