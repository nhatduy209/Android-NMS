package com.example.notemanagement.ui.category;

import android.app.AlertDialog;
import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notemanagement.DB.DaoClass.CategoryDaoClass;
import com.example.notemanagement.DB.Database;
import com.example.notemanagement.DB.EntityClass.CategoryModel;
import com.example.notemanagement.R;
import com.example.notemanagement.Session;

import java.util.List;
import java.util.zip.Inflater;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{
    private List<CategoryModel> listCategory;
    private Context context;
    private Session session;

    Database database;
    CategoryDaoClass categoryDao;

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private TextView txtCategoryName, txtCategoryCrD;


        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtCategoryName = itemView.findViewById(R.id.txtCategoryName);
            txtCategoryCrD = itemView.findViewById(R.id.txtCategoryCrD);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, final View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.add(contextMenu.NONE,R.id.MenuEditCategory,contextMenu.NONE,"Edit");

            contextMenu.add(contextMenu.NONE,R.id.MenuDeleteCategory,contextMenu.NONE,"Delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    session = new Session(view.getContext());
                    CategoryModel categoryModel = listCategory.get(position);
                    database = Database.getInstance(context);
                    categoryDao = database.categoryDaoClass();
                    categoryDao.deleteData(categoryModel);
                    listCategory = categoryDao.getAllData(session.getIdAccount());
                    notifyDataSetChanged();
                    return false;
                }
            });
        }
    }


    public CategoryAdapter(Context context, List<CategoryModel> listCategory){
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
        CategoryModel category = listCategory.get(position);
        holder.txtCategoryName.setText("Name: "+ category.getName());
        holder.txtCategoryCrD.setText("Created Date: "+ category.getCatCrD());
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