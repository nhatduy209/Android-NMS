package com.example.notemanagement.ui.friority;

import android.app.AlertDialog;
import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentViewHolder;

import com.example.notemanagement.DB.DaoClass.FriorityDaoClass;
import com.example.notemanagement.DB.Database;
import com.example.notemanagement.DB.EntityClass.CategoryModel;
import com.example.notemanagement.DB.EntityClass.FriorityModel;
import com.example.notemanagement.R;
import com.example.notemanagement.ui.category.CategoryAdapter;
import com.example.notemanagement.ui.status.StatusAdapter;
import com.example.notemanagement.ui.status.StatusViewModel;

import java.util.List;

public class FriorityAdapter extends RecyclerView.Adapter<FriorityAdapter.ViewHolder> {
    private List<FriorityModel> listFriority;
    private Context context;
    Database database;
    FriorityDaoClass friorityDao;

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
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.add(contextMenu.NONE,R.id.MenuEditFriority,contextMenu.NONE,"Edit");
            contextMenu.add(contextMenu.NONE,R.id.MenuDeleteFriority,contextMenu.NONE,"Delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    FriorityModel friorityModel = listFriority.get(position);
                    database = Database.getInstance(context);
                    friorityDao = database.friorityDaoClass();
                    friorityDao.deleteData(friorityModel);
                    listFriority = friorityDao.getAllData();
                    notifyDataSetChanged();
                    return false;
                }
            });
        }
    }

    public FriorityAdapter(Context context, List<FriorityModel> listFriority){
        this.listFriority = listFriority;
        this.context = context;
    }

    @NonNull
    @Override

    public FriorityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        //gán view

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friority_item_view, parent, false);
        return new FriorityAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FriorityAdapter.ViewHolder holder, int position){
        //Gán dữ liệu
        FriorityModel friority = listFriority.get(position);
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