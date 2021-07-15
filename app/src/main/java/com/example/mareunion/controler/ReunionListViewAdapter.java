package com.example.mareunion.controler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareunion.R;
import com.example.mareunion.model.Reunion;

import java.util.ArrayList;
import java.util.List;

public class ReunionListViewAdapter extends RecyclerView.Adapter<ReunionListViewHolder> {
    public List<Reunion> mReunions;
    List<Reunion> filteredList = new ArrayList<>();
    private final DummyApiService mApiService = DI.getApiService();
    private final List<Reunion> mReunionsFull;

    public ReunionListViewAdapter(List<Reunion> items) {
        mReunions = items;
        mReunionsFull = mReunions;
    }

    @NonNull
    @Override
    public ReunionListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_list, parent, false);

        return new ReunionListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReunionListViewHolder holder, int position) {
        holder.bind(mReunions.get(position));
        holder.delette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.deletteReunion(mReunions.get(position));
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mReunions.size();
    }

    public void updateReunion(List<Reunion> newReunion) {
        mReunions = newReunion;
        notifyDataSetChanged();
    }

}

