package com.example.ahmed.social;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
   // private ProgressBar progressBar;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList <Contact> arrayList = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //progressBar = (ProgressBar) findViewById(R.id.prog);
        //progressBar.setVisibility(View.VISIBLE);
        //setProgressBarIndeterminateVisibility(true);
        backgroundTask backgroundTask = new backgroundTask(MainActivity.this);
        arrayList= backgroundTask.getList();
        adapter = new RecyclerAdapter(getApplicationContext(),arrayList);
        recyclerView.setAdapter(adapter);






    }
    public void create(View view){
        Intent intent = new Intent(this , CreateGroup.class);
        startActivity(intent);

    }



}
