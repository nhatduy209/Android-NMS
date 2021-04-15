package com.example.notemanagement.ui.note;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.notemanagement.R;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    static Context context;
    ArrayList<NoteModel> listNote;



    public NoteAdapter(Context context, ArrayList<NoteModel> listNote){
        this.context = context;
        this.listNote = listNote;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        //gán view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        //Gán dữ liệu

        NoteModel note = listNote.get(position);
        holder.txtName.setText(note.getName());
        holder.txtCategory.setText(note.getCategory());
        holder.txtPriority.setText(note.getPriority());
        holder.txtStatus.setText(note.getStatus());
        holder.txtPlanDate.setText(note.getPlanDate());
        holder.txtCreateDate.setText(note.getCreateDate());

    }

    @Override
    public int getItemCount() {
        return listNote.size(); // trả item tại vị trí postion
    }

  /*  public void deleteItem(int position) {
        listNote.remove(position);
        notifyItemRemoved(position);

    }

    public Context getContext() {
        return NoteAdapter.context;
    }*/


    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtName,txtCategory,txtPriority, txtStatus, txtPlanDate, txtCreateDate;
        TextView txtvName, txtvCategory, txtvPriority, txtvStatus, txtvPlanDate, txtvCreateDate;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            //ánh xạ view
           txtName = itemView.findViewById((R.id.txtName));
           txtCategory = itemView.findViewById(R.id.txtCategory);
           txtStatus = itemView.findViewById(R.id.txtStatus);
           txtPriority = itemView.findViewById(R.id.txtPriority);
           txtPlanDate = itemView.findViewById(R.id.txtPlanDate);
           txtCreateDate = itemView.findViewById(R.id.txtCreateDate);
           txtvName = itemView.findViewById(R.id.txtvName);
            txtvCategory = itemView.findViewById(R.id.txtvCategory);
            txtvStatus = itemView.findViewById(R.id.txtvStatus);
            txtvPriority = itemView.findViewById(R.id.txtvPriority);
            txtvPlanDate = itemView.findViewById(R.id.txtvPlanDate);
            txtvCreateDate = itemView.findViewById(R.id.txtvCreateDate);




        }
    }
}

