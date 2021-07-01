package com.example.mareunion;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ImageButton addButton;
    private RecyclerView mRecyclerView;
    private ReunionListViewAdapter mAdapter;
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    private ApiService mApiService;
    private List<Reunion> mReunions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.configureToolBar();
        configureRecyclerView();
        addButton = findViewById(R.id.addReunion);
        mApiService= DI.getApiService();

        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent addReunion = new Intent(MainActivity.this, AddActivity.class);
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
        toolbar = findViewById(R.id.activity_main_toolBar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //3 - Handle actions on menu items
        switch (item.getItemId()) {
            case R.id.menu_activity_filter:

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void configureRecyclerView() {
        mRecyclerView = findViewById(R.id.View);
        mRecyclerView.setAdapter(mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void removeReunion(RemoveReunionEvent event){
        mApiService.deletteReunion(event.reunion);
        initList();
    }

    public void onResume() {
        super.onResume();
        initList();
    }

    private void initList() {
        mReunions= mApiService.getReunions();
        mRecyclerView.setAdapter(new ReunionListViewAdapter(mReunions));
    }
}



