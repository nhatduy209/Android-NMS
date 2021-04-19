package com.example.notemanagement.ui.friority;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentViewHolder;

import com.example.notemanagement.R;
import com.example.notemanagement.ui.status.StatusAdapter;
import com.example.notemanagement.ui.status.StatusViewModel;

import java.util.List;

public class FriorityAdapter extends RecyclerView.Adapter<FriorityAdapter.ViewHolder> {
    private List<FriorityViewModel> listFriority;
    private Context context;

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtFriorityName, txtFriorityCrD;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtFriorityName = itemView.findViewById(R.id.txtFriorityName);
            txtFriorityCrD = itemView.findViewById(R.id.txtFriorityCrD);
        }

    }

    public FriorityAdapter(Context context, List<FriorityViewModel> listFriority){
        this.listFriority = listFriority;
        this.context = context;
    }

    @NonNull
    @Override

    public FriorityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        //gán view

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friority_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        //Gán dữ liệu
        FriorityViewModel friority = listFriority.get(position);
        holder.txtFriorityName.setText("Name: "+ friority.getFriorityName());
        holder.txtFriorityCrD.setText("Created Date: "+ friority.getFrorityCrD());
    }

    @Override
    public int getItemCount() {
        return listFriority.size(); // trả item tại vị trí postion
    }
}
