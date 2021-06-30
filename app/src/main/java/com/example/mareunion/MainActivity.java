package com.example.mareunion;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {
    int filtre;
    private ImageButton addButton;
    private RecyclerView mRecyclerView;
    private ReunionListViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.configureToolBar();
        configureRecyclerView();
        addButton = findViewById(R.id.addReunion);

        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent addReunion = new Intent(MainActivity.this, addActivity.class);
                startActivity(addReunion);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }

    private void configureToolBar() {
        Toolbar toolbar = findViewById(R.id.activity_main_toolBar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //3 - Handle actions on menu items
        switch (item.getItemId()) {
            case R.id.menu_activity_filter:
                if (filtre == 0) {
                    Toast.makeText(this, "Filtre par date", Toast.LENGTH_SHORT).show();
                    filtre = 1;
                    return true;
                }
                if (filtre == 1) {
                    Toast.makeText(this, "Filtre par salle", Toast.LENGTH_SHORT).show();
                    filtre = 2;
                    return true;
                }
                if (filtre == 2) {
                    Toast.makeText(this, "Tout afficher", Toast.LENGTH_SHORT).show();
                    filtre = 0;
                    return true;
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void configureRecyclerView() {
        mRecyclerView = findViewById(R.id.View);
        mAdapter = new ReunionListViewAdapter(ReunionListViewAdapter.mReunions);
        mRecyclerView.setAdapter(mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    private void initList() {
        mRecyclerView.setAdapter(new ReunionListViewAdapter(ReunionListViewAdapter.mReunions));
    }

    public void onResume() {
        super.onResume();
        initList();
    }
}



