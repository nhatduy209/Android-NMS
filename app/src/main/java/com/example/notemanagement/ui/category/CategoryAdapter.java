package com.example.notemanagement.ui.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtCategoryName, txtCategoryCrD;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtCategoryName = itemView.findViewById(R.id.txtCategoryName);
            txtCategoryCrD = itemView.findViewById(R.id.txtCategoryCrD);
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
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position){
        //Gán dữ liệu
        CategoryViewModel category = listCategory.get(position);
        holder.txtCategoryName.setText("Name: "+ category.getCategoryName());
        holder.txtCategoryCrD.setText("Created Date: "+ category.getCategoryCrD());
    }

    @Override
    public int getItemCount() {
        return listCategory.size(); // trả item tại vị trí postion
    }
}
