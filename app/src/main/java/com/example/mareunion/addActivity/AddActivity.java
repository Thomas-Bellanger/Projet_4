package com.example.mareunion.addActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareunion.R;
import com.example.mareunion.addActivity.adapter.ParticipantsAdapater;
import com.example.mareunion.addActivity.event.RetirerParticipantEvent;
import com.example.mareunion.apiService.DI;
import com.example.mareunion.apiService.DummyApiService;
import com.example.mareunion.model.Participants;
import com.example.mareunion.model.Reunion;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int image;
    private Button create;
    private ImageButton plus;
    private EditText editTextMail;
    private String participantMail;
    private EditText editOrganisateur;
    private TextView date;
    private DatePickerDialog datePickerDialog;
    private Spinner salles;
    private Reunion mReunion;
    private ImageView mImageView;
    private TextView hours;
    private RecyclerView mRecyclerView;
    private Participants participant;
    private ParticipantsAdapater mAdapater;
    private DummyApiService mApiService;
    private TimePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        plus = findViewById(R.id.plus);
        hours = findViewById(R.id.textView2);
        editTextMail = findViewById(R.id.editTextTextEmailAddress);
        editOrganisateur = findViewById(R.id.editTextOganisateur);
        create = findViewById(R.id.button_planifier);
        salles = findViewById(R.id.choix_salle);
        mImageView = findViewById(R.id.imageView);
        date = findViewById(R.id.editTextDate);
        mApiService = DI.getApiService();
        mApiService.participants.clear();

        ArrayAdapter<CharSequence> adapterSalles = ArrayAdapter.createFromResource(this, R.array.salles, android.R.layout.simple_spinner_item);
        adapterSalles.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        salles.setAdapter(adapterSalles);
        salles.setOnItemSelectedListener(this);


        this.configureToolBar();

        configureRecyclerView();

        plus.setOnClickListener(v -> {
            String list = "";
            participantMail = editTextMail.getText().toString();
            participant = new Participants(participantMail);
            mApiService.addParticipant(participant);
            editTextMail.getText().clear();
            initList();
        });
        hours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                picker = new TimePickerDialog(AddActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hours.setText(hour + ":" + minute);
                    }
                }, hour, minute, true);
                picker.show();
            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText(dayOfMonth + "/" + (month + "/") + (year));
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String list = "";
                for (int i = 0; i < mApiService.participants.size(); i++) {
                    list += mApiService.participants.get(i).getMail() + "-";
                }
                mReunion = new Reunion(
                        "reunion " + mApiService.getReunions().size() + "   -",
                        date.getText().toString(), hours.getText().toString() + "    -",
                        salles.getSelectedItem().toString(),
                        editOrganisateur.getText().toString() + "-" + list);
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