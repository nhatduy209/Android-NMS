package com.example.notemanagement.ui.category;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class CategoryFragment extends Fragment {
    private RecyclerView recyclerCategoryView;
    CategoryAdapter categoryAdapter;
    List<CategoryModel> listCategory;
    Database database;
    CategoryDaoClass categoryDao;
    EditText name;

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
                alert.setView(v);
                alert.setCancelable(true);

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

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
                    }
                });
                name = v.findViewById(R.id.txtAddCategory);
                AlertDialog dialog = alert.create();
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
