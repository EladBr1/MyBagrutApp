package com.example.mybagrutapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlayerAdapterG extends RecyclerView.Adapter<PlayerAdapterG.PlayerViewHolder>
{
    private ArrayList<Player> players;

    public PlayerAdapterG(ArrayList<Player> players) {
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

        String goals = String.valueOf(currentPlayer.getGoals());

        holder.name.setText(currentPlayer.getTitName());
        holder.team.setText(currentPlayer.getCrTeam());
        holder.num.setText(goals);
    }

    @Override
    public int getItemCount() {
        return players.size();
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
