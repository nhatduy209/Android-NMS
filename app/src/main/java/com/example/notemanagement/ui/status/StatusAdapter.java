package com.example.notemanagement.ui.status;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notemanagement.R;
import com.example.notemanagement.ui.note.NoteAdapter;
import com.example.notemanagement.ui.note.NoteModel;

import java.util.List;
import java.util.Random;

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.ViewHolder> {
    private List<StatusViewModel> listStatus;
    private Context context;
//    private Random random;

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtstatusContent, txtcreatedDateContent;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtstatusContent = itemView.findViewById(R.id.lbStatusContent);
            txtcreatedDateContent = itemView.findViewById(R.id.lbCreatedContent);
        }

    }
    public StatusAdapter(Context context, List<StatusViewModel> listStatus){
        this.listStatus = listStatus;
        this.context = context;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        //gán view

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_status_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        //Gán dữ liệu
        StatusViewModel status = listStatus.get(position);
        holder.txtstatusContent.setText("Name: "+ status.getStatusName());
        holder.txtcreatedDateContent.setText("Created Date: "+status.getCreatedDate());
    }

    @Override
    public int getItemCount() {
        return listStatus.size(); // trả item tại vị trí postion
    }



}
