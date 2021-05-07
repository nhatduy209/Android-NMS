package com.example.notemanagement.ui.status;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notemanagement.DB.DaoClass.StatusDaoClass;
import com.example.notemanagement.DB.Database;
import com.example.notemanagement.DB.EntityClass.CategoryModel;
import com.example.notemanagement.DB.EntityClass.StatusModel;
import com.example.notemanagement.DB.Note;
import com.example.notemanagement.DB.NoteDao;
import com.example.notemanagement.R;
import com.example.notemanagement.extension.Session;


import java.util.List;
import java.util.Random;

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.ViewHolder> {
    private List<StatusModel> listStatus;
    private Context context;
    private Session session;
    Database database;
    StatusDaoClass statusDao;

    private int position;

    public int getPosition() {
        return position;
    }
//    private Random random;


    public void setPosition(int position) {
        this.position = position;
    }
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        private TextView txtstatusContent, txtcreatedDateContent;
        private View itemView;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            this.itemView = itemView;
            txtstatusContent = itemView.findViewById(R.id.lbStatusContent);
            txtcreatedDateContent = itemView.findViewById(R.id.lbCreatedContent);
            itemView.setOnCreateContextMenuListener(this);
        }


        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, final View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.add(contextMenu.NONE,R.id.MenuEditStatus,contextMenu.NONE,"Edit");

            contextMenu.add(contextMenu.NONE,R.id.MenuDeleteStatus,contextMenu.NONE,"Delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    session = new Session(itemView.getContext());
                    StatusModel statusModel = listStatus.get(position);
                    if(isExist(statusModel)){
                        Toast.makeText(view.getContext(),"This Status is in Note!",Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    database = Database.getInstance(context);
                    statusDao = database.statusDaoClass();
                    statusDao.deleteData(statusModel);
                    listStatus = statusDao.getAllData(session.getIdAccount());
                    notifyDataSetChanged();
                    return false;
                }
            });
        }
    }
    public boolean isExist(StatusModel status){
        database = Database.getInstance(context);
        NoteDao noteDao;
        noteDao = database.noteDao();
        List<Note> list ;
        list = noteDao.getNote(status.getName());
        if(list.size() != 0)
            return true;
        else
            return false;
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
                setPosition(holder.getPosition());
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listStatus.size(); // trả item tại vị trí postion
    }



}