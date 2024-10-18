package com.example.tugas3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<String> nameList;
    private ArrayList<String> phoneList;

    public DataAdapter(ArrayList<String> nameList, ArrayList<String> phoneList) {
        this.nameList = nameList;
        this.phoneList = phoneList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameTextView.setText(nameList.get(position));
        holder.phoneTextView.setText(phoneList.get(position));
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView phoneTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(android.R.id.text1);
            phoneTextView = itemView.findViewById(android.R.id.text2);
        }
    }
}
