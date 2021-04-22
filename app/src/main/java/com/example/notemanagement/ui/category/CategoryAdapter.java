package com.example.notemanagement.ui.category;

import android.app.AlertDialog;
import android.content.Context;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notemanagement.R;
import com.example.notemanagement.ui.friority.FriorityAdapter;
import com.example.notemanagement.ui.friority.FriorityViewModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{
    private List<CategoryViewModel> listCategory;
    private Context context;

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private TextView txtCategoryName, txtCategoryCrD;
        private View vOption;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtCategoryName = itemView.findViewById(R.id.txtCategoryName);
            txtCategoryCrD = itemView.findViewById(R.id.txtCategoryCrD);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.add(0,view.getId(),0,"Edit");
            contextMenu.add(0,view.getId(),0,"Cancel");
        }
    }

    public CategoryAdapter(Context context, List<CategoryViewModel> listCategory){
        this.listCategory = listCategory;
        this.context = context;
    }

    @NonNull
    @Override

    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        //gán view

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_view, parent, false);
        return new CategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryAdapter.ViewHolder holder, final int position){
        //Gán dữ liệu
        CategoryViewModel category = listCategory.get(position);
        holder.txtCategoryName.setText("Name: "+ category.getCategoryName());
        holder.txtCategoryCrD.setText("Created Date: "+ category.getCategoryCrD());
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
        return listCategory.size(); // trả item tại vị trí postion
    }
}
