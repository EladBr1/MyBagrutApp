package com.example.mybagrutapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//adapter for the assists
public class PlayerAdapterAst extends RecyclerView.Adapter<PlayerAdapterAst.PlayerViewHolder>
{
    private ArrayList<Player> players;

    public PlayerAdapterAst(ArrayList<Player> players) {
        this.players = players;
    }

    @NonNull
    @Override
    public PlayerAdapterAst.PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View playerView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleritem_player,parent,false);

        return new PlayerAdapterAst.PlayerViewHolder(playerView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerAdapterAst.PlayerViewHolder holder, int position)
    {
        Player currentPlayer = players.get(position);

        String asissts = String.valueOf(currentPlayer.getAsissts());

        holder.name.setText(currentPlayer.getTitName());
        holder.team.setText(currentPlayer.getCrTeam());
        holder.num.setText(asissts);
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
