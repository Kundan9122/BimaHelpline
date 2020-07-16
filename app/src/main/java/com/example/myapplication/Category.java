package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;


import android.os.Bundle;




public class Category extends AppCompatActivity{



    private Toolbar toolbar,toolbar1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

       toolbar1 = findViewById(R.id.toolbar);
       setSupportActionBar(toolbar1);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       getSupportActionBar().setDisplayShowHomeEnabled(true);


    }

   @Override
   public boolean onSupportNavigateUp() {
       onBackPressed();
       return true;
   }
}
