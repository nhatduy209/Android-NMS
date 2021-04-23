package com.example.notemanagement.ui.status;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notemanagement.DB.EntityClass.StatusModel;
import com.example.notemanagement.R;
import com.example.notemanagement.ui.note.NoteAdapter;


import java.util.List;
import java.util.Random;

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.ViewHolder> {
    private List<StatusModel> listStatus;
    private Context context;
    private interface OnItemLongClickListener{
        public boolean onItemLongClicked(int posotion);
    }
//    private Random random;

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtstatusContent, txtcreatedDateContent;
        private View itemView;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            this.itemView = itemView;
            txtstatusContent = itemView.findViewById(R.id.lbStatusContent);
            txtcreatedDateContent = itemView.findViewById(R.id.lbCreatedContent);

        }


    }

    public StatusAdapter(Context context, List<StatusModel> listStatus){
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
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position){
        //Gán dữ liệu
        StatusModel status = listStatus.get(position);
        holder.txtstatusContent.setText("Name: "+ status.getName());
        holder.txtcreatedDateContent.setText("Created Date: "+status.getStCrD());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v){


                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listStatus.size(); // trả item tại vị trí postion
    }



}
