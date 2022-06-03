package com.example.mybagrutapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//adapter for the age sort
public class PlayerAdapterAge extends RecyclerView.Adapter<PlayerAdapterAge.PlayerViewHolder>
{
    private ArrayList<Player> players;

    public PlayerAdapterAge(ArrayList<Player> players) {
        this.players = players;
    }

    @NonNull
    @Override
    public PlayerAdapterAge.PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View playerView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleritem_player,parent,false);

        return new PlayerAdapterAge.PlayerViewHolder(playerView);
    }

    //set the view for each player
    @Override
    public void onBindViewHolder(@NonNull PlayerAdapterAge.PlayerViewHolder holder, int position)
    {
        Player currentPlayer = players.get(position);

        String age = String.valueOf(currentPlayer.getAge());

        holder.name.setText(currentPlayer.getTitName());
        holder.team.setText(currentPlayer.getCrTeam());
        holder.num.setText(age);
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    //find the views
    public static class PlayerViewHolder extends RecyclerView.ViewHolder
    {

        public TextView name;
        public TextView team;
        public TextView num;

        public PlayerViewHolder(@NonNull View itemView)
        {
            super(itemView);
            name = itemView.findViewById(R.id.tvNamePl);
            team = itemView.findViewById(R.id.team);
            num = itemView.findViewById(R.id.tvRequiredNum);

        }

    }
}
