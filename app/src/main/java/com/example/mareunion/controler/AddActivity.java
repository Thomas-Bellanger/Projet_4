package com.example.mareunion.controler;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareunion.R;
import com.example.mareunion.model.Participants;
import com.example.mareunion.model.Reunion;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int image;
    private Button create;
    private ImageButton plus;
    private EditText editTextMail;
    private String participantMail;
    private EditText editOrganisateur;
    private Spinner salles;
    private EditText time;
    private Reunion mReunion;
    private ImageView mImageView;
    private RecyclerView mRecyclerView;
    private Participants participant;
    private ParticipantsAdapater mAdapater;
    private DummyApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        plus = findViewById(R.id.plus);
        editTextMail = findViewById(R.id.editTextTextEmailAddress);
        editOrganisateur = findViewById(R.id.editTextOganisateur);
        create = findViewById(R.id.button_planifier);
        salles = findViewById(R.id.choix_salle);
        mImageView = findViewById(R.id.imageView);
        mApiService = DI.getApiService();

        ArrayAdapter<CharSequence> adapterSalles = ArrayAdapter.createFromResource(this, R.array.salles, android.R.layout.simple_spinner_item);
        adapterSalles.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        salles.setAdapter(adapterSalles);
        salles.setOnItemSelectedListener(this);

        this.configureToolBar();

        time = findViewById(R.id.time);

        configureRecyclerView();

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                participantMail = editTextMail.getText().toString();
                participant = new Participants(participantMail);
                mApiService.addParticipant(participant);
                editTextMail.getText().clear();
                initList();
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mReunion = new Reunion(
                        "reunion " + mApiService.getReunions().size() + "   -",
                        time.getText().toString() + "    -",
                        salles.getSelectedItem().toString(),
                        editOrganisateur.getText().toString() + mApiService.getParticipants());
                mApiService.addReunion(mReunion);
                finish();
            }
        });
    }

    private void configureToolBar() {
        Toolbar mToolbar = findViewById(R.id.activity_main_toolBar);
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private void configureRecyclerView() {
        mRecyclerView = findViewById(R.id.participantsRecyclerView);
        mAdapater = new ParticipantsAdapater(mApiService.getParticipants());
        mRecyclerView.setAdapter(mAdapater);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(AddActivity.this));
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
    public void clickRemove(RetirerParticipantEvent event) {
        mApiService.deletteParticipant(event.participant);
        initList();
    }

    private void initList() {
        mRecyclerView.setAdapter(new ParticipantsAdapater(mApiService.getParticipants()));
    }
}