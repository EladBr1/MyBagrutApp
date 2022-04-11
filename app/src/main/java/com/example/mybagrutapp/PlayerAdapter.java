package com.example.mybagrutapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>
{
    private ArrayList<Player> players;

    public PlayerAdapter(ArrayList<Player> players) {
        this.players = players;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View playerView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleritem_player,parent,false);

        return new PlayerViewHolder(playerView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position)
    {
        Player currentPlayer = players.get(position);
        holder.name.setText(currentPlayer.getTitName());
        holder.team.setText(currentPlayer.getNltTeam());
        holder.num.setText(currentPlayer.getGoals());
    }

    @Override
    public int getItemCount() {
        return 4;
    }

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
