package com.example.mychords;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mychords.databinding.ActivityMainBinding;
import com.example.mychords.databinding.ActivityNewChordProgressionBinding;

public class NewChordProgressionActivity extends AppCompatActivity {
    private ActivityNewChordProgressionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewChordProgressionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Toolbar toolbar = (Toolbar) binding.topAppBar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}