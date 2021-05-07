package com.example.notemanagement.ui.category;

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

import com.example.notemanagement.DB.DaoClass.CategoryDaoClass;
import com.example.notemanagement.DB.Database;
import com.example.notemanagement.DB.EntityClass.CategoryModel;
import com.example.notemanagement.DB.EntityClass.StatusModel;
import com.example.notemanagement.DB.Note;
import com.example.notemanagement.DB.NoteDao;
import com.example.notemanagement.R;
import com.example.notemanagement.extension.Session;


import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{
    private List<CategoryModel> listCategory;
    private Context context;
    private Session session;
    Database database;
    CategoryDaoClass categoryDao;
    private int position;

    //get position of item
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    //create view holder
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private TextView txtCategoryName, txtCategoryCrD;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtCategoryName = itemView.findViewById(R.id.txtCategoryName);
            txtCategoryCrD = itemView.findViewById(R.id.txtCategoryCrD);
            itemView.setOnCreateContextMenuListener(this);
        }

        //create menu option when take long press on item.
        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, final View view, ContextMenu.ContextMenuInfo contextMenuInfo) {

            contextMenu.add(contextMenu.NONE,R.id.MenuEditCategory,contextMenu.NONE,"Edit");
            contextMenu.add(contextMenu.NONE,R.id.MenuDeleteCategory,contextMenu.NONE,"Delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    session = new Session(view.getContext());
                    CategoryModel categoryModel = listCategory.get(position);
                    if(isExist(categoryModel)){
                        Toast.makeText(view.getContext(),"This Category is in Note!",Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    database = Database.getInstance(context);
                    categoryDao = database.categoryDaoClass();
                    categoryDao.deleteData(categoryModel);
                    listCategory = categoryDao.getAllData(session.getIdAccount());
                    notifyDataSetChanged();
                    return false;
                }
            });;
        }
    }

    public boolean isExist(CategoryModel category){
        database = Database.getInstance(context);
        NoteDao noteDao;
        noteDao = database.noteDao();
        List<Note> list ;
        list = noteDao.getNote(category.getName());
        if(list.size() != 0)
            return true;
        else
            return false;
    }


    public CategoryAdapter(Context context, List<CategoryModel> listCategory){
        this.listCategory = listCategory;
        this.context = context;
    }

    @NonNull
    @Override

    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_view, parent, false);
        return new CategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryAdapter.ViewHolder holder, final int position){
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
        return listCategory.size();
    }
}