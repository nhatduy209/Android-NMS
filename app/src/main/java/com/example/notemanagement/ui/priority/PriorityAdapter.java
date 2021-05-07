package com.example.notemanagement.ui.priority;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notemanagement.DB.DaoClass.PriorityDaoClass;
import com.example.notemanagement.DB.Database;
import com.example.notemanagement.DB.EntityClass.PriorityModel;
import com.example.notemanagement.DB.EntityClass.StatusModel;
import com.example.notemanagement.DB.Note;
import com.example.notemanagement.DB.NoteDao;
import com.example.notemanagement.R;
import com.example.notemanagement.extension.Session;


import java.util.List;

public class PriorityAdapter extends RecyclerView.Adapter<PriorityAdapter.ViewHolder> {
    private List<PriorityModel> listFriority;
    private Context context;
    private Session session;
    Database database;
    PriorityDaoClass friorityDao;

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private TextView txtFriorityName, txtFriorityCrD;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtFriorityName = itemView.findViewById(R.id.txtFriorityName);
            txtFriorityCrD = itemView.findViewById(R.id.txtFriorityCrD);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, final View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.add(contextMenu.NONE,R.id.MenuEditFriority,contextMenu.NONE,"Edit");
            contextMenu.add(contextMenu.NONE,R.id.MenuDeleteFriority,contextMenu.NONE,"Delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    session = new Session(itemView.getContext());
                    PriorityModel priorityModel = listFriority.get(position);
                    if(isExist(priorityModel)){
                        Toast.makeText(view.getContext(),"This Priority is in Note!",Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    database = Database.getInstance(context);
                    friorityDao = database.friorityDaoClass();
                    friorityDao.deleteData(priorityModel);
                    listFriority = friorityDao.getAllData(session.getIdAccount());
                    notifyDataSetChanged();
                    return false;
                }
            });
        }
    }

    public boolean isExist(PriorityModel priority){
        database = Database.getInstance(context);
        NoteDao noteDao;
        noteDao = database.noteDao();
        List<Note> list ;
        list = noteDao.getNote(priority.getName());
        if(list.size() != 0)
            return true;
        else
            return false;
    }

    public PriorityAdapter(Context context, List<PriorityModel> listFriority){
        this.listFriority = listFriority;
        this.context = context;
    }

    @NonNull
    @Override

    public PriorityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        //gán view

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friority_item_view, parent, false);
        return new PriorityAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PriorityAdapter.ViewHolder holder, int position){
        //Gán dữ liệu
        PriorityModel friority = listFriority.get(position);
        holder.txtFriorityName.setText("Name: "+ friority.getName());
        holder.txtFriorityCrD.setText("Created Date: "+ friority.getFrCrD());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v){
                setPosition(holder.getPosition());
                return false;
            }
        });
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return listFriority.size(); // trả item tại vị trí postion
    }
}