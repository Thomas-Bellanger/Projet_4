package com.example.mareunion.controler;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareunion.R;
import com.example.mareunion.model.Reunion;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;


public class MainActivity extends AppCompatActivity implements Filterable {
    private ImageButton addButton;
    private RecyclerView mRecyclerView;
    private ReunionListViewAdapter mAdapter;
    private Toolbar toolbar;
    private ApiService mApiService;
    public Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            results.values = mApiService.filterReunion(constraint.toString());

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mAdapter.updateReunion((List) results.values);
        }
    };
    private List<Reunion> mReunions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.configureToolBar();
        addButton = findViewById(R.id.addReunion);
        mApiService = DI.getApiService();
        configureRecyclerView();

        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent addReunion = new Intent(MainActivity.this, AddActivity.class);
                startActivity(addReunion);
            }
        });
    }

    private void configureToolBar() {
        toolbar = findViewById(R.id.activity_main_toolBar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
    }

    private void configureRecyclerView() {
        mRecyclerView = findViewById(R.id.View);
        mReunions = mApiService.getReunions();
        mAdapter = new ReunionListViewAdapter(mReunions);
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
    public void removeReunion(RemoveReunionEvent event) {
        mApiService.deletteReunion(event.reunion);
        initList();
    }

    public void onResume() {
        super.onResume();
        initList();
    }

    private void initList() {
        mReunions = mApiService.getReunions();
        mAdapter.updateReunion(mReunions);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.filtre, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
}



