package com.example.mareunion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReunionListViewAdapter extends RecyclerView.Adapter<ReunionListViewHolder> {
    public static List<Reunion> mReunions = new ArrayList<>();

    public ReunionListViewAdapter(List<Reunion> items) {
        mReunions = items;
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
}

