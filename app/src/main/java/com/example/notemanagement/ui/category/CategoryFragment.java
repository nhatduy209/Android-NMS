package com.example.notemanagement.ui.category;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notemanagement.DB.DaoClass.CategoryDaoClass;
import com.example.notemanagement.DB.Database;
import com.example.notemanagement.DB.EntityClass.CategoryModel;
import com.example.notemanagement.DB.EntityClass.FriorityModel;
import com.example.notemanagement.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static android.content.ContentValues.TAG;

public class CategoryFragment extends Fragment {
    private RecyclerView recyclerCategoryView;
    CategoryAdapter categoryAdapter;
    List<CategoryModel> listCategory;
    Database database;
    CategoryDaoClass categoryDao;
    EditText name;
    Button add, cancel;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerCategoryView = view.findViewById(R.id.recyclerCategoryView);
        registerForContextMenu(recyclerCategoryView);

        FloatingActionButton floating = view.findViewById(R.id.category_fab);


        floating.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(final View view) {

                final AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());//khởi tạo alert
                View v = inflater.inflate(R.layout.dialog_add_category,null);
                name = v.findViewById(R.id.txtAddCategory);
                add = v.findViewById(R.id.btnAddCategory);
                cancel = v.findViewById(R.id.btnCancelCategory);

                alert.setView(v);
                alert.setCancelable(true);
                final AlertDialog dialog = alert.create();
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String txtName = name.getText().toString().trim();
                        String createdDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

                        if(txtName != null){
                            CategoryModel categoryModel = new CategoryModel();
                            categoryModel.setIdAccount("1");
                            categoryModel.setName(txtName);
                            categoryModel.setCatCrD(createdDate);
                            categoryDao.insertData(categoryModel);

                            Toast.makeText(getContext(),"data successfully added",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getContext(),"The input is empty!",Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        database = Database.getInstance(getActivity().getApplicationContext());

        categoryDao  = database.categoryDaoClass();

        listCategory = categoryDao.getAllData();
        if(listCategory.size() == 0){
            CategoryModel categoryModel = new CategoryModel();
            categoryModel.setIdAccount("1");
            categoryModel.setName("0");
            categoryModel.setCatCrD("High");
            categoryDao.insertData(categoryModel);

            Toast.makeText(getContext(),"data successfully added",Toast.LENGTH_SHORT).show();
        }
        categoryAdapter = new CategoryAdapter(getActivity().getApplicationContext(), listCategory);

//        createStatusList();
        recyclerCategoryView.setHasFixedSize(true);
        recyclerCategoryView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerCategoryView.setAdapter(categoryAdapter);
        return view;
    }
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        int position = -1;
//        try {
//            position = (getAdapter()).getPosition();
//        } catch (Exception e) {
//            Log.d(TAG, e.getLocalizedMessage(), e);
//            return super.onContextItemSelected(item);
//        }
//        switch (item.getItemId()) {
//            case R.id.Edit:
//                // do your stuff
//                break;
//            case R.id.Delete:
//                // do your stuff
//                break;
//        }
//        return super.onContextItemSelected(item);
//    }
}
