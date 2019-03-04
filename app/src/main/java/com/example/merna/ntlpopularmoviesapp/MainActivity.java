package com.example.merna.ntlpopularmoviesapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.merna.ntlpopularmoviesapp.view.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,
                    new MainFragment()).commit();
        }

        setContentView(R.layout.activity_main);
    }
}
