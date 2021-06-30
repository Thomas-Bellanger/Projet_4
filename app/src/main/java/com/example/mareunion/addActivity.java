package com.example.mareunion;

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

public class addActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    static Button create;
    ImageButton plus;
    EditText editTextMail;
    String participantMail;
    EditText editOrganisateur;
    Spinner salles;
    EditText time;
    Reunion mReunion;
    ImageView mImageView;
    String mImage;
    RecyclerView mRecyclerView;
    private Participants participant;
    private ParticipantsAdapater mAdapater;


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
        mImage = "https://i.pravatar.cc/150?u=";

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
                ParticipantsAdapater.mParticipants.add(participant);
                initList();
                editTextMail.getText().clear();
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mReunion = new Reunion(
                        "reunion " + ReunionListViewAdapter.mReunions.size() + "   -",
                        time.getText().toString() + "    -",
                        salles.getSelectedItem().toString(),
                        editOrganisateur.getText().toString() + ParticipantsAdapater.mParticipants,
                        mImage);
                ReunionListViewAdapter.mReunions.add(mReunion);
                finish();
            }
        });
    }

    private void configureToolBar() {
        Toolbar toolbar = findViewById(R.id.activity_main_toolBar);
        setSupportActionBar(toolbar);
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
        mAdapater = new ParticipantsAdapater(ParticipantsAdapater.mParticipants);
        mRecyclerView.setAdapter(mAdapater);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(addActivity.this));
    }

    private void initList() {
        mRecyclerView.setAdapter(new ParticipantsAdapater(ParticipantsAdapater.mParticipants));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ParticipantsAdapater.mParticipants.clear();
    }
}
